
import com.senac.musicstore.exceptions.PedidoException;
import com.senac.musicstore.model.Pedido;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Magno
 */
public class ValidadorVenda {
    
    public static void validar(Pedido pedido) throws PedidoException{
        //Realização de Validações de Negocio
        if(pedido == null){
            throw new PedidoException("Não foi informado o pedido");
        }
        
    }
    
}
