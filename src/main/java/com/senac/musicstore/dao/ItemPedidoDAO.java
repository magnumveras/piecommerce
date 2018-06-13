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
                 String query = " insert into itempedido (codigovenda, codigoproduto, quantidade )"
        + " values (?, ?, ?)";
        
        
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, codigopedido);
            preparedStatement.setInt(2, codigoproduto);
            preparedStatement.setInt(3, quantidade);

            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao salvar venda");
        }
    }
    
    //Lista itens de determinada venda
    public List<ItemPedido> listarPedidoeItens(int codigopedido){ //retorna todos itens
        List<ItemPedido> lista = new ArrayList<>();
        System.out.println("Buscando produto na base de dados...");
        String query = "Select v.codigovenda as codigovenda, p.codigo as codigoproduto,p.nome, p.descricao as descricao,\n" +
                       "       c.nome as categoria, v.quantidade, p.precovenda from itemvenda as v\n" +
                       "       inner join produtos p on p.CODIGO = v.CODIGOPRODUTO\n" +
                       "       inner join categoria c on c.CODIGO = p.CODIGOCATEGORIA\n" +
                       "where codigovenda = ?";
        
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            preparedStatement.setInt(1, codigopedido);
            
            ResultSet rs = preparedStatement.executeQuery();

            
                while (rs.next()){
                    ItemPedido itemvenda = new ItemPedido();
                    itemvenda.setCodigoVenda(rs.getInt(1));
                    itemvenda.setCodigoProduto(rs.getInt(2));
                    itemvenda.setNomeproduto(rs.getString(3));
                    itemvenda.setDescricaoproduto(rs.getString(4));
                    itemvenda.setNomecategoria(rs.getString(5));
                    itemvenda.setQuantidade(rs.getInt(6));
                    itemvenda.setPrevovenda(rs.getDouble(7));
                    lista.add(itemvenda);
                }

            System.out.println("Busca efetuada com sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar Itens da venda: "+ex);
        }        
        return lista;
    
    }
}
