/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.musicstore.service;

import com.senac.musicstore.dao.ItemCarrinhoDAO;
import com.senac.musicstore.exceptions.CarrinhoException;
import com.senac.musicstore.exceptions.DataSourceException;
import com.senac.musicstore.exceptions.ItemCarrinhoException;
import com.senac.musicstore.model.ItemCarrinho;
import java.util.List;
/**
 *
 * @author Magno
 */
public class ServicoItemCarrinho {
        ItemCarrinhoDAO itemCarrinhoDAO = new ItemCarrinhoDAO();
    
       //Insere um Produto na fonte de dados Carrinho
    public void cadastraritemCarrinho(int codigocarrinho, int codigoproduto, int quantidade) throws CarrinhoException, DataSourceException, ItemCarrinhoException, Exception{

        try {
            itemCarrinhoDAO.cadastrarItemCarrinho(codigocarrinho, codigoproduto, quantidade);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }
    
      //Exclui carrinho e dados do topo do carrinho
    public void excluirCarrinho(int codigocarrinho) throws CarrinhoException, DataSourceException, ItemCarrinhoException, Exception{


        try {
            itemCarrinhoDAO.deletarItensCarrinho(codigocarrinho);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }
    
    //Exclui carrinho e dados do topo do carrinho
    public void excluiritemCarrinho(int codigocarrinho, int codigoitem) throws CarrinhoException, DataSourceException, ItemCarrinhoException, Exception{

        try {
            itemCarrinhoDAO.deletarItemCarrinho(codigocarrinho, codigoitem);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }
    
    //Realiza a pesquisa e retorna todos os produtos da fonte de dados
    public List<ItemCarrinho> listarItensCarrinho(int codigocarrinho) throws DataSourceException, Exception {
        try {
            return itemCarrinhoDAO.listarItensCarrinho(codigocarrinho);
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
            
        }
    }
    
       //Altera quantidade item carrinho
    public void alteraQuantidadeItemCarrinho(int codigocarrinho, int codigoproduto, int quantidade) throws CarrinhoException, DataSourceException, ItemCarrinhoException, Exception{


        try {
            itemCarrinhoDAO.alteraQuantidadeItemCarrinho(codigocarrinho, codigoproduto, quantidade);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }
}
