package com.example.trab_e_commerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FinalizarCompra extends AppCompatActivity {

    TextView txtNomeProduto, txtValorProduto;

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
        Intent intent = getIntent();
        String nome = intent.getStringExtra("nome");
        String valor = intent.getStringExtra("valor");
        txtNomeProduto.setText(nome);
        txtValorProduto.setText(valor);
    }

    private void setupButtons() {
        // faz requisição para diminuir 1 no estoque do produto
    }
}