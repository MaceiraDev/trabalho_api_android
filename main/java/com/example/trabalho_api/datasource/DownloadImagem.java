package com.example.trabalho_api.datasource;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class DownloadImagem extends AsyncTask<String, Void, Bitmap> {
    ImageView imagem;
    public DownloadImagem(ImageView imagem) {
        this.imagem = imagem;
    }
    @Override
    protected Bitmap doInBackground(String... strings) {
        String urlImagem = strings[0];
        Bitmap bitmap = null;

        try {
            InputStream stream = new URL(urlImagem).openStream();

            bitmap = BitmapFactory.decodeStream(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return bitmap;
    }
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        imagem.setImageBitmap(bitmap);
    }
}