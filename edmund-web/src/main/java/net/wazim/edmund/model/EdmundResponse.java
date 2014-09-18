package net.wazim.edmund.model;

public class EdmundResponse {

    private Exception exception;
    private int statusCode;
    private String responseBody;

    public EdmundResponse(int statusCode, String responseBody) {
        this.statusCode = statusCode;
        this.responseBody = responseBody;
    }

    public EdmundResponse(Exception exception) {
        this.exception = exception;
    }

    public int code() {
        return statusCode;
    }

    public String body() {
        return responseBody;
    }

    public Exception exception() {
        return exception;
    }

    public boolean hasException() {
        return exception != null;
    }

}
