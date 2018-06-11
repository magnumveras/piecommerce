
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.musicstore.servlets;


import com.senac.musicstore.service.ServicoProduto;
import com.senac.musicstore.model.Produto;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
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
@WebServlet(name = "CadastroProdutoServlet", urlPatterns = {"/cadastroproduto"})
public class CadastroProdutoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher
	    = request.getRequestDispatcher("/cadastroProduto.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession sessao = request.getSession();
                
        String nomeproduto = request.getParameter("nomeproduto");
        String categoria = request.getParameter("categoria");
        String fornecedor = request.getParameter("fornecedor");
        String precoCompra = request.getParameter("precoCompra");
        String precoVenda = request.getParameter("precoVenda");
        String estoque = request.getParameter("estoque");
        String descricao = request.getParameter("descricao");
        Timestamp datacadastro = new Timestamp(System.currentTimeMillis());
        precoCompra = precoCompra.replace(".", "");
        precoCompra = precoCompra.replace(",", ".");
        precoVenda = precoVenda.replace(".", "");
        precoVenda = precoVenda.replace(",", ".");
        
        //Verifica campos obrigatórios
            if((nomeproduto.length() == 0)||(categoria == null)||(categoria.isEmpty())||
                    (fornecedor.isEmpty())||(fornecedor == null)||(precoCompra.length() == 0)||(precoVenda.length() == 0)||(estoque.length() == 0)||
                    (descricao.length() == 0)){
                sessao.setAttribute("mensagemErroCampos", "Verifique campos obrigatórios!");
                RequestDispatcher dispatcher
                = request.getRequestDispatcher("/cadastroProduto.jsp");
                dispatcher.forward(request, response);
            }else{
                 ServicoProduto sp = new ServicoProduto();
                 boolean proexiste = false;
                 try {
                //Verifica se produto existe no cadastro
                proexiste = sp.encontrarProdutoCadastro(nomeproduto);
                } catch (Exception e) {
                    
                }
                 //Caso não exista
                 if(!proexiste){
                     sessao.setAttribute("mensagemErroCampos", "");
                     
                     Produto p = new Produto();
                     p.setNome(nomeproduto);
                     p.setDescricao(descricao);
                     p.setCategoria(Integer.parseInt(categoria));
                     p.setFornecedor(Integer.parseInt(fornecedor));
                     p.setEstoque(Integer.parseInt(estoque));
                     p.setPrecocompra(Double.parseDouble(precoCompra));
                     p.setPrecovenda(Double.parseDouble(precoVenda));
                     p.setDatacadastro(datacadastro);
                     
                     //Cadastra novo produto na tabela
                    try {
                        sp.cadastrarProduto(p);
                        sessao.setAttribute("Produto", p);
                        sessao.setAttribute("produtoexiste", "");
                        response.sendRedirect(request.getContextPath() + "/cadastroProduto.jsp");
                        System.out.println("Produto Inserido com sucesso!");
            
                    } catch (Exception e) {
                        request.setAttribute("mensagemErro", "Produto não cadastrado");
                        sessao.setAttribute("produtoexiste", "");
                        RequestDispatcher dispatcher
                        = request.getRequestDispatcher("/cadastroProduto.jsp");
                        dispatcher.forward(request, response);
                        System.out.println("Erro na inserção de novo produto!");
                    }   
                }else{
                     sessao.setAttribute("produtoexiste", "Produto ja cadastrado!");
                     request.setAttribute("produtoexiste", "Produto ja cadastrado!");
                     RequestDispatcher dispatcher
                     = request.getRequestDispatcher("/cadastroProduto.jsp");
                     dispatcher.forward(request, response);
                    System.out.println("Erro na inserção de novo Produto!");
                 }
            } 
        
    }

}
