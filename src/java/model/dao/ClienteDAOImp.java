/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import model.pojo.Cliente;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

/**
 *
 * @author creanogales
 */
public class ClienteDAOImp implements ClienteDAO {

    @Override
    public void insertar(Cliente cliente) {
        HibernateUtil.buildSessionFactory();
        try {
            HibernateUtil.openSessionAndBindToThread();
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            Query query1=sesion.createSQLQuery("insert into Direccion (localidad,calle,codigo_postal,numero, piso, puerta) "
                    + "values (:localidad,:calle,:cod,:num,:pis,:puerta)");
            query1.setParameter("localidad", cliente.getDireccion().getLocalidad());
            query1.setParameter("calle", cliente.getDireccion().getCalle());
            query1.setParameter("cod", cliente.getDireccion().getCodigoPostal());
            query1.setParameter("num", cliente.getDireccion().getNumero());
            query1.setParameter("pis", cliente.getDireccion().getPiso());
            query1.setParameter("puerta", cliente.getDireccion().getPuerta());
            
            query1.executeUpdate();
            
            Integer idDireccion=(Integer)sesion.createQuery("select iddireccion from Direccion where calle=:calle").setParameter("calle", cliente.getDireccion().getCalle()).uniqueResult();
            
            System.out.println("direccion del select -- "+idDireccion);
            
            Query query = sesion.createSQLQuery("insert into Cliente (nombre_cliente,apellido_cliente, sexo, fecha_alta_cliente, fecha_nacimiento,telefono_cliente,movil_cliente, dni, nombre_perfil, direccion_iddireccion) "
                    + "values (:nombre, :apellido,:sexo, :fechaal, :fechanac, :telefono, :movil, :dni,:perfil,:direc)");

            query.setParameter("nombre", cliente.getNombreCliente());
            query.setParameter("apellido", cliente.getApellidoCliente());
            query.setParameter("sexo", cliente.getSexo());
            query.setParameter("fechaal", cliente.getFechaAltaCliente());
            query.setParameter("fechanac", cliente.getFechaNacimiento());
            query.setParameter("telefono", cliente.getTelefonoCliente());
            query.setParameter("movil", cliente.getMovilCliente());
            query.setParameter("dni", cliente.getDni());
            query.setParameter("perfil", cliente.getPerfilCliente().getNombrePerfil());
            query.setParameter("direc", idDireccion);
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
    public void eliminar(Integer idCliente) {
        HibernateUtil.buildSessionFactory();
        System.out.println("------------------entra en eliminar DAO-------"+ idCliente);
        try {
            HibernateUtil.openSessionAndBindToThread();
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            Query query = sesion.createQuery("delete from Cliente where idcliente =:idCli");
            query.setParameter("idCli", idCliente);
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
    public void update(Cliente cliente, Integer idCliente) {
        HibernateUtil.buildSessionFactory();
        try {
            System.out.println("+++++++++++++entra en update cliente DAO--------------- "+idCliente);
            HibernateUtil.openSessionAndBindToThread();
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            System.out.println("localidad "+cliente.getDireccion().getLocalidad());
            System.out.println("perfil : "+cliente.getPerfilCliente().getNombrePerfil());
            Query query = sesion.createQuery("update Direccion set localidad =:loc, calle=:calle,numero=:num,puerta=:puerta,piso=:pis where iddireccion=:iddirec");
            query.setParameter("loc", cliente.getDireccion().getLocalidad());
            query.setParameter("calle", cliente.getDireccion().getCalle());
            query.setParameter("puerta", cliente.getDireccion().getPuerta());
            query.setParameter("pis", cliente.getDireccion().getPiso());
            query.setParameter("num", cliente.getDireccion().getNumero());
            query.setParameter("iddirec", cliente.getDireccion().getIddireccion());
            query.executeUpdate();
            Query query1 = sesion.createQuery("update Cliente set nombreCliente =:nomCliente, apellidoCliente =:apeCliente, fechaNacimiento =:fechanac, fechaAltaCliente =:fechaal, sexo =:sex,telefonoCliente=:tel, movilCliente=:mov, nombre_perfil=:perfil "
                    + "where idcliente =:idcli");
            query1.setParameter("nomCliente", cliente.getNombreCliente());
            query1.setParameter("apeCliente", cliente.getApellidoCliente());
            query1.setParameter("fechanac", cliente.getFechaNacimiento());
            query1.setParameter("fechaal", cliente.getFechaAltaCliente());
            query1.setParameter("sex", cliente.getSexo());
            query1.setParameter("tel", cliente.getTelefonoCliente());
            query1.setParameter("mov", cliente.getMovilCliente());
            query1.setParameter("perfil", cliente.getPerfilCliente().getNombrePerfil());
            query1.setParameter("idcli", idCliente);
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
    public Cliente getCliente(Integer idCliente) {
        Cliente cliente = new Cliente();
        HibernateUtil.buildSessionFactory();
        try {
            HibernateUtil.openSessionAndBindToThread();
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            String hql = "from Cliente where idCliente=:idCli";
            Query query = sesion.createQuery(hql);
            query.setParameter("idCli", idCliente);
            cliente = (Cliente) query.uniqueResult();
            return cliente;
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ClienteDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            HibernateUtil.closeSessionAndUnbindFromThread();
        }
        //HibernateUtil.closeSessionFactory();
        return null;
    }

    @Override
    public List<Cliente> getAllCliente() {
        List<Cliente> lista = new ArrayList<>();
        HibernateUtil.buildSessionFactory();
        try {
            HibernateUtil.openSessionAndBindToThread();
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            String hql = "from Cliente";
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
