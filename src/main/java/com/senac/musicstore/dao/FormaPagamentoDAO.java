/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.musicstore.dao;


import com.senac.musicstore.model.FormaPagamento;
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
public class FormaPagamentoDAO {
        ConexaoBanco conexaoBanco = new ConexaoBanco();    
        Connection conn = conexaoBanco.createConnection();
        
        public Integer inserirFormaPagamento(FormaPagamento FormaPagamento){
        System.out.println("Iniciando processo de inserção de fornecedor...");
        String query = "insert into Forma_Pagamento(cartaocredito, cartaodebito, numerocartao, nomecartao, vencimento, codigoseguranca, parcelas)" +
                                              " values (?, ?, ?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            preparedStatement.setString(1, FormaPagamento.getCartaocredito());
            preparedStatement.setString(2, FormaPagamento.getCartaodebito());
            preparedStatement.setString(3, FormaPagamento.getNumerocartao());
            preparedStatement.setString(4, FormaPagamento.getNomecartao());
            preparedStatement.setString(5, FormaPagamento.getVencimento());
            preparedStatement.setString(6, FormaPagamento.getCodigoseguranca());
            preparedStatement.setString(7, FormaPagamento.getParcelas());

            preparedStatement.executeUpdate();
            
            ResultSet  rs = preparedStatement.getGeneratedKeys();
            rs.next();
            int codigo = rs.getInt(1);
            preparedStatement.close();

            return codigo;
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Erro ao salvar Forma de Pagamento");
            return null;
        }
    }
}
