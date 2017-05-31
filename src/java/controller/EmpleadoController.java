/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.EmpleadoDAO;
import model.pojo.Empleado;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author creanogales
 */
@Controller
public class EmpleadoController {
    
    @Autowired
    private EmpleadoDAO empleadoDAO;
    
    
    @ResponseBody
    @RequestMapping(value = "/iniciarSesion/{nombreUsuario}/{clave}", method = RequestMethod.GET, produces = "application/json")
    public void iniciarSesion(HttpServletRequest httpRequest, HttpServletResponse response, @PathVariable("nombreUsuario") String nombreUsuario, @PathVariable("clave") String clave) {
        List<Empleado> lista = empleadoDAO.getAll();
        boolean salida=false;
        Map hashmap=new HashMap();
        for(Empleado empleado:lista){
            if(empleado.getNombreEmpleado().equals(nombreUsuario)){
                if(empleado.getPassword().equals(clave)){
                    salida=true;
                    hashmap.put("empleado", empleado);
                    hashmap.put("puesto", empleado.getPuestoempleado().getNombrePuesto());
                }
            }
        }
        hashmap.put("resultado", salida);
        
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(hashmap);
            response.getWriter().write(json);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @ResponseBody
    @RequestMapping(value = "/ListaEmpleados", method = RequestMethod.GET, produces = "application/json")
    public void ObtenerEmpleado(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Empleado> lista = empleadoDAO.getAll();
        Map mMap=null;
        List<Map> list = new ArrayList();
        for(Empleado empleado:lista){
            mMap = new HashMap();
            System.out.println("entra en controller empleados "+ empleado.getNombreEmpleado());
            mMap.put("empleado", empleado);
            mMap.put("iddireccion", empleado.getDireccion().getIddireccion());
            mMap.put("localidad", empleado.getDireccion().getLocalidad());
            mMap.put("calle", empleado.getDireccion().getCalle());
            mMap.put("numero", empleado.getDireccion().getNumero());
            mMap.put("puerta", empleado.getDireccion().getPuerta());
            mMap.put("piso", empleado.getDireccion().getPiso());
            mMap.put("codigoPostal", empleado.getDireccion().getCodigoPostal());
            list.add(mMap);
        }
        
        ObjectMapper mapper = new ObjectMapper();
        try {
            response.setStatus(HttpServletResponse.SC_OK);
            String json = mapper.writeValueAsString(list);
            response.getWriter().write(json);
            Logger.getLogger(Empleado.class.getName()).log(Logger.Level.INFO, lista);
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            java.util.logging.Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @ResponseBody
    @RequestMapping(value = "/getEmpleado/{idEmpleado}", method = RequestMethod.GET, produces = "application/json")
    public void ObtenerEmpleado(HttpServletRequest httpRequest, HttpServletResponse response, @PathVariable("idEmpleado") Integer idEmpleado) {
        Empleado empleado = empleadoDAO.getEmpleado(idEmpleado);
        System.out.println("entra en controller empleado "+empleado.getNombreEmpleado());
        HashMap<String, Object> hashmap = new HashMap<>();
        hashmap.put("puestoempleado", empleado.getPuestoempleado().getNombrePuesto());
        hashmap.put("empleado", empleado);
        hashmap.put("direccion", empleado.getDireccion());
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(hashmap);
            response.getWriter().write(json);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @RequestMapping(value = "/insertEmpleado", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public void insertarEmpleado(HttpServletRequest request, HttpServletResponse response, @RequestBody String jsonEntrada) {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("----------------------entra en insert------------------------");
        try {
            System.out.println("-++++++++"+jsonEntrada);
            Empleado empleado = mapper.readValue(jsonEntrada, Empleado.class);
            System.out.println("**********objeto----"+empleado);
            empleadoDAO.insertar(empleado);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @RequestMapping(value = "/updateEmpleado/{idempleado}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public void actualizarEmpleado(HttpServletRequest request, HttpServletResponse response, @RequestBody String jsonEntrada, @PathVariable("idempleado") Integer idempleado) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Empleado empleado = mapper.readValue(jsonEntrada, Empleado.class);
            System.out.println("controller empleado update .  --- "+empleado.getNombreEmpleado());
            empleadoDAO.update(empleado, idempleado);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @RequestMapping(value = "/deleteEmpleado/{idEmpleado}", method = RequestMethod.DELETE)
    public void eliminarEmpleado(HttpServletRequest request, HttpServletResponse response, @PathVariable("idEmpleado") Integer idEmpleado) {
        System.out.println("************entra en eliminar///////////////////-----------------------");
        empleadoDAO.eliminar(idEmpleado);
        response.setStatus(HttpServletResponse.SC_OK);
    }
    
    
}
