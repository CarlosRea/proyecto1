package model.pojo;
// Generated 13-ene-2015 9:45:59 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Servicio generated by hbm2java
 */
@Entity
@Table(name="servicio"
    ,catalog="peluqueria"
)
public class Servicio  implements java.io.Serializable {


     private Integer idservicio;
     private String nombreServicio;
     private BigDecimal precioServicio;
     private Set<LineaServicio> lineaServicios = new HashSet<LineaServicio>(0);

    public Servicio() {
    }

    public Servicio(String nombreServicio, BigDecimal precioServicio, Set<LineaServicio> lineaServicios) {
       this.nombreServicio = nombreServicio;
       this.precioServicio = precioServicio;
       this.lineaServicios = lineaServicios;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idservicio", unique=true, nullable=false)
    public Integer getIdservicio() {
        return this.idservicio;
    }
    
    public void setIdservicio(Integer idservicio) {
        this.idservicio = idservicio;
    }

    
    @Column(name="nombre_servicio", length=45)
    public String getNombreServicio() {
        return this.nombreServicio;
    }
    
    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    
    @Column(name="precio_servicio", precision=6)
    public BigDecimal getPrecioServicio() {
        return this.precioServicio;
    }
    
    public void setPrecioServicio(BigDecimal precioServicio) {
        this.precioServicio = precioServicio;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="servicio")
    public Set<LineaServicio> getLineaServicios() {
        return this.lineaServicios;
    }
    
    public void setLineaServicios(Set<LineaServicio> lineaServicios) {
        this.lineaServicios = lineaServicios;
    }




}

