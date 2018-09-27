package roadhint.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import roadhint.RoadhintApplication;
import roadhint.exception.HintException;
import roadhint.manager.HintService;
import roadhint.model.HintCluster;
import roadhint.model.MessageHint;
import roadhint.model.ResponseWrapper;

import java.util.List;

@SpringBootTest(classes = {RoadhintApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class HintControllerIT {

    @Autowired
    private HintService hintService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private GeometryFactory geometryFactory;

    private ObjectMapper objectMapper;


    @Before
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void getHints() throws HintException {

        MessageHint messageHint1 = new MessageHint();
        messageHint1.setMessage("m1");
        Point point1 = geometryFactory.createPoint(new Coordinate(5, 5));
        messageHint1.setLocation(point1);

        MessageHint messageHint2 = new MessageHint();
        messageHint2.setMessage("m2");
        Point point2 = geometryFactory.createPoint(new Coordinate(10, 10));
        messageHint2.setLocation(point2);

        MessageHint savedMessageHint1 = hintService.saveOrUpdateMessageHint(messageHint1);
        MessageHint savedMessageHint2 = hintService.saveOrUpdateMessageHint(messageHint2);

        Double x1 = 0.0;
        Double y1 = 0.0;
        Double x2 = 100.0;
        Double y2 = 100.0;
        ResponseWrapper responseWrapper = restTemplate.getForEntity("/hints?x1=" + x1 + "&y1=" + y1 + "&x2=" + x2 + "&y2=" + y2, ResponseWrapper.class).getBody();
        List<HintCluster> result = objectMapper.convertValue(responseWrapper.getData(), List.class);

        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.size());

        //clear data
        hintService.deleteMessageHint(savedMessageHint1.getId());
        hintService.deleteMessageHint(savedMessageHint2.getId());
    }


}
