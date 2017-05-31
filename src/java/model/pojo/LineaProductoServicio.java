package model.pojo;
// Generated 13-ene-2015 9:45:59 by Hibernate Tools 4.3.1


import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * LineaProductoServicio generated by hbm2java
 */
@Entity
@Table(name="linea_producto_servicio"
    ,catalog="peluqueria"
)
public class LineaProductoServicio  implements java.io.Serializable {


     private int idproductoServicio;
     private OrdenServicio ordenServicio;
     private Producto producto;
     private Integer gastado;

    public LineaProductoServicio() {
    }

	
    public LineaProductoServicio(int idproductoServicio, OrdenServicio ordenServicio, Producto producto) {
        this.idproductoServicio = idproductoServicio;
        this.ordenServicio = ordenServicio;
        this.producto = producto;
    }
    public LineaProductoServicio(int idproductoServicio, OrdenServicio ordenServicio, Producto producto, Integer gastado) {
       this.idproductoServicio = idproductoServicio;
       this.ordenServicio = ordenServicio;
       this.producto = producto;
       this.gastado = gastado;
    }
   
     @Id 

    
    @Column(name="idproductoServicio", unique=true, nullable=false)
    public int getIdproductoServicio() {
        return this.idproductoServicio;
    }
    
    public void setIdproductoServicio(int idproductoServicio) {
        this.idproductoServicio = idproductoServicio;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="orden_servicio_idfactura", nullable=false)
@JsonBackReference(value = "ordenservicio-lineaproductoservicio")
    public OrdenServicio getOrdenServicio() {
        return this.ordenServicio;
    }
    
    public void setOrdenServicio(OrdenServicio ordenServicio) {
        this.ordenServicio = ordenServicio;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="producto_idproducto", nullable=false)
@JsonBackReference(value = "producto-lineaproductoservicios")
    public Producto getProducto() {
        return this.producto;
    }
    
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    
    @Column(name="gastado")
    public Integer getGastado() {
        return this.gastado;
    }
    
    public void setGastado(Integer gastado) {
        this.gastado = gastado;
    }




}

