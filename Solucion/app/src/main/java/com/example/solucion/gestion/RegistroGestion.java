package com.example.solucion.gestion;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.solucion.data.AdminDB;
import com.example.solucion.modelo.Registro;

import java.io.Console;
import java.util.ArrayList;
import java.util.Date;

public class RegistroGestion {

    private static AdminDB data = null;
    private static SQLiteDatabase conexion;

    public static void init(AdminDB data) {
        RegistroGestion.data = data;
    }

    //Metodos CRUD
    public static boolean insert(Registro registro) {
        long reg = -1;

        if (registro != null) {
            ContentValues info = new ContentValues();

            info.put("fecha", registro.getFecha().toString());
            info.put("descripcion", registro.getDescripcion());
            info.put("peso", registro.getPeso());
            info.put("imc", registro.getImc());
            info.put("grasacorporal", registro.getGrasacorporal());
            info.put("minutos", registro.getMinutos());
            conexion = data.getWritableDatabase();
            reg = conexion.insert("registros", null, info);
            conexion.close();
        }
        return reg != -1;
    }


    public static Registro findById(int id) {
        Registro registro = null;

        conexion = data.getReadableDatabase();
        Cursor datos = conexion.rawQuery("select * from registros where id=?",
                new String[]{"" + id + ""});

        if (datos.moveToFirst()) {
            registro = new Registro(id,
                    datos.getString(1),
                    datos.getString(2),
                    Integer.parseInt(datos.getString(3)),
                    Double.parseDouble(datos.getString(4)),
                    Double.parseDouble(datos.getString(5)),
                    Double.parseDouble(datos.getString(6)));
        }
        conexion.close();
        return registro;
    }

    //public Registro(int id, Date fecha, int minutos, String descripcion, double peso, double imc, double grasacorporal) {
    public static boolean Update(Registro registro) {
        long reg = -1;
        if (registro != null) {
            ContentValues info = new ContentValues();
            info.put("id", registro.getId());
            info.put("fecha", registro.getFecha().toString());
            info.put("minutos", registro.getMinutos());
            info.put("descripcion", registro.getDescripcion());
            info.put("peso", registro.getPeso());
            info.put("imc", registro.getImc());
            info.put("grasacorporal", registro.getGrasacorporal());
            conexion = data.getWritableDatabase();
            reg = conexion.update("registros", info, "id=?",
                    new String[]{"" + registro.getId()});
            conexion.close();
        }
        return reg == 1;
    }


    public static boolean Delete(int id) {
        conexion = data.getWritableDatabase();
        long reg = conexion.delete("registros", "id=?", new String[]{"" + id + ""});
        conexion.close();
        return reg == 1;
    }

    public static ArrayList<Registro> getRegistros() {
        ArrayList<Registro> lista = new ArrayList<>();

        conexion = data.getReadableDatabase();

        Cursor datos = conexion.rawQuery("select * from registros", null);

        while (datos.moveToNext()) {
            lista.add(new Registro(datos.getInt(0),
                    datos.getString(1),
                    datos.getString(3),
                    datos.getInt(2),
                    datos.getDouble(4),
                    datos.getDouble(5),
                    datos.getDouble(6)
                    ));
        }
        conexion.close();
        return lista;
    }
}
