/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.musicstore.service;

import com.senac.musicstore.dao.ItemPedidoDAO;
import com.senac.musicstore.dao.ItemVendaDAO;
import com.senac.musicstore.exceptions.CarrinhoException;
import com.senac.musicstore.exceptions.DataSourceException;
import com.senac.musicstore.exceptions.ItemPedidoException;
import com.senac.musicstore.exceptions.ItemVendaException;
import com.senac.musicstore.exceptions.PedidoException;
import com.senac.musicstore.exceptions.VendaException;
import com.senac.musicstore.model.ItemPedido;
import com.senac.musicstore.model.ItemVenda;
import java.util.List;
/**
 *
 * @author Magno
 */
public class ServicoItemVenda {
        ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
    
    
    public void cadastrarItemVenda(int codigovenda, int codigoproduto, int quantidade) throws VendaException, DataSourceException, ItemVendaException, Exception{

        try {
            itemVendaDAO.cadastrarItemVenda(codigovenda, codigoproduto, quantidade);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }
    
    
    public List<ItemVenda> listarItensVenda(int codigovenda) throws DataSourceException, Exception {
        try {
            return itemVendaDAO.listarItensVenda(codigovenda);
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
            
        }
    }
    
    public List<ItemVenda> listarItensVenda() throws DataSourceException, Exception {
        try {
            return itemVendaDAO.listarItensVendas();
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
            
        }
    }
}
