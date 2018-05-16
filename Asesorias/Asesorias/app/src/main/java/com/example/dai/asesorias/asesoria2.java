package com.example.dai.asesorias;

import android.content.ContentValues;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class asesoria2 extends AppCompatActivity {
    Bundle bundle;
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asesoria2);

        lista = findViewById(R.id.lista);

        TextView titulo = findViewById(R.id.tvAs);

        bundle = this.getIntent().getExtras();

        //Parsea el contenido
        StringBuilder temp = new StringBuilder();
        String materia = bundle.getString("Materia");
        int i = 0;
        while (i<materia.length() && Character.isDigit(materia.charAt(i)))
        {
            temp.append(materia.charAt(i));
            i++;
        }
        materia = temp.toString();


        String tipo = bundle.getString("Tipo").toLowerCase();

        temp = new StringBuilder();
        String horario = bundle.getString("Horario");
        i = 0;
        while (i<horario.length() && Character.isDigit(horario.charAt(i)))
        {
            temp.append(horario.charAt(i));
            i++;
        }
        horario = temp.toString();

        String dia = bundle.getString("Dia").toLowerCase();

        titulo.setText("Asesorias disponibles para la materia " + bundle.getString("Materia"));

        //Forma los diferente query viendo si se escogió restricción o no.
        StringBuilder query = new StringBuilder();
        query.append("select cClase, profesor.tipo, profesor.nombre, profesor.apellidos, dia, horario, lugar from clase join materia on clase.cMateria = materia.cMateria join profesor on clase.cProfesor = profesor.cProfesor where clase.cMateria = '" + materia + "' ");
        if (!tipo.equals(""))
        {
            query.append("and profesor.tipo = '" + tipo + "' ");
        }
        if (!horario.equals(""))
        {
            query.append("and horario = '" + horario + "' ");
        }
        if (!dia.equals(""))
        {
            query.append("and dia = '" + dia + "' ");
        }

        List<String> resultados = new ArrayList<String>();

        //Coloca los resultados en el ListView
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Administrador", null, 14);
        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor fila = db.rawQuery(query.toString(), null);
        while (fila.moveToNext())
        {
            resultados.add("Clase " + fila.getInt(0) + ": con " + fila.getString(1) + " " + fila.getString(2) + " " + fila.getString(3) + ", el " + fila.getString(4) + " de " + fila.getInt(5) + ":00 a " + ((Integer)fila.getInt(5)+1) + ":00; en " + fila.getString(6));

        }
        fila.close();

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, resultados);
        lista.setAdapter(adapter);

        final String[] pos = resultados.toArray(new String[resultados.size()]);

        bundle.remove("Materia");
        bundle.remove("Tipo");
        bundle.remove("Día");
        bundle.remove("Horario");


        //Para seleccionar al hacer clic
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                //Crea un nuevo dialogo para aceptar o cancelar la incripción

                AlertDialog.Builder builder = new AlertDialog.Builder(asesoria2.this);
                builder.setTitle("Está seguro que quiere inscribirse a esta asesoría?");
                builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(asesoria2.this, "Administrador", null, 14);
                        SQLiteDatabase db = admin.getWritableDatabase();

                        String str = pos[position];
                        int i = 6;
                        StringBuilder temp = new StringBuilder();
                        while (i<str.length() && Character.isDigit(str.charAt(i)))
                        {
                            temp.append(str.charAt(i));
                            i++;
                        }

                        ContentValues inscribir = new ContentValues();
                        inscribir.put("cCLase", Integer.parseInt(temp.toString()));
                        inscribir.put("cu", bundle.getString("Clave"));

                        long res = db.insert("inscrito", null, inscribir);

                        db.execSQL("update clase set inscritos = inscritos+1 where cClase = 1");

                        if (res==-1) {
                            Toast.makeText(asesoria2.this, "Ya está inscrito en este curso", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Intent intent = new Intent(asesoria2.this, Accion.class);
                            intent.putExtras(bundle);
                            startActivity(intent);

                            Toast.makeText(asesoria2.this, "Se ha inscrito exitosamente al curso", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

                AlertDialog dialog = builder.create();

                dialog.show();
            }
        });

    }

    //Regresa a la página principal
    public void regresar(View view) {
        Intent intent = new Intent(asesoria2.this, asesoria.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
