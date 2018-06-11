/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.musicstore.model;

/**
 *
 * @author magno
 */
public class Usuario {
    
   private int codigo;
   private String nome;
   private String login;
   private String senha;
   private int codigoperfil;

    public Usuario() {
        super();
    }

    public Usuario(int codigo, String nome, String login, String senha, int codigoperfil) {
        this.codigo = codigo;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.codigoperfil = codigoperfil;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getCodigoperfil() {
        return codigoperfil;
    }

    public void setCodigoperfil(int codigoperfil) {
        this.codigoperfil = codigoperfil;
    }
    
    
}
