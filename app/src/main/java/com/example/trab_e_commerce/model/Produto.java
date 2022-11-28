package com.example.trab_e_commerce.model;


public class Produto {
    private String mNome;
    private String mQuantidade;
    private String mValor;

    public Produto() { } // Needed for Firebase

    public Produto(String nome, String quantidade, String valor) {
        mNome = nome;
        mQuantidade = quantidade;
        mValor = valor;
    }

    public String getName() { return mNome; }

    public void setName(String name) { mNome = name; }

    public String getStock() { return mQuantidade; }

    public void setStock(String qtd) { mQuantidade = qtd; }

    public String getPrice() { return mValor; }

    public void setPrice(String valor) { mValor = valor; }

}