/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.musicstore.dao;

import com.senac.musicstore.model.Usuario;
import com.senac.musicstore.utils.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author magno
 */
public class UsuarioDAO {
    
    ConexaoBanco conexaoBanco = new ConexaoBanco();
    Connection conn = conexaoBanco.createConnection();
    
        public Integer inserirUsuario(Usuario usuario){
        
         String query = " insert into usuario (nome, login, senha, codigoperfil)"
        + " values (?, ?, ?, ?)";
        
        
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getLogin());
            preparedStatement.setString(3, usuario.getSenha());
            preparedStatement.setInt(4, usuario.getCodigoperfil());
       
            preparedStatement.executeUpdate();
            
            //Retorna código gerado
            ResultSet  rs = preparedStatement.getGeneratedKeys();
            rs.next();
            int codigo = rs.getInt(1);
            preparedStatement.close();
            
            return codigo;
        } catch (SQLException ex) {
            System.out.println("Erro ao salvar usuário"+ex);
            return null;
        }
        
        
    }
    
        public List<Usuario> listarUsuarios() throws Exception{
        System.out.println("Iniciando listagem de cliente...");
        List<Usuario> lista = new ArrayList<>();
        String query = "";
        
        boolean vazio = true;
              
        query = "SELECT * FROM usuario";
        
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Usuario usuario = new Usuario();
                
                usuario.setCodigo(rs.getInt("codigo"));
                usuario.setNome(rs.getString("nome"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setCodigoperfil(rs.getInt("codigoperfil"));
                
                lista.add(usuario);
            }
            
        } catch (SQLException ex) {
            throw new Exception("Erro ao listar usuáio", ex);
        }

        
        return lista;
    
    }
        
    public Usuario encontrarUsuarioPorCodigo(int codigo) throws Exception{
        System.out.println("Iniciando listagem de cliente...");
        
        Usuario usuario = new Usuario();
        String query = "SELECT * FROM usuario WHERE codigo=?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, codigo);
           
            
            ResultSet rs = preparedStatement.executeQuery();
            
            System.out.println("Busca efetuada com sucesso");
            
            while (rs.next()){
                usuario.setCodigo(rs.getInt(1));
                usuario.setNome(rs.getString(2));
                usuario.setLogin(rs.getString(3));
                usuario.setSenha(rs.getString(4));
                usuario.setCodigoperfil(rs.getInt(5));
            }
            
        } catch (SQLException ex) {
            throw new Exception("Erro ao listar Usuário", ex);
        }

        
        return usuario;
    }
    
        public Usuario encontrarUsuarioPorLogin(String login) throws Exception{
        System.out.println("Iniciando listagem de cliente...");
        
        Usuario usuario = new Usuario();
        String query = "SELECT * FROM usuario WHERE login=?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, login);
           
            
            ResultSet rs = preparedStatement.executeQuery();
            
            System.out.println("Busca efetuada com sucesso");
            
            while (rs.next()){
                usuario.setCodigo(rs.getInt(1));
                usuario.setNome(rs.getString(2));
                usuario.setLogin(rs.getString(3));
                usuario.setSenha(rs.getString(4));
                usuario.setCodigoperfil(rs.getInt(5));
            }
            
        } catch (SQLException ex) {
            throw new Exception("Erro ao listar Usuário", ex);
        }

        
        return usuario;
    }

    public void alterarUsuarioSenha(String login, String senha, Integer codigo) throws Exception {
        System.out.println("Iniciando processo de atualização de cliente...");
         String query = "UPDATE usuario SET login=?, senha=? "
                 + " WHERE codigo=?";
       
        try {
                PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
                preparedStatement.setString(1, login);
                preparedStatement.setString(2, senha);
                preparedStatement.setInt(3, codigo);
                
                preparedStatement.executeUpdate();
                preparedStatement.close();
        } catch (SQLException ex) {
            throw new Exception("Erro ao atualizar cliente!", ex);
        }

    }
    
    public void deletarUsuario(int codigo) throws Exception{
        System.out.println("Deletando usuário de código: "+codigo);
        String query = "DELETE FROM usuario WHERE codigo=?";


        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, codigo);
      
                preparedStatement.execute();
                System.out.println("Usuário deletado");
            } catch (SQLException ex) {
                throw new Exception("Erro ao deletar o usuário", ex);
        
            }
    }
    
    //encontra usuário por código
    public Usuario encontrarUsuario(String login, String senha){//retorna um item
        List<Usuario> lista = new ArrayList<>();
        Usuario usuario = new Usuario();
        System.out.println("Buscando Usuário na base de dados...");
        String query = "SELECT * FROM usuario WHERE login=? and senha=?";
        
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            preparedStatement.setString(1,login);
            preparedStatement.setString(2,senha);

            
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                usuario.setCodigo(rs.getInt(1));
                usuario.setNome(rs.getString(2));
                usuario.setLogin(rs.getString(3));
                usuario.setSenha(rs.getString(4));
                usuario.setCodigoperfil(rs.getInt(5));
                
            }else{
                return null;
            }
            
            System.out.println("Busca efetuada com sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar usuario"+ex);
            
        }        
        return usuario;
    }
}
