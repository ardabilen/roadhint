package roadhint;

import com.bedatadriven.jackson.datatype.jts.JtsModule;
import com.vividsolutions.jts.geom.GeometryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public JtsModule jtsModule() {
        return new JtsModule();
    }


    @Bean
    public GeometryFactory geometryFactory() {
        return new GeometryFactory();
    }
}