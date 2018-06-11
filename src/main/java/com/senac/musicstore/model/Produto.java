package com.senac.musicstore.model;

import java.sql.Timestamp;

public class Produto {
    
    private Integer codigo;
    private String nome;
    private String descricao;
    private Integer categoria; 
    private Integer fornecedor;
    private Double precocompra;
    private Double precovenda;
    private Integer estoque;
    private Integer imagem;
    private Timestamp datacadastro;

    public Produto(Integer codigo, String nome, String descricao, Integer categoria, Integer fornecedor, Double precocompra, Double precovenda, Integer estoque, Integer imagem, Timestamp datacadastro) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.fornecedor = fornecedor;
        this.precocompra = precocompra;
        this.precovenda = precovenda;
        this.estoque = estoque;
        this.imagem = imagem;
        this.datacadastro = datacadastro;
    }

    public Produto() {
        super();
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public Integer getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Integer fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Double getPrecocompra() {
        return precocompra;
    }

    public void setPrecocompra(Double precocompra) {
        this.precocompra = precocompra;
    }

    public Double getPrecovenda() {
        return precovenda;
    }

    public void setPrecovenda(Double precovenda) {
        this.precovenda = precovenda;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public Integer getImagem() {
        return imagem;
    }

    public void setImagem(Integer imagem) {
        this.imagem = imagem;
    }

    public Timestamp getDatacadastro() {
        return datacadastro;
    }

    public void setDatacadastro(Timestamp datacadastro) {
        this.datacadastro = datacadastro;
    }
  
}
