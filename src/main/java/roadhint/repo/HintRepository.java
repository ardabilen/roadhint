package roadhint.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import roadhint.model.HintCluster;
import roadhint.model.Location;
import roadhint.model.mapper.HintClusterMapper;

import java.util.ArrayList;
import java.util.List;

@Repository
public class HintRepository {

    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Autowired
    HintClusterMapper hintClusterMapper;

    /**
     * Gets hints.
     *
     * @param locations   the locations
     * @param epsDistance the eps distance
     * @return the hints
     */
    public List<HintCluster> getHints(ArrayList<Location> locations, double epsDistance) {
        final String sql = new StringBuilder().append("SELECT count(*) as count,array_agg(ST_X(location)) AS X_locations,")
                .append("array_agg(ST_Y(location)) AS Y_locations,array_agg(type) AS types_in_cluster  , array_agg(id) AS ids_in_cluster,")
                .append("ST_Centroid(ST_Collect(location)) as center, sqrt(ST_Area(ST_MinimumBoundingCircle(ST_Collect(location)))/ pi()) as radius ")
                .append("FROM (  SELECT id, ST_ClusterDBSCAN(location, eps := ?, minpoints := 1) over () AS cid, location,type ")
                .append("FROM hint WHERE ST_Within(location,ST_GeomFromText('POLYGON((' || ? || ' ' || ? || ', ' || ? || ' ' || ? || ")
                .append("', ' || ? || ' ' || ? || ', ' || ? || ' ' || ? || ', ' || ? || ' ' || ? ||  '))')) ) sq ")
                .append("GROUP BY cid").toString();


        List<Object> queryParams = new ArrayList<>();
        queryParams.add(epsDistance);
        for(Location location : locations){
            queryParams.add(location.getX());
            queryParams.add(location.getY());
        }

        return jdbcTemplate.query(sql, queryParams.toArray(), hintClusterMapper);

    }


}
