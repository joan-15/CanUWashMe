package com.example.canuwashme;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

public class Bienvenida extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);

        // Asignar el título y la descripción
        String titulo = getString(R.string.bienvenida_titulo);
        String descripcion = getString(R.string.bienvenida_descripcion);

        TextView tvTitulo = findViewById(R.id.tvTitulo);
        TextView tvDescripcion = findViewById(R.id.tvDescripcion);

        tvTitulo.setText(titulo);
        tvDescripcion.setText(descripcion);

        // Mostrar la imagen de la empresa
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.logo);
        // Asegúrate de reemplazar "tu_imagen_de_empresa" con el nombre de tu imagen en el directorio drawable
    }

    // Método para manejar el click del botón
    public void agendarCita(View view) {
        Intent intent = new Intent(this, Pantalla2.class);
        startActivity(intent);
    }
}
