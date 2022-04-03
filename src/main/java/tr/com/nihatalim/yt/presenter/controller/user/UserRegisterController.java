package tr.com.nihatalim.yt.presenter.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.nihatalim.yt.presenter.dto.request.UserRegisterRequest;
import tr.com.nihatalim.yt.presenter.service.UserRegisterService;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/user/register")
@Validated
public class UserRegisterController {
    private final UserRegisterService userRegisterService;

    public UserRegisterController(UserRegisterService userRegisterService) {
        this.userRegisterService = userRegisterService;
    }

    @PostMapping
    public void register(@RequestBody @Valid UserRegisterRequest request) {
        try {
            userRegisterService.registerUser(request);
        } catch (Exception e) {
            log.error("An exception occured while register user", e);
        }
    }
}
