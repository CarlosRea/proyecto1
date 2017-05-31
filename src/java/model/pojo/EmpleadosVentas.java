package model.pojo;
// Generated 13-ene-2015 9:45:59 by Hibernate Tools 4.3.1

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * EmpleadosVentas generated by hbm2java
 */
@Entity
@Table(name = "empleados_ventas", catalog = "peluqueria"
)
public class EmpleadosVentas implements java.io.Serializable {

    private Integer idempleadosVentas;
    private Empleado empleado;
    private Date fechaVentas;
    private Integer valorVenta;
    private Integer comisionVenta;

    public EmpleadosVentas() {
    }

    public EmpleadosVentas(Empleado empleado) {
        this.empleado = empleado;
    }

    public EmpleadosVentas(Empleado empleado, Date fechaVentas, Integer valorVenta, Integer comisionVenta) {
        this.empleado = empleado;
        this.fechaVentas = fechaVentas;
        this.valorVenta = valorVenta;
        this.comisionVenta = comisionVenta;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "idempleados_ventas", unique = true, nullable = false)
    public Integer getIdempleadosVentas() {
        return this.idempleadosVentas;
    }

    public void setIdempleadosVentas(Integer idempleadosVentas) {
        this.idempleadosVentas = idempleadosVentas;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Empleados_idempleado", nullable = false)
    @JsonBackReference(value = "empleado-empleadosVentases")
    public Empleado getEmpleado() {
        return this.empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_ventas", length = 10)
    public Date getFechaVentas() {
        return this.fechaVentas;
    }

    public void setFechaVentas(Date fechaVentas) {
        this.fechaVentas = fechaVentas;
    }

    @Column(name = "valor_venta")
    public Integer getValorVenta() {
        return this.valorVenta;
    }

    public void setValorVenta(Integer valorVenta) {
        this.valorVenta = valorVenta;
    }

    @Column(name = "comision_venta")
    public Integer getComisionVenta() {
        return this.comisionVenta;
    }

    public void setComisionVenta(Integer comisionVenta) {
        this.comisionVenta = comisionVenta;
    }

}
