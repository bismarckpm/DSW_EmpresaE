package ucab.empresae.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cliente", schema = "mercadeoucab")
public class ClienteEntity extends BaseEntity{
    private String estado;
    private String razonSocial;
    private int rif;
    private LugarEntity lugar;
    private UsuarioEntity usuario;
    private List<TelefonoEntity> telefonos;
    private List<ClienteEstudioEntity> clienteestudios;

    @Basic
    @Column(name = "estado")
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Basic
    @Column(name = "razon_social")
    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    @Basic
    @Column(name = "rif")
    public int getRif() {
        return rif;
    }

    public void setRif(int rif) {
        this.rif = rif;
    }


    @ManyToOne
    @JoinColumn(name = "id_lugar", referencedColumnName = "id", nullable = false)
    public LugarEntity getLugar() {
        return lugar;
    }

    public void setLugar(LugarEntity lugar) {
        this.lugar = lugar;
    }

    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", nullable = false)
    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    @OneToMany(mappedBy = "cliente")
    public List<TelefonoEntity> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<TelefonoEntity> telefonos) {
        this.telefonos = telefonos;
    }

    @OneToMany(mappedBy = "cliente")
    public List<ClienteEstudioEntity> getClienteestudios() {
        return clienteestudios;
    }

    public void setClienteestudios(List<ClienteEstudioEntity> clienteestudios) {
        this.clienteestudios = clienteestudios;
    }
}
