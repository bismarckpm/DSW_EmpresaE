package ucab.empresae.daos;

import ucab.empresae.entidades.NivelAcademicoEntity;
import javax.persistence.EntityManager;


public class DaoNivelAcademico extends Dao<NivelAcademicoEntity> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoNivelAcademico( )
    {
        super( _handler );
    }
}
