package ucab.empresae.entidades;

import javax.persistence.*;

@Entity
@Table(name = "telefono", schema = "mercadeoucab")
@NamedQueries({
        @NamedQuery(name = "getTelefonoByCliente", query = "select t from TelefonoEntity t where t.cliente = :cliente"),
        @NamedQuery(name = "getTelefonoByEncuestado", query = "select t from TelefonoEntity t where t.encuestado = :encuestado")
})
public class TelefonoEntity extends BaseEntity{

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
    @Column(name = "numero")
    private String numero;
    public String  getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @ManyToOne
    @JoinColumn(name = "id_encuestado", referencedColumnName = "id")
    private EncuestadoEntity encuestado;
    public EncuestadoEntity getEncuestado() {
        return encuestado;
    }

    public void setEncuestado(EncuestadoEntity encuestado) {
        this.encuestado = encuestado;
    }

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private ClienteEntity cliente;
    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }
}
