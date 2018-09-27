package roadhint.manager;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.EmptyResultDataAccessException;
import roadhint.exception.HintException;
import roadhint.model.HintCluster;
import roadhint.model.MessageHint;
import roadhint.model.PhotoHint;
import roadhint.repo.HintRepository;
import roadhint.repo.MessageHintRepository;
import roadhint.repo.PhotoHintRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class HintServiceUnitTest {

    @Mock
    private HintRepository hintRepository;

    @Mock
    private MessageHintRepository messageHintRepository;

    @Mock
    private PhotoHintRepository photoHintRepository;

    @InjectMocks
    private HintService hintService;

    @Test(expected = HintException.class)
    public void getHints_ShouldThrowException_IfEpsDistanceIsNegative() throws HintException {
        hintService.getHints(1.0, 1.0, 1.0, 1.0, -5.0);
    }

    @Test
    public void getHints_ShouldReturnHintsInClusters() throws HintException {
        List<HintCluster> mockHintClusterList = new ArrayList<>();
        mockHintClusterList.add(new HintCluster());

        Mockito.doReturn(mockHintClusterList).when(hintRepository).getHints(Mockito.any(), Mockito.anyDouble());

        List<HintCluster> result = hintService.getHints(1.0, 1.0, 1.0, 1.0, null);
        Assert.assertEquals(mockHintClusterList.size(),result.size());
    }

    @Test
    public void saveOrUpdateMessageHint_ShouldSaveMessageHint() {
        MessageHint mockMessageHint = new MessageHint();
        mockMessageHint.setMessage("testMessage");

        Mockito.doReturn(mockMessageHint).when(messageHintRepository).save(Mockito.any());

        MessageHint result = hintService.saveOrUpdateMessageHint(new MessageHint());
        Assert.assertEquals(mockMessageHint.getMessage(), result.getMessage());
    }

    @Test(expected = HintException.class)
    public void getMessageHint_ShouldThrowException_IfNoMessageExistWithGivenId() throws HintException {
        Mockito.doReturn(Optional.empty()).when(messageHintRepository).findById(Mockito.anyLong());

        hintService.getMessageHint(1);
    }

    @Test
    public void getMessageHint_ShouldReturnMessageHint() throws HintException {
        MessageHint mockMessageHint = new MessageHint();
        mockMessageHint.setMessage("testMessage");

        Mockito.doReturn(Optional.of(mockMessageHint)).when(messageHintRepository).findById(Mockito.anyLong());

        MessageHint result = hintService.getMessageHint(1);
        Assert.assertEquals(mockMessageHint.getMessage(), result.getMessage());
    }

    @Test
    public void saveOrUpdatePhotoHint_ShouldSavePhotoHint() {
        PhotoHint mockPhotoHint = new PhotoHint();
        mockPhotoHint.setBase64photos(new ArrayList<>());

        Mockito.doReturn(mockPhotoHint).when(photoHintRepository).save(Mockito.any());

        PhotoHint result = hintService.saveOrUpdatePhotoHint(new PhotoHint());
        Assert.assertNotNull(result.getBase64photos());
    }

    @Test(expected = HintException.class)
    public void getPhotoHint_ShouldThrowException_IfNoPhotoHintExistWithGivenId() throws HintException {
        Mockito.doReturn(Optional.empty()).when(photoHintRepository).findById(Mockito.anyLong());

        hintService.getPhotoHint(1);
    }

    @Test
    public void getPhotoHint_ShouldReturnPhotoHint() throws HintException {
        PhotoHint mockPhotoHint = new PhotoHint();
        mockPhotoHint.setBase64photos(new ArrayList<>());

        Mockito.doReturn(Optional.of(mockPhotoHint)).when(photoHintRepository).findById(Mockito.anyLong());

        PhotoHint result = hintService.getPhotoHint(1);
        Assert.assertNotNull(result.getBase64photos());
    }

    @Test(expected = HintException.class)
    public void deleteMessageHint_ShouldThrowException_IfNoHintExistWithGivenId() throws HintException {
        Mockito.doThrow(EmptyResultDataAccessException.class).when(messageHintRepository).deleteById(Mockito.anyLong());
        hintService.deleteMessageHint(1);
    }

    @Test
    public void deleteMessageHint_ShouldReturnTrue() throws HintException {
        Mockito.doNothing().when(messageHintRepository).deleteById(Mockito.anyLong());
        boolean result = hintService.deleteMessageHint(1);
        Assert.assertTrue(result);
    }

    @Test(expected = HintException.class)
    public void deletePhotoHint_ShouldThrowException_IfNoHintExistWithGivenId() throws HintException {
        Mockito.doThrow(EmptyResultDataAccessException.class).when(photoHintRepository).deleteById(Mockito.anyLong());
        hintService.deletePhotoHint(1);
    }

    @Test
    public void deletePhotoHint_ShouldReturnTrue() throws HintException {
        Mockito.doNothing().when(photoHintRepository).deleteById(Mockito.anyLong());
        boolean result = hintService.deletePhotoHint(1);
        Assert.assertTrue(result);
    }


}
