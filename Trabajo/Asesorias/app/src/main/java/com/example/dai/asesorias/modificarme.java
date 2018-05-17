package com.example.dai.asesorias;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class modificarme extends AppCompatActivity {
    private EditText contrasena, datoNuevo;
    private Spinner modificar;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificarme);
        //Traer datos
        contrasena = (EditText) findViewById(R.id.vContrasena);
        datoNuevo = (EditText) findViewById(R.id.datoNuevo);

        modificar = (Spinner) findViewById(R.id.sModificar);

        bundle = this.getIntent().getExtras();

        List<String> cambios = new ArrayList<String>();
        cambios.add("Nombre");
        cambios.add("Apellidos");
        cambios.add("Edad");
        cambios.add("Mail");
        cambios.add("Carrera");

        ArrayAdapter<String> ad = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, cambios);
        modificar.setAdapter(ad);


    }


    public void modificar (View v)
    {
        //Primeramente tenemos que checar que la contraseña escrita sea igual a la ya establecida por el usuario
        if (contrasena.getText().toString().equals(bundle.get("Contrasena"))) {
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Administrador", null, 14);
            SQLiteDatabase db = admin.getWritableDatabase();

            //Se modifica ya que se checo la contraseña
            db.execSQL("update usuario set " + modificar.getSelectedItem().toString().toLowerCase() + " = '" + datoNuevo.getText().toString() + "' where cu = '" + bundle.get("Clave") + "';");
            db.close();

            Intent intent = new Intent(modificarme.this, Accion.class);
            intent.putExtras(bundle);
            startActivity(intent);
            //Aparece dato para mostrarle al usuario que el cambio se realizó
            Toast.makeText(this, "se cambió el dato exitosamente.", Toast.LENGTH_SHORT).show();

        }
        else
            //cuando la contraseña dada no es igual a la que ya estaba establecida
            Toast.makeText(this, "contraseña incorrecta", Toast.LENGTH_SHORT).show();
    }

    //Se regresa a acción
    public void regresar(View view) {
        Intent intent = new Intent(modificarme.this, Accion.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
