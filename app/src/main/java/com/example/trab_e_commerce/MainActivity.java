package com.example.trab_e_commerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnCadastro, btnLogin;
    //private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCadastro = findViewById(R.id.btnCadastro);
        btnLogin = findViewById(R.id.btnLogin);
        btnCadastro.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        //mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCadastro:
                Intent intent1 = new Intent(this, CadastroUsuario.class);
                startActivity(intent1);
                break;
            case R.id.btnLogin:
                Toast.makeText(getApplicationContext(), "teste", Toast.LENGTH_LONG).show();
                Intent intent2 = new Intent(this, Compra.class);
                startActivity(intent2);
                break;
        }
    }
}