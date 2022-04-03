package tr.com.nihatalim.yt.presenter.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.nihatalim.yt.presenter.dto.request.UserRegisterRequest;
import tr.com.nihatalim.yt.presenter.entity.User;
import tr.com.nihatalim.yt.presenter.mail.MailService;
import tr.com.nihatalim.yt.presenter.mail.context.RegisterMailContext;
import tr.com.nihatalim.yt.presenter.repository.UserRegisterRepository;

@Slf4j
@Service
public class UserRegisterService {
    private final MailService mailService;
    private final UserRegisterRepository userRegisterRepository;

    public UserRegisterService(MailService mailService, UserRegisterRepository userRegisterRepository) {
        this.mailService = mailService;
        this.userRegisterRepository = userRegisterRepository;
    }

    @Transactional
    public void registerUser(UserRegisterRequest request) {
        final User user = new User();
        user.setMail(request.getMail());
        user.setName(request.getName());
        user.setPassword(request.getPassword());

        userRegisterRepository.save(user);

        mailService.sendMail(new RegisterMailContext(request.getMail()));
    }
}
