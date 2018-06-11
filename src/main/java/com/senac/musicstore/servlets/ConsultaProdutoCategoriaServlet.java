
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.musicstore.servlets;


import com.senac.musicstore.service.ServicoProduto;
import com.senac.musicstore.model.Produto;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "ConsultaProdutoCategoriaServlet", urlPatterns = {"/consultaporcategoria"})
public class ConsultaProdutoCategoriaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         Produto c = new Produto();
        
        //Instância de ArrayList para acumular fornecedores
        ArrayList<Produto> Lista = new ArrayList();
        
        //Instância serviço de servidor para efetuar consulta e ligação com FornecedorDAO
        ServicoProduto sp = new ServicoProduto();
        
        //Criação se sessão para retorno em tela
        HttpSession sessao = request.getSession();
        
        //Atribuição de valores digitados na tela de fornecedor e código da empresa
        String categoria = (String) request.getParameter("categoria");

        try {
            Lista = (ArrayList<Produto>) sp.encontrarProdutoPorCategoria(Integer.parseInt(categoria));
        } catch (Exception e) {
        }
       
        //sessao.setAttribute("ListaFornecedores", ListaF);
        sessao.setAttribute("ListaProdutos", Lista);
        response.sendRedirect(request.getContextPath() + "/corpo.jsp");   
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Produto c = new Produto();
        
        //Instância de ArrayList para acumular fornecedores
        ArrayList<Produto> Lista = new ArrayList();
        
        //Instância serviço de servidor para efetuar consulta e ligação com FornecedorDAO
        ServicoProduto sp = new ServicoProduto();
        
        //Criação se sessão para retorno em tela
        HttpSession sessao = request.getSession();
        
        //Atribuição de valores digitados na tela de fornecedor e código da empresa
        String categoria = (String) request.getAttribute("categoria");

        try {
            Lista = (ArrayList<Produto>) sp.encontrarProdutoPorCategoria(Integer.parseInt(categoria));
        } catch (Exception e) {
        }
       
        //sessao.setAttribute("ListaFornecedores", ListaF);
        sessao.setAttribute("ListaProdutos", Lista);
        response.sendRedirect(request.getContextPath() + "/corpo.jsp");   
        
    }

}
