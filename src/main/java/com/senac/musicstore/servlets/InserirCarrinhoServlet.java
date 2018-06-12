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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mayra Pereira
 */
@WebServlet(name = "InserirCarrinhoServlet", urlPatterns = {"/inserircarrinho"})
public class InserirCarrinhoServlet extends HttpServlet {


     
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        
        ServicoCarrinho sc = new ServicoCarrinho();
        ServicoItemCarrinho sic = new ServicoItemCarrinho();
        ServicoProduto sp = new ServicoProduto();
        ServicoCliente scli = new ServicoCliente();
        
        Carrinho carrinho = new Carrinho();
        Carrinho carrinhomock = new Carrinho();
        ItemCarrinho itemcarrinho = new ItemCarrinho();
        Cliente cliente = new Cliente();

        List<ItemCarrinho> listaitens= new ArrayList<ItemCarrinho>();  

        
        List<ItemCarrinho> listaitenscadastrado= new ArrayList<ItemCarrinho>();
        List<Produto> listaprodutos = new ArrayList<Produto>();
        List<Cliente> listaclientes = new ArrayList<Cliente>();
        
        Produto produto = new Produto();
        
        String codigoproduto = request.getParameter("produto");
        
        try {
            produto = sp.encontrarProdutoPorCodigo(Integer.parseInt(codigoproduto));
        } catch (Exception e) {
        }
        
        String qtd = request.getParameter("quantidade");
        int quantidade = 0;
        
        if(qtd == null){
            quantidade = 1;
        }else{
            quantidade = Integer.parseInt(qtd);
        }
        
        carrinho = (Carrinho) sessao.getAttribute("carrinhocliente");
        
        if(carrinho != null){
            try {
                sic.cadastraritemCarrinho(carrinho.getCodigo(), produto.getCodigo(), quantidade);
                listaitenscadastrado = sic.listarItensCarrinho(carrinho.getCodigo());
                listaprodutos = sp.listarProdutostotais();
            } catch (Exception e) {
            }
        }else{
            try {
                listaprodutos = sp.listarProdutostotais();
            } catch (Exception e) {
            }
            
            itemcarrinho.setProduto(produto.getCodigo());
            itemcarrinho.setQuantidade(quantidade);
            
            //Verifica se carrinho sem cliente já não existe
            if(sessao.getAttribute("carrinhoiniciado") != null){
               List<ItemCarrinho> list = new ArrayList<ItemCarrinho>();
               list = (List<ItemCarrinho>) sessao.getAttribute("itenscarrinho");
               
               for(int i = 0; i < list.size(); i++){
                   listaitens.add(list.get(i));
               }
               
               sessao.removeAttribute("itenscarrinho");
               listaitens.add(itemcarrinho);
            }else{
               listaitens.add(itemcarrinho); 
            }
                       
        }
        
        sessao.setAttribute("cabecalhocarrinho", carrinho);
        sessao.setAttribute("itenscarrinhocadastrado", listaitenscadastrado);
        sessao.setAttribute("itenscarrinho", listaitens);
        sessao.setAttribute("listaprodutos", listaprodutos);
        sessao.setAttribute("carrinhoiniciado", "ok");

        response.sendRedirect(request.getContextPath() + "/carrinho.jsp");   
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Criação se sessão para retorno em tela


    }

}
