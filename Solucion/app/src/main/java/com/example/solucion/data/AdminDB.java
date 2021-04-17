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
        db.execSQL("create table registros (id INTEGER primary key AUTOINCREMENT, fecha Text," +
                "minutos int," +
                "descripcion text," +
                "peso DECIMAL(5,2)," +
                "imc DECIMAL(4,2)," +
                "grasacorporal decimal) ");
        db.execSQL("insert into registros values (0,'20/02/2021',18,'Descripcion',25,25,25)");
        db.execSQL("insert into registros values (1,'20/02/2021',20,'Descripcion',25,25,25)");
        db.execSQL("insert into registros values (2,'20/02/2021',25,'Descripcion',25,25,25)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("create table registros (id INTEGER primary key AUTOINCREMENT, fecha Text," +
                "minutos int," +
                "descripcion text," +
                "peso DECIMAL(5,2)," +
                "imc DECIMAL(4,2)," +
                "grasacorporal decimal) ");
        db.execSQL("insert into registros values (0,'20/02/2021',18,'Descripcion',25,25,25)");
        db.execSQL("insert into registros values (1,'20/02/2021',20,'Descripcion',25,25,25)");
        db.execSQL("insert into registros values (2,'20/02/2021',25,'Descripcion',25,25,25)");
    }
}
