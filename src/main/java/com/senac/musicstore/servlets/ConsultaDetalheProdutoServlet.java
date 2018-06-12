
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
@WebServlet(name = "ConsultaDetalheProdutoServlet", urlPatterns = {"/consultadetalhes"})
public class ConsultaDetalheProdutoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          HttpSession sessao = request.getSession();
          Produto p = new Produto();
          ArrayList<Produto> Lista = new ArrayList();
          ServicoProduto sp = new ServicoProduto();
          String verifica = "ok";
          
          Produto produto = new Produto();
          String codigoproduto = request.getParameter("produto");
                    
        try {
            produto = sp.encontrarProdutoPorCodigo(Integer.parseInt(codigoproduto));
            
        } catch (Exception ex) {
            
        }
          
            sessao.setAttribute("produtodetalhes", produto);

            RequestDispatcher dispatcher
	    = request.getRequestDispatcher("/produtosDetalhes.jsp");
    dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
