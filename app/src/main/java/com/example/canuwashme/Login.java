package com.example.canuwashme;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextNickname; // Agregado
    private Button buttonRegister;
    private Button buttonLogin;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextNickname = findViewById(R.id.editTextNickname); // Agregado
        buttonRegister = findViewById(R.id.buttonRegister);
        buttonLogin = findViewById(R.id.buttonLogin);

        db = FirebaseFirestore.getInstance();

        buttonRegister.setOnClickListener(v -> registerUser());
        buttonLogin.setOnClickListener(v -> loginUser());
    }

    private void registerUser() {
        String email = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String nickname = editTextNickname.getText().toString().trim(); // Agregado

        if (email.isEmpty() || password.isEmpty() || nickname.isEmpty()) { // Modificado
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new User(email, password, nickname); // Modificado

        db.collection("users")
                .add(user)
                .addOnSuccessListener(documentReference -> {
                    Log.d("LoginActivity", "Usuario registrado con ID: " + documentReference.getId());
                    Toast.makeText(this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();
                    // Pasar el nickname a la pantalla de bienvenida
                    Intent intent = new Intent(Login.this, Bienvenida.class);
                    intent.putExtra("nickname", nickname);
                    startActivity(intent);
                    finish();
                })
                .addOnFailureListener(e -> {
                    Log.w("LoginActivity", "Error al registrar usuario", e);
                    Toast.makeText(this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
                });
    }

    private void loginUser() {
        String email = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        db.collection("users")
                .whereEqualTo("email", email)
                .whereEqualTo("password", password)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (DocumentSnapshot document : task.getResult()) {
                            Log.d("LoginActivity", document.getId() + " => " + document.getData());
                            Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                            // Pasar el nickname a la pantalla de bienvenida
                            String nickname = document.getString("nickname");
                            Intent intent = new Intent(Login.this, Bienvenida.class);
                            intent.putExtra("nickname", nickname);
                            startActivity(intent);
                            finish();
                            return;
                        }
                        Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.w("LoginActivity", "Error getting documents.", task.getException());
                        Toast.makeText(this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
