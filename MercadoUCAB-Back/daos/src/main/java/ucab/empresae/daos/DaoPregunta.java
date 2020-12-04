package ucab.empresae.daos;

import ucab.empresae.entidades.PreguntaEntity;

import javax.persistence.EntityManager;

public class DaoPregunta extends Dao<PreguntaEntity>
{
    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoPregunta() {
        super(_handler);
    }
}
