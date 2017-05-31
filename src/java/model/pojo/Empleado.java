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
 * Empleado generated by hbm2java
 */
@Entity
@Table(name = "empleado", catalog = "peluqueria"
)
public class Empleado implements java.io.Serializable {

    private Integer idempleado;
    private Direccion direccion;
    private Puestoempleado puestoempleado;
    private String nombreEmpleado;
    private String apellidoEmpleado;
    private String dni;
    private String password;
    private String sexo;
    private Date fechaNacimiento;
    private Date fechaAltaEmpleado;
    private Integer telefonoEmpleado;
    private Integer movilEmpleado;
    private BigDecimal comisionPorcentaje;
    private Set<OrdenProducto> ordenProductos = new HashSet<>(0);
    private Set<OrdenServicio> ordenServicios = new HashSet<>(0);
    private Set<EmpleadosVentas> empleadosVentases = new HashSet<>(0);

    public Empleado() {
    }

    public Empleado(Direccion direccion, Puestoempleado puestoempleado) {
        this.direccion = direccion;
        this.puestoempleado = puestoempleado;
    }

    public Empleado(Direccion direccion, Puestoempleado puestoempleado, String nombreEmpleado, String apellidoEmpleado, String dni, String password, String sexo, Date fechaNacimiento, Date fechaAltaEmpleado, Integer telefonoEmpleado, Integer movilEmpleado, BigDecimal comisionPorcentaje, Set<OrdenProducto> ordenProductos, Set<OrdenServicio> ordenServicios, Set<EmpleadosVentas> empleadosVentases) {
        this.direccion = direccion;
        this.puestoempleado = puestoempleado;
        this.nombreEmpleado = nombreEmpleado;
        this.apellidoEmpleado = apellidoEmpleado;
        this.dni = dni;
        this.password = password;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaAltaEmpleado = fechaAltaEmpleado;
        this.telefonoEmpleado = telefonoEmpleado;
        this.movilEmpleado = movilEmpleado;
        this.comisionPorcentaje = comisionPorcentaje;
        this.ordenProductos = ordenProductos;
        this.ordenServicios = ordenServicios;
        this.empleadosVentases = empleadosVentases;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "idempleado", unique = true, nullable = false)
    public Integer getIdempleado() {
        return this.idempleado;
    }

    public void setIdempleado(Integer idempleado) {
        this.idempleado = idempleado;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = javax.persistence.CascadeType.REMOVE)
    @JoinColumn(name = "direccion_iddireccion", nullable = false)
    @JsonBackReference(value = "empleado-direccion")
    public Direccion getDireccion() {
        return this.direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "puestoEmpleado_nombre_puesto", nullable = false)
    @JsonBackReference(value = "empleado-puestoempleado")
    public Puestoempleado getPuestoempleado() {
        return this.puestoempleado;
    }

    public void setPuestoempleado(Puestoempleado puestoempleado) {
        this.puestoempleado = puestoempleado;
    }

    @Column(name = "nombre_empleado", length = 70)
    public String getNombreEmpleado() {
        return this.nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    @Column(name = "apellido_empleado", length = 100)
    public String getApellidoEmpleado() {
        return this.apellidoEmpleado;
    }

    public void setApellidoEmpleado(String apellidoEmpleado) {
        this.apellidoEmpleado = apellidoEmpleado;
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
    @Column(name = "fecha_alta_empleado", length = 10)
    public Date getFechaAltaEmpleado() {
        return this.fechaAltaEmpleado;
    }

    public void setFechaAltaEmpleado(Date fechaAltaEmpleado) {
        this.fechaAltaEmpleado = fechaAltaEmpleado;
    }

    @Column(name = "telefono_empleado")
    public Integer getTelefonoEmpleado() {
        return this.telefonoEmpleado;
    }

    public void setTelefonoEmpleado(Integer telefonoEmpleado) {
        this.telefonoEmpleado = telefonoEmpleado;
    }

    @Column(name = "movil_empleado")
    public Integer getMovilEmpleado() {
        return this.movilEmpleado;
    }

    public void setMovilEmpleado(Integer movilEmpleado) {
        this.movilEmpleado = movilEmpleado;
    }

    @Column(name = "comision_porcentaje", precision = 6)
    public BigDecimal getComisionPorcentaje() {
        return this.comisionPorcentaje;
    }

    public void setComisionPorcentaje(BigDecimal comisionPorcentaje) {
        this.comisionPorcentaje = comisionPorcentaje;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "empleado")
    @JsonManagedReference(value = "ordenproducto-empleado")
    public Set<OrdenProducto> getOrdenProductos() {
        return this.ordenProductos;
    }

    public void setOrdenProductos(Set<OrdenProducto> ordenProductos) {
        this.ordenProductos = ordenProductos;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "empleado")
    @JsonManagedReference(value = "ordenservicio-empleado")
    public Set<OrdenServicio> getOrdenServicios() {
        return this.ordenServicios;
    }

    public void setOrdenServicios(Set<OrdenServicio> ordenServicios) {
        this.ordenServicios = ordenServicios;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "empleado")
    @JsonManagedReference(value = "empleado-empleadosVentases")
    public Set<EmpleadosVentas> getEmpleadosVentases() {
        return this.empleadosVentases;
    }

    public void setEmpleadosVentases(Set<EmpleadosVentas> empleadosVentases) {
        this.empleadosVentases = empleadosVentases;
    }

}