
package com.example.trab_e_commerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CadastroUsuario extends AppCompatActivity {

    TextView txtCadUsuario, txtCadSenha, txtConfSenha, txtCadNome;
    Button btnCadastrar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);



        setupWidgets();
        setupButtons();


    }

    private void setupWidgets() {
        btnCadastrar = findViewById(R.id.btnCadastrar);
        txtCadNome = findViewById(R.id.txtCadNome);
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
                    register(txtCadNome.getText().toString(),txtCadUsuario.getText().toString(), txtCadSenha.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "Senhas não conferem", Toast.LENGTH_LONG).show();
                }

            }
        });
    }


    private void register(String nome, String email, String senha){

        if(senha.isEmpty() ||    senha.length() < 6){
            Toast.makeText(getApplicationContext(), "É necessário informar uma senha válida com 6 caracteres",
                    Toast.LENGTH_SHORT).show();
        }
        else if(nome.isEmpty()){
            Toast.makeText(getApplicationContext(), "É necessário informar um nome",
                    Toast.LENGTH_SHORT).show();
        }
        else if (!email.isEmpty()) {
            mAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        FirebaseUser user = mAuth.getCurrentUser();
                        String userId = user.getUid();
                        JSONObject jsonBody = new JSONObject();
                        boolean operationSucceeded = false;
                        try {
                            jsonBody.put("id", userId);
                            jsonBody.put("full_name", nome);
                            jsonBody.put("email", email);
                            String URL = "https://us-central1-trabalho-ecommerce.cloudfunctions.net/api/createUser";
                            String result = ApiCall.post(URL,jsonBody,getApplicationContext());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        Toast.makeText(getApplicationContext(), "Cadastro realizado com sucesso",
                                Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CadastroUsuario.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Cadastro falhou." + task.getException().getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "É necessário informar e-mail válido.",
                    Toast.LENGTH_SHORT).show();
        }
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
                Intent homePage = new Intent(CadastroUsuario.this, MainActivity.class);
                startActivity(homePage);
                return true;
        }
        return false;
    }


}