package com.senac.musicstore.model;

/**
 *
 * @author geoinformacao
 */
public class ItemPedido {
    private int iditempedido;
    private int codigopedido;
    private int codigoproduto; 
    private int quantidade;
    private String nomecategoria;
    private String nomeproduto;
    private String descricaoproduto;
    private Double prevovenda;

    public ItemPedido() {
        super();
    }

    public String getDescricaoproduto() {
        return descricaoproduto;
    }

    public void setDescricaoproduto(String descricaoproduto) {
        this.descricaoproduto = descricaoproduto;
    }
    
    
    public String getNomecategoria() {
        return nomecategoria;
    }

    public void setNomecategoria(String nomecategoria) {
        this.nomecategoria = nomecategoria;
    }

    public String getNomeproduto() {
        return nomeproduto;
    }

    public void setNomeproduto(String nomeproduto) {
        this.nomeproduto = nomeproduto;
    }

    public Double getPrevovenda() {
        return prevovenda;
    }

    public void setPrevovenda(Double prevovenda) {
        this.prevovenda = prevovenda;
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

    public int getIditempedido() {
        return iditempedido;
    }

    public void setIditempedido(int iditempedido) {
        this.iditempedido = iditempedido;
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
