/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import model.pojo.OrdenServicio;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

/**
 *
 * @author creanogales
 */
public class OrdenServicioDAOImp implements OrdenServicioDAO{

    @Override
    public void insertar(OrdenServicio ordenservicio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Integer idservicio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(OrdenServicio producto, Integer idservicio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrdenServicio getProductoVenta(Integer idservicio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrdenServicio> getAllOrdenServicio() {
        List<OrdenServicio> lista = new ArrayList<>();
        HibernateUtil.buildSessionFactory();
        try {
            HibernateUtil.openSessionAndBindToThread();
            Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
            String hql = "from OrdenServicio";
            Query query = sesion.createQuery(hql);
            lista = query.list();
            return lista;
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(OrdenServicioDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            HibernateUtil.closeSessionAndUnbindFromThread();
        }
        //HibernateUtil.closeSessionFactory();
        return null;
    }
    
}
