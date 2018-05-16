package com.example.dai.asesorias;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


//se crea para poder hacer la base de datos
public class AdminSQLiteOpenHelper extends SQLiteOpenHelper{
    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Crear tablas del modelo
        sqLiteDatabase.execSQL("create table usuario (cu integer primary key, nombre text, apellidos text, edad integer, mail text, carrera text, contrasena text);");
        sqLiteDatabase.execSQL("create table profesor (cProfesor integer primary key, nombre text, apellidos text, mail text, tipo text);");
        sqLiteDatabase.execSQL("create table materia (cMateria integer primary key, nombre text, tipo text);");
        sqLiteDatabase.execSQL("create table clase (cClase integer primary key, cMateria integer references materia(cMateria), cProfesor integer references profesor(cProfesor), horario integer, dia text, cupo integer, inscritos integer, lugar text);");
        sqLiteDatabase.execSQL("create table calificacion (cu integer references usuario(cu), cProfesor integer references profesor(cProfesor), puntaje integer, comentario text, primary key (cu, cProfesor));");
        sqLiteDatabase.execSQL("create table inscrito (cClase integer references clase(cClase), cu integer references usuario(cu), primary key (cClase, cu));");

        //Profesores determinados para ponerlos en la aplicación
        sqLiteDatabase.execSQL("insert into profesor values (023, 'Ricardo', 'Meadowcroft', 'rmeadowc@itam.mx', 'estudiante');");
        sqLiteDatabase.execSQL("insert into profesor values (011, 'Ana Lidia', 'Franzoni', 'alidia@itam.mx', 'profesor');");
        sqLiteDatabase.execSQL("insert into profesor values (123, 'Roberto', 'Tello', 'tyo@itam.mx', 'laboratorista');");
        sqLiteDatabase.execSQL("insert into profesor values (124, 'Sofia', 'Occhipinti', 'sofi@itam.mx', 'estudiante');");
        sqLiteDatabase.execSQL("insert into profesor values (125, 'Caro', 'Marti', 'carmar@itam.mx', 'laboratorista');");
        sqLiteDatabase.execSQL("insert into profesor values (126, 'Estela', 'Romero', 'romero@itam.mx', 'profesor');");
        sqLiteDatabase.execSQL("insert into profesor values (127, 'Michelle', 'Desdier', 'desdier@itam.mx', 'estudiante');");
        sqLiteDatabase.execSQL("insert into profesor values (128, 'Estevan', 'Salar', 'salar@itam.mx', 'profesor');");
        sqLiteDatabase.execSQL("insert into profesor values (129, 'Carmelo', 'Cruz', 'cruz@itam.mx', 'laboratorista');");
        sqLiteDatabase.execSQL("insert into profesor values (130, 'Jorge', 'Díaz', 'díaz@itam.mx', 'profesor');");
        sqLiteDatabase.execSQL("insert into profesor values (131, 'Carlos', 'Lopéz', 'lop@itam.mx', 'estudiante');");
        sqLiteDatabase.execSQL("insert into profesor values (132, 'Rodrigo', 'Juaréz', 'ro@itam.mx', 'laboratorista');");

        //Materias pre-determinadas
        sqLiteDatabase.execSQL("insert into materia values (11, 'Introduccion a la Computación', 'Computacionales');");
        sqLiteDatabase.execSQL("insert into materia values (12, 'Algoritmos y programas', 'Computacionales');");
        sqLiteDatabase.execSQL("insert into materia values (22, 'Cálculo I', 'Matemáticas');");
        sqLiteDatabase.execSQL("insert into materia values (23, 'Cálculo II', 'Matemáticas');");
        sqLiteDatabase.execSQL("insert into materia values (25, 'Cálculo III', 'Matemáticas');");
        sqLiteDatabase.execSQL("insert into materia values (26, 'Algebra Lineal', 'Matemáticas');");
        sqLiteDatabase.execSQL("insert into materia values (31, 'Ideas I', 'Estudios Generales');");
        sqLiteDatabase.execSQL("insert into materia values (41, 'Problemas I', 'Estudios Generales');");
        sqLiteDatabase.execSQL("insert into materia values (42, 'Problemas II', 'Estudios Generales');");
        sqLiteDatabase.execSQL("insert into materia values (51, 'Conta I', 'Contabilidad');");
        sqLiteDatabase.execSQL("insert into materia values (52, 'Contabilidad para ingenieros', 'Contabilidad');");
        sqLiteDatabase.execSQL("insert into materia values (61, 'Economía I', 'Economía');");
        sqLiteDatabase.execSQL("insert into materia values (71, 'Mercadotecnia I', 'Administración');");


        //Clases pre-determinadas
        sqLiteDatabase.execSQL("insert into clase values (1, 11, 023, '9', 'Lunes', 5, 0, 'CC202');");
        sqLiteDatabase.execSQL("insert into clase values (2, 11, 011, '16', 'Martes', 10, 0, 'CC201');");
        sqLiteDatabase.execSQL("insert into clase values (3, 11, 132, '16', 'Miércoles', 10, 0, 'CC201');");
        sqLiteDatabase.execSQL("insert into clase values (4, 12, 124, '16', 'Miércoles', 6, 0, 'Salón 301');");
        sqLiteDatabase.execSQL("insert into clase values (5, 12, 125, '8', 'Viernes', 20, 0, 'Salón 201');");
        sqLiteDatabase.execSQL("insert into clase values (6, 12, 126, '10', 'Lunes', 10, 0, 'Salón 213');");
        sqLiteDatabase.execSQL("insert into clase values (7, 22, 127, '10', 'Jueves', 11, 0, 'Salón 311');");
        sqLiteDatabase.execSQL("insert into clase values (8, 22, 128, '11', 'Viernes', 20, 0, 'Salón 311');");
        sqLiteDatabase.execSQL("insert into clase values (9, 23, 129, '15', 'Martes', 5, 0, 'Salón B3');");
        sqLiteDatabase.execSQL("insert into clase values (10, 23, 130, '9', 'Lunes', 20, 0, 'Salón PB2');");
        sqLiteDatabase.execSQL("insert into clase values (11, 25, 131, '9', 'Lunes', 15, 0, 'Salón 101');");
        sqLiteDatabase.execSQL("insert into clase values (12, 26, 132, '9', 'Lunes', 12, 0, 'Salón 102');");
        sqLiteDatabase.execSQL("insert into clase values (13, 26, 023, '7', 'Viernes', 20, 0, 'Salón 311');");
        sqLiteDatabase.execSQL("insert into clase values (14,31, 011, '15', 'Martes', 5, 0, 'Salón B3');");
        sqLiteDatabase.execSQL("insert into clase values (15, 31, 125, '14', 'Lunes', 20, 0, 'Salón PB2');");
        sqLiteDatabase.execSQL("insert into clase values (16, 31, 124, '13', 'Viernes', 15, 0, 'Salón 104');");
        sqLiteDatabase.execSQL("insert into clase values (17, 41, 126, '12', 'Martes', 12, 0, 'Salón 103');");
        sqLiteDatabase.execSQL("insert into clase values (18,42, 127, '12', 'Martes', 15, 0, 'Salón B3');");
        sqLiteDatabase.execSQL("insert into clase values (19, 42, 128, '18', 'Lunes', 30, 0, 'Salón 305');");
        sqLiteDatabase.execSQL("insert into clase values (20, 42, 129, '8', 'Viernes', 17, 0, 'Salón 307');");
        sqLiteDatabase.execSQL("insert into clase values (21, 51, 130, '13', 'Lunes', 12, 0, 'Salón 309');");
        sqLiteDatabase.execSQL("insert into clase values (22, 51, 131, '12', 'Lunes', 12, 0, 'Salón 311');");
        sqLiteDatabase.execSQL("insert into clase values (23, 51, 132, '14', 'Miércoles', 12, 0, 'Salón 106');");
        sqLiteDatabase.execSQL("insert into clase values (24, 52, 023, '12', 'Lunes', 12, 0, 'Salón 101');");
        sqLiteDatabase.execSQL("insert into clase values (25, 52, 011, '15', 'Lunes', 12, 0, 'Salón 102');");
        sqLiteDatabase.execSQL("insert into clase values (26, 61, 123, '11', 'Jueves', 12, 0, 'Salón 202');");
        sqLiteDatabase.execSQL("insert into clase values (27, 61, 124, '10', 'Miércoles', 12, 0, 'Salón 101s');");
        sqLiteDatabase.execSQL("insert into clase values (28, 71, 125, '9', 'Viernes', 12, 0, 'Salón 311');");
        sqLiteDatabase.execSQL("insert into clase values (29, 71, 126, '17', 'Martes', 12, 0, 'Salón 300');");
        sqLiteDatabase.execSQL("insert into clase values (30, 71, 127, '8', 'Lunes', 12, 0, 'Salón 303');");

        sqLiteDatabase.execSQL("insert into clase values (31, 31, 125, '7', 'Martes', 20, 0, 'Salón 303');");
        sqLiteDatabase.execSQL("insert into clase values (32, 31, 124, '12', 'Viernes', 15, 0, 'Salón 302');");
        sqLiteDatabase.execSQL("insert into clase values (33, 31, 125, '11', 'Martes', 20, 0, 'Salón 106');");
        sqLiteDatabase.execSQL("insert into clase values (34, 31, 124, '16', 'Viernes', 15, 0, 'Salón 107');");


        sqLiteDatabase.execSQL("insert into clase values (35, 41, 125, '11', 'Martes', 12, 0, 'Salón 102');");
        sqLiteDatabase.execSQL("insert into clase values (36, 41, 126, '12', 'Viernes', 12, 0, 'Salón 202');");
        sqLiteDatabase.execSQL("insert into clase values (37, 41, 125, '13', 'Jueves', 12, 0, 'Salón 208');");
        sqLiteDatabase.execSQL("insert into clase values (38, 41, 126, '14', 'Lunes', 12, 0, 'Salón 203');");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists usuario;");
        sqLiteDatabase.execSQL("drop table if exists profesor;");
        sqLiteDatabase.execSQL("drop table if exists materia;");
        sqLiteDatabase.execSQL("drop table if exists clase;");
        sqLiteDatabase.execSQL("drop table if exists calificacion;");
        sqLiteDatabase.execSQL("drop table if exists inscrito;");

//Crear tablas del modelo
        sqLiteDatabase.execSQL("create table usuario (cu integer primary key, nombre text, apellidos text, edad integer, mail text, carrera text, contrasena text);");
        sqLiteDatabase.execSQL("create table profesor (cProfesor integer primary key, nombre text, apellidos text, mail text, tipo text);");
        sqLiteDatabase.execSQL("create table materia (cMateria integer primary key, nombre text, tipo text);");
        sqLiteDatabase.execSQL("create table clase (cClase integer primary key, cMateria integer references materia(cMateria), cProfesor integer references profesor(cProfesor), horario integer, dia text, cupo integer, inscritos integer, lugar text);");
        sqLiteDatabase.execSQL("create table calificacion (cu integer references usuario(cu), cProfesor integer references profesor(cProfesor), puntaje integer, comentario text, primary key (cu, cProfesor));");
        sqLiteDatabase.execSQL("create table inscrito (cClase integer references clase(cClase), cu integer references usuario(cu), primary key (cClase, cu));");

        //Profesores determinados para ponerlos en la aplicación
        sqLiteDatabase.execSQL("insert into profesor values (023, 'Ricardo', 'Meadowcroft', 'rmeadowc@itam.mx', 'estudiante');");
        sqLiteDatabase.execSQL("insert into profesor values (011, 'Ana Lidia', 'Franzoni', 'alidia@itam.mx', 'profesor');");
        sqLiteDatabase.execSQL("insert into profesor values (123, 'Roberto', 'Tello', 'tyo@itam.mx', 'laboratorista');");
        sqLiteDatabase.execSQL("insert into profesor values (124, 'Sofia', 'Occhipinti', 'sofi@itam.mx', 'estudiante');");
        sqLiteDatabase.execSQL("insert into profesor values (125, 'Caro', 'Marti', 'carmar@itam.mx', 'laboratorista');");
        sqLiteDatabase.execSQL("insert into profesor values (126, 'Estela', 'Romero', 'romero@itam.mx', 'profesor');");
        sqLiteDatabase.execSQL("insert into profesor values (127, 'Michelle', 'Desdier', 'desdier@itam.mx', 'estudiante');");
        sqLiteDatabase.execSQL("insert into profesor values (128, 'Estevan', 'Salar', 'salar@itam.mx', 'profesor');");
        sqLiteDatabase.execSQL("insert into profesor values (129, 'Carmelo', 'Cruz', 'cruz@itam.mx', 'laboratorista');");
        sqLiteDatabase.execSQL("insert into profesor values (130, 'Jorge', 'Díaz', 'díaz@itam.mx', 'profesor');");
        sqLiteDatabase.execSQL("insert into profesor values (131, 'Carlos', 'Lopéz', 'lop@itam.mx', 'estudiante');");
        sqLiteDatabase.execSQL("insert into profesor values (132, 'Rodrigo', 'Juaréz', 'ro@itam.mx', 'laboratorista');");

        //Materias pre-determinadas
        sqLiteDatabase.execSQL("insert into materia values (11, 'Introduccion a la Computación', 'Computacionales');");
        sqLiteDatabase.execSQL("insert into materia values (12, 'Algoritmos y programas', 'Computacionales');");
        sqLiteDatabase.execSQL("insert into materia values (22, 'Cálculo I', 'Matemáticas');");
        sqLiteDatabase.execSQL("insert into materia values (23, 'Cálculo II', 'Matemáticas');");
        sqLiteDatabase.execSQL("insert into materia values (25, 'Cálculo III', 'Matemáticas');");
        sqLiteDatabase.execSQL("insert into materia values (26, 'Algebra Lineal', 'Matemáticas');");
        sqLiteDatabase.execSQL("insert into materia values (31, 'Ideas I', 'Estudios Generales');");
        sqLiteDatabase.execSQL("insert into materia values (41, 'Problemas I', 'Estudios Generales');");
        sqLiteDatabase.execSQL("insert into materia values (42, 'Problemas II', 'Estudios Generales');");
        sqLiteDatabase.execSQL("insert into materia values (51, 'Conta I', 'Contabilidad');");
        sqLiteDatabase.execSQL("insert into materia values (52, 'Contabilidad para ingenieros', 'Contabilidad');");
        sqLiteDatabase.execSQL("insert into materia values (61, 'Economía I', 'Economía');");
        sqLiteDatabase.execSQL("insert into materia values (71, 'Mercadotecnia I', 'Administración');");


        //Clases pre-determinadas
        sqLiteDatabase.execSQL("insert into clase values (1, 11, 023, '9', 'Lunes', 5, 0, 'CC202');");
        sqLiteDatabase.execSQL("insert into clase values (2, 11, 011, '16', 'Martes', 10, 0, 'CC201');");
        sqLiteDatabase.execSQL("insert into clase values (3, 11, 132, '16', 'Miércoles', 10, 0, 'CC201');");
        sqLiteDatabase.execSQL("insert into clase values (4, 12, 124, '16', 'Miércoles', 6, 0, 'Salón 301');");
        sqLiteDatabase.execSQL("insert into clase values (5, 12, 125, '8', 'Viernes', 20, 0, 'Salón 201');");
        sqLiteDatabase.execSQL("insert into clase values (6, 12, 126, '10', 'Lunes', 10, 0, 'Salón 213');");
        sqLiteDatabase.execSQL("insert into clase values (7, 22, 127, '10', 'Jueves', 11, 0, 'Salón 311');");
        sqLiteDatabase.execSQL("insert into clase values (8, 22, 128, '11', 'Viernes', 20, 0, 'Salón 311');");
        sqLiteDatabase.execSQL("insert into clase values (9, 23, 129, '15', 'Martes', 5, 0, 'Salón B3');");
        sqLiteDatabase.execSQL("insert into clase values (10, 23, 130, '9', 'Lunes', 20, 0, 'Salón PB2');");
        sqLiteDatabase.execSQL("insert into clase values (11, 25, 131, '9', 'Lunes', 15, 0, 'Salón 101');");
        sqLiteDatabase.execSQL("insert into clase values (12, 26, 132, '9', 'Lunes', 12, 0, 'Salón 102');");
        sqLiteDatabase.execSQL("insert into clase values (13, 26, 023, '7', 'Viernes', 20, 0, 'Salón 311');");
        sqLiteDatabase.execSQL("insert into clase values (14,31, 011, '15', 'Martes', 5, 0, 'Salón B3');");
        sqLiteDatabase.execSQL("insert into clase values (15, 31, 125, '14', 'Lunes', 20, 0, 'Salón PB2');");
        sqLiteDatabase.execSQL("insert into clase values (16, 31, 124, '13', 'Viernes', 15, 0, 'Salón 104');");
        sqLiteDatabase.execSQL("insert into clase values (17, 41, 126, '12', 'Martes', 12, 0, 'Salón 103');");
        sqLiteDatabase.execSQL("insert into clase values (18,42, 127, '12', 'Martes', 15, 0, 'Salón B3');");
        sqLiteDatabase.execSQL("insert into clase values (19, 42, 128, '18', 'Lunes', 30, 0, 'Salón 305');");
        sqLiteDatabase.execSQL("insert into clase values (20, 42, 129, '8', 'Viernes', 17, 0, 'Salón 307');");
        sqLiteDatabase.execSQL("insert into clase values (21, 51, 130, '13', 'Lunes', 12, 0, 'Salón 309');");
        sqLiteDatabase.execSQL("insert into clase values (22, 51, 131, '12', 'Lunes', 12, 0, 'Salón 311');");
        sqLiteDatabase.execSQL("insert into clase values (23, 51, 132, '14', 'Miércoles', 12, 0, 'Salón 106');");
        sqLiteDatabase.execSQL("insert into clase values (24, 52, 023, '12', 'Lunes', 12, 0, 'Salón 101');");
        sqLiteDatabase.execSQL("insert into clase values (25, 52, 011, '15', 'Lunes', 12, 0, 'Salón 102');");
        sqLiteDatabase.execSQL("insert into clase values (26, 61, 123, '11', 'Jueves', 12, 0, 'Salón 202');");
        sqLiteDatabase.execSQL("insert into clase values (27, 61, 124, '10', 'Miércoles', 12, 0, 'Salón 101s');");
        sqLiteDatabase.execSQL("insert into clase values (28, 71, 125, '9', 'Viernes', 12, 0, 'Salón 311');");
        sqLiteDatabase.execSQL("insert into clase values (29, 71, 126, '17', 'Martes', 12, 0, 'Salón 300');");
        sqLiteDatabase.execSQL("insert into clase values (30, 71, 127, '8', 'Lunes', 12, 0, 'Salón 303');");

        sqLiteDatabase.execSQL("insert into clase values (31, 31, 125, '7', 'Martes', 20, 0, 'Salón 303');");
        sqLiteDatabase.execSQL("insert into clase values (32, 31, 124, '12', 'Viernes', 15, 0, 'Salón 302');");
        sqLiteDatabase.execSQL("insert into clase values (33, 31, 125, '11', 'Martes', 20, 0, 'Salón 106');");
        sqLiteDatabase.execSQL("insert into clase values (34, 31, 124, '16', 'Viernes', 15, 0, 'Salón 107');");


        sqLiteDatabase.execSQL("insert into clase values (35, 41, 125, '11', 'Martes', 12, 0, 'Salón 102');");
        sqLiteDatabase.execSQL("insert into clase values (36, 41, 126, '12', 'Viernes', 12, 0, 'Salón 202');");
        sqLiteDatabase.execSQL("insert into clase values (37, 41, 125, '13', 'Jueves', 12, 0, 'Salón 208');");
        sqLiteDatabase.execSQL("insert into clase values (38, 41, 126, '14', 'Lunes', 12, 0, 'Salón 203');");


    }
}