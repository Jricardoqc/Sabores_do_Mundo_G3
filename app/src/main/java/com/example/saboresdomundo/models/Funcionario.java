package com.example.saboresdomundo.models;

public class Funcionario {
    private int id;
    private String nome;
    private String cargo;
    private String turno;
    private double horasTrabalhadas;

    public Funcionario(int id, String nome, String cargo, String turno, double horasTrabalhadas) {
        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
        this.turno = turno;
        this.horasTrabalhadas = horasTrabalhadas;
    }

    public Funcionario(String nome, String cargo, String turno) {
        this.nome = nome;
        this.cargo = cargo;
        this.turno = turno;
        this.horasTrabalhadas = 0;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getCargo() { return cargo; }
    public String getTurno() { return turno; }
    public double getHorasTrabalhadas() { return horasTrabalhadas; }
}

