package roadhint.exception;

import org.springframework.http.HttpStatus;

public class HintException extends Exception {


    private HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR;

    public HintException() {

    }

    public HintException(HttpStatus statusCode) {
        super(statusCode.getReasonPhrase());
        this.statusCode = statusCode;
    }

    public HintException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }


    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }
}
