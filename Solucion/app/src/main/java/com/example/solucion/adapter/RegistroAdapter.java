package com.example.solucion.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.solucion.R;
import com.example.solucion.gestion.RegistroGestion;
import com.example.solucion.modelo.Registro;

import java.util.List;

public class RegistroAdapter extends RecyclerView.Adapter<RegistroAdapter.RegistroViewHolder>
        implements View.OnClickListener {

    private View.OnClickListener listener;

    public RegistroAdapter(View.OnClickListener listener) {
        this.listener = listener;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }


    private List<Registro> lista;

    public RegistroAdapter(List<Registro> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public RegistroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_registros, parent, false);
        vista.setOnClickListener(this);
        return new RegistroViewHolder(vista);
    }

    public void refresh() {
        lista = RegistroGestion.getRegistros();
    }

    @Override
    public void onBindViewHolder(@NonNull RegistroViewHolder holder, int position) {
        holder.tvId.setText("" + lista.get(position).getId());
        holder.tvFecha.setText("" + lista.get(position).getFecha());
        holder.tvDescripcion.setText("" + lista.get(position).getDescripcion());
        holder.tvMinutos.setText("" + lista.get(position).getMinutos());
        holder.tvPeso.setText("" + lista.get(position).getPeso());
        holder.tvIMC.setText("" + lista.get(position).getImc());
        holder.tvGrasaCorporal.setText("" + lista.get(position).getGrasacorporal());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


    public class RegistroViewHolder extends RecyclerView.ViewHolder {

        public TextView tvId;
        public TextView tvFecha;
        public TextView tvDescripcion;
        public TextView tvMinutos;
        public TextView tvPeso;
        public TextView tvIMC;
        public TextView tvGrasaCorporal;

        public RegistroViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvId = itemView.findViewById(R.id.tvID_Card);
            this.tvFecha = itemView.findViewById(R.id.tvFecha_Card);
            this.tvDescripcion = itemView.findViewById(R.id.tvDescripcion_Card);
            this.tvMinutos = itemView.findViewById(R.id.tvMinutos_Card);
            this.tvPeso = itemView.findViewById(R.id.tvPeso_Card);
            this.tvIMC = itemView.findViewById(R.id.tvIMC_Card);
            this.tvGrasaCorporal = itemView.findViewById(R.id.tvGrasaCorporal_Card);
        }
    }
}
