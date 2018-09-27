package roadhint.model.mapper;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import roadhint.enums.HintType;
import roadhint.model.Circle;
import roadhint.model.Hint;
import roadhint.model.HintCluster;
import roadhint.model.Location;
import org.postgis.PGgeometry;
import org.postgis.Point;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by ardab on 27.06.2017.
 */

@Component
public class HintClusterMapper implements RowMapper<HintCluster> {

    @Autowired
    public GeometryFactory geometryFactory;


    @Override
    public HintCluster mapRow(ResultSet rs, int rowNum) throws SQLException {
        HintCluster hintCluster = new HintCluster();

        int count = rs.getInt("count");
        hintCluster.setNumberOfHints(count);
        PGgeometry geom = (PGgeometry) rs.getObject("center");
        Point center = geom.getGeometry().getFirstPoint();
        Circle circle = new Circle();
        circle.setRadius(rs.getDouble("radius"));
        circle.setCenter(new Location(center.getX(),center.getY()));
        hintCluster.setCircle(circle);


        Double[] xLocations = (Double[]) (rs.getArray("X_locations").getArray());
        Double[] yLocations = (Double[]) (rs.getArray("Y_locations").getArray());
        Long[] ids = (Long[]) (rs.getArray("ids_in_cluster").getArray());
        Integer [] types = (Integer []) (rs.getArray("types_in_cluster").getArray());
        ArrayList<Hint> hints= new ArrayList<>();
        for(int i=0;i<count;i++){
            Hint temp = new Hint();
            temp.setId(ids[i]);
            com.vividsolutions.jts.geom.Point p = geometryFactory.createPoint(new Coordinate(xLocations[i], yLocations[i]));
            temp.setLocation(p);
            temp.setType(HintType.values()[types[i]]);
            hints.add(temp);
        }

        hintCluster.setHints(hints);
        return hintCluster;

    }
}
