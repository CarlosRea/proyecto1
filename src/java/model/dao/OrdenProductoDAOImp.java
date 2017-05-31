/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import model.pojo.LineaProducto;
import model.pojo.OrdenProducto;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

/**
 *
 * @author creanogales
 */
public class OrdenProductoDAOImp implements OrdenProductoDAO{
    @Override
    public void insertar(OrdenProducto producto) {
        HibernateUtil.buildSessionFactory();
        try {
            HibernateUtil.openSessionAndBindToThread();
            Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
            sesion.beginTransaction();
            Query query = sesion.createSQLQuery("insert into OrdenProducto (cliente, empleado, comisionEmpleado,factura, fechaCompra) "
                    + "values ( :cliente, :empleado, :comision, :factura, :fecha)");

            query.setParameter("cliente", producto.getCliente());
            query.setParameter("empleado", producto.getEmpleado());
            query.setParameter("comision", producto.getComisionEmpleado());
            query.setParameter("factura", producto.getFacturado());
            query.setParameter("fecha", producto.getFechaCompra());
            query.executeUpdate();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(OrdenProductoDAOImp.class.getName()).log(Level.SEVERE, null, ex);
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
            Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
            sesion.beginTransaction();
            String hql = "delete from OrdenProducto where idCompra=:idCom";
            Query query = sesion.createQuery(hql);
            query.setParameter("idCom", idProducto);
            sesion.getTransaction().commit();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(OrdenProductoDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            HibernateUtil.closeSessionAndUnbindFromThread();
        }
        //HibernateUtil.closeSessionFactory();
    }

    @Override
    public void update(OrdenProducto producto, Integer idProducto) {
        HibernateUtil.buildSessionFactory();
        try {
            HibernateUtil.openSessionAndBindToThread();
            Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
            sesion.beginTransaction();
            String hql = "update OrdenProducto set clientes=: cliente, empleados=: empleado, comisionEmpleado=: comision, factura=: fac, fechaCompra=:fecha "
                    + "where idCliente=:idcliente";
            Query query = sesion.createQuery(hql);
            query.setParameter("cliente", producto.getCliente());
            query.setParameter("empleado", producto.getEmpleado());
            query.setParameter("comision", producto.getComisionEmpleado());
            query.setParameter("fac", producto.getFacturado());
            query.setParameter("fecha", producto.getFechaCompra());

            producto = (OrdenProducto) query.uniqueResult();
            sesion.getTransaction().commit();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(OrdenProductoDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            HibernateUtil.closeSessionAndUnbindFromThread();
        }
       // HibernateUtil.closeSessionFactory();
    }

    @Override
    public OrdenProducto getProductoVenta(Integer idProducto) {
        OrdenProducto producto = new OrdenProducto();
        HibernateUtil.buildSessionFactory();
        try {
            HibernateUtil.openSessionAndBindToThread();
            Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
            String hql = "from OrdenProducto where idCompra=:idPro";
            Query query = sesion.createQuery(hql);
            query.setParameter("idPro", idProducto);
            producto = (OrdenProducto) query.uniqueResult();
            return producto;
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(OrdenProductoDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            HibernateUtil.closeSessionAndUnbindFromThread();
        }
        //HibernateUtil.closeSessionFactory();
        return null;
    }

    @Override
    public List<OrdenProducto> getAllOrdenProducto() {
        List<OrdenProducto> lista = new ArrayList<>();
        HibernateUtil.buildSessionFactory();
        try {
            HibernateUtil.openSessionAndBindToThread();
            Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
            String hql = "from OrdenProducto";
            Query query = sesion.createQuery(hql);
            lista = query.list();
            return lista;
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(OrdenProductoDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            HibernateUtil.closeSessionAndUnbindFromThread();
        }
        //HibernateUtil.closeSessionFactory();
        return null;
    }
    
    
}
