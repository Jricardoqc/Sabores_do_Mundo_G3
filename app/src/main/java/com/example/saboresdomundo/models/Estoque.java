package com.example.saboresdomundo.models;


public class Estoque {
    private int id;
    private int produtoId;
    private int quantidadeAtual;
    private int limiteMinimo;

    public Estoque(int id, int produtoId, int quantidadeAtual, int limiteMinimo) {
        this.id = id;
        this.produtoId = produtoId;
        this.quantidadeAtual = quantidadeAtual;
        this.limiteMinimo = limiteMinimo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public int getQuantidadeAtual() {
        return quantidadeAtual;
    }

    public void setQuantidadeAtual(int quantidadeAtual) {
        this.quantidadeAtual = quantidadeAtual;
    }

    public int getLimiteMinimo() {
        return limiteMinimo;
    }

    public void setLimiteMinimo(int limiteMinimo) {
        this.limiteMinimo = limiteMinimo;
    }
}

