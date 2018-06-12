/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.musicstore.servlets;

import com.senac.musicstore.model.Carrinho;
import com.senac.musicstore.model.Cliente;
import com.senac.musicstore.model.Usuario;
import com.senac.musicstore.service.ServicoCarrinho;
import com.senac.musicstore.service.ServicoCliente;
import com.senac.musicstore.service.ServicoItemCarrinho;
import com.senac.musicstore.service.ServicoUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
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
        HttpSession sessao = request.getSession();
        int codigocarrinho = 0;
        
        String usuario = request.getParameter("usuario");
        String senha = request.getParameter("senha");
      
        u.setLogin(usuario);
        u.setSenha(senha);

        try {
            verifica = su.retornaUsuarioLogin(u.getLogin(), u.getSenha());
                
            //Retorna usuário completo para verificar perfil do mesmo
            usuarioperil = su.retornaUsuarioLogin(u.getLogin(), u.getSenha());
        } catch (Exception e) {
                
            
        }
        
            if (verifica != null) {
                sessao.setAttribute("Usuario", usuario);
                
                if(verifica.getCodigoperfil() != 3){
                    sessao.setAttribute("loginoperacao", "loginoperacao");
                }else{
                    sessao.setAttribute("logincliente", "logincliente");
                }
                
                //Envia perfil de usuário para as páginas
                sessao.setAttribute("perfilusuario", usuarioperil);
                
                Carrinho carrinho = new Carrinho();
                Cliente cliente = new Cliente();
                
                try {
                    cliente = sc.obterClientePorCodigoUsuario(usuarioperil.getCodigo());
                } catch (Exception e) {
                }
                
                carrinho.setCliente(cliente.getId());
                
                Timestamp dataDeHoje = new Timestamp(System.currentTimeMillis());
                carrinho.setData(dataDeHoje);
                carrinho.setValorTotal(0.00);
                
                try {
                   codigocarrinho = scar.cadastrarCarrinho(carrinho);
                } catch (Exception e) {
                }
                
                sessao.setAttribute("carrinhocliente", carrinho);
                sessao.setAttribute("codigocarrinho", codigocarrinho);
                sessao.setAttribute("clientecarrinho", cliente);
                
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
