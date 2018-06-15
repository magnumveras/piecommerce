package com.senac.musicstore.model;

/**
 *
 * @author geoinformacao
 */
public class ItemPedido {
    private int codigo;
    private int codigopedido;
    private int codigoproduto; 
    private int quantidade;


    public ItemPedido() {
        super();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigoProduto() {
        return codigoproduto;
    }

    public void setCodigoProduto(int codigoproduto) {
        this.codigoproduto = codigoproduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getCodigopedido() {
        return codigopedido;
    }

    public void setCodigopedido(int codigopedido) {
        this.codigopedido = codigopedido;
    }

    public int getCodigoproduto() {
        return codigoproduto;
    }

    public void setCodigoproduto(int codigoproduto) {
        this.codigoproduto = codigoproduto;
    }
    
    
}
