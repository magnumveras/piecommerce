/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.musicstore.model.validador;

import com.senac.musicstore.exceptions.ClienteException;
import com.senac.musicstore.model.Usuario;

/**
 *
 * @author Magno
 */
public class ValidadorUsuario {
    public static void validar(Usuario usuario) throws ClienteException{
        //Realização de Validações de Negocio
        if(usuario == null){
            throw new ClienteException("Não foi informado um Usuário");
        }
        
        if(usuario.getNome()== null || "".equals(usuario.getNome())){
            throw new ClienteException("Não foi informado o nome do Usuário");
        }
        
        if (usuario.getLogin() == null
                || "".equals(usuario.getLogin())) {
            throw new ClienteException("É necessário informar um "
                    + "login de usuário");
        }
        
        if (usuario.getSenha()== null
                || "".equals(usuario.getSenha())) {
            throw new ClienteException("É necessário informar a "
                    + "senha do usuário");
        }
        
    }
}