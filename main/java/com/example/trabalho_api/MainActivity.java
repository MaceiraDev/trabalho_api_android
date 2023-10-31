package com.example.trabalho_api;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.trabalho_api.datasource.BuscarDadosApi;
import com.example.trabalho_api.models.Cat;

public class MainActivity extends ListActivity {

    private ArrayList<Cat> listaDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            listaDados = new BuscarDadosApi().execute(Config.baseUrl).get();
            ListAdapter adapter = new SimpleAdapter(
                    this,
                    dadosToMap(listaDados),
                    R.layout.listview_modelo,
                    new String[]{"artist_name"},
                    new int[]{R.id.txtNome}
            );
            setListAdapter(adapter);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private List<HashMap<String, String>> dadosToMap(ArrayList<Cat> listaDados) {
        List<HashMap<String, String>> lista = new ArrayList<>();

        for (int i = 0; i < listaDados.size(); i++) {
            HashMap<String, String> item = new HashMap<>();
            item.put("artist_name", listaDados.get(i).artist_name);
            item.put("url", listaDados.get(i).url);

            lista.add(item);
        }

        return lista;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Cat cat = listaDados.get(position);
        Intent tela = new Intent(MainActivity.this, DetalhesActivity.class);
        Bundle parametros = new Bundle();
        parametros.putString("artist_name", cat.artist_name);
        parametros.putString("url", cat.url);
        tela.putExtras(parametros);

        startActivity(tela);
    }
}