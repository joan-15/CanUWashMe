package com.example.canuwashme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

public class Bienvenida extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);

        String titulo = getString(R.string.bienvenida_titulo);
        String descripcion = getString(R.string.bienvenida_descripcion);

        TextView tvTitulo = findViewById(R.id.tvTitulo);
        TextView tvDescripcion = findViewById(R.id.tvDescripcion);

        tvTitulo.setText(titulo);
        tvDescripcion.setText(descripcion);

        RoundedImageView imageView = findViewById(R.id.imageView);
        Picasso.get()
                .load(R.drawable.logo)
                .fit()
                .centerCrop()
                .into(imageView);

        // Obtener el nombre de usuario y apodo pasado desde la actividad de inicio de sesión
        String username = getIntent().getStringExtra("email");
        String nickname = getIntent().getStringExtra("nickname");
        // Mostrar un mensaje de bienvenida personalizado con el apodo
        TextView tvSaludo = findViewById(R.id.tvSaludo);
        tvSaludo.setText("¡Bienvenido " + nickname + "! Te presentamos CanUWashMe!");
    }

    public void agendarCita(View view) {
        Intent intent = new Intent(this, Pantalla2.class);
        startActivity(intent);
    }

    public void verCitasAgendadas(View view) {
        Intent intent = new Intent(this, CitasAgendadas.class);
        startActivity(intent);
    }
}
