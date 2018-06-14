package com.senac.musicstore.model.validador;



import com.senac.musicstore.exceptions.FormaPagamentoException;
import com.senac.musicstore.model.FormaPagamento;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Magno
 */
public class ValidadorFormaPagamento {
    
    public static void validar(FormaPagamento formapagamento) throws FormaPagamentoException{
        //Realização de Validações de Negocio
        if(formapagamento == null){
            throw new FormaPagamentoException("Não foi informado o endereço de entrega");
        }
        
    }
    
}
