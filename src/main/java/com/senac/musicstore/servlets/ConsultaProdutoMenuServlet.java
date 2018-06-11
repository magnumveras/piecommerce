
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.musicstore.servlets;


import com.senac.musicstore.model.Fornecedor;
import com.senac.musicstore.service.ServicoFornecedor;
import com.senac.musicstore.service.ServicoProduto;

import com.senac.musicstore.model.Produto;
import java.io.IOException;
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
@WebServlet(name = "ConsultaProdutoServlet", urlPatterns = {"/consultaprodutosmenu"})
public class ConsultaProdutoMenuServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          //Instância de ArrayList para acumular fornecedores
        ArrayList<Produto> Lista = new ArrayList();
        ArrayList<Fornecedor> Listaf = new ArrayList();
        
        
        ServicoProduto sp = new ServicoProduto();
        ServicoFornecedor sf = new ServicoFornecedor();
        
        HttpSession sessao = request.getSession();
        
        //Atribuição de valores digitados na tela de fornecedor e código da empresa
        String produto = request.getParameter("produto");
        
        if(produto == null){
            produto = "";
        }
        
        try {
            Lista = (ArrayList<Produto>) sp.encontrarProdutoPorNome(produto);
            Listaf = (ArrayList<Fornecedor>) sf.listarFornecedor();
        } catch (Exception e) {
        }
        
        sessao.setAttribute("ListaFornecedores", Listaf);
        sessao.setAttribute("ListaProdutos", Lista);
        sessao.removeAttribute("Altera");
        response.sendRedirect(request.getContextPath() + "/consultaProduto.jsp");   
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                //Instância de ArrayList para acumular fornecedores
        ArrayList<Produto> Lista = new ArrayList();
        ArrayList<Fornecedor> Listaf = new ArrayList();
        
        
        ServicoProduto sp = new ServicoProduto();
        ServicoFornecedor sf = new ServicoFornecedor();
        
        HttpSession sessao = request.getSession();
        
        //Atribuição de valores digitados na tela de fornecedor e código da empresa
        String produto = request.getParameter("produto");

        try {
            Lista = (ArrayList<Produto>) sp.encontrarProdutoPorNome(produto);
            Listaf = (ArrayList<Fornecedor>) sf.listarFornecedor();
        } catch (Exception e) {
        }
        
        sessao.setAttribute("ListaFornecedores", Listaf);
        sessao.setAttribute("ListaProdutos", Lista);
        
        response.sendRedirect(request.getContextPath() + "/corpo.jsp");  
    }

}
