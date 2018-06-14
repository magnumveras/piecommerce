/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.musicstore.service;


import com.senac.musicstore.dao.EnderecoEntregaDAO;
import com.senac.musicstore.dao.FormaPagamentoDAO;
import com.senac.musicstore.exceptions.DataSourceException;
import com.senac.musicstore.exceptions.EnderecoEntregaException;
import com.senac.musicstore.model.EnderecoEntrega;
import com.senac.musicstore.model.FormaPagamento;
/**
 *
 * @author magno
 */
public class ServicoFormaPagamento {

    FormaPagamentoDAO formapagamendoDAO = new FormaPagamentoDAO();
    
    //Insere um Produto na fonte de dados
    public Integer cadastrarFormaPagamento(FormaPagamento formapagamento) throws EnderecoEntregaException, DataSourceException {
        try {
            return formapagamendoDAO.inserirFormaPagamento(formapagamento);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }
    
    
}
