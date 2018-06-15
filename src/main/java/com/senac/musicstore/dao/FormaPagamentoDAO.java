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
import java.util.ArrayList;
import java.util.List;

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

    public List<FormaPagamento> listarpagamentos() {
        List<FormaPagamento> lista = new ArrayList<>();
        System.out.println("Buscando produto na base de dados...");
        String query = "Select * from Forma_Pagamento";
        
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            ResultSet rs = preparedStatement.executeQuery();

            
                while (rs.next()){
                    FormaPagamento formapagamento = new FormaPagamento();
                    formapagamento.setCodigo(rs.getInt(1));
                    formapagamento.setCartaocredito(rs.getString(2));
                    formapagamento.setCartaodebito(rs.getString(3));
                    formapagamento.setNumerocartao(rs.getString(4));
                    formapagamento.setNomecartao(rs.getString(5));
                    formapagamento.setVencimento(rs.getString(6));
                    formapagamento.setCodigoseguranca(rs.getString(7));
                    formapagamento.setParcelas(rs.getString(8));

                    lista.add(formapagamento);
                }

            System.out.println("Busca efetuada com sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar Pagamentos: "+ex);
        }        
        return lista;    }

    public FormaPagamento consultaPagamento(int codigo) {
        FormaPagamento pagamento = new FormaPagamento();
        System.out.println("Buscando produto na base de dados...");
        String query = "SELECT * FROM Forma_Pagamento WHERE codigo=?";//addicionar o % %
        
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,codigo);

            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()){
                pagamento.setCodigo(rs.getInt(1));
                pagamento.setCartaocredito(rs.getString(2));
                pagamento.setCartaodebito(rs.getString(3));
                pagamento.setNumerocartao(rs.getString(4));
                pagamento.setNomecartao(rs.getString(5));
                pagamento.setVencimento(rs.getString(6));
                pagamento.setCodigoseguranca(rs.getString(7));
                pagamento.setParcelas(rs.getString(8));
            }
            
            System.out.println("Busca efetuada com sucesso");
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar Pagamento"+ex);
        }        
        return pagamento;
    }
}
