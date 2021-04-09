package com.example.solucion.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminDB extends SQLiteOpenHelper {
    public AdminDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table registros (id int primary key AUTOINCREMENT, fecha Text," +
                "minutos int," +
                "decripcion int," +
                "peso DECIMAL(5,2)," +
                "imc DECIMAL(4,2)," +
                "grasacorporal decimal) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("create table registros (id int primary key AUTOINCREMENT, fecha Text," +
                "minutos int," +
                "decripcion text," +
                "peso DECIMAL(5,2)," +
                "imc DECIMAL(4,2)," +
                "grasacorporal decimal) ");
    }
}
