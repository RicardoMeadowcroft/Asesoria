package com.example.dai.asesorias;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class califica extends AppCompatActivity {
    private RatingBar estrellas;
    private Spinner profesores;
    private EditText comentario;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_califica);

        comentario = (EditText) findViewById(R.id.comentario);
        estrellas = (RatingBar) findViewById(R.id.estrellas);

        profesores = (Spinner) findViewById(R.id.profesores);

        bundle = this.getIntent().getExtras();


        //Obtiene que profesores tiene el usuario
        List<String> profes =  new ArrayList<String>();

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Administrador", null, 14);
        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor fila = db.rawQuery("select distinct profesor.cProfesor, profesor.nombre, profesor.apellidos from profesor join clase on profesor.cProfesor = clase.cProfesor join inscrito on clase.cClase = inscrito.cClase where cu = '" + bundle.get("Clave").toString() + "';", null);
        while (fila.moveToNext())
        {
            profes.add(fila.getInt(0) + ": " + fila.getString(1) + " " + fila.getString(2));
        }
        fila.close();

        ArrayAdapter<String> ad = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, profes);
        profesores.setAdapter(ad);
    }
    //Se califica el profesor/asesor/estudiante mediante uso de "estrellas"
    public void aCalificar (View v) {
        //se multiplica para que la calificación sea base 10
        int cal = (int) estrellas.getRating()*2;

        if (cal==0) {
            Toast.makeText(this, "escoja su puntuación.", Toast.LENGTH_SHORT).show();
        }
        else {
            //Encuentra el profesor que el alumno va a calificar
            String original = profesores.getSelectedItem().toString();
            StringBuilder cProfe = new StringBuilder();
            int i = 0;
            while (i < original.length() && Character.isDigit(original.charAt(i))) {
                cProfe.append(original.charAt(i));
                i++;
            }
            String claveP = cProfe.toString();

            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Administrador", null, 14);
            SQLiteDatabase db = admin.getWritableDatabase();
            db.execSQL("delete from calificacion where cu = '" + bundle.get("Clave").toString() + "' and cProfesor = '" + claveP + "';");
            ContentValues registro = new ContentValues();
            registro.put("cu", bundle.get("Clave").toString());
            registro.put("cProfesor", claveP);
            registro.put("puntaje", cal);
            registro.put("comentario", comentario.getText().toString());
            long res = db.insert("calificacion", null, registro);
            db.close();

            Intent intent = new Intent(califica.this, Accion.class);
            intent.putExtras(bundle);
            startActivity(intent);
            //Aparece texto en la parte de abajo cuando se submite la calificacion y el comentario
            Toast.makeText(this, "se registró la calificación exitosamente.", Toast.LENGTH_SHORT).show();
        }
    }
    //Redirige a la página accion
    public void regresar(View view) {
        Intent intent = new Intent(califica.this, Accion.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
