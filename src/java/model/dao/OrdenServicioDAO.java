/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.pojo.OrdenServicio;

/**
 *
 * @author creanogales
 */
public interface OrdenServicioDAO {
    void insertar(OrdenServicio ordenservicio);
    void eliminar(Integer idservicio);
    void update(OrdenServicio producto,Integer idservicio);
    OrdenServicio getProductoVenta(Integer idservicio);
    List<OrdenServicio> getAllOrdenServicio();
}
