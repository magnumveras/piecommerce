/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.musicstore.exceptions;

/**
 *
 * @author magno
 */
public class UsuarioException extends Exception{
    //Construtor de exceções que permite informar uma mensagem
    public UsuarioException(String message){
        super(message);
    }
}
