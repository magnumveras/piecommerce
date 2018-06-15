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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author magno
 */
public class EnderecoEntregaDAO {
        ConexaoBanco conexaoBanco = new ConexaoBanco();    
        Connection conn = conexaoBanco.createConnection();
        
        public Integer inserirEndereco(EnderecoEntrega EnderecoEntrega){
        System.out.println("Iniciando processo de inserção de fornecedor...");
        String query = "insert into Endereco_Entrega (endereco, complemento, numero, bairro, cidade, estado, cep)" +
                                              " values (?, ?, ?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            preparedStatement.setString(1, EnderecoEntrega.getEndereco());
            preparedStatement.setString(2, EnderecoEntrega.getComplemento());
            preparedStatement.setString(3, EnderecoEntrega.getNumero());
            preparedStatement.setString(4, EnderecoEntrega.getBairro());
            preparedStatement.setString(5, EnderecoEntrega.getCidade());
            preparedStatement.setString(6, EnderecoEntrega.getEstado());
            preparedStatement.setString(7, EnderecoEntrega.getCep());

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

    public List<EnderecoEntrega> listarEnderecos() {
        List<EnderecoEntrega> lista = new ArrayList<>();
        System.out.println("Buscando produto na base de dados...");
        String query = "Select * from Endereco_Entrega";
        
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            ResultSet rs = preparedStatement.executeQuery();

            
                while (rs.next()){
                    EnderecoEntrega endereco = new EnderecoEntrega();
                    endereco.setCodigo(rs.getInt(1));
                    endereco.setEndereco(rs.getString(2));
                    endereco.setComplemento(rs.getString(3));
                    endereco.setNumero(rs.getString(4));
                    lista.add(endereco);
                }

            System.out.println("Busca efetuada com sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar endereços: "+ex);
        }        
        return lista;
    }
    
        //busca endereço
    public EnderecoEntrega consultaEndereco(int codigo){//retorna um item
        EnderecoEntrega endereco = new EnderecoEntrega();
        System.out.println("Buscando produto na base de dados...");
        String query = "SELECT * FROM Endereco_Entrega WHERE codigo=?";//addicionar o % %
        
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,codigo);

            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()){
                endereco.setCodigo(rs.getInt(1));
                endereco.setEndereco(rs.getString(2));
                endereco.setComplemento(rs.getString(3));
                endereco.setNumero(rs.getString(4));
                endereco.setBairro(rs.getString(5));
                endereco.setCidade(rs.getString(6));
                endereco.setEstado(rs.getString(7));
                endereco.setCep(rs.getString(8));
            }
            
            System.out.println("Busca efetuada com sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar Endereço"+ex);
        }        
        return endereco;
    
    }
}
