package com.example.solucion.registro;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.solucion.R;
import com.example.solucion.adapter.RegistroAdapter;
import com.example.solucion.gestion.RegistroGestion;
import com.example.solucion.modelo.Registro;

import java.util.List;


public class RVRegistroFragment extends Fragment {

    private RecyclerView recyclerView;
    private RegistroAdapter registroAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_r_v_registro, container, false);

        recyclerView=root.findViewById(R.id.reciclador);
        recyclerView.setHasFixedSize(true);

        layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        List<Registro> lista= RegistroGestion.getRegistros();
        final List<Registro> listaFinal=lista;

        registroAdapter =new RegistroAdapter(lista);
        registroAdapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posicion=recyclerView.getChildAdapterPosition(v);
                Registro registro = listaFinal.get(posicion);
                Toast.makeText(getContext(),
                        "Es "+registro.getDescripcion(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getContext(),RegistroActivity.class);

                intent.putExtra("registro",registro);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(registroAdapter);
        return root;
    }
    @Override
    public void onResume() {
        super.onResume();
        registroAdapter.refresh();
        registroAdapter.notifyDataSetChanged();
    }
}