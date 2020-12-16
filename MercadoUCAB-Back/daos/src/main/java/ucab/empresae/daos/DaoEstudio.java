package ucab.empresae.daos;

import ucab.empresae.entidades.ClienteEntity;
import ucab.empresae.entidades.ClienteEstudioEntity;
import ucab.empresae.entidades.EstudioEntity;
import ucab.empresae.entidades.LugarEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DaoEstudio extends Dao<EstudioEntity> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoEstudio() {
        super(_handler);
        this._em = _handler.getSession();
    }

    public List<EstudioEntity> estudiosCliente(long id) {
        try{
            TypedQuery<EstudioEntity> estudios = this._em
                                    .createNamedQuery("getEstudiosCliente", EstudioEntity.class);
            estudios.setParameter("id", id).getResultList();

            List<EstudioEntity> resultado = estudios.getResultList();

            return resultado;
        }catch (Exception e){
            return null;
        }
    }



}
