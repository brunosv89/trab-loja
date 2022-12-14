package com.example.trab_e_commerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity  {


    Button btnCadastro;
    Button btnLogin;
    EditText txtEmail;
    EditText txtSenha;

    private FirebaseAuth mAuth;
    private  FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        logoutAnyUser();

        setUpButtons();

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
                Intent homePage = new Intent(MainActivity.this, MainActivity.class);
                startActivity(homePage);
                finish();
                return true;
        }
        return false;
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
                                DocumentReference userData = db.collection("user").document(user.getUid());

                                userData.get()
                                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                            @Override
                                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                if (documentSnapshot.exists()) {
                                                    String userType = documentSnapshot.getString("user_type");
                                                    if (!userType.equals("admin")) {
                                                        Intent intent1 = new Intent(MainActivity.this, Compra.class);
                                                        startActivity(intent1);
                                                    } else {
                                                        Intent intent2 = new Intent(MainActivity.this, Estoque.class);
                                                        startActivity(intent2);
                                                    }
                                                }
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(MainActivity.this, "Erro de API", Toast.LENGTH_SHORT).show();
                                            }
                                        });



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