package com.example.saboresdomundo.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.saboresdomundo.models.*;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "sabores_mundo.db";
    private static final int DATABASE_VERSION = 1;

    // table names
    public static final String TABLE_USUARIOS = "usuarios";
    public static final String TABLE_PERFIS = "perfis";
    public static final String TABLE_PRODUTOS = "produtos";
    public static final String TABLE_ESTOQUE = "estoque";
    public static final String TABLE_FUNCIONARIOS = "funcionarios";
    public static final String TABLE_ESCALAS = "escalas";
    public static final String TABLE_FORNECEDORES = "fornecedores";
    public static final String TABLE_PEDIDOS = "pedidos";
    public static final String TABLE_RECEITAS = "receitas";
    public static final String TABLE_CARDAPIO = "cardapio";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String u = "CREATE TABLE " + TABLE_USUARIOS + " (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, perfilId INTEGER)";
        String p = "CREATE TABLE " + TABLE_PERFIS + " (id INTEGER PRIMARY KEY AUTOINCREMENT, descricao TEXT)";
        String prod = "CREATE TABLE " + TABLE_PRODUTOS + " (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, quantidade INTEGER, unidade TEXT, preco REAL)";
        String est = "CREATE TABLE " + TABLE_ESTOQUE + " (id INTEGER PRIMARY KEY AUTOINCREMENT, produtoId INTEGER, quantidadeAtual INTEGER, limiteMinimo INTEGER)";
        String f = "CREATE TABLE " + TABLE_FUNCIONARIOS + " (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, cargo TEXT, turno TEXT, horasTrabalhadas REAL)";
        String esc = "CREATE TABLE " + TABLE_ESCALAS + " (id INTEGER PRIMARY KEY AUTOINCREMENT, funcionarioId INTEGER, diaSemana TEXT, horario TEXT)";
        String forn = "CREATE TABLE " + TABLE_FORNECEDORES + " (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, email TEXT, telefone TEXT)";
        String ped = "CREATE TABLE " + TABLE_PEDIDOS + " (id INTEGER PRIMARY KEY AUTOINCREMENT, fornecedorId INTEGER, data TEXT, status TEXT)";
        String rec = "CREATE TABLE " + TABLE_RECEITAS + " (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, ingredientes TEXT, instrucoes TEXT)";
        String card = "CREATE TABLE " + TABLE_CARDAPIO + " (id INTEGER PRIMARY KEY AUTOINCREMENT, nomePrato TEXT, preco REAL, receitaId INTEGER)";

        db.execSQL(u);
        db.execSQL(p);
        db.execSQL(prod);
        db.execSQL(est);
        db.execSQL(f);
        db.execSQL(esc);
        db.execSQL(forn);
        db.execSQL(ped);
        db.execSQL(rec);
        db.execSQL(card);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERFIS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUTOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ESTOQUE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FUNCIONARIOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ESCALAS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FORNECEDORES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PEDIDOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECEITAS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARDAPIO);
        onCreate(db);
    }

    // ----------------------------
    // PERFIS
    // ----------------------------
    public long insertPerfil(String descricao) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("descricao", descricao);
        long id = db.insert(TABLE_PERFIS, null, cv);
        db.close();
        return id;
    }

    public List<String> getAllPerfis() {
        List<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT id, descricao FROM " + TABLE_PERFIS, null);
        while (c.moveToNext()) {
            list.add(c.getInt(0) + " - " + c.getString(1));
        }
        c.close();
        db.close();
        return list;
    }

    // ----------------------------
    // USUARIOS
    // ----------------------------
    public long insertUsuario(String nome, int perfilId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", nome);
        cv.put("perfilId", perfilId);
        long id = db.insert(TABLE_USUARIOS, null, cv);
        db.close();
        return id;
    }

    // ----------------------------
    // PRODUTOS / ESTOQUE
    // ----------------------------
    public long insertProduto(Produto p) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", p.getNome());
        cv.put("quantidade", p.getQuantidade());
        cv.put("unidade", p.getUnidade());
        cv.put("preco", p.getPreco());
        long id = db.insert(TABLE_PRODUTOS, null, cv);

        // cria registro no estoque para este produto
        ContentValues estCv = new ContentValues();
        estCv.put("produtoId", id);
        estCv.put("quantidadeAtual", p.getQuantidade());
        estCv.put("limiteMinimo", 5); // default
        db.insert(TABLE_ESTOQUE, null, estCv);

        db.close();
        return id;
    }

    public List<Produto> getAllProdutos() {
        List<Produto> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT id, nome, quantidade, unidade, preco FROM " + TABLE_PRODUTOS, null);
        while (c.moveToNext()) {
            Produto p = new Produto(c.getInt(0), c.getString(1), c.getInt(2), c.getString(3), c.getDouble(4));
            list.add(p);
        }
        c.close();
        db.close();
        return list;
    }

    // get estoque item
    public int getEstoqueQuantidade(int produtoId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT quantidadeAtual FROM " + TABLE_ESTOQUE + " WHERE produtoId = ?", new String[]{String.valueOf(produtoId)});
        int q = 0;
        if (c.moveToFirst()) q = c.getInt(0);
        c.close();
        db.close();
        return q;
    }

    public void updateEstoqueQuantidade(int produtoId, int novaQuantidade) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("quantidadeAtual", novaQuantidade);
        db.update(TABLE_ESTOQUE, cv, "produtoId = ?", new String[]{String.valueOf(produtoId)});
        db.close();
    }

    // ----------------------------
    // FUNCIONARIOS / ESCALAS
    // ----------------------------
    public long insertFuncionario(Funcionario f) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", f.getNome());
        cv.put("cargo", f.getCargo());
        cv.put("turno", f.getTurno());
        cv.put("horasTrabalhadas", f.getHorasTrabalhadas());
        long id = db.insert(TABLE_FUNCIONARIOS, null, cv);
        db.close();
        return id;
    }

    public List<Funcionario> getAllFuncionarios() {
        List<Funcionario> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT id, nome, cargo, turno, horasTrabalhadas FROM " + TABLE_FUNCIONARIOS, null);
        while (c.moveToNext()) {
            Funcionario f = new Funcionario(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getDouble(4));
            list.add(f);
        }
        c.close();
        db.close();
        return list;
    }

    // ----------------------------
    // FORNECEDORES / PEDIDOS
    // ----------------------------
    public long insertFornecedor(Fornecedor f) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", f.getNome());
        cv.put("email", f.getEmail());
        cv.put("telefone", f.getTelefone());
        long id = db.insert(TABLE_FORNECEDORES, null, cv);
        db.close();
        return id;
    }

    public List<Fornecedor> getAllFornecedores() {
        List<Fornecedor> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT id, nome, email, telefone FROM " + TABLE_FORNECEDORES, null);
        while (c.moveToNext()) {
            list.add(new Fornecedor(c.getInt(0), c.getString(1), c.getString(2), c.getString(3)));
        }
        c.close();
        db.close();
        return list;
    }

    public long insertPedido(PedidoFornecedor p) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("fornecedorId", p.getFornecedorId());
        cv.put("data", p.getData());
        cv.put("status", p.getStatus());
        long id = db.insert(TABLE_PEDIDOS, null, cv);
        db.close();
        return id;
    }

    public List<PedidoFornecedor> getAllPedidos() {
        List<PedidoFornecedor> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT id, fornecedorId, data, status FROM " + TABLE_PEDIDOS, null);
        while (c.moveToNext()) {
            list.add(new PedidoFornecedor(c.getInt(0), c.getInt(1), c.getString(2), c.getString(3)));
        }
        c.close();
        db.close();
        return list;
    }

    // ----------------------------
    // RECEITAS & CARDAPIO
    // ----------------------------
    public long insertReceita(Receita r) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", r.getNome());
        cv.put("ingredientes", r.getIngredientes());
        cv.put("instrucoes", r.getInstrucoes());
        long id = db.insert(TABLE_RECEITAS, null, cv);
        db.close();
        return id;
    }

    public List<Receita> getAllReceitas() {
        List<Receita> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT id, nome, ingredientes, instrucoes FROM " + TABLE_RECEITAS, null);
        while (c.moveToNext()) {
            list.add(new Receita(c.getInt(0), c.getString(1), c.getString(2), c.getString(3)));
        }
        c.close();
        db.close();
        return list;
    }
}

