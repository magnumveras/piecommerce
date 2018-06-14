/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.musicstore.model;

/**
 *
 * @author magno
 */
public class FormaPagamento {
    private int codigo;
    private String cartaocredito;
    private String cartaodebito;
    private String numerocartao;
    private String nomecartao;
    private String codigoseguranca;

    public FormaPagamento() {
        super();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCartaocredito() {
        return cartaocredito;
    }

    public void setCartaocredito(String cartaocredito) {
        this.cartaocredito = cartaocredito;
    }

    public String getCartaodebito() {
        return cartaodebito;
    }

    public void setCartaodebito(String cartaodebito) {
        this.cartaodebito = cartaodebito;
    }

    public String getNumerocartao() {
        return numerocartao;
    }

    public void setNumerocartao(String numerocartao) {
        this.numerocartao = numerocartao;
    }

    public String getNomecartao() {
        return nomecartao;
    }

    public void setNomecartao(String nomecartao) {
        this.nomecartao = nomecartao;
    }

    public String getCodigoseguranca() {
        return codigoseguranca;
    }

    public void setCodigoseguranca(String codigoseguranca) {
        this.codigoseguranca = codigoseguranca;
    }

    
    
    
}
