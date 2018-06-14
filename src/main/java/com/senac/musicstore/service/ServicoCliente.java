    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senac.musicstore.service;

import com.senac.musicstore.dao.ClienteDAO;
import com.senac.musicstore.exceptions.ClienteException;
import com.senac.musicstore.exceptions.DataSourceException;
import com.senac.musicstore.model.Cliente;
import com.senac.musicstore.model.validador.ValidadorCliente;
import java.util.List;
/**
 *
 * @author magno
 */

//Classe de servico do cliente
public class ServicoCliente {
     ClienteDAO clienteDAO = new ClienteDAO();
     

    public void cadastrarCliente(Cliente cliente) throws ClienteException, DataSourceException, Exception {

        ValidadorCliente.validar(cliente);

        try {
            clienteDAO.inserirCliente(cliente);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }

    //Atualiza um cliente na fonte de dados
    public void atualizarCliente(Cliente cliente, int codigocliente, int codigousuario) throws ClienteException, DataSourceException, Exception {
        
        //ValidadorCliente.validar(cliente);

        try {
            clienteDAO.updateCliente(cliente, codigocliente, codigousuario);
        } catch (Exception e) {
            //Imprime qualquer erro técnico no console e devolve
            //uma exceção e uma mensagem amigável a camada de visão
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }

    //Realiza a pesquisa de um cliente por nome na fonte de dados
    public List<Cliente> procurarCliente(String nome) throws ClienteException, DataSourceException, Exception {
        try {
            return clienteDAO.listarCliente(nome);
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
            
        }
    }
    
    //Realiza a pesquisa de um cliente por nome na fonte de dados
    public Cliente obterClientePorCpf(String cpf) throws ClienteException, DataSourceException, Exception {
        try {
            if (cpf.length() == 0) {
                throw new Exception("Campo cpf vazio!");
            } else {
                return clienteDAO.encontrarClientePorCpf(cpf);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }
    
    //Realiza a pesquisa de um cliente por nome na fonte de dados
    public Cliente obterClientePorCodigoUsuario(int codigo) throws ClienteException, DataSourceException, Exception {
        try {
          
                return clienteDAO.encontrarClientePorCodigoUsuario(codigo);
         
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }
    
    //Realiza a pesquisa de um cliente por nome na fonte de dados
    public Cliente obterClientePorCodigo(int codigo) throws ClienteException, DataSourceException, Exception {
        try {
          
                return clienteDAO.encontrarClientePorCodigo(codigo);
         
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }


    //Exclui o cliente com ID informado do mock
    public void excluirCliente(String cpf) throws ClienteException, DataSourceException, Exception {
        try {
            //Solicita ao DAO a exclusão do cliente informado
            clienteDAO.deletarCliente(cpf);
        } catch (Exception e) {
            //Imprime qualquer erro técnico no console e devolve
            //uma exceção e uma mensagem amigável a camada de visão
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }
    
    //Lista produtos de determinada empresa
    public List<Cliente> listarClientes() throws DataSourceException, Exception {
        try {
            return clienteDAO.listarClientes();
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
            
        }
    }
    
        //Lista produtos de determinada empresa
    public List<Cliente> listarclientestotais() throws DataSourceException, Exception {
        try {
            return clienteDAO.listarClientestotais();
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
            
        }
    }
}
