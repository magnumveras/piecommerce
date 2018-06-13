/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.musicstore.servlets;

import com.senac.musicstore.exceptions.DataSourceException;
import com.senac.musicstore.exceptions.ProdutoException;
import com.senac.musicstore.model.Carrinho;
import com.senac.musicstore.model.Cliente;
import com.senac.musicstore.model.ItemCarrinho;
import com.senac.musicstore.model.Produto;
import com.senac.musicstore.model.Usuario;
import com.senac.musicstore.service.ServicoCarrinho;
import com.senac.musicstore.service.ServicoCliente;
import com.senac.musicstore.service.ServicoItemCarrinho;
import com.senac.musicstore.service.ServicoProduto;
import com.senac.musicstore.service.ServicoUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          RequestDispatcher dispatcher
	    = request.getRequestDispatcher("/index.jsp");
    dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Usuario u = new Usuario();
        Usuario verifica = new Usuario();
        Usuario usuarioperil = new Usuario();
        ServicoUsuario su = new ServicoUsuario();
        ServicoCliente sc = new ServicoCliente();
        ServicoCarrinho scar = new ServicoCarrinho();
        ServicoItemCarrinho sic = new ServicoItemCarrinho();
        ServicoProduto sp = new ServicoProduto();
        HttpSession sessao = request.getSession();
        List<ItemCarrinho> listaitens = new ArrayList<ItemCarrinho>();
        List<ItemCarrinho> itenscadastrados = new ArrayList<ItemCarrinho>();
        List<Produto> listaprodutos = new ArrayList<Produto>();
        int codigocarrinho = 0;
        
        String usuario = request.getParameter("usuario");
        String senha = request.getParameter("senha");
      
        u.setLogin(usuario);
        u.setSenha(senha);

        try {
            verifica = su.retornaUsuarioLogin(u.getLogin(), u.getSenha());
                
            //Retorna usuário completo para verificar perfil do mesmo
            usuarioperil = su.retornaUsuarioLogin(u.getLogin(), u.getSenha());
            listaprodutos = sp.listarProdutostotais();
        } catch (Exception e) {
                
            
        }
        
            if (verifica != null) {
                sessao.setAttribute("Usuario", usuario);
                
                if(verifica.getCodigoperfil() != 3){
                    sessao.setAttribute("loginoperacao", "loginoperacao");
                }else{
                    sessao.setAttribute("logincliente", "logincliente");
                    
                    Carrinho carrinho = new Carrinho();
                    Cliente cliente = new Cliente();
                
                    try {
                        cliente = sc.obterClientePorCodigoUsuario(usuarioperil.getCodigo());
                    }    catch (Exception e) {
                    }
                
                    Timestamp dataDeHoje = new Timestamp(System.currentTimeMillis());
                
                    Timestamp datah = new Timestamp(System.currentTimeMillis());
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
                    String data = format.format(datah.getTime()); 

                    try {
                        carrinho = scar.consultaCarrinhoPorClienteData(cliente.getId(), data);
                    } catch (Exception e) {
                    }
                
                    //////////////////////////////*********************////////////////////////////////////////////////////////
                    //Verifica se já não existe carrinho cadastrado para cliente em questão
                    if(carrinho.getCodigo() != null){
                        try {
                            listaitens = sic.listarItensCarrinho(carrinho.getCodigo());
                     
                        } catch (Exception e) {
                        }
                    
                    }else{
                        carrinho.setCliente(cliente.getId());
                        carrinho.setData(dataDeHoje);
                        Double valor = 0.0;
                    
                        carrinho.setValorTotal(valor);
                
                        try {
                            codigocarrinho = scar.cadastrarCarrinho(carrinho);
                        } catch (Exception e) {
                        }
                
                        //Verifica se carrinho já foi iniciado sem cliente
                        if(sessao.getAttribute("carrinhoiniciado") != null){
                            listaitens = (List<ItemCarrinho>) sessao.getAttribute("itenscarrinho");
                            for(int i = 0; i < listaitens.size(); i++){
                                try {
                                    sic.cadastraritemCarrinho(codigocarrinho, listaitens.get(i).getProduto(), listaitens.get(i).getQuantidade());
                                } catch (Exception e) {
                                }
                            }
                        }
                    
                        //Recebe itens cadastrados no carrinho
                        try {
                            itenscadastrados = sic.listarItensCarrinho(codigocarrinho);
                        } catch (Exception e) {
                        }
                    
                        //Calcular Valor Total do Carrinho e se coloca no carrinho
                        double soma = 0;
                        for(int i = 0; i < itenscadastrados.size(); i++){
                            Produto p = new Produto();
                            int codigoproduto = itenscadastrados.get(i).getProduto();
                            int quantidade = itenscadastrados.get(i).getQuantidade();
                        
                            try {
                                p = sp.encontrarProdutoPorCodigo(codigoproduto);
                            } catch (Exception e) {
                            }
                        
                            soma += p.getPrecovenda() * quantidade;
                        }
                    
                        try {
                            scar.alteraValorCarrinho(soma, codigocarrinho);
                            carrinho = scar.retornaCarrinho(codigocarrinho);
                        } catch (Exception e) {
                        }
                    sessao.removeAttribute("itenscarrinho");
                    sessao.removeAttribute("carrinhoiniciado");
                    sessao.setAttribute("carrinhocadastrado", carrinho);
                    sessao.setAttribute("codigocarrinho", codigocarrinho);
                    sessao.setAttribute("clientecarrinho", cliente);
                    sessao.setAttribute("listacarrinhocadastrado", itenscadastrados);
                    sessao.setAttribute("listaprodutos", listaprodutos);
                }
            }
                
                //Envia perfil de usuário para as páginas
                sessao.setAttribute("perfilusuario", usuarioperil);

                response.sendRedirect(request.getContextPath() + "/index.jsp");
                sessao.removeAttribute("erro");
            }else{
                sessao.setAttribute("erro", "Verifique usuário ou senha!");
                RequestDispatcher dispatcher
                = request.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
                System.out.println("Usuário não encontrado!");
            }
        
    }

}
