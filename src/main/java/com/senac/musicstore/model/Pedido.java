/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.musicstore.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author magno
 */
public class Pedido {
    private Integer codigo;
    private Integer codigoCliente;
    private Timestamp data;
    private Double valorTotal;
    private Integer codigopagamento; //crud >> insert getCodigo();
    private Integer codigoendereco;
    private Integer codigovenda;

    public Pedido() {
        super();
    }
    
    public Pedido(Integer codigo, Integer codigoCliente, Timestamp data, Double valorTotal, Integer codigopagamento) {
        this.codigo = codigo;
        this.codigoCliente = codigoCliente;
        this.data = data;
        this.valorTotal = valorTotal;
        this.codigopagamento = codigopagamento;
    }

    public Integer getCodigovenda() {
        return codigovenda;
    }

    public void setCodigovenda(Integer codigovenda) {
        this.codigovenda = codigovenda;
    }

    
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(Integer codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getCodigopagamento() {
        return codigopagamento;
    }

    public void setCodigopagamento(Integer codigopagamento) {
        this.codigopagamento = codigopagamento;
    }

    public Integer getCodigoendereco() {
        return codigoendereco;
    }

    public void setCodigoendereco(Integer codigoendereco) {
        this.codigoendereco = codigoendereco;
    }
   
    
    
    
    
}
