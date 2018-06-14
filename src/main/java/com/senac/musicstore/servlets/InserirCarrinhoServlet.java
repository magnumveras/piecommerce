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
        Carrinho carrinho = new Carrinho();
        ItemCarrinho itemcarrinho = new ItemCarrinho();
        List<ItemCarrinho> listaitens= new ArrayList<ItemCarrinho>();  
        List<ItemCarrinho> listaitenscadastrado= new ArrayList<ItemCarrinho>();
        List<Produto> listaprodutos = new ArrayList<Produto>();
        
        //Limpa aviso de sem estoque
        if(sessao.getAttribute("semestoque") != null){
            sessao.removeAttribute("semestoque");
        }
 
        
        Produto produto = new Produto();
        
        String codigoproduto = request.getParameter("produto");
        
        try {
            //Captura produto clicado na tela
            produto = sp.encontrarProdutoPorCodigo(Integer.parseInt(codigoproduto));
        } catch (Exception e) {
        }
        
        String qtd = request.getParameter("quantidade");
        int quantidade = 0;
        
        //Verifica se quantidade veio da tela principal ou da tela de detalhes onde é possível escolher um quantidade maior do que 1;
        if(qtd == null){
            quantidade = 1;
        }else{
            quantidade = Integer.parseInt(qtd);
        }
        
        //Verifica se quantidade tem disponível em estoque para produto em questão.
        if(produto.getEstoque() < quantidade){
            //Caso não existe disponibilidade em estoque envia para tela principal aviso.
            sessao.setAttribute("semestoque", "Quantidade indisponível!");
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }else{
            carrinho = (Carrinho) sessao.getAttribute("carrinhocadastrado");
            
            //Verifica se existe login de algum cliente no carrinho, senão inicia carrinho sem cadastro de itemcarrinho e cliente.
            if(carrinho != null){
                    try {
                        listaitenscadastrado = sic.listarItensCarrinho(carrinho.getCodigo());
                        boolean verifica = false;
                        
                        //Verifica se produto já existe no carrinho
                        for(int i = 0; i < listaitenscadastrado.size(); i++){
                            int cdproduto = listaitenscadastrado.get(i).getProduto();
                            
                            if(cdproduto == Integer.parseInt(codigoproduto)){
                                verifica = true;
                            }
                        }
                        
                        if(verifica == false){
                           sic.cadastraritemCarrinho(carrinho.getCodigo(), Integer.parseInt(codigoproduto), quantidade);
                        }else{
                            int alteraqtd = 0;
                            for(int i = 0; i < listaitenscadastrado.size(); i++){
                                int cdproduto = listaitenscadastrado.get(i).getProduto();
                            
                                if(cdproduto == Integer.parseInt(codigoproduto)){
                                   alteraqtd = listaitenscadastrado.get(i).getQuantidade();
                                }
                            }
                            
                            sic.alteraQuantidadeItemCarrinho(carrinho.getCodigo(), Integer.parseInt(codigoproduto) , alteraqtd + quantidade);
                        }
                        
                        listaitenscadastrado = sic.listarItensCarrinho(carrinho.getCodigo());
                        listaprodutos = sp.listarProdutostotais();
                        
                        //Calcular Valor Total do Carrinho e se coloca no carrinho
                        double soma = 0;
                        for(int i = 0; i < listaitenscadastrado.size(); i++){
                            Produto p = new Produto();
                            int codiproduto = listaitenscadastrado.get(i).getProduto();
                            int quantiproduto = listaitenscadastrado.get(i).getQuantidade();
                        
                            try {
                                p = sp.encontrarProdutoPorCodigo(Integer.parseInt(codigoproduto));
                            } catch (Exception e) {
                            }
                            
                            if(codiproduto == Integer.parseInt(codigoproduto)){
                               soma += p.getPrecovenda() * quantiproduto;
                            }else{
                                p = sp.encontrarProdutoPorCodigo(listaitenscadastrado.get(i).getProduto());
                                soma += p.getPrecovenda() * listaitenscadastrado.get(i).getQuantidade();
                            }
                            
                        }
                        
                        sc.alteraValorCarrinho(soma, carrinho.getCodigo());
                        carrinho = sc.retornaCarrinho(carrinho.getCodigo());
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
                    boolean verifica = false;
                    int codigo = 0;
                    
                    //Laço para verificação de produtos já lançados
                    for(int i = 0; i < list.size(); i++){
                        listaitens.add(list.get(i));
                    }
                    
                    //Laço para verificação de produto já presente em lista
                    for(int i = 0; i < listaitens.size(); i++){
                        if(listaitens.get(i).getProduto() == itemcarrinho.getProduto()){
                            verifica = true;
                            codigo = itemcarrinho.getProduto();
                        }
                    }
                    
                    sessao.removeAttribute("itenscarrinho");
                    
                    //Verifica se produto já existe e acrescenta em quantidade
                    if(verifica == true){
                        for(int i = 0; i < listaitens.size(); i++){
                        if(listaitens.get(i).getProduto() == itemcarrinho.getProduto()){
                            listaitens.get(i).setQuantidade(listaitens.get(i).getQuantidade() + quantidade);
                        }
                    }
                    }else{
                       listaitens.add(itemcarrinho); 
                    }
                    
                }else{
                    listaitens.add(itemcarrinho); 
                }
                  sessao.setAttribute("itenscarrinho", listaitens);
                  sessao.setAttribute("carrinhoiniciado", "ok");
            }
        
            sessao.setAttribute("carrinhocadastrado", carrinho);
            sessao.setAttribute("listacarrinhocadastrado", listaitenscadastrado);
            sessao.setAttribute("listaprodutos", listaprodutos);

            response.sendRedirect(request.getContextPath() + "/carrinho.jsp");    
        }
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Criação se sessão para retorno em tela


    }

}
