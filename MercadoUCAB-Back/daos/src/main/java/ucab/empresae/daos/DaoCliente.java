package ucab.empresae.daos;

import ucab.empresae.entidades.ClienteEntity;

import javax.persistence.EntityManager;

public class DaoCliente extends Dao<ClienteEntity> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoCliente() {
        super(_handler);
    }

}
