/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.musicstore.service;


import com.senac.musicstore.dao.EnderecoEntregaDAO;
import com.senac.musicstore.exceptions.DataSourceException;
import com.senac.musicstore.exceptions.EnderecoEntregaException;
import com.senac.musicstore.model.EnderecoEntrega;
import java.util.List;
/**
 *
 * @author magno
 */
public class ServicoEnderecoEntrega {

    EnderecoEntregaDAO enderecoEntregaDAO = new EnderecoEntregaDAO();
    
    //Insere um Produto na fonte de dados
    public Integer cadastrarEnderecoEntrega(EnderecoEntrega enderecoEntrega) throws EnderecoEntregaException, DataSourceException {
        try {
            return enderecoEntregaDAO.inserirEndereco(enderecoEntrega);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }
    
      public List<EnderecoEntrega> listarEnderecos() throws DataSourceException, Exception {
        try {
            return enderecoEntregaDAO.listarEnderecos();
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
            
        }
    }
      
      public EnderecoEntrega consultaEndereco(int codigo) throws DataSourceException, Exception {
        EnderecoEntrega ee = new EnderecoEntrega();
          try {
            ee = enderecoEntregaDAO.consultaEndereco(codigo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
            
        }
          return ee;
    }
}
