package com.example.trab_e_commerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trab_e_commerce.model.Produto;
import com.example.trab_e_commerce.utils.DownloadImageTask;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import org.checkerframework.checker.nullness.qual.NonNull;

public class Compra extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private Button btnComprar;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirestoreRecyclerAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);

//        loadList();
        setUp();
        loadProductList();
    }


    private void setUp() {
        linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView = findViewById(R.id.listaProdutos);
        recyclerView.setLayoutManager(linearLayoutManager);

        btnComprar = findViewById(R.id.btnComp);
        btnComprar.setOnClickListener(view -> {
            Intent intent = new Intent(Compra.this, FinalizarCompra.class);
            intent.putExtra("nome", "Teclado Hello Kit");
            intent.putExtra("valor", "R$ 39.90");
            startActivity(intent);
        });



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

        public ViewHolder(@NonNull View itemView){
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
                    Toast.makeText(Compra.this, nomeDoProduto + valorDoProduto, Toast.LENGTH_LONG).show();
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