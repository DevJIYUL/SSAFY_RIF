package team.a501.rif.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import team.a501.rif.config.Jwt.JwtTokenProvider;
import team.a501.rif.domain.auth.Token;

import javax.transaction.Transactional;
@Service
@RequiredArgsConstructor
public class AuthSeriveceImpl implements AuthService{

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Transactional
    @Override
    public Token login(String id, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(id,password);

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        Token token = jwtTokenProvider.issueToken(authentication);
        return token;
    }
}
