package com.example.trab_e_commerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity  {


    Button btnCadastro;
    Button btnLogin;
    EditText txtEmail;
    EditText txtSenha;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        logoutAnyUser();

        setUpButtons();

    }

    private void setUpButtons() {
        txtEmail = findViewById(R.id.txtUsuario);
        txtSenha = findViewById(R.id.txtSenha);


        btnCadastro = findViewById(R.id.btnCadastro);
        btnLogin = findViewById(R.id.btnLogin);

        btnCadastro.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CadastroUsuario.class);
            startActivity(intent);
        });

        btnLogin.setOnClickListener(view -> login());

    }


    private void login() {
        String email = txtEmail.getText().toString();
        String senha = txtSenha.getText().toString();

        if(!(senha.isEmpty() || email.isEmpty())){
            mAuth.signInWithEmailAndPassword(email, senha)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                String userType = "other";
                                if (userType == "admin") {
                                    Intent intent1 = new Intent(MainActivity.this, Compra.class);
                                    startActivity(intent1);
                                } else {
                                    Intent intent2 = new Intent(MainActivity.this, Estoque.class);
                                    startActivity(intent2);
                                }


                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(MainActivity.this, "Dados incorretos",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }else {
            Toast.makeText(MainActivity.this, "campos vazios",
                    Toast.LENGTH_SHORT).show();
        }

    }

    private void logoutAnyUser(){
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            mAuth.signOut();
        }
    }


}