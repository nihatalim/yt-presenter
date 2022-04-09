package tr.com.nihatalim.yt.presenter.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.nihatalim.yt.presenter.dto.request.UserLoginRequest;
import tr.com.nihatalim.yt.presenter.dto.response.UserLoginResponse;
import tr.com.nihatalim.yt.presenter.service.UserLoginService;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/user/login")
@Validated
public class UserLoginController {
    private final UserLoginService userLoginService;

    public UserLoginController(UserLoginService userLoginService) {
        this.userLoginService = userLoginService;
    }

    @PostMapping
    public UserLoginResponse login(@RequestBody @Valid UserLoginRequest request) {
        try {
            return userLoginService.login(request);
        } catch (AuthenticationException e) {
            log.warn("[UserLoginController] [login] Authentication failed for user {}", request.getMail());

            throw e;
        }
    }
}
