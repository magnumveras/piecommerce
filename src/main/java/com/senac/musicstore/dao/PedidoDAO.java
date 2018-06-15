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
 * @author Magno
 */
public class PedidoDAO {
        ConexaoBanco conexaoBanco = new ConexaoBanco();    
        Connection conn = conexaoBanco.createConnection();
        
    public Integer cadastrarPedido(Pedido pedido){
                 String query = " insert into pedido (codigocliente, datavenda, valortotal, codigopagamento, codigoendereco)"
        + " values (?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, pedido.getCodigoCliente());
            preparedStatement.setTimestamp(2, pedido.getData());
            preparedStatement.setDouble(3,pedido.getValorTotal());
            preparedStatement.setInt(4,pedido.getCodigopagamento());
            preparedStatement.setInt(5,pedido.getCodigoendereco());
            
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
     
     //Consulta pedido por código
     public Pedido consultarPedido(int codigo) throws Exception{
         String query = "Select * from pedido where codigo=?";
         
         Pedido pedido = new Pedido();
         
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
 
            preparedStatement.setInt(1, codigo);

            
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()){        
                pedido.setCodigo(rs.getInt(1));
                pedido.setCodigoCliente(rs.getInt(2));
                pedido.setData(rs.getTimestamp(3));
                pedido.setValorTotal(rs.getDouble(4));
                pedido.setCodigopagamento(rs.getInt(5));
                pedido.setCodigoendereco(rs.getInt(6));
                pedido.setCodigovenda(rs.getInt(7));
            }
            
        } catch (SQLException ex) {
            throw new Exception("Erro ao listar carrinho", ex);
        }
          
        return pedido;
       
    }

    public List<Pedido> listarPedidosTotais() throws Exception {
        String query = "select * from pedido";
         
         List<Pedido> listadepedidos = new ArrayList<>();
         
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()){
                Pedido pedido = new Pedido();
                
                pedido.setCodigo(rs.getInt(1));
                pedido.setCodigoCliente(rs.getInt(2));
                pedido.setData(rs.getTimestamp(3));
                pedido.setValorTotal(rs.getDouble(4));
                listadepedidos.add(pedido);
            }
            
        } catch (SQLException ex) {
            throw new Exception("Erro ao listar carrinho", ex);
        }
          
        return listadepedidos;
    }

    public void atualizarPedido(int codigovenda, int codigopedido) throws Exception{
        System.out.println("Iniciando processo de atualização de pedido...");
         String query = "UPDATE pedido SET codigovenda=? WHERE codigo=?";

        try {
                PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
                preparedStatement.setInt(1, codigovenda);
                preparedStatement.setInt(2, codigopedido);
               
                preparedStatement.executeUpdate();
                preparedStatement.close();
        } catch (SQLException ex) {
            throw new Exception("Erro ao atualizar cliente!", ex);
        }

    
    }

    public List<Pedido> consultarPedidosCliente(Integer CodigoCliente) throws Exception {
         String query = "select * from pedido where codigocliente = ?";
         
         List<Pedido> listadepedidos = new ArrayList<>();
         
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            preparedStatement.setInt(1,CodigoCliente);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()){
                Pedido pedido = new Pedido();
                
                pedido.setCodigo(rs.getInt(1));
                pedido.setCodigoCliente(rs.getInt(2));
                pedido.setData(rs.getTimestamp(3));
                pedido.setValorTotal(rs.getDouble(4));
                listadepedidos.add(pedido);
            }
            
        } catch (SQLException ex) {
            throw new Exception("Erro ao listar carrinho", ex);
        }
          
        return listadepedidos;    
    }
}
