package com.example.trab_e_commerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class CadastroProduto extends AppCompatActivity {


    EditText nome, quantidade, valor, url;
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
        url = findViewById(R.id.url);
    }

    private void setupButtons() {
        btnAdd = findViewById(R.id.btnNovoProduto);
        btnAdd.setOnClickListener(view -> {
            String nome = this.nome.getText().toString();
            String qtd = this.quantidade.getText().toString();
            String preco = this.valor.getText().toString();
            String img_url = this.url.getText().toString();

            JSONObject jsonBody = new JSONObject();
            try {
                jsonBody.put("name", nome);
                jsonBody.put("stock", qtd);
                jsonBody.put("price", preco);
                jsonBody.put("image_url", img_url);
                String URL = "https://us-central1-trabalho-ecommerce.cloudfunctions.net/api/createProduct";
                String result = ApiCall.post(URL,jsonBody,getApplicationContext());
                Thread.sleep(200);

                Toast.makeText(getApplicationContext(), "Produto cadastrado com sucesso",
                        Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Erro de API",
                        Toast.LENGTH_SHORT).show();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Intent intent = new Intent(CadastroProduto.this, Estoque.class);
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.meumenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemHome:
                Intent homePage = new Intent(CadastroProduto.this, MainActivity.class);
                startActivity(homePage);
                finish();
                return true;
        }
        return false;
    }
}