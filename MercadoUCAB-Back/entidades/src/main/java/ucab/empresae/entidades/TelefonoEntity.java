package ucab.empresae.entidades;

import javax.persistence.*;

@Entity
@Table(name = "telefono", schema = "mercadeoucab")
public class TelefonoEntity extends BaseEntity{
    private String estado;
    private int numero;
    private EncuestadoEntity encuestado;
    private ClienteEntity cliente;

    @Basic
    @Column(name = "estado")
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Basic
    @Column(name = "numero")
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @ManyToOne
    @JoinColumn(name = "id_encuestado", referencedColumnName = "id")
    public EncuestadoEntity getEncuestado() {
        return encuestado;
    }

    public void setEncuestado(EncuestadoEntity encuestado) {
        this.encuestado = encuestado;
    }

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }
}
