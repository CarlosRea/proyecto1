package model.pojo;
// Generated 13-ene-2015 9:45:59 by Hibernate Tools 4.3.1

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * PerfilCliente generated by hbm2java
 */
@Entity
@Table(name = "perfil_cliente", catalog = "peluqueria"
)
public class PerfilCliente implements java.io.Serializable {

    private String nombrePerfil;
    private Integer cuota;
    private Set<Cliente> clientes = new HashSet<Cliente>(0);

    public PerfilCliente() {
    }

    public PerfilCliente(String nombrePerfil) {
        this.nombrePerfil = nombrePerfil;
    }

    public PerfilCliente(String nombrePerfil, Integer cuota, Set<Cliente> clientes) {
        this.nombrePerfil = nombrePerfil;
        this.cuota = cuota;
        this.clientes = clientes;
    }

    @Id

    @Column(name = "nombre_perfil", unique = true, nullable = false, length = 45)
    public String getNombrePerfil() {
        return this.nombrePerfil;
    }

    public void setNombrePerfil(String nombrePerfil) {
        this.nombrePerfil = nombrePerfil;
    }

    @Column(name = "cuota")
    public Integer getCuota() {
        return this.cuota;
    }

    public void setCuota(Integer cuota) {
        this.cuota = cuota;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "perfilCliente")
    @JsonManagedReference(value = "cliente-perfil")
    public Set<Cliente> getClientes() {
        return this.clientes;
    }

    public void setClientes(Set<Cliente> clientes) {
        this.clientes = clientes;
    }

}