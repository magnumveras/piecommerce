/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.musicstore.service;

import com.senac.musicstore.dao.ProdutoDAO;
import com.senac.musicstore.exceptions.DataSourceException;
import com.senac.musicstore.exceptions.ProdutoException;
import com.senac.musicstore.model.validador.ValidadorProduto;
import com.senac.musicstore.model.Produto;
import java.util.List;
/**
 *
 * @author magno
 */
//Classe de Servico de Produto
public class ServicoProduto {
    ProdutoDAO produtoDAO = new ProdutoDAO();
    
    public void cadastrarProduto(Produto produto) throws ProdutoException, DataSourceException {
        
        ValidadorProduto.validar(produto);

        try {
            //Realiza a chamada de inserção na fonte de dados
            produtoDAO.inserirProduto(produto);
        } catch (Exception e) {
            //Imprime qualquer erro técnico no console e devolve
            //uma exceção e uma mensagem amigável a camada de visão
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }

    //Atualiza um produto na fonte de dados
    public void atualizarProduto(Produto produto) throws ProdutoException, DataSourceException {
        ValidadorProduto.validar(produto);

        try {
            produtoDAO.updateProduto(produto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }

    public List<Produto> encontrarProdutoPorNome(String nome) throws ProdutoException, DataSourceException {
        try {
            return produtoDAO.encontrarProduto(nome);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }
    
    public Produto encontrarProdutoPorCodigo(int codigo) throws ProdutoException, DataSourceException {
        try {
            return produtoDAO.encontrarProdutoCodigo(codigo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }
    
    //Retorna produtos por categoria
    public List<Produto> encontrarProdutoPorCategoria(int categoria) throws ProdutoException, DataSourceException {
        try {
            return produtoDAO.encontrarProdutosCategoria(categoria);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }
    public boolean encontrarProdutoCadastro(String nome) throws ProdutoException, DataSourceException {
        try {
            return produtoDAO.encontrarProdutoCadastro(nome);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }

    public void excluirProduto(Integer codigo) throws ProdutoException, DataSourceException {
        try {
            produtoDAO.deletarProduto(codigo);
        } catch (Exception e) {
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }
    
    public void atualizaEstoque(int codigo, int estoque) throws ProdutoException, DataSourceException {
        try {
            produtoDAO.atualizarEstoque(codigo, estoque);
        } catch (Exception e) {
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }
    
    //Lista produtos de determinada empresa
    public List<Produto> listarProdutos(int codigoempresa) throws DataSourceException, Exception {
        try {
            return produtoDAO.listarProdutos(codigoempresa);
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
            
        }
    }
    
    //Lista produtos Totais
    public List<Produto> listarProdutostotais() throws DataSourceException, Exception {
        try {
            return produtoDAO.listarProdutostotais();
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }
}
