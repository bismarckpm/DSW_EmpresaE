package ucab.empresae.daos;

import ucab.empresae.entidades.CategoriaEntity;
import ucab.empresae.entidades.SubcategoriaEntity;
import ucab.empresae.entidades.UsuarioEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DaoSubcategoria extends Dao<SubcategoriaEntity>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();


    public DaoSubcategoria( )
    {
        super( _handler );
        this._em = _handler.getSession();
    }

    public List<SubcategoriaEntity> getSubcategorias(long id) {
        try{
            TypedQuery<SubcategoriaEntity> subcategorias = this._em.createQuery("select a from SubcategoriaEntity a where a.categoria._id = :id", SubcategoriaEntity.class);
            subcategorias.setParameter("id", id);

            return subcategorias.getResultList();
        }catch (Exception ex) {
            return null;
        }
    }
}
