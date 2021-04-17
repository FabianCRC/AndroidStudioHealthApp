package com.example.solucion;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.solucion.data.AdminDB;
import com.example.solucion.gestion.RegistroGestion;
import com.example.solucion.modelo.Registro;


public class RegistroFragment extends Fragment {


    private EditText etFecha;
    private EditText etDescripcion;
    private EditText etMinutos;
    private EditText etPeso;
    private EditText etIMC;
    private EditText etGrasa;
    private Button btGuardar;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_registro, container, false);

        etFecha=root.findViewById(R.id.etFechaRegistro);
        etDescripcion=root.findViewById(R.id.etDescripcionRegistro);
        etMinutos=root.findViewById(R.id.etMinutosRegistro);
        etPeso=root.findViewById(R.id.etPesoRegistro);
        etIMC=root.findViewById(R.id.etIMCRegistro);
        etGrasa=root.findViewById(R.id.etGrasaRegistro);

        btGuardar=root.findViewById(R.id.btGuardarRegistro);

        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });

        return root;
    }

    private void limpiarCampos() {
        etFecha.setText("");
        etDescripcion.setText("");
        etMinutos.setText("");
        etPeso.setText("");
        etIMC.setText("");
        etGrasa.setText("");
    }

    private Registro getRegistro() {
        Registro registro=null;
        String fecha=etFecha.getText().toString();
        String descripcion=etDescripcion.getText().toString();
        int minutos=Integer.parseInt(etMinutos.getText().toString());
        double peso=Double.parseDouble(etPeso.getText().toString());
        double imc=Double.parseDouble(etIMC.getText().toString());
        double grasacorporal=Double.parseDouble(etGrasa.getText().toString());
        if (!fecha.isEmpty() && !descripcion.isEmpty() && (minutos>=0)&& (peso>=0)&& (imc>=0)&& (grasacorporal>=0)) {
            registro = new Registro(0,fecha,descripcion,minutos,peso,imc,grasacorporal);
        }
        return registro;
    }

    private void insert() {
        if (RegistroGestion.insert(getRegistro())) {
            Toast.makeText(getContext(), "Se ha hecho el registro correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Hubo un error al hacer el registro", Toast.LENGTH_SHORT).show();
        }
    }
    private void update() {
        if (RegistroGestion.Update(getRegistro())) {
            Toast.makeText(getContext(), "Se ha hecho la actualizacion correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Hubo un error al hacer la actualizacion", Toast.LENGTH_SHORT).show();
        }
    }
    private void delete() {
        if (RegistroGestion.Delete(getRegistro().getId())) {
            Toast.makeText(getContext(), "Se ha la eliminación correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Hubo un error al hacer la eliminación", Toast.LENGTH_SHORT).show();
        }
    }


}