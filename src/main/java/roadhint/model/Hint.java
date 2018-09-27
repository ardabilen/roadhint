package roadhint.model;

import com.vividsolutions.jts.geom.Point;
import roadhint.enums.HintType;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "hint")
@Inheritance(strategy = InheritanceType.JOINED)
public class Hint implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private HintType type;

//    @Column(columnDefinition = "geometry(Point,4326)")
    @Column(name = "location")
    private Point location;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HintType getType() {
        return type;
    }

    public void setType(HintType type) {
        this.type = type;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

}