package com.example.saboresdomundo.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.saboresdomundo.R;
import com.example.saboresdomundo.models.Produto;

import java.util.List;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ViewHolder> {
    private List<Produto> produtos;

    public ProdutoAdapter(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produto, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Produto p = produtos.get(position);
        holder.nome.setText(p.getNome());
        holder.quantidade.setText(String.valueOf(p.getQuantidade()));
        holder.unidade.setText(p.getUnidade());
    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nome, quantidade, unidade;
        public ViewHolder(View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.itemProdutoNome);
            quantidade = itemView.findViewById(R.id.itemProdutoQuantidade);
            unidade = itemView.findViewById(R.id.itemProdutoUnidade);
        }
    }
}
