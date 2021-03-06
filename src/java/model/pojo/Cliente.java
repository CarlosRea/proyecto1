package model.pojo;
// Generated 13-ene-2015 9:45:59 by Hibernate Tools 4.3.1

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
 * Cliente generated by hbm2java
 */
@Entity
@Table(name = "cliente", catalog = "peluqueria"
)
public class Cliente implements java.io.Serializable {

    private Integer idcliente;
    private Direccion direccion;
    private PerfilCliente perfilCliente;
    private String nombreCliente;
    private String apellidoCliente;
    private String dni;
    private String password;
    private String sexo;
    private Date fechaNacimiento;
    private Date fechaAltaCliente;
    private Integer telefonoCliente;
    private Integer movilCliente;
    private Set<OrdenServicio> ordenServicios = new HashSet<>(0);
    private Set<OrdenProducto> ordenProductos = new HashSet<>(0);

    public Cliente() {
    }

    public Cliente(Direccion direccion, PerfilCliente perfilCliente) {
        this.direccion = direccion;
        this.perfilCliente = perfilCliente;
    }

    public Cliente(Direccion direccion, PerfilCliente perfilCliente, String nombreCliente, String apellidoCliente, String dni, String password, String sexo, Date fechaNacimiento, Date fechaAltaCliente, Integer telefonoCliente, Integer movilCliente, Set<OrdenServicio> ordenServicios, Set<OrdenProducto> ordenProductos) {
        this.direccion = direccion;
        this.perfilCliente = perfilCliente;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.dni = dni;
        this.password = password;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaAltaCliente = fechaAltaCliente;
        this.telefonoCliente = telefonoCliente;
        this.movilCliente = movilCliente;
        this.ordenServicios = ordenServicios;
        this.ordenProductos = ordenProductos;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "idcliente", unique = true, nullable = false)
    public Integer getIdcliente() {
        return this.idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = javax.persistence.CascadeType.REMOVE)
    @JoinColumn(name = "direccion_iddireccion", nullable = false)
    @JsonBackReference(value = "cliente-direccion")
    public Direccion getDireccion() {
        return this.direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nombre_perfil", nullable = false)
    @JsonBackReference(value = "cliente-perfil")
    public PerfilCliente getPerfilCliente() {
        return this.perfilCliente;
    }

    public void setPerfilCliente(PerfilCliente perfilCliente) {
        this.perfilCliente = perfilCliente;
    }

    @Column(name = "nombre_cliente", length = 100)
    public String getNombreCliente() {
        return this.nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    @Column(name = "apellido_cliente", length = 100)
    public String getApellidoCliente() {
        return this.apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    @Column(name = "dni", length = 9)
    public String getDni() {
        return this.dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Column(name = "password", length = 45)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "sexo", length = 10)
    public String getSexo() {
        return this.sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_nacimiento", length = 10)
    public Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_alta_cliente", length = 10)
    public Date getFechaAltaCliente() {
        return this.fechaAltaCliente;
    }

    public void setFechaAltaCliente(Date fechaAltaCliente) {
        this.fechaAltaCliente = fechaAltaCliente;
    }

    @Column(name = "telefono_cliente")
    public Integer getTelefonoCliente() {
        return this.telefonoCliente;
    }

    public void setTelefonoCliente(Integer telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    @Column(name = "movil_cliente")
    public Integer getMovilCliente() {
        return this.movilCliente;
    }

    public void setMovilCliente(Integer movilCliente) {
        this.movilCliente = movilCliente;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cliente")
    @JsonManagedReference(value = "cliente-ordenservicios")
    public Set<OrdenServicio> getOrdenServicios() {
        return this.ordenServicios;
    }

    public void setOrdenServicios(Set<OrdenServicio> ordenServicios) {
        this.ordenServicios = ordenServicios;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cliente")
    @JsonManagedReference(value = "cliente-ordenproducto")
    public Set<OrdenProducto> getOrdenProductos() {
        return this.ordenProductos;
    }

    public void setOrdenProductos(Set<OrdenProducto> ordenProductos) {
        this.ordenProductos = ordenProductos;
    }

}
