package com.example.trab_e_commerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnCadastro;
    //private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCadastro = findViewById(R.id.btnCadastro);
        btnCadastro.setOnClickListener(this);

        //mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCadastro:
                Intent intent = new Intent(this, CadastroUsuario.class);
                startActivity(intent);
                break;
            //case R.id.btnLogin:
        }
    }
}