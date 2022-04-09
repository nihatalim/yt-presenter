package tr.com.nihatalim.yt.presenter.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.nihatalim.yt.presenter.dto.request.UserRegisterRequest;
import tr.com.nihatalim.yt.presenter.entity.AppUser;
import tr.com.nihatalim.yt.presenter.mail.MailService;
import tr.com.nihatalim.yt.presenter.mail.context.RegisterMailContext;
import tr.com.nihatalim.yt.presenter.repository.UserRepository;

@Slf4j
@Service
public class UserRegisterService {
    private final MailService mailService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserRegisterService(MailService mailService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.mailService = mailService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void registerUser(UserRegisterRequest request) {
        final AppUser appUser = new AppUser();
        appUser.setMail(request.getMail());
        appUser.setName(request.getName());
        appUser.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(appUser);

        mailService.sendMail(new RegisterMailContext(request.getMail()));
    }
}
