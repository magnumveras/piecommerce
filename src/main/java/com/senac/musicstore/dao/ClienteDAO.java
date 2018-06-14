/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.musicstore.dao;

import com.senac.musicstore.model.Cliente;
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
 * @author geoinformacao
 */
public class ClienteDAO {
    ConexaoBanco conexaoBanco = new ConexaoBanco();
    
    Connection conn = conexaoBanco.createConnection();
    

    public void inserirCliente(Cliente cliente){
        
         String query = " insert into cliente (nome, sobrenome, sexo, cpf, rg, datanasc, telefone, email,"
                 + "endereco, numero, bairro, complemento, cidade, estado, cep, codigousuario, ofertas)"
        + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getSobrenome());
            preparedStatement.setString(3, cliente.getSexo());
            preparedStatement.setString(4, cliente.getCpf());    
            preparedStatement.setString(5, cliente.getRg());
            preparedStatement.setString(6, cliente.getDatanascimento());
            preparedStatement.setString(7, cliente.getTelefone());
            preparedStatement.setString(8, cliente.getEmail());
            preparedStatement.setString(9, cliente.getEndereco());
            preparedStatement.setString(10, cliente.getNumero());
            preparedStatement.setString(11, cliente.getBairro());
            preparedStatement.setString(12, cliente.getComplemento());
            preparedStatement.setString(13, cliente.getCidade());
            preparedStatement.setString(14, cliente.getEstado());
            preparedStatement.setString(15, cliente.getCep());
            preparedStatement.setInt(16, cliente.getCodigousuario());
            preparedStatement.setBoolean(17, cliente.getOfertas());
           
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao salvar usuário"+ex);
        }
    }
    
    public Cliente updateCliente(Cliente cliente, Integer codigocliente, Integer codigousuario) throws Exception{
        System.out.println("Iniciando processo de atualização de cliente...");
         String query = "UPDATE cliente SET nome=?, sobrenome=?, sexo=?, rg=?, cpf=?, datanasc=?, telefone=?, email=?, "
                 + "endereco=?, numero=?, bairro=?, complemento=?, cidade=?, estado=?, cep=?, codigousuario=?, ofertas=? WHERE id=?";
        
        System.out.println(cliente.toString());
        try {
                PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
                preparedStatement.setString(1, cliente.getNome());
                preparedStatement.setString(2, cliente.getSobrenome());
                preparedStatement.setString(3, cliente.getSexo());
                preparedStatement.setString(4, cliente.getRg());
                preparedStatement.setString(5, cliente.getCpf());
                preparedStatement.setString(6, cliente.getDatanascimento());
                preparedStatement.setString(7, cliente.getTelefone());
                preparedStatement.setString(8, cliente.getEmail());
                preparedStatement.setString(9, cliente.getEndereco());
                preparedStatement.setString(10, cliente.getNumero());
                preparedStatement.setString(11, cliente.getBairro());
                preparedStatement.setString(12, cliente.getComplemento());
                preparedStatement.setString(13, cliente.getCidade());
                preparedStatement.setString(14, cliente.getEstado());
                preparedStatement.setString(15, cliente.getCep());
                preparedStatement.setInt(16, codigousuario);
                preparedStatement.setBoolean(17, cliente.getOfertas());
                preparedStatement.setInt(18, codigocliente);
                
                System.out.println("cpf: "+cliente.getCpf());
                
                preparedStatement.executeUpdate();
                preparedStatement.close();
        } catch (SQLException ex) {
            throw new Exception("Erro ao atualizar cliente!", ex);
        }

        
        return cliente;
    
    }
    
    public List<Cliente> listarCliente(String nome) throws Exception{
        System.out.println("Iniciando listagem de cliente...");
        List<Cliente> lista = new ArrayList<>();
        String query = "";
        
        boolean vazio = true;
              
        if(nome.length() == 0){
            vazio = true;
            query = "SELECT * FROM cliente";
        }else{
            vazio = false;
            query = "SELECT * FROM cliente WHERE nome LIKE ? ";
        }
         

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            if(vazio != true){
                preparedStatement.setString(1, nome+"%");
            }
            
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Cliente cliente = new Cliente();
                
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setSexo(rs.getString("sexo"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setRg(rs.getString("rg"));            
                cliente.setIdade(rs.getString("datanasc"));                
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setNumero(rs.getString("numero"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setComplemento(rs.getString("complemento"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setEstado(rs.getString("estado"));
                cliente.setCep(rs.getString("cep"));
                cliente.setCodigousuario(rs.getInt("codigousuario"));
                cliente.setOfertas(rs.getBoolean("ofertas"));
                
                lista.add(cliente);
            }
            
        } catch (SQLException ex) {
            throw new Exception("Erro ao listar cliente", ex);
        }

        
        return lista;
    
    }
    
    public List<Cliente> listarClientes() throws Exception{
        System.out.println("Iniciando listagem de cliente...");
        List<Cliente> lista = new ArrayList<>();
        String query = "";
        
        boolean vazio = true;
              
        query = "SELECT * FROM cliente";
        
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Cliente cliente = new Cliente();
     
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setSexo(rs.getString("sexo"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setRg(rs.getString("rg"));            
                cliente.setIdade(rs.getString("datanasc"));                
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setNumero(rs.getString("numero"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setComplemento(rs.getString("complemento"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setEstado(rs.getString("estado"));
                cliente.setCep(rs.getString("cep"));
                cliente.setCodigousuario(rs.getInt("codigousuario"));
                cliente.setOfertas(rs.getBoolean("ofertas"));
                lista.add(cliente);
            }
            
        } catch (SQLException ex) {
            throw new Exception("Erro ao listar cliente", ex);
        }

        
        return lista;
    
    }
    
        public List<Cliente> listarClientestotais() throws Exception{
        System.out.println("Iniciando listagem de cliente...");
        List<Cliente> lista = new ArrayList<>();
        String query = "";
        
        boolean vazio = true;
              
        query = "SELECT * FROM clientes";
        
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Cliente cliente = new Cliente();
                
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setSexo(rs.getString("sexo"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setRg(rs.getString("rg"));            
                cliente.setIdade(rs.getString("datanasc"));                
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setNumero(rs.getString("numero"));
                cliente.setComplemento(rs.getString("complemento"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setEstado(rs.getString("estado"));
                cliente.setCep(rs.getString("cep"));
                lista.add(cliente);
            }
            
        } catch (SQLException ex) {
            throw new Exception("Erro ao listar cliente", ex);
        }

        
        return lista;
    
    }
    
    public Cliente encontrarClientePorCpf(String cpf) throws Exception{
        System.out.println("Iniciando listagem de cliente...");
        
        Cliente cliente = new Cliente();
        String query = "SELECT * FROM cliente WHERE cpf LIKE ?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,cpf + "%");
            
            ResultSet rs = preparedStatement.executeQuery();
            
            System.out.println("Busca efetuada com sucesso");
            
            while (rs.next()){
                cliente.setId(rs.getInt(1));
                cliente.setNome(rs.getString(2));
                cliente.setSobrenome(rs.getString(3));
                cliente.setSexo(rs.getString(4));
                cliente.setCpf(rs.getString(5));
                cliente.setRg(rs.getString(6));
                cliente.setDatanascimento(rs.getString(7));
                cliente.setTelefone(rs.getString(8));       
                cliente.setEmail(rs.getString(9));
                cliente.setEndereco(rs.getString(10));
                cliente.setNumero(rs.getString(11));
                cliente.setBairro(rs.getString(12));
                cliente.setComplemento(rs.getString(13));
                cliente.setCidade(rs.getString(14));
                cliente.setEstado(rs.getString(15));
                cliente.setCep(rs.getString(16));
                cliente.setCodigousuario(rs.getInt(17));
                cliente.setOfertas(rs.getBoolean(18));
            }
            
        } catch (SQLException ex) {
            throw new Exception("Erro ao listar cliente", ex);
        }

        
        return cliente;
    }
        public Cliente encontrarClientePorIdCarrinho(int id, int codigoempresa) throws Exception{
        System.out.println("Iniciando listagem de cliente...");
        
        Cliente cliente = new Cliente();
        String query = "SELECT * FROM clientes WHERE id=? and codigoempresa = ?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,String.valueOf(id));
            preparedStatement.setString(2,String.valueOf(codigoempresa));
            
            ResultSet rs = preparedStatement.executeQuery();
            
            System.out.println("Busca efetuada com sucesso");
            
            while (rs.next()){
                cliente.setId(rs.getInt(1));
                cliente.setNome(rs.getString(2));
                cliente.setSobrenome(rs.getString(3));
                cliente.setSexo(rs.getString(4));
                cliente.setCpf(rs.getString(5));
                cliente.setRg(rs.getString(6));            
                cliente.setIdade(rs.getString(7));                
                cliente.setTelefone(rs.getString(8));
                cliente.setEmail(rs.getString(10));
                cliente.setEndereco(rs.getString(11));
                cliente.setNumero(rs.getString(12));
                cliente.setComplemento(rs.getString(13));
                cliente.setCidade(rs.getString(14));
                cliente.setEstado(rs.getString(15));
                cliente.setCep(rs.getString(17));
            }
            
        } catch (SQLException ex) {
            throw new Exception("Erro ao listar cliente", ex);
        }

        
        return cliente;
    }
        
        public Cliente encontrarClientePorCodigoUsuario(int codigo) throws Exception{
        System.out.println("Iniciando listagem de cliente...");
        
        Cliente cliente = new Cliente();
        String query = "SELECT * FROM cliente WHERE codigousuario=?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,codigo);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            System.out.println("Busca efetuada com sucesso");
            
            while (rs.next()){
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setSexo(rs.getString("sexo"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setRg(rs.getString("rg"));            
                cliente.setIdade(rs.getString("datanasc"));                
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setNumero(rs.getString("numero"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setComplemento(rs.getString("complemento"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setEstado(rs.getString("estado"));
                cliente.setCep(rs.getString("cep"));
                cliente.setCodigousuario(rs.getInt("codigousuario"));
                cliente.setOfertas(rs.getBoolean("ofertas"));
            }
            
        } catch (SQLException ex) {
            throw new Exception("Erro ao listar cliente", ex);
        }

        
        return cliente;
    }
    
    
    public void deletarCliente(String cpf) throws Exception{
        System.out.println("Deletando clientes de cpf: "+cpf);
        String query = "DELETE FROM cliente WHERE cpf=?";


    try {
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, cpf);
      
        preparedStatement.execute();
        System.out.println("Cliente deletado");
    } catch (SQLException ex) {
        throw new Exception("Erro ao deletar o cliente", ex);
        
    }
    }

    public Cliente encontrarClientePorCodigo(int codigo) throws Exception {
        System.out.println("Iniciando listagem de cliente...");
        
        Cliente cliente = new Cliente();
        String query = "SELECT * FROM cliente WHERE id=?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,codigo);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            System.out.println("Busca efetuada com sucesso");
            
            while (rs.next()){
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setSexo(rs.getString("sexo"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setRg(rs.getString("rg"));            
                cliente.setIdade(rs.getString("datanasc"));                
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setNumero(rs.getString("numero"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setComplemento(rs.getString("complemento"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setEstado(rs.getString("estado"));
                cliente.setCep(rs.getString("cep"));
                cliente.setCodigousuario(rs.getInt("codigousuario"));
                cliente.setOfertas(rs.getBoolean("ofertas"));
            }
            
        } catch (SQLException ex) {
            throw new Exception("Erro ao listar cliente", ex);
        }

        
        return cliente;
    }
}
