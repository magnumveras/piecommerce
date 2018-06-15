/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.musicstore.service;

import com.senac.musicstore.dao.ItemPedidoDAO;
import com.senac.musicstore.dao.PedidoDAO;
import com.senac.musicstore.dao.VendaDAO;
import com.senac.musicstore.exceptions.DataSourceException;
import com.senac.musicstore.exceptions.ItemPedidoException;
import com.senac.musicstore.exceptions.ItemVendaException;
import com.senac.musicstore.exceptions.PedidoException;
import com.senac.musicstore.exceptions.VendaException;
import com.senac.musicstore.model.Pedido;
import com.senac.musicstore.model.Venda;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author magno
 */
public class ServicoVenda {
    public static List<Venda> listavendas = new ArrayList<>();
    VendaDAO vendadao = new VendaDAO();
    ItemPedidoDAO item = new ItemPedidoDAO();
    
    //Insere um Produto na fonte de dados
    public Integer cadatrarVenda(Venda venda) throws VendaException, DataSourceException, ItemVendaException {
        try {
            return vendadao.cadastrarVenda(venda);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }
    
    //Insere um Produto na fonte de dados
    public Venda ConsultarVenda(Integer Codigo) throws VendaException, DataSourceException, ItemVendaException {
        try {
            return vendadao.consultarVenda(Codigo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }
    
        //Retorna Carrinho
   public List<Venda> listavendas(Date datainicial, Date datafinal) throws VendaException, DataSourceException, ItemVendaException, Exception{
        
        try {
            return vendadao.listarVendasPeriodo(datainicial, datafinal);
            //return vendaDAO.(codigocarrinho);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }
    
   public List<Venda> listarvendastotais() throws VendaException, DataSourceException, ItemVendaException, Exception{
        
        try {
            return vendadao.listarVendasTotais();
            //return vendaDAO.(codigocarrinho);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }
    
}
