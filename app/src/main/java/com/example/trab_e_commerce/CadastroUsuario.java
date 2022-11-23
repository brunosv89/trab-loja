package com.example.trab_e_commerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CadastroUsuario extends AppCompatActivity implements View.OnClickListener {

    TextView txtCadUsuario, txtCadSenha, txtConfSenha;
    Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCadastrar:
                if (txtCadSenha.getText() == txtConfSenha.getText()) {
                    //
                    //salva usuário e senha no BD
                    //
                    Toast.makeText(getApplicationContext(), "Usuário cadastrado com sucesso", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Senhas não conferem", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}