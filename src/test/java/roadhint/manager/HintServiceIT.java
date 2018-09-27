package roadhint.manager;


import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import roadhint.RoadhintApplication;
import roadhint.exception.HintException;
import roadhint.model.HintCluster;
import roadhint.model.MessageHint;
import roadhint.model.PhotoHint;
import roadhint.repo.HintRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = {RoadhintApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class HintServiceIT {

    @Autowired
    HintService hintService;

    @Autowired
    HintRepository hintRepository;

    @Autowired
    private GeometryFactory geometryFactory;

    @Test
    public void getHints() throws HintException {
        MessageHint messageHint = new MessageHint();
        Point point1 = geometryFactory.createPoint(new Coordinate(5, 5));
        messageHint.setLocation(point1);
        messageHint.setMessage("m1");

        PhotoHint photoHint = new PhotoHint();
        Point point2 = geometryFactory.createPoint(new Coordinate(10, 10));
        photoHint.setLocation(point2);
        List<String> photos = new ArrayList<>();
        photos.add("base64encodedPhoto1");
        photos.add("base64encodedPhoto2");
        photoHint.setBase64photos(photos);

        MessageHint savedMessageHint =  hintService.saveOrUpdateMessageHint(messageHint);
        PhotoHint savedPhotoHint = hintService.saveOrUpdatePhotoHint(photoHint);

        List<HintCluster> result = hintService.getHints(0.0, 0.0, 100.0, 100.0, null);

        Assert.assertNotNull(result);
        Assert.assertTrue(result.size() == 1);
        Assert.assertTrue(result.get(0).getNumberOfHints() == 2);
        Assert.assertTrue(result.get(0).getCircle().getCenter().getX() == 7.5);

        //clear data
        hintService.deleteMessageHint(savedMessageHint.getId());
        hintService.deletePhotoHint(savedPhotoHint.getId());
    }

    @Test
    public void saveOrUpdateMessageHint() throws HintException {
        MessageHint messageHint = new MessageHint();
        Point point = geometryFactory.createPoint(new Coordinate(5, 5));
        messageHint.setLocation(point);
        messageHint.setMessage("m1");

        MessageHint savedMessageHint =  hintService.saveOrUpdateMessageHint(messageHint);

        Assert.assertTrue(savedMessageHint != null);
        Assert.assertEquals(messageHint.getMessage(), savedMessageHint.getMessage());
        Assert.assertEquals(messageHint.getLocation(), savedMessageHint.getLocation());

        //clear data
        hintService.deleteMessageHint(savedMessageHint.getId());
    }

    @Test
    public void getMessageHint() throws HintException {
        MessageHint messageHint = new MessageHint();
        Point point = geometryFactory.createPoint(new Coordinate(5, 5));
        messageHint.setLocation(point);
        messageHint.setMessage("m1");

        MessageHint savedMessageHint =  hintService.saveOrUpdateMessageHint(messageHint);
        MessageHint result = hintService.getMessageHint(savedMessageHint.getId());

        Assert.assertTrue(result != null);
        Assert.assertEquals(messageHint.getMessage(), result.getMessage());
        Assert.assertEquals(messageHint.getLocation(), result.getLocation());


        //clear data
        hintService.deleteMessageHint(savedMessageHint.getId());

    }

    @Test
    public void deleteMessageHint() throws HintException {
        MessageHint messageHint = new MessageHint();
        Point point = geometryFactory.createPoint(new Coordinate(5, 5));
        messageHint.setLocation(point);
        messageHint.setMessage("m1");

        MessageHint savedMessageHint =  hintService.saveOrUpdateMessageHint(messageHint);
        boolean result = hintService.deleteMessageHint(savedMessageHint.getId());

        Assert.assertTrue(result);

    }

    @Test
    public void saveOrUpdatePhotoHint() throws HintException {
        PhotoHint photoHint = new PhotoHint();
        Point point = geometryFactory.createPoint(new Coordinate(10, 10));
        photoHint.setLocation(point);
        List<String> photos = new ArrayList<>();
        photos.add("base64encodedPhoto1");
        photos.add("base64encodedPhoto2");
        photoHint.setBase64photos(photos);

        PhotoHint savedPhotoHint = hintService.saveOrUpdatePhotoHint(photoHint);

        Assert.assertTrue(savedPhotoHint != null);
        Assert.assertTrue(photoHint.getBase64photos().size() == 2);
        Assert.assertEquals(photoHint.getLocation(), savedPhotoHint.getLocation());

        //clear data
        hintService.deletePhotoHint(savedPhotoHint.getId());
    }

    @Test
    public void getPhotoHint() throws HintException {
        PhotoHint photoHint = new PhotoHint();
        Point point = geometryFactory.createPoint(new Coordinate(10, 10));
        photoHint.setLocation(point);
        List<String> photos = new ArrayList<>();
        photos.add("base64encodedPhoto1");
        photos.add("base64encodedPhoto2");
        photoHint.setBase64photos(photos);

        PhotoHint savedPhotoHint = hintService.saveOrUpdatePhotoHint(photoHint);
        PhotoHint result = hintService.getPhotoHint(savedPhotoHint.getId());

        Assert.assertTrue(result != null);
        Assert.assertTrue(result.getBase64photos().size() == 2);
        Assert.assertEquals(photoHint.getLocation(), result.getLocation());

        //clear data
        hintService.deletePhotoHint(savedPhotoHint.getId());
    }

    @Test
    public void deletePhotoHint() throws HintException {
        PhotoHint photoHint = new PhotoHint();
        Point point = geometryFactory.createPoint(new Coordinate(10, 10));
        photoHint.setLocation(point);
        List<String> photos = new ArrayList<>();
        photos.add("base64encodedPhoto1");
        photos.add("base64encodedPhoto2");
        photoHint.setBase64photos(photos);

        PhotoHint savedPhotoHint = hintService.saveOrUpdatePhotoHint(photoHint);
        boolean result = hintService.deletePhotoHint(savedPhotoHint.getId());

        Assert.assertTrue(result);
    }

}
