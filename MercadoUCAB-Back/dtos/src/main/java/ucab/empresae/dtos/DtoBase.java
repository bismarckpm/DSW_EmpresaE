package ucab.empresae.dtos;

import ucab.empresae.excepciones.PruebaExcepcion;

public class DtoBase {
    private long _id;


    public DtoBase( long id ) throws Exception
    {
        set_id( id );
    }

    public DtoBase()
    {
    }

    public long get_id()
    {
        return _id;
    }

    public void set_id( long id ) throws PruebaExcepcion
    {
        if ( id >= 0 )
        {
            _id = id;
        }
        else
        {
            throw new PruebaExcepcion();
        }
    }
}
