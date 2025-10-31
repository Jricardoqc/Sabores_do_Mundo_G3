package com.example.saboresdomundo.models;


public class Cardapio {
    private int id;
    private String nomePrato;
    private double preco;
    private int receitaId;

    public Cardapio(int id, String nomePrato, double preco, int receitaId) {
        this.id = id;
        this.nomePrato = nomePrato;
        this.preco = preco;
        this.receitaId = receitaId;
    }
}

