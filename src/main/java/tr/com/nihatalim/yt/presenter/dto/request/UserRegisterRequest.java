package tr.com.nihatalim.yt.presenter.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserRegisterRequest {
    @NotBlank
    private String name;
    @Email
    private String mail;
    @NotBlank
    private String password;
}
