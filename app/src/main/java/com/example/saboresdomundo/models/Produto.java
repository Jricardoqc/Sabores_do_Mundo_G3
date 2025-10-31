package com.example.saboresdomundo.models;

public class Produto {
    private int id;
    private String nome;
    private int quantidade;
    private String unidade;
    private double preco;

    public Produto(int id, String nome, int quantidade, String unidade, double preco) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.unidade = unidade;
        this.preco = preco;
    }

    public Produto(String nome, int quantidade, String unidade, double preco) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.unidade = unidade;
        this.preco = preco;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public int getQuantidade() { return quantidade; }
    public String getUnidade() { return unidade; }
    public double getPreco() { return preco; }

    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
}
