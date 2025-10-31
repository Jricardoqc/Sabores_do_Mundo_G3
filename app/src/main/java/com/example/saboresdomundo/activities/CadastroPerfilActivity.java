package com.example.saboresdomundo.activities;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.saboresdomundo.R;
import com.example.saboresdomundo.database.DatabaseHelper;
import java.util.List;
import android.widget.ArrayAdapter;

public class CadastroPerfilActivity extends AppCompatActivity {
    DatabaseHelper db;
    Button btnAddPerfil;
    ListView lvPerfis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_perfil);
        db = new DatabaseHelper(this);
        btnAddPerfil = findViewById(R.id.btnAddPerfil);
        lvPerfis = findViewById(R.id.lvPerfis);
        loadPerfis();

        btnAddPerfil.setOnClickListener(v -> {
            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setTitle("Novo Perfil");
            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            b.setView(input);
            b.setPositiveButton("Salvar", (d, w) -> {
                String desc = input.getText().toString().trim();
                if (!desc.isEmpty()) {
                    db.insertPerfil(desc);
                    loadPerfis();
                }
            });
            b.setNegativeButton("Cancelar", null);
            b.show();
        });
    }

    private void loadPerfis() {
        List<String> list = db.getAllPerfis();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        lvPerfis.setAdapter(adapter);
    }
}
