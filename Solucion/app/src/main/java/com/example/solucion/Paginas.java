package com.example.solucion;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Paginas extends Fragment {

    Button btInfoAlimentacion;
    Button btInfoEjercicios;
    Button btInfoRecetas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_paginas, container, false);

        btInfoAlimentacion = root.findViewById(R.id.btInformacionAlimentacion);
        btInfoEjercicios = root.findViewById(R.id.btInformacionEjercicios);
        btInfoRecetas = root.findViewById(R.id.btInformacionRecetas);

        btInfoAlimentacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri webpage = Uri.parse("https://www.nutrinfo.com/");
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(intent);
            }
        });
        btInfoEjercicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri webpage = Uri.parse("https://lifestyle.fit/");
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(intent);
            }
        });
        btInfoRecetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri webpage = Uri.parse("https://www.recetas.fitness/");
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(intent);
            }
        });


        return root;
    }
}