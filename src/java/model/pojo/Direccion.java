package model.pojo;
// Generated 13-ene-2015 9:45:59 by Hibernate Tools 4.3.1

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
 * Direccion generated by hbm2java
 */
@Entity
@Table(name = "direccion", catalog = "peluqueria"
)
public class Direccion implements java.io.Serializable {

    private Integer iddireccion;
    private String localidad;
    private String calle;
    private Integer codigoPostal;
    private Integer numero;
    private Integer piso;
    private Integer puerta;
    private Set<Empleado> empleados = new HashSet<Empleado>(0);
    private Set<Cliente> clientes = new HashSet<Cliente>(0);

    public Direccion() {
    }

    public Direccion(String localidad, String calle, Integer codigoPostal, Integer numero, Integer piso, Integer puerta, Set<Empleado> empleados, Set<Cliente> clientes) {
        this.localidad = localidad;
        this.calle = calle;
        this.codigoPostal = codigoPostal;
        this.numero = numero;
        this.piso = piso;
        this.puerta = puerta;
        this.empleados = empleados;
        this.clientes = clientes;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "iddireccion", unique = true, nullable = false)
    public Integer getIddireccion() {
        return this.iddireccion;
    }

    public void setIddireccion(Integer iddireccion) {
        this.iddireccion = iddireccion;
    }

    @Column(name = "localidad", length = 45)
    public String getLocalidad() {
        return this.localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    @Column(name = "calle", length = 90)
    public String getCalle() {
        return this.calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    @Column(name = "codigo_postal")
    public Integer getCodigoPostal() {
        return this.codigoPostal;
    }

    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    @Column(name = "numero")
    public Integer getNumero() {
        return this.numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    @Column(name = "piso")
    public Integer getPiso() {
        return this.piso;
    }

    public void setPiso(Integer piso) {
        this.piso = piso;
    }

    @Column(name = "puerta")
    public Integer getPuerta() {
        return this.puerta;
    }

    public void setPuerta(Integer puerta) {
        this.puerta = puerta;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "direccion")
    @JsonManagedReference(value = "empleado-direccion")
    public Set<Empleado> getEmpleados() {
        return this.empleados;
    }

    public void setEmpleados(Set<Empleado> empleados) {
        this.empleados = empleados;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "direccion")
    @JsonManagedReference(value = "cliente-direccion")
    public Set<Cliente> getClientes() {
        return this.clientes;
    }

    public void setClientes(Set<Cliente> clientes) {
        this.clientes = clientes;
    }

}