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
import java.util.List;
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
    
    public List<FormaPagamento> listarpagamentos() throws DataSourceException, Exception {
        try {
            return formapagamendoDAO.listarpagamentos();
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
            
        }
    }
    
    public FormaPagamento consultaPagamento(int codigo) throws DataSourceException, Exception {
        FormaPagamento fp = new FormaPagamento();
          try {
            fp = formapagamendoDAO.consultaPagamento(codigo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
            
        }
          return fp;
    }
}
