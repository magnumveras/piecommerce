/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.musicstore.dao;


import com.senac.musicstore.model.Carrinho;
import com.senac.musicstore.utils.ConexaoBanco;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author geoinformacao
 */
public class CarrinhoDAO {
    ConexaoBanco conexaoBanco = new ConexaoBanco();
    Connection conn = conexaoBanco.createConnection();
    
    public Integer inserirCarrinho(Carrinho carrinho){
         String query = " insert into carrinho(codigocliente, datacarrinho, valortotal)"
        + " values (?, ?, ?)";
        
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            preparedStatement.setInt(1, carrinho.getCodigoCliente());
            preparedStatement.setTimestamp(2, carrinho.getData());
            preparedStatement.setDouble(3, carrinho.getValorTotal());
            
            preparedStatement.executeUpdate();

            ResultSet  rs = preparedStatement.getGeneratedKeys();
            rs.next();
            int codigo = rs.getInt(1);
            preparedStatement.close();

            return codigo;
        } catch (SQLException ex) {
            System.out.println("Erro ao salvar carrinho"+ex);
            return null;
        }
    }
    
    //DAO Para deletar carrinho completo da página, o mesmo será acionado após pressionar botão 'Cancelar'
    public void deletarCarrinho(int codigo) throws Exception{
        String query = "DELETE FROM carrinho WHERE codigo=?";

    try {
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, codigo);

        preparedStatement.execute();
        System.out.println("Carrinho deletado");
    } catch (SQLException ex) {
        throw new Exception("Erro ao deletar o carrinho", ex);
        
    }
    }
    
    //Altera valor total do carrinho
    public void alterarValor(double valor, int codigo) throws Exception{
        String query = "UPDATE carrinho SET valortotal= ? where codigo = ?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setDouble(1, valor);
            preparedStatement.setInt(2, codigo);
             
            preparedStatement.execute();
            System.out.println("Valor Alterado");
        } catch (SQLException ex) {
            throw new Exception("Erro ao deletar o carrinho", ex);
        
        }
    }
    
    public Carrinho retornaCarrinho(int codigocarrinho) throws Exception{
         String query = " Select * from carrinho WHERE codigo = ?";
         Carrinho carrinho = new Carrinho();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            preparedStatement.setInt(1, codigocarrinho);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()){
                carrinho.setCodigo(rs.getInt(1));
                carrinho.setCliente(rs.getInt(2));
                carrinho.setData(rs.getTimestamp(3));
                carrinho.setValorTotal(rs.getDouble(4));
          
            }
            
        } catch (SQLException ex) {
            throw new Exception("Erro ao listar carrinho", ex);
        }
          
        return carrinho;
       
    }

    public Carrinho retornaCarrinhoPorClienteData(int codigoCliente, String data) throws Exception {
        String query = " Select * from carrinho WHERE codigocliente = ? and datacarrinho like ?";
         Carrinho carrinho = new Carrinho();
         
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            preparedStatement.setInt(1, codigoCliente);
            preparedStatement.setTimestamp(2, Timestamp.valueOf(data+"%"));
            
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()){
                carrinho.setCodigo(rs.getInt(1));
                carrinho.setCliente(rs.getInt(2));
                carrinho.setData(rs.getTimestamp(3));
                carrinho.setValorTotal(rs.getDouble(4));
          
            }

        } catch (SQLException ex) {
            throw new Exception("Erro ao listar carrinho", ex);
        }
          
        return carrinho;
    }
}
