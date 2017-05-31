/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import model.pojo.Producto;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

/**
 *
 * @author creanogales
 */
public class ProductoDAOImp implements ProductoDAO{
    
    @Override
    public void insertar(Producto producto) {
        HibernateUtil.buildSessionFactory();
        try {
            HibernateUtil.openSessionAndBindToThread();
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            System.out.println("insert "+producto.getCategoriaProducto().getNombreCategoria());
            Query query = sesion.createSQLQuery("insert into Producto (nombre_producto,capacidad_producto, precio_compra, precio_venta, precio_servicio, bar_codigo, categoriaProducto) "
                    + "values (:nombre, :capacidad, :precioCompra, :precioVenta, :precioServicio, :codigo,:categoria)");

            query.setParameter("nombre", producto.getNombreProducto());
            query.setParameter("capacidad", producto.getCapacidadProducto());
            query.setParameter("precioCompra", producto.getPrecioCompra());
            query.setParameter("precioVenta", producto.getPrecioVenta());
            query.setParameter("precioServicio", producto.getPrecioServicio());
            query.setParameter("codigo", producto.getBarCodigo());
            query.setParameter("categoria", producto.getCategoriaProducto());
            query.executeUpdate();
            sesion.getTransaction().commit();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ClienteDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            HibernateUtil.closeSessionAndUnbindFromThread();
        }
        //HibernateUtil.closeSessionFactory();
    }

    @Override
    public void eliminar(Integer idProducto) {
        HibernateUtil.buildSessionFactory();
        try {
            HibernateUtil.openSessionAndBindToThread();
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            Query query = sesion.createQuery("delete from Producto where idproducto =:id");
            query.setParameter("id", idProducto);
            query.executeUpdate();
            sesion.getTransaction().commit();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ClienteDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            HibernateUtil.closeSessionAndUnbindFromThread();
        }
        //HibernateUtil.closeSessionFactory();
    }

    @Override
    public void update(Producto producto, Integer idProducto) {
        HibernateUtil.buildSessionFactory();
        try {
            HibernateUtil.openSessionAndBindToThread();
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            Query query1 = sesion.createQuery("update Producto set nombreProducto =:nom, capacidadProducto =:capacidad, precioCompra =:precioC, precioVenta =:precioV, precioServicio=:precioS, barCodigo=:codigo, categoriaProducto=:categoria "
                    + "where idproducto =:id");
            query1.setParameter("nom", producto.getNombreProducto());
            query1.setParameter("capacidad", producto.getCapacidadProducto());
            query1.setParameter("precioC", producto.getPrecioCompra());
            query1.setParameter("precioV", producto.getPrecioVenta());
            query1.setParameter("precioS", producto.getPrecioServicio());
            query1.setParameter("codigo", producto.getBarCodigo());
            query1.setParameter("categoria", producto.getCategoriaProducto());
            query1.setParameter("id", producto.getIdproducto());
            query1.executeUpdate();
            sesion.getTransaction().commit();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ClienteDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            HibernateUtil.closeSessionAndUnbindFromThread();
        }
        //HibernateUtil.closeSessionFactory();
    }

    @Override
    public Producto getProducto(Integer idProducto) {
        Producto producto = new Producto();
        HibernateUtil.buildSessionFactory();
        try {
            HibernateUtil.openSessionAndBindToThread();
            Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
            String hql = "from Producto where idproducto=:idProd";
            Query query = sesion.createQuery(hql);
            query.setParameter("idProd", idProducto);
            producto = (Producto) query.uniqueResult();
            return producto;
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ClienteDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            HibernateUtil.closeSessionAndUnbindFromThread();
        }
        //HibernateUtil.closeSessionFactory();
        return null;
    }

    @Override
    public List<Producto> getAllProducto() {
        List<Producto> lista = new ArrayList<>();
        HibernateUtil.buildSessionFactory();
        try {
            HibernateUtil.openSessionAndBindToThread();
            Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
            String hql = "from Producto as p LEFT JOIN FETCH p.lineaProductos LEFT JOIN FETCH p.lineaProductoServicios LEFT JOIN FETCH p.productoInventarios";
            Query query = sesion.createQuery(hql);
            lista = query.list();
            return lista;
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ClienteDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            HibernateUtil.closeSessionAndUnbindFromThread();
        }
        //HibernateUtil.closeSessionFactory();
        return null;
    }
}
