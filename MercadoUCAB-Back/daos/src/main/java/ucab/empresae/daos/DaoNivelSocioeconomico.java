package ucab.empresae.daos;

import ucab.empresae.entidades.NivelSocioeconomicoEntity;

import javax.persistence.EntityManager;

public class DaoNivelSocioeconomico extends Dao<NivelSocioeconomicoEntity> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoNivelSocioeconomico() {
        super(_handler);
    }

}
