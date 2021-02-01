package ucab.empresae.dtos;

public class DtoBase {
    private long _id;


    public DtoBase( long id )
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

    public void set_id( long id )
    {
        if ( id >= 0 )
        {
            _id = id;
        }
    }
}
