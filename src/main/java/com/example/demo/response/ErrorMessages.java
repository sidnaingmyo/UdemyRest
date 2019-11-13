package com.example.demo.response;

public enum ErrorMessages {

    MISSING_REQUIRED_FIELD("Missing required field,Please check documentation for required fields"),
    RECORD_ALREDY_EXISTS("Record Already Exists"),
    INTERNAL_SERVER_ERROR("Internal Server Error"),
    NO_RECORD_FOUND("Record with provided id is not found"),
    AUTHENTICATION_FIELD("Authentication failed"),
    COULD_NOT_UPDATE_RECORD("Could not update record"),
    COULD_NOT_DELETE_RECORD("Could not delete record"),
    EMAIL_ADDRESS_VERIFIED("Email Address could not be verified");

    private String errorMessage;

    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
