package com.example.solucion.documentos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.solucion.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;


public class DocumentosFragment extends Fragment {

    //Variables utilizadas
    private EditText etArchivo;
    private Button btDescarga;
    private Button btBusca;
    private Button btCarga;

    //Referencia Storage utilizadas
    private StorageReference mStorageR;
    private Uri uri;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_documentos, container, false);

        etArchivo = root.findViewById(R.id.etArchivo);
        btDescarga = root.findViewById(R.id.btDescarga);
        btBusca = root.findViewById(R.id.btBuscar);
        btCarga = root.findViewById(R.id.btCargar);

        btDescarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                descargar();
            }
        });

        btBusca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscar();
            }
        });

        btCarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargar();
            }
        });

        return root;
    }

    private void cargar() {

        String archivo = etArchivo.getText().toString();
        if (uri == null) {
            Toast.makeText(getContext(), "Primero debe seleccionar el archivo que desea subir", Toast.LENGTH_SHORT).show();
        } else if (archivo.isEmpty()) {
            Toast.makeText(getContext(), "Primero debe asignar un nombre al archivo que desea subir", Toast.LENGTH_SHORT).show();
        } else {
            mStorageR = FirebaseStorage
                    .getInstance().getReference().child(archivo);
            mStorageR.putFile(uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(getContext(), "Archivo Subido", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), "Error de subida", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private void buscar() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("application/pdf");
        startActivityForResult(intent, 333);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        uri = data.getData();
        Toast.makeText(getContext(), "Se ha seleccionado un archivo", Toast.LENGTH_SHORT).show();

    }


    private void descargar() {
        String archivo = etArchivo.getText().toString();
        mStorageR = FirebaseStorage
                .getInstance().getReference().child(archivo);
        File localFile = null;
        try {
            localFile = File.createTempFile("doc", "pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }
        File finalLocalFile = localFile;
        mStorageR.getFile(localFile)
                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        String archivo = finalLocalFile.getAbsolutePath();
                        Toast.makeText(getContext(), "Se descargo en " + archivo, Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Error Descargando", Toast.LENGTH_SHORT).show();
            }
        });
    }

}