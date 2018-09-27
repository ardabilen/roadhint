package roadhint.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import roadhint.exception.HintException;
import roadhint.manager.HintService;
import roadhint.model.HintCluster;
import roadhint.model.ResponseWrapper;

import java.util.List;


/**
 * Created by ardab on 24.06.2017.
 */
@RestController
@RequestMapping(value = "/hints")
public class HintController {


    @Autowired
    public HintService hintService;


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
    @GetMapping("")
    public ResponseWrapper<List<HintCluster>> getHints(@RequestParam Double x1,
                                                       @RequestParam Double y1,
                                                       @RequestParam Double x2,
                                                       @RequestParam Double y2,
                                                       @RequestParam (required = false) Double epsDistance) throws HintException {

        return ResponseWrapper.response(hintService.getHints(x1, y1, x2, y2, epsDistance));

    }


}