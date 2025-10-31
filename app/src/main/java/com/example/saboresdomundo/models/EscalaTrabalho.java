package com.example.saboresdomundo.models;


public class EscalaTrabalho {
    private int id;
    private int funcionarioId;
    private String diaSemana;
    private String horario;

    public EscalaTrabalho(int id, int funcionarioId, String diaSemana, String horario) {
        this.id = id;
        this.funcionarioId = funcionarioId;
        this.diaSemana = diaSemana;
        this.horario = horario;
    }
}

