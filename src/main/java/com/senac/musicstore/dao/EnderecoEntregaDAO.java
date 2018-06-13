/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.musicstore.dao;

import com.senac.musicstore.model.EnderecoEntrega;
import com.senac.musicstore.utils.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author magno
 */
public class EnderecoEntregaDAO {
        ConexaoBanco conexaoBanco = new ConexaoBanco();    
        Connection conn = conexaoBanco.createConnection();
        
        public Integer inserirEndereco(EnderecoEntrega EnderecoEntrega){
        System.out.println("Iniciando processo de inserção de fornecedor...");
        String query = "insert into Endereco_Entrega (endereco, complemento, numero, bairro, cidade, estado, cep" +
                                              " values (?, ?, ?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            preparedStatement.setString(1, EnderecoEntrega.getEndereco());
            preparedStatement.setString(2, EnderecoEntrega.getComplemento());
            preparedStatement.setString(3, EnderecoEntrega.getNumero());
            preparedStatement.setString(4, EnderecoEntrega.getBairro());
            preparedStatement.setString(4, EnderecoEntrega.getCidade());
            preparedStatement.setString(4, EnderecoEntrega.getEstado());
            preparedStatement.setString(4, EnderecoEntrega.getCep());

            preparedStatement.executeUpdate();
            
            ResultSet  rs = preparedStatement.getGeneratedKeys();
            rs.next();
            int codigo = rs.getInt(1);
            preparedStatement.close();

            return codigo;
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Erro ao salvar Endereço de Entrega");
            return null;
        }
    }
}
