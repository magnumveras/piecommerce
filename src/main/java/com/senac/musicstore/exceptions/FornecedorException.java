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
public class FornecedorException extends Exception{
    //Construtor de exceções que permite mostrar uma mensagem
    public FornecedorException(String message){
        super(message);
    }
    
}
