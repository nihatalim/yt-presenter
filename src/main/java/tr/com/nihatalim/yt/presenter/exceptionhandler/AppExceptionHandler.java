package tr.com.nihatalim.yt.presenter.exceptionhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tr.com.nihatalim.yt.presenter.dto.response.ErrorResponse;
import tr.com.nihatalim.yt.presenter.enums.ErrorEnum;

@Slf4j
@RestControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleAuthenticationException(AuthenticationException error) {
        return new ErrorResponse(ErrorEnum.AUTHENTICATION_ERROR);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception error) {
        log.error("[AppExceptionHandler] [handleException] Exception occured!", error);

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode("-1");
        errorResponse.setErrorMessage("An exception occured while processing operation!");
        return errorResponse;
    }
}
