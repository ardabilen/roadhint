package roadhint.manager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import roadhint.exception.HintException;
import roadhint.model.HintCluster;
import roadhint.model.Location;
import roadhint.model.MessageHint;
import roadhint.model.PhotoHint;
import roadhint.repo.HintRepository;
import roadhint.repo.MessageHintRepository;
import roadhint.repo.PhotoHintRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * The type Hint service.
 */
@Service
@Transactional
public class HintService {

    /**
     * The Message hint repository.
     */
    @Autowired
    public MessageHintRepository messageHintRepository;

    /**
     * The Photo hint repository.
     */
    @Autowired
    public PhotoHintRepository photoHintRepository;

    /**
     * The Hint repository.
     */
    @Autowired
    public HintRepository hintRepository;


    /**
     * Gets hints.
     *
     * @param x1          the x 1
     * @param y1          the y 1
     * @param x2          the x 2
     * @param y2          the y 2
     * @param epsDistance the eps distance
     * @return the hints
     * @throws HintException the hint exception
     */
    public List<HintCluster> getHints(Double x1, Double y1, Double x2, Double y2, Double epsDistance) throws HintException {

        if (epsDistance != null && epsDistance < 0) {
            throw new HintException("Distance cannot be negative", HttpStatus.BAD_REQUEST);
        }

        //make a polygon from given points for filtering hints
        //polygon will be a rectangle.
        ArrayList<Location> locations = new ArrayList<>();
        locations.add(new Location(x1,y1));
        locations.add(new Location(x2,y1));
        locations.add(new Location(x2,y2));
        locations.add(new Location(x1,y2));
        locations.add(new Location(x1,y1));

        // Divide screen width by numberOfGrid to calculate eps distance.
        // its a greedy approach.
        if(epsDistance == null) {
            epsDistance = (x2-x1)/10;
        }

        return hintRepository.getHints(locations,epsDistance);

    }

    /**
     * Save or update message hint.
     *
     * @param messageHint the message hint
     * @return the message hint
     */
    @Transactional
    public MessageHint saveOrUpdateMessageHint(MessageHint messageHint) {
        return messageHintRepository.save(messageHint);
    }

    /**
     * Gets message hint.
     *
     * @param id the id
     * @return the message hint
     * @throws HintException the hint exception
     */
    public MessageHint getMessageHint(long id) throws HintException {
        Optional<MessageHint> hint = messageHintRepository.findById(id);
        if (hint.isPresent()) {
            return hint.get();
        } else {
            throw new HintException(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Delete message hint.
     *
     * @param id the id
     * @return the boolean
     * @throws HintException the hint exception
     */
    @Transactional
    public boolean deleteMessageHint(long id) throws HintException {
        try {
            messageHintRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new HintException(HttpStatus.NOT_FOUND);
        }
        return true;
    }

    /**
     * Save or update photo hint.
     *
     * @param photoHint the photo hint
     * @return the photo hint
     */
    @Transactional
    public PhotoHint saveOrUpdatePhotoHint(PhotoHint photoHint) {
        return photoHintRepository.save(photoHint);
    }

    /**
     * Gets photo hint.
     *
     * @param id the id
     * @return the photo hint
     * @throws HintException the hint exception
     */
    public PhotoHint getPhotoHint(long id) throws HintException {
        Optional<PhotoHint> hint = photoHintRepository.findById(id);
        if (hint.isPresent()) {
            return hint.get();
        } else {
            throw new HintException(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete photo hint.
     *
     * @param id the id
     * @return the boolean
     * @throws HintException the hint exception
     */
    @Transactional
    public boolean deletePhotoHint(long id) throws HintException {
        try {
            photoHintRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new HintException(HttpStatus.NOT_FOUND);
        }
        return true;
    }


}
