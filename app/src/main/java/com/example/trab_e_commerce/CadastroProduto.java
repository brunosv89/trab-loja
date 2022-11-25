package com.example.trab_e_commerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

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
            //
            //fazer a requisição para gravar novo produto no estoque
            //
            Intent intent = new Intent(CadastroProduto.this, Estoque.class);
            startActivity(intent);
        });
    }
}