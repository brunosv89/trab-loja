package com.example.trab_e_commerce.model;


public class Produto {
    private String mNome;
    private String mQuantidade;
    private String mValor;
    private String mImage_url;

    public Produto() { } // Needed for Firebase

    public Produto(String nome, String quantidade, String valor, String image_url) {
        mNome = nome;
        mQuantidade = quantidade;
        mValor = valor;
        mImage_url = image_url;
    }

    public String getName() { return mNome; }

    public void setName(String name) { mNome = name; }

    public String getStock() { return mQuantidade; }

    public void setStock(String qtd) { mQuantidade = qtd; }

    public String getPrice() { return mValor; }

    public void setPrice(String valor) { mValor = valor; }

    public String getImage_url() { return mImage_url; }

    public void setImage_url(String image_url) { mImage_url = image_url; }

}