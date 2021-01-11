package ucab.empresae.entidades;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @Column( name = "Id" )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long _id;

    public BaseEntity( long id ) {
        _id = id;
    }

    public BaseEntity() {

    }

    public long get_id() {
        return _id;
    }

    public void set_id( long id ) {
        this._id = id;
    }
}
