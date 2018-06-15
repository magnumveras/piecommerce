/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.musicstore.dao;


import com.senac.musicstore.model.ItemPedido;
import com.senac.musicstore.model.ItemVenda;
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
public class ItemVendaDAO {
    
        ConexaoBanco conexaoBanco = new ConexaoBanco();    
        Connection conn = conexaoBanco.createConnection();
    
    //Cadastra um novo item na venda
    public void cadastrarItemVenda(int codigovenda, int codigoproduto, int quantidade){
                 String query = " insert into itemvenda (codigovenda, codigoproduto, quantidade )"
        + " values (?, ?, ?)";   
        
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, codigovenda);
            preparedStatement.setInt(2, codigoproduto);
            preparedStatement.setInt(3, quantidade);

            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao salvar item venda");
        }
    }
    
    //Lista itens de determinada venda
    public List<ItemVenda> listarItensVenda(int codigovenda){ //retorna todos itens
        List<ItemVenda> lista = new ArrayList<>();
        System.out.println("Buscando produto na base de dados...");
        String query = "Select * from itemvenda where codigovenda = ?";
        
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            preparedStatement.setInt(1, codigovenda);
            
            ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()){
                    ItemVenda item = new ItemVenda();
                    item.setCodigo(rs.getInt(1));
                    item.setCodigovenda(rs.getInt(2));
                    item.setCodigoproduto(rs.getInt(3));
                    item.setQuantidade(rs.getInt(4));
                    lista.add(item);
                }

            System.out.println("Busca efetuada com sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar Itens da Venda: "+ex);
        }        
        return lista;
    
    }

    public List<ItemVenda> listarItensVendas() {
        List<ItemVenda> lista = new ArrayList<>();
        System.out.println("Buscando produto na base de dados...");
        String query = "Select * from itemvenda";
        
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            ResultSet rs = preparedStatement.executeQuery();

            
                while (rs.next()){
                    ItemVenda itemvenda = new ItemVenda();
                    itemvenda.setCodigo(rs.getInt(1));
                    itemvenda.setCodigovenda(rs.getInt(2));
                    itemvenda.setCodigoProduto(rs.getInt(3));
                    itemvenda.setQuantidade(rs.getInt(4));
                    lista.add(itemvenda);
                }

            System.out.println("Busca efetuada com sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar Itens da pedido: "+ex);
        }        
        return lista;
    }
}
