package roadhint.exception;


import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import roadhint.model.ResponseWrapper;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class HintExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ResponseWrapper> hintException(Exception ex) {

        if (ex instanceof HintException) {
            HintException hintException = (HintException) ex;
            ResponseWrapper error = ResponseWrapper.error(hintException.getMessage(), hintException.getStatusCode());
            return new ResponseEntity<>(error,hintException.getStatusCode());
        }

        return new ResponseEntity<>(ResponseWrapper.error("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
