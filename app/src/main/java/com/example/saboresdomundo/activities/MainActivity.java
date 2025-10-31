package com.example.saboresdomundo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.saboresdomundo.R;

public class MainActivity extends AppCompatActivity {
    Button btnPerfis, btnEstoque, btnFuncionarios, btnProducao, btnPedidos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPerfis = findViewById(R.id.btnPerfis);
        btnEstoque = findViewById(R.id.btnEstoque);
        btnFuncionarios = findViewById(R.id.btnFuncionarios);
        btnProducao = findViewById(R.id.btnProducao);
        btnPedidos = findViewById(R.id.btnPedidos);

        btnPerfis.setOnClickListener(v -> startActivity(new Intent(this, CadastroPerfilActivity.class)));
        btnEstoque.setOnClickListener(v -> startActivity(new Intent(this, ControleEstoqueActivity.class)));
        btnFuncionarios.setOnClickListener(v -> startActivity(new Intent(this, GestaoFuncionariosActivity.class)));
        btnProducao.setOnClickListener(v -> startActivity(new Intent(this, MonitoramentoProducaoActivity.class)));
        btnPedidos.setOnClickListener(v -> startActivity(new Intent(this, PedidosFornecedoresActivity.class)));
    }
}