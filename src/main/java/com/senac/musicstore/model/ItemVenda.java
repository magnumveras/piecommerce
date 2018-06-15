package com.senac.musicstore.model;

/**
 *
 * @author geoinformacao
 */
public class ItemVenda {
    private int codigo;
    private int codigovenda;
    private int codigoproduto; 
    private int quantidade;


    public ItemVenda() {
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

    public int getCodigovenda() {
        return codigovenda;
    }

    public void setCodigovenda(int codigovenda) {
        this.codigovenda = codigovenda;
    }
    
    public int getCodigoproduto() {
        return codigoproduto;
    }

    public void setCodigoproduto(int codigoproduto) {
        this.codigoproduto = codigoproduto;
    }
    
    
}
