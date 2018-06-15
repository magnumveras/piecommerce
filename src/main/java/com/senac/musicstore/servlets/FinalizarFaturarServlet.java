
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
@WebServlet(name = "FinalizarFaturarServlet", urlPatterns = {"/finalizarfaturar"})
public class FinalizarFaturarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        ServicoPedido sp = new ServicoPedido();
        ServicoVenda sv = new ServicoVenda();
        ServicoItemVenda siv = new ServicoItemVenda();
        ServicoItemPedido sio = new ServicoItemPedido();
        ServicoProduto sprod = new ServicoProduto();
        ServicoCliente scli = new ServicoCliente();
        ServicoEnderecoEntrega see = new ServicoEnderecoEntrega();
        ServicoFormaPagamento sfp = new ServicoFormaPagamento();
        
        String codigopedido = request.getParameter("codigopedido");
        
        
        
        Pedido pedido = new Pedido();
        ItemPedido itempedido = new ItemPedido();
        ItemVenda itemvenda = new ItemVenda();
        Venda venda = new Venda();
        Cliente cliente = new Cliente();
        FormaPagamento pagamento = new FormaPagamento();
        EnderecoEntrega endereco = new EnderecoEntrega();
        
        List<ItemPedido> listaitens= new ArrayList<ItemPedido>();
        List<Produto> listaprodutos = new ArrayList<Produto>();
        List<Cliente> listaclientes = new ArrayList<Cliente>();
        List<ItemVenda> listaitensvenda = new ArrayList<ItemVenda>();
        
        try {
            //Retorna lista de itens do carrinh
            if((codigopedido != null)||(sessao.getAttribute("codpedido") != null))
                
                //Cadatrar Venda no banco
                pedido = sp.ConsultarPedido(Integer.parseInt(codigopedido));
                cliente = scli.obterClientePorCodigo(pedido.getCodigoCliente());
                pagamento = sfp.consultaPagamento(pedido.getCodigopagamento());
                endereco = see.consultaEndereco(pedido.getCodigoendereco());
                
                venda.setCodigoCliente(cliente.getId());
                venda.setCodigoendereco(endereco.getCodigo());
                venda.setCodigopagamento(pagamento.getCodigo());
                venda.setValorTotal(pedido.getValorTotal());
                venda.setCodigopedido(pedido.getCodigo());
                
                Timestamp dataDeHoje = new Timestamp(System.currentTimeMillis());
                venda.setData(dataDeHoje);
                
                Integer codigovenda = sv.cadatrarVenda(venda);
                
                //Cadastrar Itens na Venda
                
                //Recebe itens do pedido
                listaitens = sio.listarItensPedido(pedido.getCodigo());
                
                //Laço para passar itens para Lista de Itens da venda
                for(int i = 0; i < listaitens.size(); i++){
                   siv.cadastrarItemVenda(codigovenda, listaitens.get(i).getCodigoProduto(), listaitens.get(i).getQuantidade());
                }
                
                listaitensvenda = siv.listarItensVenda(codigovenda);
                venda = sv.ConsultarVenda(codigovenda);
        
                sessao.setAttribute("venda", venda);
                sessao.setAttribute("itensvenda", listaitensvenda);
                sessao.setAttribute("clientevenda", cliente);
                
                sp.atualizarPedido(codigovenda, pedido.getCodigo());
                        
                response.sendRedirect(request.getContextPath() + "/dadosVenda.jsp");
        } catch (Exception e) {
        }

        
    }

}
