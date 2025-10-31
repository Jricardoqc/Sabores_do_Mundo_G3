package com.example.saboresdomundo;

public class Produto {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "nome")
    public String nome;

    @ColumnInfo(name = "categoria")
    public String categoria;

    @ColumnInfo(name = "quantidade")
    public int quantidade;
}
