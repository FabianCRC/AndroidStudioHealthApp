package com.example.solucion.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.solucion.R;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        EditText etIMC = root.findViewById(R.id.etIMC);
        EditText etEdad = root.findViewById(R.id.etEdad);
        CheckBox cbFemenino = root.findViewById(R.id.cbFemenino);
        CheckBox cbMasculino = root.findViewById(R.id.cbMasculino);
        TextView tvGC = root.findViewById(R.id.tvMostrarGrasaCorporal);
        Button btCalcularGrasa = root.findViewById(R.id.btCalcularGrasa);

        btCalcularGrasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etIMC.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "El IMC no puede ser nulo", Toast.LENGTH_SHORT).show();
                } else if (etEdad.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "La Edad no puede ser nula", Toast.LENGTH_SHORT).show();
                } else  if (cbFemenino.isChecked() && cbMasculino.isChecked()) {
                    Toast.makeText(getContext(), "Debe seleccionar solo un sexo", Toast.LENGTH_SHORT).show();
                } else if (!(etEdad.getText().toString().equals("")) && !(etIMC.getText().toString().equals("")) && (cbFemenino.isChecked() || cbMasculino.isChecked())) {
                    double imc = Double.parseDouble(etIMC.getText().toString());
                    int edad = Integer.parseInt(etEdad.getText().toString());
                    int genero = 0;
                    if (cbMasculino.isChecked()) {
                        genero = 1;
                    } else {
                        genero = 0;
                    }

                    double resultado = (1.2) * (imc) + (0.23 * (edad)) - (10.8 * (genero)) - (5.4);
                    tvGC.setText(Math.round(resultado * 100.0) / 100.0 + "%");
                } else {
                    Toast.makeText(getContext(), "Ha ocurrido un error, verifique los datos", Toast.LENGTH_SHORT).show();
                }
            }

        });
        return root;
    }
}