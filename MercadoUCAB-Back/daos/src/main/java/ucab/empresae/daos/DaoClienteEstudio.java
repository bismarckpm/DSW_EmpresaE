package ucab.empresae.daos;

import ucab.empresae.entidades.ClienteEstudioEntity;

import javax.persistence.EntityManager;

public class DaoClienteEstudio extends Dao<ClienteEstudioEntity> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoClienteEstudio() {
        super(_handler);
    }

}
