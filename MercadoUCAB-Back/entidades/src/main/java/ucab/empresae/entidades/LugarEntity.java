package ucab.empresae.entidades;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "lugar", schema = "mercadeoucab")
public class LugarEntity extends BaseEntity{
    private String estado;
    private String nombre;
    private String tipo;
    private List<ClienteEntity> clientes;
    private List<EncuestadoEntity> encuestados;
    private List<LugarEntity> lugares;
    private LugarEntity lugar;
    private NivelSocioeconomicoEntity nivelsocioeco;
    private List<EstudioEntity> estudios;

    @Basic
    @Column(name = "estado")
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Basic
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "tipo")
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @OneToMany(mappedBy = "lugar")
    public List<ClienteEntity> getClientes() {
        return clientes;
    }

    public void setClientes(List<ClienteEntity> clientes) {
        this.clientes = clientes;
    }

    @OneToMany(mappedBy = "lugar")
    public List<EncuestadoEntity> getEncuestados() {
        return encuestados;
    }

    public void setEncuestados(List<EncuestadoEntity> encuestados) {
        this.encuestados = encuestados;
    }

    @OneToMany(mappedBy = "lugar")
    public List<LugarEntity> getLugares() {
        return lugares;
    }

    public void setLugares(List<LugarEntity> lugares) {
        this.lugares = lugares;
    }

    @ManyToOne
    @JoinColumn(name = "id_recursiva", referencedColumnName = "id")
    public LugarEntity getLugar() {
        return lugar;
    }

    public void setLugar(LugarEntity lugar) {
        this.lugar = lugar;
    }

    @ManyToOne
    @JoinColumn(name = "id_nivelsocioeco", referencedColumnName = "id")
    public NivelSocioeconomicoEntity getNivelsocioeco() {
        return nivelsocioeco;
    }

    public void setNivelsocioeco(NivelSocioeconomicoEntity nivelsocioeco) {
        this.nivelsocioeco = nivelsocioeco;
    }

    @OneToMany(mappedBy = "lugar")
    public List<EstudioEntity> getEstudios() {
        return estudios;
    }

    public void setEstudios(List<EstudioEntity> estudios) {
        this.estudios = estudios;
    }

    public LugarEntity() {

    }
    public LugarEntity(long id) {
        super(id);
    }
}
