package tr.com.nihatalim.yt.presenter.enums;

public enum ErrorEnum {
    AUTHENTICATION_ERROR("400", "Authentication exception occured!");

    private final String errorCode;
    private final String errorMessage;

    ErrorEnum(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
