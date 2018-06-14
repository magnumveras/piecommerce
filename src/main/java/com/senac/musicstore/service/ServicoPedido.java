/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.musicstore.service;

import com.senac.musicstore.dao.ItemPedidoDAO;
import com.senac.musicstore.dao.PedidoDAO;
import com.senac.musicstore.exceptions.DataSourceException;
import com.senac.musicstore.exceptions.ItemPedidoException;
import com.senac.musicstore.exceptions.PedidoException;
import com.senac.musicstore.model.Pedido;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author magno
 */
public class ServicoPedido {
    public static List<Pedido> listaPedidos = new ArrayList<>();
    PedidoDAO pedidoDAO = new PedidoDAO();
    ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
    
    //Insere um Produto na fonte de dados
    public Integer cadastrarPedido(Pedido pedido) throws PedidoException, DataSourceException, ItemPedidoException {
        try {
            return pedidoDAO.cadastrarPedido(pedido);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }
    
    //Insere um Produto na fonte de dados
    public Pedido ConsultarPedido(Integer Codigo) throws PedidoException, DataSourceException, ItemPedidoException {
        try {
            return pedidoDAO.consultarPedido(Codigo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }
    
        //Retorna Carrinho
   public List<Pedido> listavendas(Date datainicial, Date datafinal) throws PedidoException, DataSourceException, ItemPedidoException, Exception{
        
        try {
            return pedidoDAO.listarPedidos(datainicial, datafinal);
            //return vendaDAO.(codigocarrinho);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }
    
}
