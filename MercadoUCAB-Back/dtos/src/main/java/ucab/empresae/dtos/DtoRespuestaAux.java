package ucab.empresae.dtos;

import java.util.List;

public class DtoRespuestaAux extends DtoBase{

    //Atributos
    long usuario;
    long estudio;
    private List<DtoRespuesta> respuestas;

    //Constructores
    public DtoRespuestaAux() {
    }

    public DtoRespuestaAux(long id) throws Exception {
        super(id);
    }

    //Getters y Setters
    public long getUsuario() {
        return usuario;
    }

    public void setUsuario(long usuario) {
        this.usuario = usuario;
    }

    public long getEstudio() {
        return estudio;
    }

    public void setEstudio(long estudio) {
        this.estudio = estudio;
    }

    public List<DtoRespuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<DtoRespuesta> respuestas) {
        this.respuestas = respuestas;
    }
}
