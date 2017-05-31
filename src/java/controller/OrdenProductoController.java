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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.OrdenProductoDAO;
import model.dao.OrdenProductoDAOImp;
import model.pojo.LineaProducto;
import model.pojo.OrdenProducto;
import model.pojo.Producto;
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
public class OrdenProductoController {

    @Autowired
    private OrdenProductoDAO productosDAO;

    @ResponseBody
    @RequestMapping(value = "/getProductoVenta/{idcompra}", method = RequestMethod.GET, produces = "application/json")
    public void ObtenerProductoVenta(HttpServletRequest httpRequest, HttpServletResponse response, @PathVariable("idcompra") Integer idProducto) {
        OrdenProducto producto = productosDAO.getProductoVenta(idProducto);
        System.out.println("entra en ventas controller" + producto);
        Map mMap = new HashMap();

        System.out.println("controlador ventas -- cliente --" + producto.getFacturado());
        mMap.put("clientes", producto.getCliente());
        mMap.put("empleados", producto.getEmpleado().getNombreEmpleado() + " " + producto.getEmpleado().getApellidoEmpleado());
        mMap.put("fechaCompra", producto.getFechaCompra());
        mMap.put("facturado", producto.getFacturado());
        mMap.put("comisionEmpleado", producto.getComisionEmpleado());
        mMap.put("idcompra", producto.getIdcompra());

        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(mMap);
            response.getWriter().write(json);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/ListaVentas", method = RequestMethod.GET, produces = "application/json")
    public void ObtenerProductos(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<OrdenProducto> lista = productosDAO.getAllOrdenProducto();
        List<Producto> productos=new ArrayList<>();
        Map mMap = null;
        List<Map> list = new ArrayList();
        for (OrdenProducto venta : lista) {
            System.out.println("controlador ventas -- cliente --" + venta.getFacturado());
            mMap = new HashMap();
            mMap.put("clientes", venta.getCliente());
            mMap.put("empleados", venta.getEmpleado());
            mMap.put("fechaCompra", venta.getFechaCompra());
            mMap.put("facturado", venta.getFacturado());
            Iterator<LineaProducto> iter= venta.getLineaProductos().iterator();
            while(iter.hasNext()){
                productos.add(iter.next().getProducto());
            }
            mMap.put("lineaProducto", productos);
            mMap.put("lineasProducto", venta.getLineaProductos());
            mMap.put("comisionEmpleado", venta.getComisionEmpleado());
            mMap.put("idcompra", venta.getIdcompra());
            list.add(mMap);
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            response.setStatus(HttpServletResponse.SC_OK);
            String json = mapper.writeValueAsString(list);
            response.getWriter().write(json);
            Logger.getLogger(OrdenProducto.class.getName()).log(Logger.Level.INFO, lista);
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            java.util.logging.Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @RequestMapping(value = "/insertProductoVenta", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public void insertarProducto(HttpServletRequest request, HttpServletResponse response, @RequestBody String jsonEntrada) {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("entra en insertar producto");
        try {
            OrdenProducto producto = mapper.readValue(jsonEntrada, OrdenProducto.class);
            System.out.println("mapea producto en insert empleados "+producto);
            productosDAO.insertar(producto);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @RequestMapping(value = "/deleteOrdenProducto/{idProducto}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public void eliminarOrdenProducto(HttpServletRequest request, HttpServletResponse response, @PathVariable("idProducto") Integer idProducto) {
        productosDAO.eliminar(idProducto);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @RequestMapping(value = "/updateOrdenProducto/{idProducto}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public void actualizarOrdenProducto(HttpServletRequest request, HttpServletResponse response, @RequestBody String jsonEntrada, @PathVariable("idProducto") Integer idProducto) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            OrdenProducto producto = mapper.readValue(jsonEntrada, OrdenProducto.class);
            productosDAO.update(producto, idProducto);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

