package tr.com.nihatalim.yt.presenter.service;

import io.jsonwebtoken.impl.DefaultClaims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import tr.com.nihatalim.yt.presenter.dto.request.UserLoginRequest;
import tr.com.nihatalim.yt.presenter.dto.response.UserLoginResponse;
import tr.com.nihatalim.yt.presenter.util.JwtUtil;

@Slf4j
@Service
public class UserLoginService {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final JwtUserDetailService jwtUserDetailService;

    public UserLoginService(JwtUtil jwtUtil, AuthenticationManager authenticationManager, JwtUserDetailService jwtUserDetailService) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.jwtUserDetailService = jwtUserDetailService;
    }

    public UserLoginResponse login(UserLoginRequest request) throws AuthenticationException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getMail(), request.getPassword()));

        final UserDetails userDetails = jwtUserDetailService.loadUserByUsername(request.getMail());

        final String token = jwtUtil.generateToken(userDetails.getUsername(), new DefaultClaims());

        final UserLoginResponse userLoginResponse = new UserLoginResponse();
        userLoginResponse.setToken(token);

        return userLoginResponse;
    }
}
