package roadhint.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import roadhint.exception.HintException;
import roadhint.manager.HintService;
import roadhint.model.MessageHint;
import roadhint.model.ResponseWrapper;

/**
 * The type Message hint controller.
 */
@RestController
@RequestMapping(value = "/messagehint")
public class MessageHintController {


    /**
     * The Hint service.
     */
    @Autowired
    public HintService hintService;

    /**
     * Add message hint.
     *
     * @param messageHint the message hint
     * @return the response wrapper
     */
    @PostMapping(value = "")
    public ResponseWrapper<MessageHint> addMessageHint(@RequestBody MessageHint messageHint){
        return ResponseWrapper.response(hintService.saveOrUpdateMessageHint(messageHint));
    }

    /**
     * Update message hint.
     *
     * @param messageHint the message hint
     * @return the response wrapper
     */
    @PutMapping(value = "")
    public ResponseWrapper<MessageHint> updateMessageHint(@RequestBody MessageHint messageHint){
        return ResponseWrapper.response(hintService.saveOrUpdateMessageHint(messageHint));
    }

    /**
     * Gets message hint.
     *
     * @param id the id
     * @return the message hint
     * @throws HintException the hint exception
     */
    @GetMapping("{id}")
    public ResponseWrapper<MessageHint> getMessageHint(@PathVariable("id") long id) throws HintException {
        return ResponseWrapper.response(hintService.getMessageHint(id));
    }

    /**
     * Delete message hint.
     *
     * @param id the id
     * @return the response wrapper
     * @throws HintException the hint exception
     */
    @DeleteMapping("{id}")
    public ResponseWrapper<Boolean> deleteMessageHint(@PathVariable("id") long id) throws HintException {
        return ResponseWrapper.response(hintService.deleteMessageHint(id));
    }

}