package com.example.trab_e_commerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trab_e_commerce.utils.DownloadImageTask;

import org.json.JSONException;
import org.json.JSONObject;

public class FinalizarCompra extends AppCompatActivity {

    TextView txtNomeProduto, txtValorProduto;
    Button compraButton;
    ImageView productImage;
    String productId;
    String valor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalizar_compra);

        setupWidgets();
        setupButtons();
    }

    private void setupWidgets() {
        txtNomeProduto = findViewById(R.id.nomeProduto);
        txtValorProduto = findViewById(R.id.valorProduto);
        compraButton = findViewById(R.id.btnFinalizar);
        productImage = findViewById(R.id.finalImage);
        Intent intent = getIntent();
        String nome = intent.getStringExtra("nome");
        String url = intent.getStringExtra("urlImage");
        valor = intent.getStringExtra("valor");
        productId = intent.getStringExtra("id");

        txtNomeProduto.setText(nome);
        txtValorProduto.setText(valor);

        new DownloadImageTask((ImageView) this.productImage)
                .execute(url);

    }

    private void setupButtons() {
        compraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                String quantity = "1";

                JSONObject jsonBody = new JSONObject();
                try {
                    jsonBody.put("id", productId);
                    jsonBody.put("subtractValue", quantity);
                    System.out.println(valor);
                    jsonBody.put("price", valor);

                    String URL = "https://us-central1-trabalho-ecommerce.cloudfunctions.net/api/productSold";
                    String result = ApiCall.post(URL,jsonBody,getApplicationContext());

                    Toast.makeText(getApplicationContext(), "Produto comprado com sucesso",
                            Toast.LENGTH_SHORT).show();



                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Erro de API",
                            Toast.LENGTH_SHORT).show();
                }
                    finally {
                    Intent intent = new Intent(FinalizarCompra.this, Compra.class);
                    startActivity(intent);
                }
            }
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
                Intent homePage = new Intent(FinalizarCompra.this, MainActivity.class);
                startActivity(homePage);
                finish();
                return true;
        }
        return false;
    }
}