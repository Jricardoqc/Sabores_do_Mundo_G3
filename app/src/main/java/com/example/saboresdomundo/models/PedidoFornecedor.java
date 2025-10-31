package com.example.saboresdomundo.models;


public class PedidoFornecedor {
    private int id;
    private int fornecedorId;
    private String data;
    private String status;

    public PedidoFornecedor(int id, int fornecedorId, String data, String status) {
        this.id = id;
        this.fornecedorId = fornecedorId;
        this.data = data;
        this.status = status;
    }

    public PedidoFornecedor(int fornecedorId, String data, String status) {
        this.fornecedorId = fornecedorId;
        this.data = data;
        this.status = status;
    }

    public int getId() { return id; }
    public int getFornecedorId() { return fornecedorId; }
    public String getData() { return data; }
    public String getStatus() { return status; }
}
