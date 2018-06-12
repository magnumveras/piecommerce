/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.musicstore.service;
import com.senac.musicstore.dao.CarrinhoDAO;
import com.senac.musicstore.dao.ItemCarrinhoDAO;
import com.senac.musicstore.exceptions.CarrinhoException;
import com.senac.musicstore.exceptions.DataSourceException;
import com.senac.musicstore.exceptions.ItemCarrinhoException;
import com.senac.musicstore.model.Carrinho;
import com.senac.musicstore.model.validador.ValidadorCarrinho;
import java.util.List;
/**
 *
 * @author Magno
 */
public class ServicoCarrinho {
        CarrinhoDAO carrinhoDAO = new CarrinhoDAO();
        ItemCarrinhoDAO itemVendaDAO = new ItemCarrinhoDAO();
    
       //Insere um Produto na fonte de dados Carrinho
    public Integer cadastrarCarrinho(Carrinho carrinho) throws CarrinhoException, DataSourceException, ItemCarrinhoException, Exception{

        ValidadorCarrinho.validar(carrinho);

        try {
            return carrinhoDAO.inserirCarrinho(carrinho);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }
    
      //Exclui carrinho e dados do topo do carrinho
    public void excluirCarrinho(int codigocarrinho) throws CarrinhoException, DataSourceException, ItemCarrinhoException, Exception{


        try {
            carrinhoDAO.deletarCarrinho(codigocarrinho);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }
   //Exclui carrinho e dados do topo do carrinho
    public void alteraValorCarrinho(Double valor) throws CarrinhoException, DataSourceException, ItemCarrinhoException, Exception{


        try {
            carrinhoDAO.alterarValor(valor);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }
    
    //Retorna Carrinho
   public Carrinho retornaCarrinho(int codigocarrinho) throws CarrinhoException, DataSourceException, ItemCarrinhoException, Exception{


        try {
            return carrinhoDAO.retornaCarrinho(codigocarrinho);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }
}
