package com.example.canuwashme;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;

public class CitasAgendadas extends AppCompatActivity {

    private EditText editTextBuscar;
    private ListView listViewCitas;
    private FirebaseFirestore db;
    private List<String> citasList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas_agendadas);

        db = FirebaseFirestore.getInstance();
        editTextBuscar = findViewById(R.id.editTextBuscar);
        listViewCitas = findViewById(R.id.listViewCitas);
        citasList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, citasList);
        listViewCitas.setAdapter(adapter);

        obtenerCitasDesdeFirestore();

        editTextBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void obtenerCitasDesdeFirestore() {
        db.collection("formularios")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            String nombreCliente = documentSnapshot.getString("nombreCliente");
                            String cita = nombreCliente + ": " + documentSnapshot.getData();
                            citasList.add(cita);
                        }
                        adapter.notifyDataSetChanged();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CitasAgendadas.this, "Error al obtener citas", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
