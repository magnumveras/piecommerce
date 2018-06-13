/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.musicstore.servlets;

import com.senac.musicstore.model.Carrinho;
import com.senac.musicstore.model.Cliente;
import com.senac.musicstore.model.ItemCarrinho;
import com.senac.musicstore.model.Produto;
import com.senac.musicstore.service.ServicoCarrinho;
import com.senac.musicstore.service.ServicoCliente;
import com.senac.musicstore.service.ServicoItemCarrinho;
import com.senac.musicstore.service.ServicoProduto;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author magno
 */
@WebServlet(name = "ExcluirItemCarrinhoServlet", urlPatterns = {"/removeritemcarrinho"})
public class ExcluirItemCarrinhoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Instância serviço de servidor para efetuar consulta e ligação com ClienteDAO
        ServicoCarrinho sc = new ServicoCarrinho();
        ServicoItemCarrinho sic = new ServicoItemCarrinho();
        ServicoProduto sp = new ServicoProduto();
        ServicoCliente scli = new ServicoCliente();
        Produto produto = new Produto();
        Carrinho carrinho = new Carrinho();
        List<Produto> lista= new ArrayList<Produto>();
        
        //Criação se sessão para retorno em tela
        HttpSession sessao = request.getSession();
        
        if(sessao.getAttribute("itenscarrinho") != null){
            List<ItemCarrinho> list = new ArrayList<ItemCarrinho>();
            list = (List<ItemCarrinho>) sessao.getAttribute("itenscarrinho");
            try {
                lista = sp.listarProdutostotais();
            } catch (Exception e) {
            }
            //Recebe código do produto
            String codigoproduto = request.getParameter("codigoproduto");
            
            for(int i = 0; i < list.size(); i++){
                if(list.get(i).getProduto() == Integer.parseInt(codigoproduto)){
                    list.remove(i);
                }
            }
            
            sessao.setAttribute("listaprodutos", lista);
            sessao.setAttribute("itenscarrinho", list);
        }else{
            //Dados pegos da tela
            String codigocarrinho = request.getParameter("codigocarrinho");
            String codigoproduto = request.getParameter("codigoproduto");
            String quantidade = request.getParameter("quantidade");
        
        
            //Pega dados do produto que será retirado
            try {
                Double valor = 0.0;
                produto = sp.encontrarProdutoPorCodigo(Integer.parseInt(codigoproduto));
            
                //Atualiza estoque
                //sp.atualizaEstoque(produto.getCodigo(), produto.getEstoque() + Integer.parseInt(quantidade));
            
                //Apaga produto e quantidade do carrinho
                sic.excluiritemCarrinho(Integer.parseInt(codigocarrinho), Integer.parseInt(codigoproduto));
            
                //Atualiza valor total do Carrinho
                valor = produto.getPrecovenda() * Integer.parseInt(quantidade);
            
                //Pega carrinho para atualizar mesmo
                carrinho = sc.retornaCarrinho(Integer.parseInt(codigocarrinho));
            
                //Atualiza valor do carrinho
                sc.alteraValorCarrinho(carrinho.getValorTotal() - valor, carrinho.getCodigo());
            
            } catch (Exception e) {
            }
        
            List<ItemCarrinho> listaitens= new ArrayList<ItemCarrinho>();
            List<Produto> listaprodutos = new ArrayList<Produto>();
            List<Cliente> listaclientes = new ArrayList<Cliente>();
        
            try {
                carrinho = sc.retornaCarrinho(Integer.parseInt(codigocarrinho));
                listaitens = sic.listarItensCarrinho(Integer.parseInt(codigocarrinho));
                listaprodutos = sp.listarProdutostotais();
            } catch (Exception e) {
            }
            sessao.setAttribute("carrinhocadastrado", carrinho);
            sessao.setAttribute("listacarrinhocadastrado", listaitens);
            sessao.setAttribute("listaprodutos", listaprodutos);
            sessao.setAttribute("listaclientes", listaclientes);
        }
        
        
        
        response.sendRedirect(request.getContextPath() + "/carrinho.jsp");

        }
           
        
}


