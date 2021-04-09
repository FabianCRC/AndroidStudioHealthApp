package com.example.solucion.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.solucion.R;

import java.text.DecimalFormat;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        EditText etPeso = root.findViewById(R.id.etPeso);
        EditText etAltura = root.findViewById(R.id.etAltura);
        TextView tvIMC = root.findViewById(R.id.tvIMC);
        Button btCalcular = root.findViewById(R.id.btCalcularIMC);

        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etPeso.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "El peso no puede ser nulo", Toast.LENGTH_SHORT).show();
                } else  if (etAltura.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "La altura no puede ser nula", Toast.LENGTH_SHORT).show();
                } else if (!(etAltura.getText().toString().equals("")) && !(etPeso.getText().toString().equals(""))){
                    double peso = Double.parseDouble(etPeso.getText().toString());
                    double altura = (Double.parseDouble(etAltura.getText().toString())) / 100;
                    double resultado = peso / Math.pow(altura, 2);
                    tvIMC.setText(Math.round(resultado * 100.0) / 100.0 + "");
                }else{
                    Toast.makeText(getContext(), "Ha ocurrido un error, verifique los datos", Toast.LENGTH_SHORT).show();
                }
            }

        });


        return root;
    }
}