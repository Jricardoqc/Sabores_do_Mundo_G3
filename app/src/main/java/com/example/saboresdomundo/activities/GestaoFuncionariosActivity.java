package com.example.saboresdomundo.activities;



import android.app.AlertDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saboresdomundo.R;
import com.example.saboresdomundo.adapters.FuncionarioAdapter;
import com.example.saboresdomundo.database.DatabaseHelper;
import com.example.saboresdomundo.models.Funcionario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class GestaoFuncionariosActivity extends AppCompatActivity {
    DatabaseHelper db;
    RecyclerView rv;
    FloatingActionButton fab;
    FuncionarioAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestao_funcionarios);
        db = new DatabaseHelper(this);
        rv = findViewById(R.id.rvFuncionarios);
        rv.setLayoutManager(new LinearLayoutManager(this));
        fab = findViewById(R.id.fabAddFuncionario);
        loadFuncionarios();

        fab.setOnClickListener(v -> showAddFuncionarioDialog());
    }

    private void loadFuncionarios() {
        List<Funcionario> list = db.getAllFuncionarios();
        adapter = new FuncionarioAdapter(list);
        rv.setAdapter(adapter);
    }

    private void showAddFuncionarioDialog() {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("Novo Funcionário");
        View view = getLayoutInflater().inflate(R.layout.dialog_add_funcionario, null);
        b.setView(view);

        EditText etNome = view.findViewById(R.id.etFuncionarioNome);
        EditText etCargo = view.findViewById(R.id.etFuncionarioCargo);
        EditText etTurno = view.findViewById(R.id.etFuncionarioTurno);

        b.setPositiveButton("Salvar", (d, w) -> {
            String nome = etNome.getText().toString().trim();
            String cargo = etCargo.getText().toString().trim();
            String turno = etTurno.getText().toString().trim();
            if (!nome.isEmpty()) {
                Funcionario f = new Funcionario(nome, cargo, turno);
                db.insertFuncionario(f);
                loadFuncionarios();
            }
        });
        b.setNegativeButton("Cancelar", null);
        b.show();
    }
}
