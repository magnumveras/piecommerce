/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.musicstore.servlets;


import com.senac.musicstore.model.Carrinho;
import com.senac.musicstore.model.Cliente;
import com.senac.musicstore.model.EnderecoEntrega;
import com.senac.musicstore.model.FormaPagamento;
import com.senac.musicstore.model.ItemCarrinho;
import com.senac.musicstore.model.ItemPedido;
import com.senac.musicstore.model.Pedido;
import com.senac.musicstore.model.Produto;
import com.senac.musicstore.model.Usuario;
import com.senac.musicstore.service.ServicoCarrinho;
import com.senac.musicstore.service.ServicoCliente;
import com.senac.musicstore.service.ServicoEnderecoEntrega;
import com.senac.musicstore.service.ServicoFormaPagamento;
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
        ServicoFormaPagamento sfp = new ServicoFormaPagamento();
        
        String codigocarrinho = request.getParameter("codigocarrinho");
        
        if(codigocarrinho != null){
            sessao.setAttribute("codcarrinho", codigocarrinho);
        }
        
        
        Carrinho carrinho = new Carrinho();
        ItemCarrinho itemcarrinho = new ItemCarrinho();
        ItemPedido itempedido = new ItemPedido();
        Pedido pedido = new Pedido();
        Cliente cliente = new Cliente();
        
        List<ItemCarrinho> listaitens= new ArrayList<ItemCarrinho>();
        List<Produto> listaprodutos = new ArrayList<Produto>();
        List<Cliente> listaclientes = new ArrayList<Cliente>();
        
        try {
            //Retorna lista de itens do carrinh
            if((codigocarrinho != null)||(sessao.getAttribute("codcarrinho") != null)){
                if(sessao.getAttribute("codcarrinho") != null){
                    codigocarrinho = (String) sessao.getAttribute("codcarrinho");
                }
                
                if(sessao.getAttribute("EnderecoEntrega") == null){
                    Integer codigoendereco = 0;
                    
                    //Retorna carrinho para descobrir código do cliente
                    carrinho = sc.retornaCarrinho(Integer.parseInt(codigocarrinho));
                    cliente = scli.obterClientePorCodigo(carrinho.getCodigoCliente());
                    
                    String verificaendereco = request.getParameter("endrecook");
                            
                    //Guarda valor total do carrinho para uso no método de pagamento
                    Double valortotal = carrinho.getValorTotal();
                    sessao.setAttribute("valortotal", valortotal);
                    
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
                        sessao.setAttribute("CodigoEndereco", codigoendereco);
                        response.sendRedirect(request.getContextPath() + "/dadosPagamento.jsp");
                    }else{
                        sessao.setAttribute("ClienteEndereco", cliente);
                        response.sendRedirect(request.getContextPath() + "/enderecoEntrega.jsp");
                    }
             
                }else if (sessao.getAttribute("dadosPagamento") == null){
                    
                    String verificapagamento = request.getParameter("pagamentos");
                    
                    if(verificapagamento != null){
                        sessao.setAttribute("dadosPagamento", "ok");
                        
                        String tipocartao = request.getParameter("cartao");
                        String numerocartao = request.getParameter("numerocartao");
                        String nomecartao = request.getParameter("nomecartao");
                        String codigoseguranca = request.getParameter("codigoseguranca");
                        String vencimento = request.getParameter("vencimento");
                        String parcelas = request.getParameter("parcelas");
                        
                        FormaPagamento forma = new FormaPagamento();
                        
                        if(tipocartao.equalsIgnoreCase("cartaocredito")){
                            forma.setCartaocredito("1");
                        }else if(tipocartao.equalsIgnoreCase("cartaodebito")){
                            forma.setCartaodebito("1");
                        }
                        
                        forma.setNumerocartao(numerocartao);
                        forma.setNomecartao(nomecartao);
                        forma.setCodigoseguranca(codigoseguranca);
                        forma.setVencimento(vencimento);
                        forma.setParcelas(parcelas);
                        
                        int codigoformapagamento = sfp.cadastrarFormaPagamento(forma);
                        ////////////////////////////////////////////////////////////////////////////////////
                        listaitens = sic.listarItensCarrinho(Integer.parseInt(codigocarrinho));
               
                        //Retorna topo do carrinho (dados que não se alteram)
                        carrinho = sc.retornaCarrinho(Integer.parseInt(codigocarrinho));
                
                       //Lista todos os produtos da empresa
                        listaprodutos = sp.listarProdutostotais();
                
                        Timestamp data = new Timestamp(System.currentTimeMillis());
                        
                        
                        String endereco = request.getParameter("endereco");
                       
       
                        //Atribuindo valores do carrinho para a venda
                        pedido.setCodigoCliente(carrinho.getCodigoCliente());
                        pedido.setData(data);
                        pedido.setValorTotal(carrinho.getValorTotal());
                        pedido.setCodigoendereco(Integer.parseInt(endereco));
                        pedido.setCodigopagamento(codigoformapagamento);
                        
                        //Cadastro de Cabeçario de Venda
                        int codigopedido = sped.cadastrarPedido(pedido);
                        pedido.setCodigo(codigopedido);
                
                        //Cadastra Itens na Venda
                        for(int i = 0; i < listaitens.size(); i++){
                            itempedido.setCodigopedido(codigopedido);
                            itempedido.setCodigoProduto(listaitens.get(i).getProduto());
                            itempedido.setQuantidade(listaitens.get(i).getQuantidade());
                
                        sip.cadastraritemPedido(itempedido.getCodigopedido(), itempedido.getCodigoProduto(), itempedido.getQuantidade());
                        }
                
                        //Esvazia ItemCarrinho
                        sic.excluirItensCarrinho(Integer.parseInt(codigocarrinho));
            
                        //Esvazia Carrinho
                        sc.excluirCarrinho(Integer.parseInt(codigocarrinho));
                        
                        Pedido ped = new Pedido();
                        Cliente cli = new Cliente();
                        
                        ped = sped.ConsultarPedido(codigopedido);
                        cli = scli.obterClientePorCodigo(ped.getCodigoCliente());
                        
                        
                        //Retorna pedido para tela de cliente
                        sessao.setAttribute("pedidocliente", ped);
                        sessao.setAttribute("clientepedido", cli);
                        
                        //Remove sessões utilizadas
                        sessao.removeAttribute("dadosPagamento");
                        sessao.removeAttribute("CodigoEndereco");
                        sessao.removeAttribute("ClienteEndereco");
                        sessao.removeAttribute("EnderecoEntrega");
                        sessao.removeAttribute("codcarrinho");
                        sessao.removeAttribute("carrinhocadastrado");
                        sessao.removeAttribute("listacarrinhocadastrado");
                        
                       
                        String codigousuario = request.getParameter("codigousuario");
                        
                        cliente = scli.obterClientePorCodigoUsuario(Integer.parseInt(codigousuario));
                        
                        Timestamp dataDeHoje = new Timestamp(System.currentTimeMillis());
                        
                        carrinho.setCliente(cliente.getId());
                        carrinho.setData(dataDeHoje);
                        Double valor = 0.0;
                        carrinho.setValorTotal(valor);
                        
                        int codcarrinho = sc.cadastrarCarrinho(carrinho);
                        carrinho = sc.retornaCarrinho(codcarrinho);
                        
                        sessao.setAttribute("carrinhocadastrado", carrinho);
                        sessao.setAttribute("codigocarrinho", codcarrinho);
                        
              
                        response.sendRedirect(request.getContextPath() + "/dadosPedido.jsp");
                    }
                    
                    
                }
               
            }
   
            //Retorna Cliente da Venda
            //cliente = scli.obterClientePorCodigo(pedido.getCodigoCliente(), Integer.parseInt(codigoempresa));
            
        } catch (Exception e) {
        }

        
    }

}
