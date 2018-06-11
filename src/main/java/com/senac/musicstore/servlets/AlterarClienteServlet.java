/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.musicstore.servlets;

import com.senac.musicstore.model.Cliente;
import com.senac.musicstore.model.Usuario;
import com.senac.musicstore.service.ServicoCliente;
import com.senac.musicstore.service.ServicoUsuario;
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
@WebServlet(name = "AlterarClienteServlet", urlPatterns = {"/alterarCliente"})
public class AlterarClienteServlet extends HttpServlet {

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
            sessao.removeAttribute("Altera");
        } else {
            destino = "cadastroCliente.jsp";
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(destino);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Instância Objeto Cliente
        Cliente cliente = new Cliente();
        Usuario usuario = new Usuario();
        
        //Instância serviço de servidor para efetuar consulta e ligação com ClienteDAO
        ServicoCliente sc = new ServicoCliente();
        ServicoUsuario su = new ServicoUsuario();
        
        List<Usuario> listausuario = new ArrayList();
        
        //Criação se sessão para retorno em tela
        request.setCharacterEncoding("UTF-8");
        HttpSession sessao = request.getSession();
        
        //Para verificação se é alteração
        String alteracao = "";
        try {
            alteracao = sessao.getAttribute("Altera").toString();
        } catch (Exception e) {
        }
        
        if ((alteracao == null)||(alteracao.length() == 0)){
              //Atribuição de valores digitados na tela de fornecedor e código da empresa
            String cpfcliente = request.getParameter("cpfcliente");
            
            try {
            cliente = sc.obterClientePorCpf(cpfcliente);
            usuario = su.obterUsuarioPorCodigo(cliente.getCodigousuario());
            } catch (Exception e) {
            }
        
            sessao.setAttribute("cli", cliente);
            sessao.setAttribute("usu", usuario);
            sessao.setAttribute("Altera", "alteracao");
            response.sendRedirect(request.getContextPath() + "/cadastroCliente.jsp");
            
        }else{
            Usuario usu = new Usuario();
            Cliente cli = new Cliente();
            usu = (Usuario) sessao.getAttribute("usu");
            cli = (Cliente) sessao.getAttribute("cli");
            
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
            String usuarioaltera = request.getParameter("usuario");
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
            
            //Para verificar se usuário já existe
            Usuario u = new Usuario();
            
            //Verifica se usuario digitado é o mesmo do cliente
            if(!usuarioaltera.equals(usu.getLogin())){
              //Verifica se usuario existe na tabela

                try {
                    u = su.obterUsuarioPorLogin(usuarioaltera);
                    } catch (Exception e){
                }
              
                //Verifica se já existe login com o mesmo nome de outro usuário
                if(u.getLogin() != null){
                    sessao.setAttribute("loginexiste", "Login já cadastrado!");  
                }else{
               
                    //Altera usuário
                    int codigousuario = 0;
                    try {
                        su.alterarUsuarioSenha(usuarioaltera, senha, cli.getCodigousuario());
                    } catch (Exception e) {
                    }
                    if(sexo.equalsIgnoreCase("m")){
                        cliente.setSexo("Masculino");  
                    }else{
                        cliente.setSexo("Feminino");
                    }
                
                    try {
                        cliente.setNome(nome);
                        cliente.setSobrenome(sobrenome);
                        cliente.setCpf(cpf);
                        cliente.setRg(rg);
                        cliente.setDatanascimento(nascimento);
                        cliente.setTelefone(telefone);
                        cliente.setEmail(email);
                        cliente.setEndereco(endereco);
                        cliente.setNumero(numero);
                        cliente.setBairro(bairro);
                        cliente.setComplemento(complemento);
                        cliente.setCidade(cidade);
                        cliente.setEstado(estado);
                        cliente.setCep(cep);
                        cliente.setOfertas(verificaofertas);
                        
                        
                        sc.atualizarCliente(cliente, cli.getId(), cli.getCodigousuario());
                        sessao.removeAttribute("loginexiste");
                        sessao.setAttribute("cliente", cliente);
                    } catch (Exception e) {
                    }
            
                }
                
            }else{
                //Altera usuário
                    int codigousuario = 0;
                    try {
                        su.alterarUsuarioSenha(usuarioaltera, senha, cli.getCodigousuario());
                    } catch (Exception e) {
                    }
                    if(sexo.equalsIgnoreCase("m")){
                        cliente.setSexo("Masculino");  
                    }else{
                        cliente.setSexo("Feminino");
                    }
                
                    try {
                        cliente.setNome(nome);
                        cliente.setSobrenome(sobrenome);
                        cliente.setCpf(cpf);
                        cliente.setRg(rg);
                        cliente.setDatanascimento(nascimento);
                        cliente.setTelefone(telefone);
                        cliente.setEmail(email);
                        cliente.setEndereco(endereco);
                        cliente.setNumero(numero);
                        cliente.setBairro(bairro);
                        cliente.setComplemento(complemento);
                        cliente.setCidade(cidade);
                        cliente.setEstado(estado);
                        cliente.setCep(cep);
                        cliente.setOfertas(verificaofertas);
                        cliente.setCodigousuario(usu.getCodigo());
              
                        sc.atualizarCliente(cliente, cli.getId(), cli.getCodigousuario());
                        sessao.removeAttribute("loginexiste");
                        sessao.setAttribute("cliente", cliente);
                    } catch (Exception e) {
                    }
                    
            }
            
            
        }
        
        response.sendRedirect(request.getContextPath() + "/alterarCliente");
    }

}
