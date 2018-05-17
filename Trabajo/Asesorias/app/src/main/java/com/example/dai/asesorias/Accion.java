package com.example.dai.asesorias;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Accion extends AppCompatActivity {

    private TextView titulo;
    private Bundle bundle;
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accion);
        titulo = (TextView) findViewById(R.id.tvTitulo);

        bundle = this.getIntent().getExtras();
        titulo.setText("Bienvenido, " + bundle.get("Nombre"));
    }

    //Redirigimos la página a modificar
    public void modificar (View v) {
        Intent intent = new Intent(Accion.this, modificarme.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    //Redirigimos la página a califica
    public void calificar (View v) {
        Intent intent = new Intent(Accion.this, califica.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    //Redirigimos la página a asesoria
    public void buscar (View v) {
        Intent intent = new Intent(Accion.this, asesoria.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    //Redirigimos la página a sitioWeb
    public void info (View v)   {
        Intent intent = new Intent(Accion.this, SitioWeb.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    //Redirigimos la págsina a inscito
    public void ver (View v) {
        Intent intent = new Intent(Accion.this, inscrito.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void cerrar(View view) {

        bundle.remove("Nombre");
        bundle.remove("Clave");
        bundle.remove("Contrasena");

        Intent intent = new Intent(Accion.this, Principal.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
