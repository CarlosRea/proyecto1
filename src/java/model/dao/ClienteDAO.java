/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.pojo.Cliente;

/**
 *
 * @author creanogales
 */
public interface ClienteDAO {
    
    void insertar(Cliente cliente);
    void eliminar(Integer idCliente);
    void update(Cliente cliente,Integer idCliente);
    Cliente getCliente(Integer idCliente);
    List<Cliente> getAllCliente();
}
