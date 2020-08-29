package Response;

public class ErrorResponse {
    private String errorMessage;
    private String errorCode;

    public ErrorResponse(final String errorMessage, final String errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setErrorCode(final String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String toString() {
        return "ErrorResponse(errorMessage=" + this.getErrorMessage() + ", errorCode=" + this.getErrorCode() + ")";
    }

    private ErrorResponse() {
    }
}
