package ucab.empresae.daos;

import ucab.empresae.entidades.LugarEntity;

import javax.persistence.EntityManager;

public class DaoLugar extends Dao<LugarEntity> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoLugar() {
        super(_handler);
    }

}