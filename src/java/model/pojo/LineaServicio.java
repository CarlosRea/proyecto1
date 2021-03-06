package model.pojo;
// Generated 13-ene-2015 9:45:59 by Hibernate Tools 4.3.1

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * LineaServicio generated by hbm2java
 */
@Entity
@Table(name = "linea_servicio", catalog = "peluqueria"
)
public class LineaServicio implements java.io.Serializable {

    private Integer idlineaServicio;
    private OrdenServicio ordenServicio;
    private Servicio servicio;
    private Integer cantidadOrdenada;

    public LineaServicio() {
    }

    public LineaServicio(OrdenServicio ordenServicio, Servicio servicio) {
        this.ordenServicio = ordenServicio;
        this.servicio = servicio;
    }

    public LineaServicio(OrdenServicio ordenServicio, Servicio servicio, Integer cantidadOrdenada) {
        this.ordenServicio = ordenServicio;
        this.servicio = servicio;
        this.cantidadOrdenada = cantidadOrdenada;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "idlineaServicio", unique = true, nullable = false)
    public Integer getIdlineaServicio() {
        return this.idlineaServicio;
    }

    public void setIdlineaServicio(Integer idlineaServicio) {
        this.idlineaServicio = idlineaServicio;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "orden_servicio_idfactura", nullable = false)
    @JsonBackReference(value = "ordenservicio-lineaservicios")
    public OrdenServicio getOrdenServicio() {
        return this.ordenServicio;
    }

    public void setOrdenServicio(OrdenServicio ordenServicio) {
        this.ordenServicio = ordenServicio;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "servicio_idservicio", nullable = false)
    public Servicio getServicio() {
        return this.servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    @Column(name = "cantidad_ordenada")
    public Integer getCantidadOrdenada() {
        return this.cantidadOrdenada;
    }

    public void setCantidadOrdenada(Integer cantidadOrdenada) {
        this.cantidadOrdenada = cantidadOrdenada;
    }

}
