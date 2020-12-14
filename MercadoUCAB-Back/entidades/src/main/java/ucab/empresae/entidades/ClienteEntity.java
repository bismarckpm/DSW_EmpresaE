package ucab.empresae.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cliente", schema = "mercadeoucab")
public class ClienteEntity extends BaseEntity{

    public ClienteEntity(long id) {
        super(id);
    }

    public ClienteEntity() {
    }

    @Basic
    @Column(name = "estado")
    private String estado;
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Basic
    @Column(name = "razon_social")
    private String razonSocial;
    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    @Basic
    @Column(name = "rif")
    private int rif;
    public int getRif() {
        return rif;
    }

    public void setRif(int rif) {
        this.rif = rif;
    }


    @ManyToOne
    @JoinColumn(name = "id_lugar", referencedColumnName = "id", nullable = false)
    private LugarEntity lugar;
    public LugarEntity getLugar() {
        return lugar;
    }

    public void setLugar(LugarEntity lugar) {
        this.lugar = lugar;
    }

    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", nullable = false)
    private UsuarioEntity usuario;
    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    /*@OneToMany(mappedBy = "cliente")
    private List<TelefonoEntity> telefonos;
    public List<TelefonoEntity> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<TelefonoEntity> telefonos) {
        this.telefonos = telefonos;
    }

    @OneToMany(mappedBy = "cliente")
    private List<ClienteEstudioEntity> clienteestudios;
    public List<ClienteEstudioEntity> getClienteestudios() {
        return clienteestudios;
    }

    public void setClienteestudios(List<ClienteEstudioEntity> clienteestudios) {
        this.clienteestudios = clienteestudios;
    }*/
}
