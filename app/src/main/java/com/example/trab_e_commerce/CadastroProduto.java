package com.example.trab_e_commerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class CadastroProduto extends AppCompatActivity {


    EditText nome, quantidade, valor;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produto);


        setupWidgets();
        setupButtons();
    }

    private void setupWidgets() {
        nome = findViewById(R.id.nome);
        quantidade = findViewById(R.id.quantidade);
        valor = findViewById(R.id.valor);

    }

    private void setupButtons() {
        btnAdd = findViewById(R.id.btnNovoProduto);
        btnAdd.setOnClickListener(view -> {
            String nome = this.nome.getText().toString();
            String qtd = this.quantidade.getText().toString();
            String preco = this.valor.getText().toString();

            JSONObject jsonBody = new JSONObject();
            try {
                jsonBody.put("name", nome);
                jsonBody.put("stock", qtd);
                jsonBody.put("price", preco);
                jsonBody.put("image_url", "https://static.vecteezy.com/ti/vetor-gratis/p3/3589716-icone-da-caixa-de-leite-vetor.jpg");
                String URL = "https://us-central1-trabalho-ecommerce.cloudfunctions.net/api/createProduct";
                String result = ApiCall.post(URL,jsonBody,getApplicationContext());

                Toast.makeText(getApplicationContext(), "Produto cadastrado com sucesso",
                        Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Erro de API",
                        Toast.LENGTH_SHORT).show();
            }

            Intent intent = new Intent(CadastroProduto.this, Estoque.class);
            startActivity(intent);
        });
    }
}