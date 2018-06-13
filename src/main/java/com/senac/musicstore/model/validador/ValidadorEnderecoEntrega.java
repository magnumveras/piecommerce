package com.senac.musicstore.model.validador;


import com.senac.musicstore.exceptions.EnderecoEntregaException;
import com.senac.musicstore.exceptions.PedidoException;
import com.senac.musicstore.model.EnderecoEntrega;
import com.senac.musicstore.model.Pedido;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Magno
 */
public class ValidadorEnderecoEntrega {
    
    public static void validar(EnderecoEntrega enderecoentrega) throws EnderecoEntregaException{
        //Realização de Validações de Negocio
        if(enderecoentrega == null){
            throw new EnderecoEntregaException("Não foi informado o endereço de entrega");
        }
        
    }
    
}
