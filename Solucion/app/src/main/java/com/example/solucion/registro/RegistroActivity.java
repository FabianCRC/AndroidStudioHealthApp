package com.example.solucion.registro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.solucion.R;
import com.example.solucion.gestion.RegistroGestion;
import com.example.solucion.modelo.Registro;

public class RegistroActivity extends AppCompatActivity {

    private EditText etFecha;
    private EditText etDescripcion;
    private EditText etMinutos;
    private EditText etPeso;
    private EditText etIMC;
    private EditText etGrasa;
    private Button btModificar;
    private Button btEliminar;

    private int tipo;
    private Registro registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        etFecha = findViewById(R.id.etFechaRegistroActivity);
        etDescripcion = findViewById(R.id.etDescripcionRegistroActivity);
        etMinutos = findViewById(R.id.etMinutosRegistroActivity);
        etPeso = findViewById(R.id.etPesoRegistroActivity);
        etIMC = findViewById(R.id.etIMCRegistroActivity);
        etGrasa = findViewById(R.id.etGrasaRegistroActivity);

        btModificar = findViewById(R.id.btGuardarRegistro);
        btEliminar = findViewById(R.id.btGuardarRegistro);


        registro = (Registro) getIntent().getSerializableExtra("registro");
        etFecha.setText("" + registro.getFecha());
        etDescripcion.setText("" + registro.getDescripcion());
        etMinutos.setText("" + registro.getMinutos());
        etPeso.setText("" + registro.getPeso());
        etIMC.setText("" + registro.getImc());
        etGrasa.setText("" + registro.getGrasacorporal());

        btModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                procesoModificar();
            }
        });


    }

    private void procesoModificar() {
        int id = registro.getId();
        String fecha = etFecha.getText().toString();
        String descripcion = etDescripcion.getText().toString();
        int minutos = Integer.parseInt(etMinutos.getText().toString());
        double peso = Double.parseDouble(etPeso.getText().toString());
        double imc = Double.parseDouble(etIMC.getText().toString());
        double grasaCorporal = Double.parseDouble(etGrasa.getText().toString());
        registro = new Registro(id, fecha, descripcion, minutos, peso, imc, grasaCorporal);
        boolean resultado = (tipo == 1) ? RegistroGestion.Update(registro) : RegistroGestion.insert(registro);
        if (resultado) {
            Toast.makeText(getApplicationContext(), "Actualizando", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Error al actualizar", Toast.LENGTH_SHORT).show();
        }
        finish();//hace un back
    }

    private void procesoEliminar(){
        int id=registro.getId();
        if (RegistroGestion.Delete(id)) {
            Toast.makeText(this, "Eliminado", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al eliminar", Toast.LENGTH_SHORT).show();
        }
        finish();//hace un back
    }
}