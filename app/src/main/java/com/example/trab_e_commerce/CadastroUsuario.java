
package com.example.trab_e_commerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CadastroUsuario extends AppCompatActivity {

    TextView txtCadUsuario, txtCadSenha, txtConfSenha;
    Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        btnCadastrar = findViewById(R.id.btnCadastrar);
        setupButtons();

    }

    private void setupButtons() {
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtCadSenha.getText().equals(txtConfSenha.getText())) {
                    System.out.println("sdsdfsdfsdfs");
                    Toast.makeText(getApplicationContext(), "Usuário cadastrado com sucesso", Toast.LENGTH_LONG).show();
                    //Intent intent = new Intent(this, MainActivity.class);
                    //startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Senhas não conferem", Toast.LENGTH_LONG).show();
                }

            }
        });
    }


}