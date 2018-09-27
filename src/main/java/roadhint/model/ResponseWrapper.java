package roadhint.model;



import org.springframework.http.HttpStatus;


import java.io.Serializable;

public class ResponseWrapper<T> implements Serializable {

    private T data;
    private int statusCode;
    private String errorMessage;


    public static <E> ResponseWrapper<E> response(E data) {
        ResponseWrapper<E> response = new ResponseWrapper();
        response.statusCode = HttpStatus.OK.value();
        response.data = data;
        return response;
    }

    public static ResponseWrapper error(String messsage, HttpStatus status) {
        ResponseWrapper response = new ResponseWrapper();
        response.statusCode = status.value();
        response.errorMessage = messsage;
        return response;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
