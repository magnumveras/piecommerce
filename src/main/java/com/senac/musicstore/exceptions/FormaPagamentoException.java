/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.musicstore.exceptions;

/**
 *
 * @author Magno
 */
//Indica uma Exceção de Venda
public class FormaPagamentoException  extends Exception{
    //Construtor de Exceções que permite informar uma mensagem
    public FormaPagamentoException(String message){
        super(message);
    }
}
