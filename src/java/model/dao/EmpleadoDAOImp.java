/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import model.pojo.Empleado;
import org.hibernate.Query;
import org.hibernate.Session;
import utils.HibernateUtil;

/**
 *
 * @author creanogales
 */
public class EmpleadoDAOImp implements EmpleadoDAO{

    @Override
    public void insertar(Empleado empleado) {
        HibernateUtil.buildSessionFactory();
        try {
            HibernateUtil.openSessionAndBindToThread();
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            Query query1=sesion.createSQLQuery("insert into Direccion (localidad,calle,codigo_postal,numero, piso, puerta) "
                    + "values (:localidad,:calle,:cod,:num,:pis,:puerta)");
            query1.setParameter("localidad", empleado.getDireccion().getLocalidad());
            query1.setParameter("calle", empleado.getDireccion().getCalle());
            query1.setParameter("cod", empleado.getDireccion().getCodigoPostal());
            query1.setParameter("num", empleado.getDireccion().getNumero());
            query1.setParameter("pis", empleado.getDireccion().getPiso());
            query1.setParameter("puerta", empleado.getDireccion().getPuerta());
            
            query1.executeUpdate();
            
            Integer idDireccion=(Integer)sesion.createQuery("select iddireccion from Direccion where calle=:calle").setParameter("calle", empleado.getDireccion().getCalle()).uniqueResult();
            
            System.out.println("direccion del select -- "+idDireccion);
            
            Integer idPuesto=(Integer) sesion.createQuery("select idpuestoEmpleado from Puestoempleado where nombrePuesto=:puesto").setParameter("puesto", empleado.getPuestoempleado().getNombrePuesto()).uniqueResult();
            System.out.println("id puesto empleado "+idPuesto);
            
            Query query = sesion.createSQLQuery("insert into Empleado (nombre_empleado,apellido_empleado, dni, sexo, fecha_alta_empleado, fecha_nacimiento,telefono_empleado,movil_empleado, comision_porcentaje, puestoEmpleado_idpuestoEmpleado, direccion_iddireccion) "
                    + "values (:nombre, :apellido,:dni,:sexo, :fechaal, :fechanac, :telefono, :movil,:comision,:idpuesto, :direc)");

            query.setParameter("nombre", empleado.getNombreEmpleado());
            query.setParameter("apellido", empleado.getApellidoEmpleado());
            query.setParameter("dni", empleado.getDni());
            query.setParameter("sexo", empleado.getSexo());
            query.setParameter("fechaal", empleado.getFechaAltaEmpleado());
            query.setParameter("fechanac", empleado.getFechaNacimiento());
            query.setParameter("telefono", empleado.getTelefonoEmpleado());
            query.setParameter("movil", empleado.getMovilEmpleado());
            query.setParameter("comision", empleado.getComisionPorcentaje());
            query.setParameter("idpuesto", idPuesto);
            query.setParameter("direc", idDireccion);
            query.executeUpdate();
            sesion.getTransaction().commit();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(EmpleadoDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            HibernateUtil.closeSessionAndUnbindFromThread();
        }
        //HibernateUtil.closeSessionFactory();
    }

    @Override
    public void eliminar(Integer idempleado) {
        HibernateUtil.buildSessionFactory();
        System.out.println("------------------entra en eliminar DAO-------"+ idempleado);
        try {
            HibernateUtil.openSessionAndBindToThread();
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            Query query = sesion.createQuery("delete from Empleado where idempleado =:id");
            query.setParameter("id", idempleado);
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
    public void update(Empleado empleado, Integer idempleado) {
        HibernateUtil.buildSessionFactory();
        try {
            System.out.println("+++++++++++++entra en update empleado DAO--------------- "+idempleado);
            HibernateUtil.openSessionAndBindToThread();
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            System.out.println("localidad "+empleado.getDireccion().getIddireccion());
            Query query = sesion.createQuery("update Direccion set localidad =:loc, calle=:calle,numero=:num,puerta=:puerta,piso=:pis where iddireccion=:iddirec");
            query.setParameter("loc", empleado.getDireccion().getLocalidad());
            query.setParameter("calle", empleado.getDireccion().getCalle());
            query.setParameter("puerta", empleado.getDireccion().getPuerta());
            query.setParameter("pis", empleado.getDireccion().getPiso());
            query.setParameter("num", empleado.getDireccion().getNumero());
            query.setParameter("iddirec", empleado.getDireccion().getIddireccion());
            query.executeUpdate();
            Query query1 = sesion.createQuery("update Empleado set nombreEmpleado =:nom, apellidoEmpleado =:ape, fechaNacimiento =:fechanac, fechaAltaEmpleado =:fechaal, sexo =:sex,telefonoEmpleado=:tel, movilEmpleado=:mov "
                    + "where idempleado =:id");
            query1.setParameter("nom", empleado.getNombreEmpleado());
            query1.setParameter("ape", empleado.getApellidoEmpleado());
            query1.setParameter("fechanac", empleado.getFechaNacimiento());
            query1.setParameter("fechaal", empleado.getFechaAltaEmpleado());
            query1.setParameter("sex", empleado.getSexo());
            query1.setParameter("tel", empleado.getTelefonoEmpleado());
            query1.setParameter("mov", empleado.getMovilEmpleado());
            query1.setParameter("id", idempleado);
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
    public Empleado getEmpleado(Integer idempleado) {
        Empleado empleado = new Empleado();
        HibernateUtil.buildSessionFactory();
        try {
            HibernateUtil.openSessionAndBindToThread();
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            String hql = "from Empleado where idempleado=:id";
            Query query = sesion.createQuery(hql);
            query.setParameter("id", idempleado);
            empleado = (Empleado) query.uniqueResult();
            return empleado;
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ClienteDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            HibernateUtil.closeSessionAndUnbindFromThread();
        }
        //HibernateUtil.closeSessionFactory();
        return null;
    }

    @Override
    public List<Empleado> getAll() {
        List<Empleado> lista = new ArrayList<>();
        HibernateUtil.buildSessionFactory();
        try {
            HibernateUtil.openSessionAndBindToThread();
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            String hql = "from Empleado";
            Query query = sesion.createQuery(hql);
            lista = query.list();
            System.out.println("lista+"+lista);
            return lista;
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(EmpleadoDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            HibernateUtil.closeSessionAndUnbindFromThread();
        }
        //HibernateUtil.closeSessionFactory();
        return null;
    }
    
}
