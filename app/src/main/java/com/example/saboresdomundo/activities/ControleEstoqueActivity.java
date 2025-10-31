package com.example.saboresdomundo.activities;


import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saboresdomundo.R;
import com.example.saboresdomundo.adapters.ProdutoAdapter;
import com.example.saboresdomundo.database.DatabaseHelper;
import com.example.saboresdomundo.models.Produto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ControleEstoqueActivity extends AppCompatActivity {
    DatabaseHelper db;
    RecyclerView rv;
    FloatingActionButton fab;
    ProdutoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controle_estoque);
        db = new DatabaseHelper(this);

        rv = findViewById(R.id.rvProdutos);
        rv.setLayoutManager(new LinearLayoutManager(this));
        fab = findViewById(R.id.fabAddProduto);
        loadProdutos();

        fab.setOnClickListener(v -> showAddProdutoDialog());
    }

    private void loadProdutos() {
        List<Produto> produtos = db.getAllProdutos();
        adapter = new ProdutoAdapter(produtos);
        rv.setAdapter(adapter);
    }

    private void showAddProdutoDialog() {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("Novo Produto");
        View view = getLayoutInflater().inflate(R.layout.dialog_add_produto, null);
        b.setView(view);

        EditText etNome = view.findViewById(R.id.etProdutoNome);
        EditText etQtd = view.findViewById(R.id.etProdutoQtd);
        EditText etUn = view.findViewById(R.id.etProdutoUnidade);
        EditText etPreco = view.findViewById(R.id.etProdutoPreco);

        b.setPositiveButton("Salvar", (d, w) -> {
            try {
                String nome = etNome.getText().toString().trim();
                int qtd = Integer.parseInt(etQtd.getText().toString().trim());
                String un = etUn.getText().toString().trim();
                double preco = Double.parseDouble(etPreco.getText().toString().trim());
                Produto p = new Produto(nome, qtd, un, preco);
                db.insertProduto(p);
                loadProdutos();
            } catch (Exception e) {
                Toast.makeText(this, "Erro: verifique os dados", Toast.LENGTH_SHORT).show();
            }
        });
        b.setNegativeButton("Cancelar", null);
        b.show();
    }
}
