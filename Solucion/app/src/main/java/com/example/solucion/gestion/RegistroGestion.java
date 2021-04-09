package com.example.solucion.gestion;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.solucion.data.AdminDB;
import com.example.solucion.modelo.Registro;

public class RegistroGestion {

    private static AdminDB data=null;
    private static SQLiteDatabase conexion;

    public static void init(AdminDB data) {
       RegistroGestion.data=data;
    }

//Metodos CRUD
    public static boolean insert(Registro registro) {
        long reg = -1;
        if (registro!=null) {
            ContentValues info = new ContentValues();
            info.put("id",registro.getId());
            info.put("fecha",registro.getFecha().toString());
            info.put("descripcion",registro.getDescripcion());
            info.put("peso",registro.getPeso());
            info.put("imc",registro.getImc());
            info.put("grasacorporal",registro.getGrasacorporal());
            conexion=data.getWritableDatabase();
            reg=conexion.insert("registros",null,info);
            conexion.close();
        }
        return reg!=-1;
    }


    public static Registro findById(int id) {
        Registro registro=null;

        conexion=data.getReadableDatabase();
        Cursor datos = conexion.rawQuery("select * from registros where id=?",
                new String[]{""+id+""});

        if (datos.moveToFirst()) {
            registro= new Registro(id,
        }
        conexion.close();
        return registro;
    }

    public static boolean Update(Estudiante estudiante) {
        long registro = -1;  //Almacenará el numero de registros modificados
        if (estudiante!=null) {
            ContentValues info = new ContentValues();
            info.put("id",estudiante.getId());
            info.put("nombre",estudiante.getNombre());
            info.put("edad",estudiante.getEdad());
            conexion=data.getWritableDatabase();
            registro=conexion.update("estudiante",info,"id=?",
                    new String[]{""+estudiante.getId()});
            conexion.close();
        }
        //info  --> map --> {{"id",7},{"nombre","Juan"},{"edad",24}}
        return registro==1;  //Verdadero si tiene un numero de registro, falso si es -1
    }


    public static boolean Delete(int id) {
        conexion = data.getWritableDatabase();
        long registro = conexion.delete("estudiante","id=?",new String[]{""+id+""});
        conexion.close();
        return registro ==1;
    }

    public static ArrayList<Estudiante> getRegistros() {
        ArrayList<Estudiante> lista=null;

        conexion=data.getReadableDatabase();
        Cursor datos = conexion.rawQuery("select * from estudiante",null);

        while (datos.moveToNext()) {  //avanza uno por uno cada registro
            lista.add(new Estudiante(datos.getInt(0),
                    datos.getString(1),
                    datos.getInt(2)));
        }
        conexion.close();
        return lista;
    }
}
