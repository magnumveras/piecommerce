package com.senac.musicstore.model.validador;


import com.senac.musicstore.exceptions.PedidoException;
import com.senac.musicstore.model.Pedido;
import com.senac.musicstore.model.Venda;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Magno
 */
public class ValidadorVenda {
    
    public static void validar(Venda venda) throws PedidoException{
        //Realização de Validações de Negocio
        if(venda == null){
            throw new PedidoException("Não foi informada a venda");
        }
        
    }
    
}
