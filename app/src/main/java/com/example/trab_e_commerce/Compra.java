package com.example.trab_e_commerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SimpleTimeZone;

public class Compra extends AppCompatActivity {

    String [] de = {"produto", "estoque", "valor"};
    int [] para = {R.id.produtoNome, R.id.produtoEstoque, R.id.produtoValor};
    ListView listaProdutos;
    Button btnComprar;
    List<Map<String, Object>> lista;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);

        loadList();
        setupButtons();
    }

    private void loadList() {
        String [] produto = {"Teclado", "Mouse", "Headset"};
        int [] estoque = {14, 22, 8};
        String [] valor = {"R$ 120,00", "R$ 53,50", "R$199,00"};

        listaProdutos = findViewById(R.id.listaProdutos);
        lista = new ArrayList<>();

        for (int i=0; i < produto.length; i++) {
            Map<String, Object> mapa = new HashMap<>();
            mapa.put(de[0], produto[i]);
            mapa.put(de[1], estoque[i]);
            mapa.put(de[2], valor[i]);
            lista.add(mapa);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, lista, R.layout.produto, de, para);
        listaProdutos.setAdapter(adapter);

        listaProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Map<String, Object> selecao = lista.get(i);
                String nomeDoProduto = selecao.get("produto").toString();
                String valorDoProduto = selecao.get("valor").toString();
                Toast.makeText(Compra.this, nomeDoProduto + valorDoProduto, Toast.LENGTH_LONG).show();
            }
        });
    }


    private void setupButtons() {

        btnComprar = findViewById(R.id.btnComp);

        btnComprar.setOnClickListener(view -> {
            Intent intent = new Intent(Compra.this, FinalizarCompra.class);
            intent.putExtra("nome", "Teclado Hello Kit");
            intent.putExtra("valor", "R$ 39.90");
            startActivity(intent);
        });
    }
}