/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.pojo.OrdenProducto;
import model.pojo.Producto;

/**
 *
 * @author creanogales
 */
public interface OrdenProductoDAO {
    void insertar(OrdenProducto producto);
    void eliminar(Integer idProducto);
    void update(OrdenProducto producto,Integer idProducto);
    OrdenProducto getProductoVenta(Integer idProducto);
    List<OrdenProducto> getAllOrdenProducto();
}
