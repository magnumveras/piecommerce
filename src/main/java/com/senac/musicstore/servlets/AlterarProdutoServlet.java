/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.musicstore.servlets;


import com.senac.musicstore.service.ServicoProduto;
import com.senac.musicstore.model.Produto;
import java.io.IOException;
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
@WebServlet(name = "AlterarProdutoServlet", urlPatterns = {"/alterarProduto"})
public class AlterarProdutoServlet extends HttpServlet {

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
        //Instância Objeto Cliente
        Produto produto = new Produto();
        
        //Instância serviço de servidor para efetuar consulta e ligação com ClienteDAO
        ServicoProduto sp = new ServicoProduto();
        
        //Criação se sessão para retorno em tela
        request.setCharacterEncoding("UTF-8");
        HttpSession sessao = request.getSession();
        
        //Para verificação se é alteração
        String alteracao = "";
        try {
            alteracao = sessao.getAttribute("Altera").toString();
        } catch (Exception e) {
        }
        
        if ((alteracao == null)||(alteracao.length() == 0)){
              //Atribuição de valores digitados na tela de fornecedor e código da empresa
            String codigoproduto = request.getParameter("codigoproduto");
            
            try {
            produto = sp.encontrarProdutoPorCodigo(Integer.parseInt(codigoproduto));
            } catch (Exception e) {
            }
        
            sessao.setAttribute("pro", produto);
            sessao.setAttribute("Altera", "alteracao");
            response.sendRedirect(request.getContextPath() + "/cadastroProduto.jsp");
            
        }else{
            Produto p = new Produto();
            p = (Produto) sessao.getAttribute("pro");
            request.setCharacterEncoding("UTF-8");
            String nomeproduto = request.getParameter("nomeproduto");
            String desc = request.getParameter("descricao");
            String cat = request.getParameter("categoria");
            String codF = request.getParameter("fornecedor");
            String estoque = request.getParameter("estoque");
            String compra = request.getParameter("precoCompra");
            String venda = request.getParameter("precoVenda");
            compra = compra.replace(".", "");
            compra = compra.replace(",", ".");
            venda = venda.replace(".", "");
            venda = venda.replace(",", ".");
            
            //Verifica campos obrigatórios
            if((nomeproduto.length() == 0)||(desc.length() == 0)||
                    (cat.length() == 0)||(codF.length() == 0)||(estoque.length() == 0)||(compra.length() == 0)||
                    (venda.length() == 0)){
                sessao.setAttribute("mensagemErroCampos", "Verifique campos obrigatórios!");
                RequestDispatcher dispatcher
                = request.getRequestDispatcher("/cadastroProduto.jsp");
                dispatcher.forward(request, response);
            }else{
                sessao.setAttribute("mensagemErroCampos", "");
                
                try {
                p.setNome(nomeproduto);
                p.setDescricao(desc);
                p.setCategoria(Integer.parseInt(cat));
                p.setFornecedor(Integer.parseInt(codF));
                p.setCategoria(Integer.parseInt(cat));
                p.setEstoque(Integer.parseInt(estoque));
                p.setPrecocompra(Double.parseDouble(compra));
                p.setPrecovenda(Double.parseDouble(venda));
                
                sp.atualizarProduto(p);
                } catch (Exception e) {
                }
                response.sendRedirect(request.getContextPath() + "/consultaProduto.jsp");
                sessao.removeAttribute("Altera");
            }
            
        }
           
        
    }

}
