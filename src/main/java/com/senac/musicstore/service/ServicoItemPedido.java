/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.musicstore.service;

import com.senac.musicstore.dao.ItemPedidoDAO;
import com.senac.musicstore.exceptions.CarrinhoException;
import com.senac.musicstore.exceptions.DataSourceException;
import com.senac.musicstore.exceptions.ItemPedidoException;
import com.senac.musicstore.exceptions.PedidoException;
import com.senac.musicstore.model.ItemPedido;
import java.util.List;
/**
 *
 * @author Magno
 */
public class ServicoItemPedido {
        ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
    
    
    public void cadastraritemPedido(int codigopedido, int codigoproduto, int quantidade) throws PedidoException, DataSourceException, ItemPedidoException, Exception{

        try {
            itemPedidoDAO.cadastrarItemPedido(codigopedido, codigoproduto, quantidade);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }
    
    
    public List<ItemPedido> listarItensPedido(int codigopedido) throws DataSourceException, Exception {
        try {
            return itemPedidoDAO.listarPedidoeItens(codigopedido);
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
            
        }
    }
    
    public List<ItemPedido> listarItensPedidos() throws DataSourceException, Exception {
        try {
            return itemPedidoDAO.listarItensPedidos();
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
            
        }
    }
}
