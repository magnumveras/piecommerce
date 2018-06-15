
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.musicstore.servlets;


import com.senac.musicstore.model.Cliente;
import com.senac.musicstore.model.EnderecoEntrega;
import com.senac.musicstore.model.FormaPagamento;
import com.senac.musicstore.model.Fornecedor;
import com.senac.musicstore.model.ItemPedido;
import com.senac.musicstore.model.ItemVenda;
import com.senac.musicstore.model.Pedido;
import com.senac.musicstore.service.ServicoFornecedor;
import com.senac.musicstore.service.ServicoProduto;

import com.senac.musicstore.model.Produto;
import com.senac.musicstore.model.Venda;
import com.senac.musicstore.service.ServicoCliente;
import com.senac.musicstore.service.ServicoEnderecoEntrega;
import com.senac.musicstore.service.ServicoFormaPagamento;
import com.senac.musicstore.service.ServicoItemPedido;
import com.senac.musicstore.service.ServicoItemVenda;
import com.senac.musicstore.service.ServicoPedido;
import com.senac.musicstore.service.ServicoVenda;
import java.io.IOException;
import java.sql.Timestamp;
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
@WebServlet(name = "VisualizarVendaServlet", urlPatterns = {"/visualizarvenda"})
public class VisualizarVendaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        ServicoVenda sv = new ServicoVenda();
        ServicoCliente sc = new ServicoCliente();
        ServicoItemVenda siv = new ServicoItemVenda();
        ServicoEnderecoEntrega see = new ServicoEnderecoEntrega();
        ServicoFormaPagamento sfp = new ServicoFormaPagamento();
        ServicoProduto servicoproduto = new ServicoProduto();
        
        String codigovenda = request.getParameter("codigovenda");
        
        Venda v = new Venda();
        Cliente c = new Cliente();
        List<ItemVenda> listaitens = new ArrayList();
        EnderecoEntrega ee = new EnderecoEntrega();
        FormaPagamento fp = new FormaPagamento();
        List<Produto> produtos = new ArrayList();
        
        try {
            v = sv.ConsultarVenda(Integer.parseInt(codigovenda));
        } catch (Exception e) {
        }
        
        try {
            c = sc.obterClientePorCodigo(v.getCodigoCliente());
            listaitens = siv.listarItensVenda(v.getCodigo());
            ee = see.consultaEndereco(v.getCodigoendereco());
            fp = sfp.consultaPagamento(v.getCodigopagamento());
            produtos = servicoproduto.listarProdutostotais();
        } catch (Exception e) {
        }
        
        
        
        sessao.setAttribute("venda", v);
        sessao.setAttribute("clientevenda", c);
        sessao.setAttribute("listaitensvenda", listaitens);
        sessao.setAttribute("enderecofatura", ee);
        sessao.setAttribute("pagamentofatura", fp);
        sessao.setAttribute("listaprodutos", produtos);
                        
        response.sendRedirect(request.getContextPath() + "/detalhesVenda.jsp");
        
        }
        
}
        

        
 


