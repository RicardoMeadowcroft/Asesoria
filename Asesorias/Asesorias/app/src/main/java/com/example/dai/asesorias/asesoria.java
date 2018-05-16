package com.example.dai.asesorias;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class asesoria extends AppCompatActivity {
    private Spinner materia, tipo, horario, dia;
    private Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asesoria);

        bundle = this.getIntent().getExtras();

        materia = (Spinner) findViewById(R.id.materia);
        tipo = (Spinner) findViewById(R.id.tipo);
        horario = (Spinner) findViewById(R.id.horario);
        dia = (Spinner) findViewById(R.id.dia);

        //Llena el contenido de los Spinner

        List<String> mat =  new ArrayList<String>();
        mat.add("");
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Administrador", null, 14);
        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor fila = db.rawQuery("select cMateria, nombre from materia;", null);
        while (fila.moveToNext())
        {
            mat.add(fila.getInt(0) + ": " + fila.getString(1));
        }
        fila.close();

        ArrayAdapter<String> ad = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, mat);
        materia.setAdapter(ad);

        List<String> ti =  new ArrayList<String>();
        ti.add("");
        ti.add("Profesor");
        ti.add("Estudiante");
        ti.add("Laboratorista");
        ad = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, ti);
        tipo.setAdapter(ad);

        List<String> hor =  new ArrayList<String>();
        hor.add("");
        for (int i = 6; i<22; i++)
            hor.add(i + ":00 - " + ((int)i+1) + ":00");
        ad = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, hor);
        horario.setAdapter(ad);

        List<String> d =  new ArrayList<String>();
        d.add("");
        d.add("Lunes");
        d.add("Martes");
        d.add("Miércoles");
        d.add("Jueves");
        d.add("Viernes");
        ad = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, d);
        dia.setAdapter(ad);


    }

    //Toma la información sobre el tipo de asesoria que el usuario requeire, que depende de lo que seleccione
    public void Buscar(View view) {

        if (materia.getSelectedItem().toString().equals(""))
            Toast.makeText(this, "Escoja la materia", Toast.LENGTH_SHORT).show();
        else {
            bundle.putString("Materia", materia.getSelectedItem().toString());
            bundle.putString("Tipo", tipo.getSelectedItem().toString());
            bundle.putString("Dia", dia.getSelectedItem().toString());
            bundle.putString("Horario", horario.getSelectedItem().toString());

            Intent intent = new Intent(asesoria.this, asesoria2.class);
            intent.putExtras(bundle);

            startActivity(intent);
        }
    }
    //Redirige a la página de accion
    public void regresar(View view) {
        Intent intent = new Intent(asesoria.this, Accion.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
