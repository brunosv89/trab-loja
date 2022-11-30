package com.example.trab_e_commerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trab_e_commerce.model.Produto;
import com.example.trab_e_commerce.utils.DownloadImageTask;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;



public class Estoque extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirestoreRecyclerAdapter adapter;
    TextView txtTotalVendas;
    Button btnNovoProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estoque);

        setupWidgets();
        setupButtons();
        loadProductList();

    }

    private void loadProductList() {
        Query query = db
                .collection("product")
                .orderBy("price");

        FirestoreRecyclerOptions<Produto> options = new FirestoreRecyclerOptions.Builder<Produto>()
                .setQuery(query, Produto.class)
                .build();


        adapter = new FirestoreRecyclerAdapter<Produto, ViewHolder>(options) {
            @Override
            public void onBindViewHolder(ViewHolder holder, int position, Produto model) {
                holder.bind(model);
            }

            @Override
            public ViewHolder onCreateViewHolder(ViewGroup group, int i) {
                // Create a new instance of the ViewHolder, in this case we are using a custom
                // layout called R.layout.message for each item
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.produto, group, false);

                return new ViewHolder(view);
            }

        };
        recyclerView.setAdapter(adapter);

    }

    private void setupWidgets() {
        txtTotalVendas = findViewById(R.id.totalVendas);
        linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView = findViewById(R.id.listaEstoque);
        recyclerView.setLayoutManager(linearLayoutManager);


    }

    private void setupButtons() {
        btnNovoProduto = findViewById(R.id.btnNovoProduto);
        btnNovoProduto.setOnClickListener(view -> {
            Intent intent = new Intent(Estoque.this, CadastroProduto.class);
            startActivity(intent);
        });
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
                Intent homePage = new Intent(Estoque.this, MainActivity.class);
                startActivity(homePage);
                return true;
        }
        return false;
    }



    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView price;
        TextView stock;
        ImageView image;

        public ViewHolder(@org.checkerframework.checker.nullness.qual.NonNull View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.produtoNome);
            price = itemView.findViewById(R.id.produtoValor);
            stock = itemView.findViewById(R.id.produtoEstoque);
            image = itemView.findViewById(R.id.img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String nomeDoProduto = name.getText().toString();
                    String valorDoProduto = price.getText().toString();
                    Toast.makeText(Estoque.this, nomeDoProduto + ": " + valorDoProduto, Toast.LENGTH_LONG).show();
                }
            });
        }

        public void bind(Produto produto){
            name.setText(produto.getName());
            price.setText("R$: " + produto.getPrice());
            stock.setText(produto.getStock());

            //image config
            String imageurl = produto.getImage_url();
            System.out.println(imageurl);

            new DownloadImageTask((ImageView) image)
                    .execute(imageurl);

        }


    }
}