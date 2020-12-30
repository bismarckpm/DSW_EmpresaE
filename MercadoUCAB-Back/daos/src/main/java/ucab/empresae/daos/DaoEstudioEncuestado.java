package ucab.empresae.daos;

import ucab.empresae.entidades.EstudioEncuestadoEntity;
import javax.persistence.EntityManager;

public class DaoEstudioEncuestado extends Dao<EstudioEncuestadoEntity>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoEstudioEncuestado() {
        super(_handler);
        this._em = _handler.getSession();
    }
}
