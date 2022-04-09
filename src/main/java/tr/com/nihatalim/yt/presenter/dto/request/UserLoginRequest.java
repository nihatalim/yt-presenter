package tr.com.nihatalim.yt.presenter.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequest {
    private String mail;
    private String password;
}
