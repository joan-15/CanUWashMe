package com.example.canuwashme;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.view.View;

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
    }

    public void agendarCita(View view) {
        Intent intent = new Intent(this, Pantalla2.class);
        startActivity(intent);
    }
}