package com.example.trabalho_api.datasource;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import com.example.trabalho_api.models.Cat;

public class BuscarDadosApi extends AsyncTask<String, Void, ArrayList<Cat>> {

    @Override
    protected ArrayList<Cat> doInBackground(String... strings) {
        ArrayList<Cat> listaDados = new ArrayList<>();

        try {
            String link = strings[0];

            URL url = new URL(link);

            URLConnection connection = url.openConnection();

            InputStream stream = connection.getInputStream();

            InputStreamReader inputStreamReader = new InputStreamReader(stream);

            BufferedReader reader = new BufferedReader(inputStreamReader);

            String dados = "";
            String linha;

            while ((linha = reader.readLine()) != null) {
                dados += linha;
            }

            JSONObject json = new JSONObject(dados);

            JSONArray lista = new JSONArray(json.getString("results"));

            for (int i = 0; i < lista.length(); i++) {
                JSONObject item = (JSONObject) lista.get(i);

                Cat cat = new Cat();
                cat.artist_name = item.getString("artist_name");
                cat.url = item.getString("url");

                listaDados.add(cat);
            }
        } catch (Exception ex) {
        }

        return listaDados;
    }
}
