package com.example.canuwashme;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class Pantalla2 extends AppCompatActivity {
    private EditText editTextNombreCliente, editTextTelefono, editTextDireccion,
            editTextNombreMascota, editTextEdad, editTextPeso, editTextServicios, editTextObservaciones;
    private Spinner spinnerEspecie, spinnerRaza, spinnerSexo;
    private Button buttonContinuar, buttonFinalizarCita;
    private int currentQuestion = 1;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla2);


        db = FirebaseFirestore.getInstance();


        editTextNombreCliente = findViewById(R.id.editTextNombreCliente);
        editTextTelefono = findViewById(R.id.editTextTelefono);
        editTextDireccion = findViewById(R.id.editTextDireccion);
        editTextNombreMascota = findViewById(R.id.editTextNombreMascota);
        spinnerEspecie = findViewById(R.id.spinnerEspecie);
        spinnerRaza = findViewById(R.id.spinnerRaza);
        editTextEdad = findViewById(R.id.editTextEdad);
        editTextPeso = findViewById(R.id.editTextPeso);
        spinnerSexo = findViewById(R.id.spinnerSexo);
        editTextServicios = findViewById(R.id.editTextServicios);
        editTextObservaciones = findViewById(R.id.editTextObservaciones);
        buttonContinuar = findViewById(R.id.buttonContinuar);
        buttonFinalizarCita = findViewById(R.id.buttonFinalizarCita);


        ocultarTodasLasPreguntas();
        findViewById(R.id.textViewNombreCliente).setVisibility(View.VISIBLE);
        editTextNombreCliente.setVisibility(View.VISIBLE);


        setupSpinners();


        buttonContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (currentQuestion) {
                    case 1:

                        String nombreCliente = editTextNombreCliente.getText().toString().trim();
                        if (nombreCliente.isEmpty()) {
                            Toast.makeText(Pantalla2.this, "Por favor, ingrese el nombre del cliente", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        findViewById(R.id.textViewNombreCliente).setVisibility(View.GONE);
                        editTextNombreCliente.setVisibility(View.GONE);
                        findViewById(R.id.textViewTelefono).setVisibility(View.VISIBLE);
                        editTextTelefono.setVisibility(View.VISIBLE);
                        break;
                    case 2:

                        String telefono = editTextTelefono.getText().toString().trim();
                        if (telefono.isEmpty()) {
                            Toast.makeText(Pantalla2.this, "Por favor, ingrese el teléfono de contacto", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        findViewById(R.id.textViewTelefono).setVisibility(View.GONE);
                        editTextTelefono.setVisibility(View.GONE);
                        findViewById(R.id.textViewDireccion).setVisibility(View.VISIBLE);
                        editTextDireccion.setVisibility(View.VISIBLE);
                        break;
                    case 3:

                        String direccion = editTextDireccion.getText().toString().trim();
                        if (direccion.isEmpty()) {
                            Toast.makeText(Pantalla2.this, "Por favor, ingrese la dirección completa del domicilio", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        findViewById(R.id.textViewDireccion).setVisibility(View.GONE);
                        editTextDireccion.setVisibility(View.GONE);
                        findViewById(R.id.textViewNombreMascota).setVisibility(View.VISIBLE);
                        editTextNombreMascota.setVisibility(View.VISIBLE);
                        break;
                    case 4:

                        String nombreMascota = editTextNombreMascota.getText().toString().trim();
                        if (nombreMascota.isEmpty()) {
                            Toast.makeText(Pantalla2.this, "Por favor, ingrese el nombre de la mascota", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        findViewById(R.id.textViewNombreMascota).setVisibility(View.GONE);
                        editTextNombreMascota.setVisibility(View.GONE);
                        findViewById(R.id.textViewEspecie).setVisibility(View.VISIBLE);
                        spinnerEspecie.setVisibility(View.VISIBLE);
                        break;
                    case 5:

                        findViewById(R.id.textViewEspecie).setVisibility(View.GONE);
                        spinnerEspecie.setVisibility(View.GONE);
                        findViewById(R.id.textViewRaza).setVisibility(View.VISIBLE);
                        spinnerRaza.setVisibility(View.VISIBLE);
                        break;
                    case 6:

                        findViewById(R.id.textViewRaza).setVisibility(View.GONE);
                        spinnerRaza.setVisibility(View.GONE);
                        findViewById(R.id.textViewEdad).setVisibility(View.VISIBLE);
                        editTextEdad.setVisibility(View.VISIBLE);
                        break;
                    case 7:

                        String edad = editTextEdad.getText().toString().trim();
                        if (edad.isEmpty()) {
                            Toast.makeText(Pantalla2.this, "Por favor, ingrese la edad de la mascota", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        findViewById(R.id.textViewEdad).setVisibility(View.GONE);
                        editTextEdad.setVisibility(View.GONE);
                        findViewById(R.id.textViewPeso).setVisibility(View.VISIBLE);
                        editTextPeso.setVisibility(View.VISIBLE);
                        break;
                    case 8:

                        String peso = editTextPeso.getText().toString().trim();
                        if (peso.isEmpty()) {
                            Toast.makeText(Pantalla2.this, "Por favor, ingrese el peso aproximado de la mascota", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        findViewById(R.id.textViewPeso).setVisibility(View.GONE);
                        editTextPeso.setVisibility(View.GONE);
                        findViewById(R.id.textViewSexo).setVisibility(View.VISIBLE);
                        spinnerSexo.setVisibility(View.VISIBLE);
                        break;
                    case 9:

                        findViewById(R.id.textViewSexo).setVisibility(View.GONE);
                        spinnerSexo.setVisibility(View.GONE);
                        findViewById(R.id.textViewServicios).setVisibility(View.VISIBLE);
                        editTextServicios.setVisibility(View.VISIBLE);
                        break;
                    case 10:

                        String servicios = editTextServicios.getText().toString().trim();
                        if (servicios.isEmpty()) {
                            Toast.makeText(Pantalla2.this, "Por favor, ingrese los servicios solicitados", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        findViewById(R.id.textViewServicios).setVisibility(View.GONE);
                        editTextServicios.setVisibility(View.GONE);
                        findViewById(R.id.textViewObservaciones).setVisibility(View.VISIBLE);
                        editTextObservaciones.setVisibility(View.VISIBLE);
                        break;
                    case 11:

                        String observaciones = editTextObservaciones.getText().toString().trim();
                        if (observaciones.isEmpty()) {
                            Toast.makeText(Pantalla2.this, "Por favor, ingrese las observaciones", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        findViewById(R.id.textViewObservaciones).setVisibility(View.GONE);
                        editTextObservaciones.setVisibility(View.GONE);
                        buttonContinuar.setVisibility(View.GONE);
                        buttonFinalizarCita.setVisibility(View.VISIBLE);
                        break;
                }
                currentQuestion++;
            }
        });


        buttonFinalizarCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarDatosEnFirestore();
            }
        });
    }

    private void ocultarTodasLasPreguntas() {
        findViewById(R.id.textViewNombreCliente).setVisibility(View.GONE);
        editTextNombreCliente.setVisibility(View.GONE);
        findViewById(R.id.textViewTelefono).setVisibility(View.GONE);
        editTextTelefono.setVisibility(View.GONE);
        findViewById(R.id.textViewDireccion).setVisibility(View.GONE);
        editTextDireccion.setVisibility(View.GONE);
        findViewById(R.id.textViewNombreMascota).setVisibility(View.GONE);
        editTextNombreMascota.setVisibility(View.GONE);
        findViewById(R.id.textViewEspecie).setVisibility(View.GONE);
        spinnerEspecie.setVisibility(View.GONE);
        findViewById(R.id.textViewRaza).setVisibility(View.GONE);
        spinnerRaza.setVisibility(View.GONE);
        findViewById(R.id.textViewEdad).setVisibility(View.GONE);
        editTextEdad.setVisibility(View.GONE);
        findViewById(R.id.textViewPeso).setVisibility(View.GONE);
        editTextPeso.setVisibility(View.GONE);
        findViewById(R.id.textViewSexo).setVisibility(View.GONE);
        spinnerSexo.setVisibility(View.GONE);
        findViewById(R.id.textViewServicios).setVisibility(View.GONE);
        editTextServicios.setVisibility(View.GONE);
        findViewById(R.id.textViewObservaciones).setVisibility(View.GONE);
        editTextObservaciones.setVisibility(View.GONE);
        buttonContinuar.setVisibility(View.VISIBLE);
        buttonFinalizarCita.setVisibility(View.GONE);
    }

    private void setupSpinners() {

        ArrayAdapter<CharSequence> especieAdapter = ArrayAdapter.createFromResource(this, R.array.species, android.R.layout.simple_spinner_item);
        especieAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEspecie.setAdapter(especieAdapter);


        spinnerEspecie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                actualizarRazas(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter<CharSequence> sexoAdapter = ArrayAdapter.createFromResource(this, R.array.sex, android.R.layout.simple_spinner_item);
        sexoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSexo.setAdapter(sexoAdapter);
    }

    private void actualizarRazas(int especiePosition) {

        int arrayResId;
        switch (especiePosition) {
            case 0:
                arrayResId = R.array.dog_breeds;
                break;
            case 1:
                arrayResId = R.array.cat_breeds;
                break;
            case 2:
            default:
                arrayResId = R.array.empty_array;
                break;
        }
        ArrayAdapter<CharSequence> razaAdapter = ArrayAdapter.createFromResource(this, arrayResId, android.R.layout.simple_spinner_item);
        razaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRaza.setAdapter(razaAdapter);
    }

    private void guardarDatosEnFirestore() {

        String nombreCliente = editTextNombreCliente.getText().toString().trim();
        String telefono = editTextTelefono.getText().toString().trim();
        String direccion = editTextDireccion.getText().toString().trim();
        String nombreMascota = editTextNombreMascota.getText().toString().trim();
        String especie = spinnerEspecie.getSelectedItem().toString();
        String raza = spinnerRaza.getSelectedItem().toString();
        String edad = editTextEdad.getText().toString().trim();
        String peso = editTextPeso.getText().toString().trim();
        String sexo = spinnerSexo.getSelectedItem().toString();
        String servicios = editTextServicios.getText().toString().trim();
        String observaciones = editTextObservaciones.getText().toString().trim();

        if (nombreCliente.isEmpty() || telefono.isEmpty() || direccion.isEmpty() || nombreMascota.isEmpty() ||
                especie.isEmpty() || raza.isEmpty() || edad.isEmpty() || peso.isEmpty() || sexo.isEmpty() ||
                servicios.isEmpty() || observaciones.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }


        Map<String, Object> formulario = new HashMap<>();
        formulario.put("nombreCliente", nombreCliente);
        formulario.put("telefono", telefono);
        formulario.put("direccion", direccion);
        formulario.put("nombreMascota", nombreMascota);
        formulario.put("especie", especie);
        formulario.put("raza", raza);
        formulario.put("edad", edad);
        formulario.put("peso", peso);
        formulario.put("sexo", sexo);
        formulario.put("servicios", servicios);
        formulario.put("observaciones", observaciones);


        db.collection("formularios")
                .add(formulario)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(Pantalla2.this, "Formulario enviado correctamente", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Pantalla2.this, "Error al enviar formulario", Toast.LENGTH_SHORT).show();
                    }
                });
    }}