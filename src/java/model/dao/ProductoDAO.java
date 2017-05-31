/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.pojo.Producto;

/**
 *
 * @author creanogales
 */
public interface ProductoDAO {
    void insertar(Producto producto);
    void eliminar(Integer idProducto);
    void update(Producto producto,Integer idProducto);
    Producto getProducto(Integer idProducto);
    List<Producto> getAllProducto();
    
}
