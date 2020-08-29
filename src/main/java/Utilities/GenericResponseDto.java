package Utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import Response.*;

public class GenericResponseDto<T> {
    private T data;
    private ErrorResponse errorResponse ;
    private ErrorResponse error;

    public NullErrorResponse getNullErrorResponse() {
        return nullErrorResponse;
    }

    public void setNullErrorResponse(NullErrorResponse nullErrorResponse) {
        this.nullErrorResponse = nullErrorResponse;
    }

    private NullErrorResponse nullErrorResponse ;

    private int statusCode;

    public T getData() {
        return this.data;
    }

    public ErrorResponse getError() {
        return this.error;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setError(ErrorResponse error) {
        this.error = error;
    }

    public synchronized GenericResponseDto<T> toClass(Response response, Class<T> class1) {
        ObjectMapper mapper = new ObjectMapper();

            this.data = response.as(class1);

        statusCode = response.getStatusCode();
        return this;
    }
    public String toString() {
        return "GenericResponseDto(data=" + this.getData() + ", error=" + this.getError() + ")";
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
