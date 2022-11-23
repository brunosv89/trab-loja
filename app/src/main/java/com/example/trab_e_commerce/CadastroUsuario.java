
package com.example.trab_e_commerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CadastroUsuario extends AppCompatActivity {

    TextView txtCadUsuario, txtCadSenha, txtConfSenha;
    Button btnCadastrar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        btnCadastrar = findViewById(R.id.btnCadastrar);
        setupWidgets();
        setupButtons();


    }

    private void setupWidgets() {
        txtCadSenha = findViewById(R.id.txtCadSenha);
        txtConfSenha = findViewById(R.id.txtConfSenha);
        txtCadUsuario = findViewById(R.id.txtCadUsuario);
        mAuth = FirebaseAuth.getInstance();
    }

    private void setupButtons() {
        btnCadastrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (txtCadSenha.getText().toString().equals(txtConfSenha.getText().toString())) {
                    login(txtCadUsuario.getText().toString(), txtCadSenha.getText().toString());
                    Toast.makeText(getApplicationContext(), "Usuário cadastrado com sucesso", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Senhas não conferem", Toast.LENGTH_LONG).show();
                }

            }
        });
    }


    private void login(String email, String senha){
        if (!email.isEmpty()) {
            mAuth.createUserWithEmailAndPassword(email, senha)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(getApplicationContext(), "Cadastro realizado com sucesso",
                                        Toast.LENGTH_SHORT).show();
                                //Intent intent = new Intent(CadastroUsuario.this, MainActivity.class);
                                //startActivity(intent);
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(getApplicationContext(), "Cadastro falhou.",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        } else {
            Toast.makeText(getApplicationContext(), "É necessário informar e-mail válido.",
                    Toast.LENGTH_SHORT).show();
        }
    }


}