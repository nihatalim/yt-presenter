package tr.com.nihatalim.yt.presenter.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tr.com.nihatalim.yt.presenter.repository.UserRepository;

import java.util.Set;

@Service
public class JwtUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public JwtUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        return userRepository.findByMail(mail)
                .map(user -> new User(user.getMail(), user.getPassword(), Set.of()))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
