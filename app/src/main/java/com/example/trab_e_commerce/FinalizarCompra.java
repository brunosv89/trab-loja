package com.example.trab_e_commerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FinalizarCompra extends AppCompatActivity {

    Button btnFinalizar;
    TextView txtNomeProduto, txtValorProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalizar_compra);


        btnFinalizar.findViewById(R.id.btnFinalizar);
        setupWidgets();
        setupButtons();

    }

    private void setupWidgets() {
        txtNomeProduto.findViewById(R.id.nomeProduto);
        txtValorProduto.findViewById(R.id.valorProduto);
        Intent intent = getIntent();
        //String nome = intent.getStringExtra("nome");
        //Float valor = intent.getFloatExtra("valor");
        //txtValorProduto = nome;
        //txtValorProduto = valor;
    }

    private void setupButtons() {
        btnFinalizar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // decrementar uma quantidade do estoque do produto no BD
            }
        });
    }
}