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
import model.dao.ClienteDAO;
import model.pojo.Cliente;

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
public class ClienteController {

    @Autowired
    private ClienteDAO clienteDAO;

    @ResponseBody
    @RequestMapping(value = "/ListaClientes", method = RequestMethod.GET, produces = "application/json")
    public void ObtenerClientes(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Cliente> lista = clienteDAO.getAllCliente();
        System.out.println("entra en obtener clientes");
        Map mMap=null;
        List<Map> list = new ArrayList();
        for(Cliente cliente:lista){
            mMap = new HashMap();
            mMap.put("cliente", cliente);
            mMap.put("iddireccion", cliente.getDireccion().getIddireccion());
            mMap.put("localidad", cliente.getDireccion().getLocalidad());
            mMap.put("calle", cliente.getDireccion().getCalle());
            mMap.put("numero", cliente.getDireccion().getNumero());
            mMap.put("puerta", cliente.getDireccion().getPuerta());
            mMap.put("piso", cliente.getDireccion().getPiso());
            mMap.put("codigoPostal", cliente.getDireccion().getCodigoPostal());
            list.add(mMap);
        }
                
        ObjectMapper mapper = new ObjectMapper();
        try {
            response.setStatus(HttpServletResponse.SC_OK);
            String json = mapper.writeValueAsString(list);
            response.getWriter().write(json);
            Logger.getLogger(Cliente.class.getName()).log(Logger.Level.INFO, lista);
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            java.util.logging.Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @ResponseBody
    @RequestMapping(value = "/getCliente/{idCliente}", method = RequestMethod.GET, produces = "application/json")
    public void ObtenerCliente(HttpServletRequest httpRequest, HttpServletResponse response, @PathVariable("idCliente") Integer idCliente) {
        Cliente cliente = clienteDAO.getCliente(idCliente);
        
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(cliente);
            response.getWriter().write(json);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //
    @ResponseBody
    @RequestMapping(value = "/getDireccion/{idCliente}", method = RequestMethod.GET, produces = "application/json")
    public void ObtenerDireccion(HttpServletRequest httpRequest, HttpServletResponse response, @PathVariable("idCliente") Integer idCliente) {
        Cliente cliente = clienteDAO.getCliente(idCliente);
        System.out.println("controller cliente direccion ++"+cliente.getDireccion().getCalle());
        HashMap<String, Object> hashmap = new HashMap<String, Object>();
        hashmap.put("iddireccion", cliente.getDireccion().getIddireccion());
        hashmap.put("localidad", cliente.getDireccion().getLocalidad());
        hashmap.put("calle", cliente.getDireccion().getCalle());
        hashmap.put("numero", cliente.getDireccion().getNumero());
        hashmap.put("puerta", cliente.getDireccion().getPuerta());
        hashmap.put("piso", cliente.getDireccion().getPiso());
        hashmap.put("codigoPostal", cliente.getDireccion().getCodigoPostal());
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(hashmap);
            response.getWriter().write(json);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //@ResponseBody
    @RequestMapping(value = "/getPerfil/{idCliente}", method = RequestMethod.GET, produces = "application/json")
    public void ObtenerPerfilCliente(HttpServletRequest httpRequest, HttpServletResponse response, @PathVariable("idCliente") Integer idCliente) {
        Cliente cliente = clienteDAO.getCliente(idCliente);
        System.out.println("controller cliente perfil ++"+cliente.getPerfilCliente().getNombrePerfil());
        HashMap<String, Object> hashmap = new HashMap<>();
        hashmap.put("nombrePerfil", cliente.getPerfilCliente().getNombrePerfil());
        hashmap.put("cuota", cliente.getPerfilCliente().getCuota());
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(hashmap);
            response.getWriter().write(json);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @RequestMapping(value = "/deleteCliente/{idCliente}", method = RequestMethod.DELETE)
    public void eliminarCliente(HttpServletRequest request, HttpServletResponse response, @PathVariable("idCliente") Integer idCliente) {
        System.out.println("************entra en eliminar///////////////////-----------------------");
        clienteDAO.eliminar(idCliente);
        response.setStatus(HttpServletResponse.SC_OK);
    }
    
    @RequestMapping(value = "/insertCliente", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public void insertarCliente(HttpServletRequest request, HttpServletResponse response, @RequestBody String jsonEntrada) {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("----------------------entra en insert------------------------");
        try {
            System.out.println("-++++++++"+jsonEntrada);
            Cliente cliente = mapper.readValue(jsonEntrada, Cliente.class);
            System.out.println("**********objeto----"+cliente);
            clienteDAO.insertar(cliente);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @RequestMapping(value = "/updateCliente/{idCliente}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public void actualizarCliente(HttpServletRequest request, HttpServletResponse response, @RequestBody String jsonEntrada, @PathVariable("idCliente") Integer idCliente) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Cliente cliente = mapper.readValue(jsonEntrada, Cliente.class);
            System.out.println("controller cliente update perfil .  --- "+cliente.getPerfilCliente());
            clienteDAO.update(cliente, idCliente);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
