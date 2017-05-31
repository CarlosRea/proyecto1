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
import model.dao.ProductoDAOImp;
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
public class ProductoController {
    @Autowired
    private ProductoDAOImp productosDAO;
    
    @ResponseBody
    @RequestMapping(value = "/ListaProductos", method = RequestMethod.GET, produces = "application/json")
    public void ObtenerProductos(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Producto> lista = productosDAO.getAllProducto();
        Map mMap=null;
        List<Map> list = new ArrayList();
        for(Producto producto:lista){
            mMap=new HashMap();
            mMap.put("idproducto", producto.getIdproducto());
            mMap.put("nombreProducto", producto.getNombreProducto());
            mMap.put("barCodigo", producto.getBarCodigo());
            mMap.put("capacidadProducto", producto.getCapacidadProducto());
            mMap.put("precioCompra", producto.getPrecioCompra());
            mMap.put("precioVenta", producto.getPrecioVenta());
            mMap.put("precioServicio", producto.getPrecioServicio());
            list.add(mMap);
        }
        System.out.println("lista de productos --++ "+lista.get(0).getNombreProducto());
        ObjectMapper mapper = new ObjectMapper();
        try {
            response.setStatus(HttpServletResponse.SC_OK);
            String json = mapper.writeValueAsString(list);
            response.getWriter().write(json);
            Logger.getLogger(ProductoController.class.getName()).log(Logger.Level.INFO, lista);
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            java.util.logging.Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @ResponseBody
    @RequestMapping(value = "/getProducto/{idProducto}", method = RequestMethod.GET, produces = "application/json")
    public void ObtenerProducto(HttpServletRequest httpRequest, HttpServletResponse response, @PathVariable("idProducto") Integer idProducto) {
        Producto producto = productosDAO.getProducto(idProducto);
        ObjectMapper mapper = new ObjectMapper();
        Map mMap=null;
            mMap=new HashMap();
            mMap.put("idproducto", producto.getIdproducto());
            mMap.put("nombreProducto", producto.getNombreProducto());
            mMap.put("barCodigo", producto.getBarCodigo());
            mMap.put("capacidadProducto", producto.getCapacidadProducto());
            mMap.put("precioCompra", producto.getPrecioCompra());
            mMap.put("precioVenta", producto.getPrecioVenta());
            mMap.put("precioServicio", producto.getPrecioServicio());
            mMap.put("categoriaProducto", producto.getCategoriaProducto().getNombreCategoria());
        try {
            String json = mapper.writeValueAsString(mMap);
            response.getWriter().write(json);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @RequestMapping(value = "/insertProducto", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public void insertarProducto(HttpServletRequest request, HttpServletResponse response, @RequestBody String jsonEntrada) {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("----------------------entra en insert------------------------");
        try {
            System.out.println("-++++++++"+jsonEntrada);
            Producto producto = mapper.readValue(jsonEntrada, Producto.class);
            System.out.println("**********objeto----"+producto);
            productosDAO.insertar(producto);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @RequestMapping(value = "/updateProducto/{idProducto}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public void actualizarCliente(HttpServletRequest request, HttpServletResponse response, @RequestBody String jsonEntrada, @PathVariable("idProducto") Integer idProducto) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Producto producto = mapper.readValue(jsonEntrada, Producto.class);
            System.out.println("controller producto update  .  --- "+producto.getCategoriaProducto());
            productosDAO.update(producto, idProducto);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @RequestMapping(value = "/deleteProducto/{idProducto}", method = RequestMethod.DELETE)
    public void eliminarCliente(HttpServletRequest request, HttpServletResponse response, @PathVariable("idProducto") Integer idProducto) {
        System.out.println("************entra en eliminar///////////////////-----------------------");
        productosDAO.eliminar(idProducto);
        response.setStatus(HttpServletResponse.SC_OK);
    }
    
}
