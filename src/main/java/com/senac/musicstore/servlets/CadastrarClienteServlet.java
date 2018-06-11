/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.musicstore.servlets;

import com.senac.musicstore.dao.ClienteDAO;
import com.senac.musicstore.model.Cliente;
import com.senac.musicstore.model.Usuario;
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
 * @author Debaza
 */
@WebServlet(name = "CadastrarClienteServlet", urlPatterns = {"/cadastroCliente"})
public class CadastrarClienteServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String destino;
        
        HttpSession sessao = request.getSession();
        if (sessao.getAttribute("cliente") != null) {
            request.setAttribute("cliente", sessao.getAttribute("cliente"));
            // Remove o atributo da sessao para usuario nao ficar preso na tela de resultados
            sessao.removeAttribute("cliente");
            
            destino = "/clientes";
        } else {
            destino = "cadastroCliente.jsp";
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(destino);
        dispatcher.forward(request, response);
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String nome = request.getParameter("nome");
        String sobrenome = request.getParameter("sobrenome");
        String sexo = request.getParameter("sexo");
        String nascimento = request.getParameter("nascimento");
        System.out.println(request.getParameter("nascimento"));
        String cpf = request.getParameter("cpf");
        String rg = request.getParameter("rg");
        String telefone = request.getParameter("telefone");
        String email = request.getParameter("email");
        String numero = request.getParameter("numero");
        String bairro = request.getParameter("bairro");
        String complemento = request.getParameter("complemento");
        String endereco = request.getParameter("endereco");
        String cidade = request.getParameter("cidade");
        String cep = request.getParameter("cep");
        String estado = request.getParameter("estado");
        String usuario = request.getParameter("usuario");
        String senha = request.getParameter("senha");
        String ofertas = request.getParameter("ofertas");
        
        boolean verificaofertas = false;
        
        if (ofertas == null){
            verificaofertas = false;
            ofertas = "False";
        }
        
        if(ofertas.equalsIgnoreCase("True")){
            verificaofertas = true;
        }
        
        Cliente novoCliente = new Cliente();
        Cliente clienteexiste = new Cliente();
        Usuario novousuario = new Usuario();
        
        ServicoCliente sc = new ServicoCliente();
        ServicoUsuario su = new ServicoUsuario();
        
        HttpSession sessao = request.getSession();
        
        try {
            clienteexiste = sc.obterClientePorCpf(cpf);
        } catch (Exception e) {
        }
        
                
        if(clienteexiste.getCpf() != null){
            sessao.setAttribute("cpfexiste", "CPF já cadastrado!");
            sessao.removeAttribute("loginexiste");
        }else{
            //Pega novo usuário
            novousuario.setCodigoperfil(3);
            novousuario.setNome(nome + " " + sobrenome);
            novousuario.setLogin(usuario);
            novousuario.setSenha(senha);
            
            if(telefone.length() == 0){
                telefone = "";
            }
            
            if(email.length() == 0){
                email = "";
            }
            
            if(nascimento.length() == 0){
                nascimento = "";
            }
            
            if(complemento.length() == 0){
                complemento = "";
            }
            
            //Verifica se usuário já existe
            Usuario u = new Usuario();
            try {
                u = su.obterUsuarioPorLogin(novousuario.getLogin());
            } catch (Exception e) {
            }
            
            //Verifica se já existe login com o mesmo nome
            if(u.getLogin() != null){
                sessao.setAttribute("loginexiste", "Login já cadastrado!");
                sessao.removeAttribute("cpfexiste");
            }else{
                //Cadastra novo usuário caso ele não exista
                int codigousuario = 0;
                try {
                    codigousuario = su.cadastrarUsuario(novousuario);
                } catch (Exception e) {
                }
                if(sexo.equalsIgnoreCase("m")){
                    novoCliente.setSexo("Masculino");  
                }else{
                    novoCliente.setSexo("Feminino");
                }
                
                novoCliente.setNome(nome);
                novoCliente.setSobrenome(sobrenome);
                novoCliente.setDatanascimento(nascimento);
                novoCliente.setCpf(cpf);
                novoCliente.setRg(rg);
                novoCliente.setTelefone(telefone);
                novoCliente.setEmail(email);
                novoCliente.setNumero(numero);
                novoCliente.setBairro(bairro);
                novoCliente.setComplemento(complemento);
                novoCliente.setEndereco(endereco);
                novoCliente.setCidade(cidade);
                novoCliente.setCep(cep);
                novoCliente.setEstado(estado);
                novoCliente.setOfertas(verificaofertas);
                novoCliente.setCodigousuario(codigousuario);
                
                //Insere novo cliente
                ClienteDAO clientedao = new ClienteDAO();
                clientedao.inserirCliente(novoCliente);
        
                sessao.removeAttribute("cpfexiste");
                sessao.removeAttribute("loginexiste");
                sessao.setAttribute("cliente", novoCliente);
            }
        }
        
        response.sendRedirect(request.getContextPath() + "/cadastroCliente");
    
    }
    
}
