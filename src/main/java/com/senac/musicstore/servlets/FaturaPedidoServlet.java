
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
import com.senac.musicstore.model.Pedido;
import com.senac.musicstore.service.ServicoFornecedor;
import com.senac.musicstore.service.ServicoProduto;

import com.senac.musicstore.model.Produto;
import com.senac.musicstore.service.ServicoCliente;
import com.senac.musicstore.service.ServicoEnderecoEntrega;
import com.senac.musicstore.service.ServicoFormaPagamento;
import com.senac.musicstore.service.ServicoItemPedido;
import com.senac.musicstore.service.ServicoPedido;
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
@WebServlet(name = "FaturaPedidoServlet", urlPatterns = {"/faturarpedido"})
public class FaturaPedidoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        
        ServicoPedido sp = new ServicoPedido();
        ServicoCliente sc = new ServicoCliente();
        ServicoItemPedido sip = new ServicoItemPedido();
        ServicoEnderecoEntrega see = new ServicoEnderecoEntrega();
        ServicoFormaPagamento sfp = new ServicoFormaPagamento();
        ServicoProduto servicoproduto = new ServicoProduto();
        
        String codigopedido = request.getParameter("codigopedido");
        
        Pedido p = new Pedido();
        Cliente c = new Cliente();
        List<ItemPedido> listaitens = new ArrayList();
        EnderecoEntrega ee = new EnderecoEntrega();
        FormaPagamento fp = new FormaPagamento();
        List<Produto> produtos = new ArrayList();
        
        try {
            p = sp.ConsultarPedido(Integer.parseInt(codigopedido));
        } catch (Exception e) {
        }
        
        try {
            c = sc.obterClientePorCodigo(p.getCodigoCliente());
            listaitens = sip.listarItensPedido(p.getCodigo());
            ee = see.consultaEndereco(p.getCodigoendereco());
            fp = sfp.consultaPagamento(p.getCodigopagamento());
            produtos = servicoproduto.listarProdutostotais();
        } catch (Exception e) {
        }
        
        
        
        sessao.setAttribute("pedidofatura", p);
        sessao.setAttribute("clientefatura", c);
        sessao.setAttribute("listaitensfatura", listaitens);
        sessao.setAttribute("enderecofatura", ee);
        sessao.setAttribute("pagamentofatura", fp);
        sessao.setAttribute("listaprodutos", produtos);
                
        response.sendRedirect(request.getContextPath() + "/faturaPedido.jsp");   
    }

}
