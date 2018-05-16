package com.example.dai.asesorias;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Principal extends AppCompatActivity {

    private EditText cu, nombre, apellidos, edad, mail, carrera, contrasena;

    private EditText loginClave, loginContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);


        cu = (EditText) findViewById(R.id.cu);
        nombre = (EditText) findViewById(R.id.nombre);
        apellidos = (EditText) findViewById(R.id.apellidos);
        edad = (EditText) findViewById(R.id.edad);
        mail = (EditText) findViewById(R.id.mail);
        carrera = (EditText) findViewById(R.id.carrera);
        contrasena = (EditText) findViewById(R.id.contrasena);

        loginClave = (EditText) findViewById(R.id.loginClave);
        loginContrasena = (EditText) findViewById(R.id.loginContrasena);
    }

    //creamos un nuevo usuario con los atributos necesarios
    public void nuevoUsuario (View v)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Administrador", null, 14);
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        //toma los valores que el usuario escirbe en los campos determinados
        registro.put("cu", cu.getText().toString());
        registro.put("nombre", nombre.getText().toString());
        registro.put("apellidos", apellidos.getText().toString());
        registro.put("edad", edad.getText().toString());
        registro.put("mail", mail.getText().toString());
        registro.put("carrera", carrera.getText().toString());
        registro.put("contrasena", contrasena.getText().toString());
        long res = db.insert("usuario", null, registro);
        db.close();

        if (res==-1)
            Toast.makeText(this, "no se pudo registrar la cuenta.", Toast.LENGTH_SHORT).show();
        else {
            Intent intent = new Intent(Principal.this, Accion.class);
            Bundle b = new Bundle();
            b.putString("Nombre", nombre.getText().toString());
            b.putString("Clave", cu.getText().toString());
            b.putString("Contrasena", contrasena.getText().toString());
            intent.putExtras(b);
            startActivity(intent);

            Toast.makeText(this, "se registró el usuario exitosamente.", Toast.LENGTH_SHORT).show();
        }
    }

    //Entra el usuario ya dado de alta anteriormente

    public void loginUsuario (View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Administrador", null, 14);
        SQLiteDatabase db = admin.getWritableDatabase();

        Cursor fila = db.rawQuery("select nombre from usuario where cu = '" + loginClave.getText().toString() + "' and contrasena = '" + loginContrasena.getText().toString() + "';", null);
        if (fila.moveToFirst()){
            Intent intent = new Intent(Principal.this, Accion.class);
            Bundle b = new Bundle();
            b.putString("Nombre", fila.getString(0));
            b.putString("Clave", loginClave.getText().toString());
            b.putString("Contrasena", loginContrasena.getText().toString());
            intent.putExtras(b);
            startActivity(intent);

            Toast.makeText(this, "Se ha entrado a la cuenta exitosamente.", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "Clave o contraseña incorrecta.", Toast.LENGTH_SHORT).show();
        fila.close();
    }
}
