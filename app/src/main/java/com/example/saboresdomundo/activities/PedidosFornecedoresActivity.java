package com.example.saboresdomundo.activities;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity;

import com.example.saboresdomundo.R;
import com.example.saboresdomundo.database.DatabaseHelper;
import com.example.saboresdomundo.models.Fornecedor;
import com.example.saboresdomundo.models.PedidoFornecedor;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.stream.Collectors;

public class PedidosFornecedoresActivity extends AppCompatActivity {
    DatabaseHelper db;
    FloatingActionButton fabAddFornecedor, fabAddPedido;
    ListView lvFornecedores;
    ListView lvPedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos_fornecedores);
        db = new DatabaseHelper(this);

        lvFornecedores = findViewById(R.id.lvFornecedores);
        lvPedidos = findViewById(R.id.lvPedidos);
        fabAddFornecedor = findViewById(R.id.fabAddFornecedor);
        fabAddPedido = findViewById(R.id.fabAddPedido);

        loadFornecedores();
        loadPedidos();

        fabAddFornecedor.setOnClickListener(v -> showAddFornecedorDialog());
        fabAddPedido.setOnClickListener(v -> showAddPedidoDialog());
    }

    private void loadFornecedores() {
        List<Fornecedor> list = db.getAllFornecedores();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                list.stream().map(f -> f.getId() + " - " + f.getNome()).collect(Collectors.toList()));
        lvFornecedores.setAdapter(adapter);
    }

    private void loadPedidos() {
        List<PedidoFornecedor> list = db.getAllPedidos();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                list.stream().map(p -> p.getId() + " - " + p.getData() + " - " + p.getStatus()).collect(Collectors.toList()));
        lvPedidos.setAdapter(adapter);
    }

    private void showAddFornecedorDialog() {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("Novo Fornecedor");
        View view = getLayoutInflater().inflate(R.layout.dialog_add_fornecedor, null);
        b.setView(view);

        EditText etNome = view.findViewById(R.id.etFornecedorNome);
        EditText etEmail = view.findViewById(R.id.etFornecedorEmail);
        EditText etTel = view.findViewById(R.id.etFornecedorTelefone);

        b.setPositiveButton("Salvar", (d, w) -> {
            String nome = etNome.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String tel = etTel.getText().toString().trim();
            if (!nome.isEmpty()) {
                db.insertFornecedor(new Fornecedor(nome, email, tel));
                loadFornecedores();
            }
        });
        b.setNegativeButton("Cancelar", null);
        b.show();
    }

    private void showAddPedidoDialog() {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("Novo Pedido");
        View view = getLayoutInflater().inflate(R.layout.dialog_add_pedido, null);
        b.setView(view);

        EditText etFornecedorId = view.findViewById(R.id.etPedidoFornecedorId);
        EditText etData = view.findViewById(R.id.etPedidoData);
        EditText etStatus = view.findViewById(R.id.etPedidoStatus);

        b.setPositiveButton("Salvar", (d, w) -> {
            try {
                int fornecedorId = Integer.parseInt(etFornecedorId.getText().toString().trim());
                String data = etData.getText().toString().trim();
                String status = etStatus.getText().toString().trim();
                db.insertPedido(new PedidoFornecedor(fornecedorId, data, status));
                loadPedidos();
            } catch (Exception e) {
                // invalid input
            }
        });
        b.setNegativeButton("Cancelar", null);
        b.show();
    }
}
