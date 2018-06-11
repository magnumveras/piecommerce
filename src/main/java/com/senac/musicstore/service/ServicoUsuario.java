    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.musicstore.service;


import com.senac.musicstore.dao.UsuarioDAO;
import com.senac.musicstore.exceptions.DataSourceException;
import com.senac.musicstore.exceptions.UsuarioException;
import com.senac.musicstore.model.Usuario;
import com.senac.musicstore.model.validador.ValidadorUsuario;

import java.util.List;
/**
 *
 * @author magno
 */

//Classe de servico do cliente
public class ServicoUsuario {
     UsuarioDAO usuariodao = new UsuarioDAO();
     
    public Integer cadastrarUsuario(Usuario usuario) throws UsuarioException, DataSourceException, Exception {
        int u;
        ValidadorUsuario.validar(usuario);

        try {
           u = usuariodao.inserirUsuario(usuario);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
        
        return u;
    }
    
        //Realiza a pesquisa de um usuário por código na fonte de dados
    public Usuario obterUsuarioPorCodigo(Integer codigo) throws UsuarioException, DataSourceException, Exception {
        try {
            if (codigo == null){
                throw new Exception("Campo código vazio!");
            } else {
                return usuariodao.encontrarUsuarioPorCodigo(codigo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }
    
        public Usuario obterUsuarioPorLogin(String login) throws UsuarioException, DataSourceException, Exception {
        try {
            if (login.length() == 0){
                throw new Exception("Campo código vazio!");
            } else {
                return usuariodao.encontrarUsuarioPorLogin(login);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }
        
     public void alterarUsuarioSenha(String login, String senha, Integer codigo) throws UsuarioException, DataSourceException, Exception {
        try {
            if (login.length() == 0){
                throw new Exception("Campo código vazio!");
            } else {
                usuariodao.alterarUsuarioSenha(login, senha, codigo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }
     
        //Exclui o cliente com ID informado do mock
    public void excluirUsuario(int codigo) throws UsuarioException, DataSourceException, Exception {
        try {
            //Solicita ao DAO a exclusão do cliente informado
            usuariodao.deletarUsuario(codigo);
        } catch (Exception e) {
            //Imprime qualquer erro técnico no console e devolve
            //uma exceção e uma mensagem amigável a camada de visão
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }

}
