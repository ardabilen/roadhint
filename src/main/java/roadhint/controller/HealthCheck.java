package roadhint.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import roadhint.model.ResponseWrapper;


@RestController
@RequestMapping("")
public class HealthCheck {

    /**
     * Heartbeat.
     *
     * @return the response wrapper
     */
    @GetMapping("/heartbeat")
    public ResponseWrapper heartbeat() {
        return ResponseWrapper.response("Ok");
    }
}