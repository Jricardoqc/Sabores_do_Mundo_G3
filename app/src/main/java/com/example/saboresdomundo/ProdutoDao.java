package com.example.saboresdomundo;

public interface ProdutoDao {
    void inserirProduto(Produto produto);

    @Query("SELECT * FROM produtos")
    List<Produto> listarProdutos();

    @Update
    void atualizarProduto(Produto produto);

    @Delete
    void deletarProduto(Produto produto);
}
