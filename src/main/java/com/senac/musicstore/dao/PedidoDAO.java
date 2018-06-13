/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.musicstore.dao;

import com.senac.musicstore.model.Pedido;
import com.senac.musicstore.utils.ConexaoBanco;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 

/**
 * @author Uriel
 */
public class PedidoDAO {
        ConexaoBanco conexaoBanco = new ConexaoBanco();    
        Connection conn = conexaoBanco.createConnection();
        
    public Integer cadastrarPedido(Pedido pedido){
                 String query = " insert into venda (codigocliente, datavenda, valortotal)"
        + " values (?, ?, ?)";
        
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, pedido.getCodigoCliente());
            preparedStatement.setTimestamp(2, pedido.getData());
            preparedStatement.setDouble(3,pedido.getValorTotal());
            
            preparedStatement.executeUpdate();            
            ResultSet  rs = preparedStatement.getGeneratedKeys();
            rs.next();
            int codigo = rs.getInt(1);
            preparedStatement.close();

            return codigo;
        } catch (SQLException ex) {
            System.out.println("Erro ao salvar venda");
            return null;
        }
    }
    
     public List<Pedido> listarPedidos(Date datainicial, Date datafinal) throws Exception{
         String query = "Select v.codigo as codigo, c.nome || ' ' || c.SOBRENOME as cliente, v.datavenda as datavenda,\n" +
                        "      v.valortotal as valortoal, e.nome || ' - ' || e.cidade || ' - ' || e.tipo as nomeempresa from venda v\n" +
                        "inner join clientes c on c.id = v.CODIGOCLIENTE \n" +
                        "inner join empresas e on e.CODIGO = v.CODIGOEMPRESA " +
                        " where datavenda between ? and ? ";
         
         List<Pedido> listadevendas = new ArrayList<>();
         
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
 
            preparedStatement.setDate(1, datainicial);
            preparedStatement.setDate(2, datafinal);

            
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()){
                Pedido venda = new Pedido();
                
                venda.setCodigo(rs.getInt(1));
                venda.setCodigoCliente(rs.getInt(2));
                venda.setData(rs.getTimestamp(3));
                venda.setValorTotal(rs.getDouble(4));
                listadevendas.add(venda);
            }
            
        } catch (SQLException ex) {
            throw new Exception("Erro ao listar carrinho", ex);
        }
          
        return listadevendas;
       
    }
}
