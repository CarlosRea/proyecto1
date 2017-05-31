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
import model.dao.OrdenServicioDAO;
import model.dao.OrdenServicioDAOImp;
import model.pojo.OrdenServicio;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author creanogales
 */
@Controller
public class OrdenServicioController {
    
    @Autowired
    private OrdenServicioDAO ordenservicioDAO;
    
    
    @ResponseBody
    @RequestMapping(value = "/ListaOrdenServicios", method = RequestMethod.GET, produces = "application/json")
    public void ObtenerServicios(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<OrdenServicio> lista = ordenservicioDAO.getAllOrdenServicio();
        Map mMap = null;
        List<Map> list = new ArrayList();
        for (OrdenServicio orden : lista) {
            System.out.println("controlador orden servicio --" + orden.getFacturado());
            mMap = new HashMap();
            mMap.put("clientes", orden.getCliente().getNombreCliente());
            mMap.put("empleados", orden.getEmpleado().getNombreEmpleado() + " " + orden.getEmpleado().getApellidoEmpleado());
            mMap.put("fechaServicio", orden.getFechaServicio());
            mMap.put("facturado", orden.getFacturado());
            mMap.put("lineaServicio", orden.getLineaServicios());
            mMap.put("comisionEmpleado", orden.getComisionEmpleado());
            mMap.put("idfactura", orden.getIdfactura());
            list.add(mMap);
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            response.setStatus(HttpServletResponse.SC_OK);
            String json = mapper.writeValueAsString(list);
            response.getWriter().write(json);
            Logger.getLogger(OrdenServicio.class.getName()).log(Logger.Level.INFO, lista);
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            java.util.logging.Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
