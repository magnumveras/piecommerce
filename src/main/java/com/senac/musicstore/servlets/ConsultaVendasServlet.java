
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
@WebServlet(name = "ConsultaVendasServlet", urlPatterns = {"/consultavendas"})
public class ConsultaVendasServlet extends HttpServlet {

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
        
        List<Venda> listavendas = new ArrayList();
        List<Cliente> listaclientes = new ArrayList();
        List<ItemVenda> listaintensvendas = new ArrayList();
        List<EnderecoEntrega> listaenderecos = new ArrayList();
        List<FormaPagamento> listaformapagamentos = new ArrayList();
        
        try {
            listavendas = sv.listarvendastotais();
            listaclientes = sc.listarClientes();
            listaintensvendas = siv.listarItensVenda();
            listaenderecos = see.listarEnderecos();
            listaformapagamentos = sfp.listarpagamentos();
        } catch (Exception e) {
        }
        
        
        sessao.setAttribute("listavendas", listavendas);
        sessao.setAttribute("listaclientes", listaclientes);
        sessao.setAttribute("listaitensvenda", listaintensvendas);
        sessao.setAttribute("listaenderecos", listaenderecos);
        sessao.setAttribute("listaformapagamentos", listaformapagamentos);
        
        response.sendRedirect(request.getContextPath() + "/consultaVendasop.jsp");   
    }

}
