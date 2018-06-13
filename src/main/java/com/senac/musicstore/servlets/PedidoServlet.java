/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.musicstore.servlets;


import com.senac.musicstore.model.Carrinho;
import com.senac.musicstore.model.Cliente;
import com.senac.musicstore.model.EnderecoEntrega;
import com.senac.musicstore.model.ItemCarrinho;
import com.senac.musicstore.model.ItemPedido;
import com.senac.musicstore.model.Pedido;
import com.senac.musicstore.model.Produto;
import com.senac.musicstore.service.ServicoCarrinho;
import com.senac.musicstore.service.ServicoCliente;
import com.senac.musicstore.service.ServicoEnderecoEntrega;
import com.senac.musicstore.service.ServicoItemCarrinho;
import com.senac.musicstore.service.ServicoItemPedido;
import com.senac.musicstore.service.ServicoPedido;
import com.senac.musicstore.service.ServicoProduto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
@WebServlet(name = "PedidoServlet", urlPatterns = {"/finalizarcarrinho"})
public class PedidoServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession sessao = request.getSession();
        ServicoCarrinho sc = new ServicoCarrinho();
        ServicoPedido sped = new ServicoPedido();
        ServicoItemPedido sip = new ServicoItemPedido();
        ServicoItemCarrinho sic = new ServicoItemCarrinho();
        ServicoProduto sp = new ServicoProduto();
        ServicoCliente scli = new ServicoCliente();
        ServicoEnderecoEntrega see = new ServicoEnderecoEntrega();
        
        String codigocarrinho = request.getParameter("codigocarrinho");
       
        
        Carrinho carrinho = new Carrinho();
        ItemCarrinho itemcarrinho = new ItemCarrinho();
        ItemPedido itemvenda = new ItemPedido();
        Pedido pedido = new Pedido();
        Cliente cliente = new Cliente();
        
        List<ItemCarrinho> listaitens= new ArrayList<ItemCarrinho>();
        List<Produto> listaprodutos = new ArrayList<Produto>();
        List<Cliente> listaclientes = new ArrayList<Cliente>();
        
        try {
            //Retorna lista de itens do carrinh
            if(codigocarrinho != null){
                
                if(sessao.getAttribute("enderecoEntrega") == null){
                    Integer codigoendereco = 0;
                    
                    //Retorna carrinho para descobrir código do cliente
                    carrinho = sc.retornaCarrinho(Integer.parseInt(codigocarrinho));
                    cliente = scli.obterClientePorCodigoUsuario(carrinho.getCodigoCliente());
                    
                    String verificaendereco = request.getParameter("endrecook");
                    
                    //Verifica se submit foi realizado na tela de endereço
                    if(verificaendereco != null){
                        sessao.setAttribute("EnderecoEntrega", "ok");
                        
                        String endereco = request.getParameter("endereco");
                        String numero = request.getParameter("numero");
                        String complemento = request.getParameter("complemento");
                        String cep = request.getParameter("cep");
                        String bairro = request.getParameter("bairro");
                        String estado = request.getParameter("estado");
                        String cidade = request.getParameter("cidade");
                        
                        EnderecoEntrega enderecoentrega = new EnderecoEntrega();
                        
                        enderecoentrega.setEndereco(endereco);
                        enderecoentrega.setNumero(numero);
                        enderecoentrega.setComplemento(complemento);
                        enderecoentrega.setCep(cep);
                        enderecoentrega.setBairro(bairro);
                        enderecoentrega.setEstado(estado);
                        enderecoentrega.setCidade(cidade);
                        
                        codigoendereco = see.cadastrarEnderecoEntrega(enderecoentrega);
                        response.sendRedirect(request.getContextPath() + "/dadosPagamento.jsp");
                    }else{
                        response.sendRedirect(request.getContextPath() + "/enderecoEntrega.jsp");
                    }
                    
                    sessao.setAttribute("CodigoEndereco", codigoendereco);
                    sessao.setAttribute("ClienteEndereco", cliente);
                    
                }else if (sessao.getAttribute("dadosPagamento") == null){
                    
                    response.sendRedirect(request.getContextPath() + "/dadosPagamento.jsp");
                }else{
                    listaitens = sic.listarItensCarrinho(Integer.parseInt(codigocarrinho));
               
                    //Retorna topo do carrinho (dados que não se alteram)
                    carrinho = sc.retornaCarrinho(Integer.parseInt(codigocarrinho));
                
                    //Lista todos os produtos da empresa
                    listaprodutos = sp.listarProdutostotais();
                
                    Timestamp data = new Timestamp(System.currentTimeMillis());
                    
                    
            
                    //Atribuindo valores do carrinho para a venda
                    pedido.setCodigoCliente(carrinho.getCodigoCliente());
                    pedido.setData(data);
                    pedido.setValorTotal(carrinho.getValorTotal());
                
                    //Cadastro de Cabeçario de Venda
                    int codigopedido = sped.cadastrarPedido(pedido);
                    pedido.setCodigo(codigopedido);
                
                    //Cadastra Itens na Venda
                    for(int i = 0; i < listaitens.size(); i++){
                        itemvenda.setCodigoVenda(codigopedido);
                        itemvenda.setCodigoProduto(listaitens.get(i).getProduto());
                        itemvenda.setQuantidade(listaitens.get(i).getQuantidade());
                
                    sip.cadastraritemVenda(itemvenda.getCodigoVenda(), itemvenda.getCodigoProduto(), itemvenda.getQuantidade());
                    }
                
                    //Esvazia ItemCarrinho
                    sic.excluirCarrinho(Integer.parseInt(codigocarrinho));
            
                    //Esvazia Carrinho
                    sc.excluirCarrinho(Integer.parseInt(codigocarrinho));
                    response.sendRedirect(request.getContextPath() + "/pedidofinalizado.jsp");
                }
               
            }
   
            //Retorna Cliente da Venda
            //cliente = scli.obterClientePorCodigo(pedido.getCodigoCliente(), Integer.parseInt(codigoempresa));
            
        } catch (Exception e) {
        }

        
    }

}
