package roadhint.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import roadhint.exception.HintException;
import roadhint.manager.HintService;
import roadhint.model.PhotoHint;
import roadhint.model.ResponseWrapper;

/**
 * The type Photo hint controller.
 */
@RestController
@RequestMapping(value = "/photohint")
public class PhotoHintController {


    /**
     * The Hint service.
     */
    @Autowired
    public HintService hintService;

    /**
     * Add photo hint.
     *
     * @param photoHint the photo hint
     * @return the response wrapper
     */
    @PostMapping(value = "")
    public ResponseWrapper<PhotoHint> addPhotoHint(@RequestBody PhotoHint photoHint){
        return ResponseWrapper.response(hintService.saveOrUpdatePhotoHint(photoHint));
    }

    /**
     * Update photo hint.
     *
     * @param photoHint the photo hint
     * @return the response wrapper
     */
    @PutMapping(value = "")
    public ResponseWrapper<PhotoHint> updatePhotoHint(@RequestBody PhotoHint photoHint){
        return ResponseWrapper.response(hintService.saveOrUpdatePhotoHint(photoHint));
    }

    /**
     * Gets photo hint.
     *
     * @param id the id
     * @return the photo hint
     * @throws HintException the hint exception
     */
    @GetMapping("{id}")
    public ResponseWrapper<PhotoHint> getPhotoHint(@PathVariable("id") long id) throws HintException {
        return ResponseWrapper.response(hintService.getPhotoHint(id));
    }

    /**
     * Delete photo hint.
     *
     * @param id the id
     * @return the response wrapper
     * @throws HintException the hint exception
     */
    @DeleteMapping("{id}")
    public ResponseWrapper<Boolean> deletePhotoHint(@PathVariable("id") long id) throws HintException {
        return ResponseWrapper.response(hintService.deletePhotoHint(id));
    }
}