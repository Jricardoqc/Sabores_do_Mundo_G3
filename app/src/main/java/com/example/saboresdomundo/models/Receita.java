package com.example.saboresdomundo.models;


public class Receita {
    private int id;
    private String nome;
    private String ingredientes;
    private String instrucoes;

    public Receita(int id, String nome, String ingredientes, String instrucoes) {
        this.id = id;
        this.nome = nome;
        this.ingredientes = ingredientes;
        this.instrucoes = instrucoes;
    }

    public Receita(String nome, String ingredientes, String instrucoes) {
        this.nome = nome;
        this.ingredientes = ingredientes;
        this.instrucoes = instrucoes;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getIngredientes() { return ingredientes; }
    public String getInstrucoes() { return instrucoes; }
}

