package com.example.dai.asesorias;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class inscrito extends AppCompatActivity {

    private TextView tiempo;
    private TextView puntaje;
    private TextView profesor;
    private TextView lugar;

    private ListView inscritos;

    private Spinner clases;

    private Bundle bundle;

    private String elegido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscrito);

        bundle = this.getIntent().getExtras();

        tiempo = (TextView) findViewById(R.id.tiempo);
        puntaje = (TextView) findViewById(R.id.puntaje);
        profesor = (TextView) findViewById(R.id.profesor);
        inscritos = (ListView) findViewById(R.id.inscritos);
        lugar = (TextView) findViewById(R.id.lugar);
        clases = (Spinner) findViewById(R.id.clases);
        //Hacer lista de las clases
        List<String> cla =  new ArrayList<String>();

        //Agrega al Spinner las clases a las que está inscrito el usuario

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Administrador", null, 14);
        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor fila = db.rawQuery("select clase.cClase, materia.nombre from inscrito join clase on clase.cClase = inscrito.cClase join materia on materia.cMateria = clase.cMateria where cu = " + bundle.getString("Clave"), null);
        while (fila.moveToNext())
        {
           cla.add("Clase " + fila.getInt(0) + ": " + fila.getString(1));
        }
        fila.close();

        ArrayAdapter<String> ad = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, cla);
        clases.setAdapter(ad);

        final String[] pos = cla.toArray(new String[cla.size()]);

        //Acción cuando se elige un objeto del Spinner
        clases.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(inscrito.this, "Administrador", null, 14);
                SQLiteDatabase db = admin.getWritableDatabase();

                String str = pos[position];
                int i = 6;
                StringBuilder temp = new StringBuilder();
                while (i<str.length() && Character.isDigit(str.charAt(i)))
                {
                    temp.append(str.charAt(i));
                    i++;
                }
                elegido = temp.toString();

                //Llena la información de la clase
                int prof = 0;
                Cursor fila = db.rawQuery("select clase.dia, clase.horario, clase.lugar, profesor.nombre, profesor.apellidos, profesor.cProfesor from clase join profesor on clase.cProfesor = profesor.cProfesor where cClase = " + elegido, null);
                if (fila.moveToFirst())
                {
                    tiempo.setText("Horario: " + fila.getString(0) + " de " + fila.getInt(1) + ":00 a " + ((Integer) fila.getInt(1)+1) + ":00");
                    lugar.setText("Lugar: " + fila.getString(2));
                    profesor.setText("Profesor: " + fila.getString(3) + " " + fila.getString(4));
                    prof = fila.getInt(5);
                }
                fila.close();

                fila = db.rawQuery("select avg(puntaje) from calificacion where cProfesor = " + prof, null);
                if (fila.moveToFirst())
                {
                    int x = fila.getInt(0);
                    if (x==0) {
                        puntaje.setText("Puntaje: todavía ho ha sido calificado");
                    }
                    else {
                        puntaje.setText("Puntaje: " + x);
                    }
                }
                fila.close();


                fila = db.rawQuery("select usuario.nombre, usuario.apellidos from inscrito join usuario on usuario.cu = inscrito.cu where cClase = " + temp.toString(), null);
                List<String> ins = new ArrayList<String>();
                while (fila.moveToNext())
                {
                    ins.add(fila.getString(0) + " " + fila.getString(1));
                }
                fila.close();

                ArrayAdapter<String> ad = new ArrayAdapter<String>(inscrito.this, android.R.layout.simple_list_item_1, ins);
                inscritos.setAdapter(ad);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });
    }
    //cuando el Alumno se quiere desinscribir
    public void desinscribir(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //Asegurarse que se quiere desinscribir
        builder.setTitle("Está seguro que quiere desinscribirse de esta asesoría?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(inscrito.this, "Administrador", null, 14);
                SQLiteDatabase db = admin.getWritableDatabase();
                //Eliminamos usando clave y la clave de clase

                db.execSQL("delete from inscrito where cu = " + bundle.getString("Clave") + " and cClase = " + elegido);

                Intent intent = new Intent(inscrito.this, Accion.class);
                intent.putExtras(bundle);
                startActivity(intent);

                dialog.dismiss();
            }
        });
        //cuando no se desea desinscribir
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();

        dialog.show();
    }

    //se redirige a accion
    public void regresar(View view) {
        Intent intent = new Intent(inscrito.this, Accion.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
