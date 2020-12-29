package ucab.empresae.daos;

import ucab.empresae.entidades.RespuestaEntity;

import javax.persistence.EntityManager;

public class DaoRespuesta extends Dao<RespuestaEntity>{
    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoRespuesta() {
        super(_handler);
    }
}
