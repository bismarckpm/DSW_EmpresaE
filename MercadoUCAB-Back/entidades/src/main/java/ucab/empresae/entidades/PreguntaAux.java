package ucab.empresae.entidades;

import java.util.List;

public class PreguntaAux extends BaseEntity{

        //Atributps
        private String estado;
        private String descripcion;

        //Relaciones
        private TipoPreguntaEntity tipo;
        private SubcategoriaEntity subcategoria;

        private List<OpcionEntity> opciones; //atributo para generar la encuesta, (preguntas con opciones)
        private List<OpcionAux> opcionesResultado; //lista de opcion auxiliar que tiene el contador de respuestas

        //Constructores
        public PreguntaAux() {
        }

        public PreguntaAux(long id) throws Exception {
            super(id);
        }

        //Getters y Setters
        public String getEstado() {
            return estado;
        }

        public void setEstado(String estado) {
            this.estado = estado;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public TipoPreguntaEntity getTipo() {
            return tipo;
        }

        public void setTipo(TipoPreguntaEntity tipo) {
            this.tipo = tipo;
        }

        public SubcategoriaEntity getSubcategoria() {
            return subcategoria;
        }

        public void setSubcategoria(SubcategoriaEntity subcategoria) {
            this.subcategoria = subcategoria;
        }

        public List<OpcionEntity> getOpciones() {
            return opciones;
        }

        public void setOpciones(List<OpcionEntity> opciones) {
            this.opciones = opciones;
        }

        public List<OpcionAux> getOpcionesResultado() {
            return opcionesResultado;
        }

        public void setOpcionesResultado(List<OpcionAux> opcionesResultado) {
            this.opcionesResultado = opcionesResultado;
        }



}
