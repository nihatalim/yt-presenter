package tr.com.nihatalim.yt.presenter.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.nihatalim.yt.presenter.enums.ErrorEnum;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse {
    private String errorCode;
    private String errorMessage;

    public ErrorResponse(ErrorEnum errorEnum) {
        setError(errorEnum);
    }

    public void setError(ErrorEnum errorEnum) {
        this.errorCode = errorEnum.getErrorCode();
        this.errorMessage = errorEnum.getErrorMessage();
    }
}
