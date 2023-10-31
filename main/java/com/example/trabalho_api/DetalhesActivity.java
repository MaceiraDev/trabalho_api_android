package com.example.trabalho_api;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trabalho_api.datasource.DownloadImagem;

public class DetalhesActivity extends AppCompatActivity {
    TextView txtNome;
    ImageView imagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        txtNome = findViewById(R.id.txtNome);
        imagem = findViewById(R.id.imagem);

        Intent caminhoRecebido = getIntent();

        if (caminhoRecebido != null) {
            Bundle parametros = caminhoRecebido.getExtras();

            if (parametros != null) {
                txtNome.setText(parametros.getString("artist_name"));
                new DownloadImagem(imagem).execute(parametros.getString("url"));
            }
        }
    }
}