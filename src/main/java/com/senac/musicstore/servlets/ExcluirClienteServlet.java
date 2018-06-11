/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.musicstore.servlets;


import com.senac.musicstore.model.Cliente;
import com.senac.musicstore.service.ServicoCliente;
import com.senac.musicstore.service.ServicoUsuario;
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
@WebServlet(name = "ExcluirClienteServlet", urlPatterns = {"/excluirCliente"})
public class ExcluirClienteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          RequestDispatcher dispatcher
	    = request.getRequestDispatcher("/consultaCliente.jsp");
    dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Instância serviço de servidor para efetuar consulta e ligação com ClienteDAO
        ServicoCliente sc = new ServicoCliente();
        
        ServicoUsuario su = new ServicoUsuario();
        
        //Criação se sessão para retorno em tela
        HttpSession sessao = request.getSession();
        
        Cliente cliente = new Cliente();
        
        //Atribuição de valores digitados na tela de fornecedor e código da empresa
        String cpfcliente = request.getParameter("cpfcliente");
            
        try {
            //Pega cliente com cpf digitado
            cliente = sc.obterClientePorCpf(cpfcliente);
            
            //Exclui esse cliente da tabela de clientes
            sc.excluirCliente(cpfcliente);
            
            //Exclui esse cliente da tabela de usuários (Dependência)
            su.excluirUsuario(cliente.getCodigousuario());
        } catch (Exception e) {
        }
        
        sessao.setAttribute("clienteexcluido", cpfcliente);
        response.sendRedirect(request.getContextPath() + "/consultaCliente.jsp");

        }
           
        
}


