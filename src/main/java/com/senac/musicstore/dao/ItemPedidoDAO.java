/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.musicstore.dao;


import com.senac.musicstore.model.ItemPedido;
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
 *
 * @author uriel.oliveira
 */
public class ItemPedidoDAO {
    
        ConexaoBanco conexaoBanco = new ConexaoBanco();    
        Connection conn = conexaoBanco.createConnection();
    
    //Cadastra um novo item na venda
    public void cadastrarItemPedido(int codigopedido, int codigoproduto, int quantidade){
                 String query = " insert into itempedido (codigopedido, codigoproduto, quantidade )"
        + " values (?, ?, ?)";
        
        
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, codigopedido);
            preparedStatement.setInt(2, codigoproduto);
            preparedStatement.setInt(3, quantidade);

            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao salvar item pedido");
        }
    }
    
    //Lista itens de determinada venda
    public List<ItemPedido> listarPedidoeItens(int codigopedido){ //retorna todos itens
        List<ItemPedido> lista = new ArrayList<>();
        System.out.println("Buscando produto na base de dados...");
        String query = "Select * from itempedido where codigopedido = ?";
        
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            preparedStatement.setInt(1, codigopedido);
            
            ResultSet rs = preparedStatement.executeQuery();

            
                while (rs.next()){
                    ItemPedido item = new ItemPedido();
                    item.setCodigopedido(rs.getInt(1));
                    item.setCodigopedido(rs.getInt(2));
                    item.setCodigoproduto(rs.getInt(3));
                    item.setQuantidade(rs.getInt(4));
                    lista.add(item);
                }

            System.out.println("Busca efetuada com sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar Itens do Pedido: "+ex);
        }        
        return lista;
    
    }

    public List<ItemPedido> listarItensPedidos() {
        List<ItemPedido> lista = new ArrayList<>();
        System.out.println("Buscando produto na base de dados...");
        String query = "Select * from itempedido";
        
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            ResultSet rs = preparedStatement.executeQuery();

            
                while (rs.next()){
                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setCodigo(rs.getInt(1));
                    itemPedido.setCodigopedido(rs.getInt(2));
                    itemPedido.setCodigoProduto(rs.getInt(3));
                    itemPedido.setQuantidade(rs.getInt(4));
                    lista.add(itemPedido);
                }

            System.out.println("Busca efetuada com sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar Itens da pedido: "+ex);
        }        
        return lista;
    }
}
