package model.pojo;
// Generated 13-ene-2015 9:45:59 by Hibernate Tools 4.3.1

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * OrdenProducto generated by hbm2java
 */
@Entity
@Table(name = "orden_producto", catalog = "peluqueria"
)
public class OrdenProducto implements java.io.Serializable {

    private Integer idcompra;
    private Cliente cliente;
    private Empleado empleado;
    private Date fechaCompra;
    private BigDecimal comisionEmpleado;
    private Boolean facturado;
    private BigDecimal precioTotal;
    private Set<LineaProducto> lineaProductos = new HashSet<>(0);

    public OrdenProducto() {
    }

    public OrdenProducto(Cliente cliente, Empleado empleado, Date fechaCompra, BigDecimal comisionEmpleado, Boolean facturado, BigDecimal precioTotal, Set<LineaProducto> lineaProductos) {
        this.cliente = cliente;
        this.empleado = empleado;
        this.fechaCompra = fechaCompra;
        this.comisionEmpleado = comisionEmpleado;
        this.facturado = facturado;
        this.precioTotal = precioTotal;
        this.lineaProductos = lineaProductos;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "idcompra", unique = true, nullable = false)
    public Integer getIdcompra() {
        return this.idcompra;
    }

    public void setIdcompra(Integer idcompra) {
        this.idcompra = idcompra;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente_cp")
    @JsonBackReference(value = "cliente-ordenproducto")
    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_empleado_cp")
    @JsonBackReference(value = "ordenproducto-empleado")
    public Empleado getEmpleado() {
        return this.empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_compra", length = 10)
    public Date getFechaCompra() {
        return this.fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    @Column(name = "comision_empleado", precision = 6)
    public BigDecimal getComisionEmpleado() {
        return this.comisionEmpleado;
    }

    public void setComisionEmpleado(BigDecimal comisionEmpleado) {
        this.comisionEmpleado = comisionEmpleado;
    }

    @Column(name = "facturado")
    public Boolean getFacturado() {
        return this.facturado;
    }

    public void setFacturado(Boolean facturado) {
        this.facturado = facturado;
    }

    @Column(name = "precio_total", precision = 6)
    public BigDecimal getPrecioTotal() {
        return this.precioTotal;
    }

    public void setPrecioTotal(BigDecimal precioTotal) {
        this.precioTotal = precioTotal;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "ordenProducto")
    @JsonManagedReference(value = "lineaproducto-ordenproducto")
    public Set<LineaProducto> getLineaProductos() {
        return this.lineaProductos;
    }

    public void setLineaProductos(Set<LineaProducto> lineaProductos) {
        this.lineaProductos = lineaProductos;
    }

}
