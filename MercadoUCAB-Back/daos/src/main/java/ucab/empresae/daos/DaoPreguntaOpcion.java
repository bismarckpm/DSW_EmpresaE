package ucab.empresae.daos;

import ucab.empresae.entidades.PreguntaOpcionEntity;

import javax.persistence.EntityManager;

public class DaoPreguntaOpcion extends Dao<PreguntaOpcionEntity>{
    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoPreguntaOpcion() {
        super(_handler);
    }
}
