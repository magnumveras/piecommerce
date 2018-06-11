/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.musicstore.servlets;

import com.senac.musicstore.dao.ClienteDAO;
import com.senac.musicstore.dao.UsuarioDAO;
import com.senac.musicstore.model.Cliente;
import com.senac.musicstore.model.Usuario;
import java.io.IOException;
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
 * @author Debaza
 */
@WebServlet(name = "ConsultarClienteServlet", urlPatterns = {"/clientes"})
public class ConsultarClienteServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Cliente> clientes = new ArrayList<>();
        List<Usuario> usuarios = new ArrayList<>();
        
        ClienteDAO cd = new ClienteDAO();
        UsuarioDAO ud = new UsuarioDAO();
        
        try {
            clientes = cd.listarClientes();
            usuarios = ud.listarUsuarios();
        } catch (Exception ex) {
            Logger.getLogger(ConsultarClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("listaClientes", clientes);
        request.setAttribute("listaUsuarios", usuarios);
        RequestDispatcher dispatcher = request.getRequestDispatcher("consultaCliente.jsp");
    dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Cliente> clientes = new ArrayList<>();
        List<Usuario> usuarios = new ArrayList<>();
        
        ClienteDAO cd = new ClienteDAO();
        UsuarioDAO ud = new UsuarioDAO();
        
        HttpSession sessao = request.getSession();
        
        //Atribuição de valores digitados na tela de fornecedor e código da empresa
        String cliente = request.getParameter("cliente");

        try {
            clientes = (ArrayList<Cliente>) cd.listarCliente(cliente);
            usuarios = (ArrayList<Usuario>) ud.listarUsuarios();
        } catch (Exception e) {
        }
        
        request.setAttribute("listaClientes", clientes);
        request.setAttribute("listaUsuarios", usuarios);
        RequestDispatcher dispatcher = request.getRequestDispatcher("consultaCliente.jsp");
    dispatcher.forward(request, response);
        
    }


}
