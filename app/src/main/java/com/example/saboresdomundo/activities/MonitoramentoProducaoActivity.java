package com.example.saboresdomundo.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.saboresdomundo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class LcFAfaNpjEG2rCQEhuE1sA1hLU4gnk6JR2 extends AppCompatActivity {

    private ListView lvReceitas;
    private FloatingActionButton fabAddReceita;
    private ArrayList<String> listaPratos;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_monitoramento_producao);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.lvReceitas), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lvReceitas = findViewById(R.id.lvReceitas);
        fabAddReceita = findViewById(R.id.fabAddReceita);

        // Lista inicial de pratos
        listaPratos = new ArrayList<>();
        listaPratos.add("Feijoada Brasileira - Em preparo");
        listaPratos.add("Sushi Japonês - Pronto");
        listaPratos.add("Curry Indiano - Em preparo");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaPratos);
        lvReceitas.setAdapter(adapter);

        // Clique no item da lista → alterna o status
        lvReceitas.setOnItemClickListener((parent, view, position, id) -> {
            String prato = listaPratos.get(position);
            if (prato.contains("Em preparo")) {
                prato = prato.replace("Em preparo", "Pronto ✅");
                Toast.makeText(this, "Prato finalizado!", Toast.LENGTH_SHORT).show();
            } else {
                prato = prato.replace("Pronto ✅", "Em preparo");
                Toast.makeText(this, "Voltando para preparo.", Toast.LENGTH_SHORT).show();
            }
            listaPratos.set(position, prato);
            adapter.notifyDataSetChanged();
        });

        // Botão flutuante → adiciona novo prato
        fabAddReceita.setOnClickListener(v -> mostrarDialogAdicionar());
    }

    private void mostrarDialogAdicionar() {
        final View dialogView = getLayoutInflater().inflate(android.R.layout.simple_list_item_1, null);
        new AlertDialog.Builder(this)
                .setTitle("Adicionar novo prato")
                .setMessage("Deseja adicionar um novo prato à lista?")
                .setPositiveButton("Sim", (dialog, which) -> {
                    listaPratos.add("Novo prato - Em preparo");
                    adapter.notifyDataSetChanged();
                    Toast.makeText(this, "Novo prato adicionado!", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }
}
