package team.a501.rif.service.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import team.a501.rif.config.Jwt.JwtTokenProvider;
import team.a501.rif.domain.auth.RefreshToken;
import team.a501.rif.domain.member.Member;
import team.a501.rif.dto.auth.TokenDto;
import team.a501.rif.repository.auth.RefreshtokenRepository;
import team.a501.rif.repository.member.MemberRepository;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final MemberRepository memberRepository;
    private final RefreshtokenRepository refreshtokenRepository;
    @Transactional
    @Override
    public TokenDto login(String studentId, String password) {
        log.info("studentId info id,password = {}", studentId,password);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(studentId, password);
        log.info("authenticationToken info = {}", authenticationToken);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        log.info("authentication info={}", authentication);
        TokenDto accessToken = jwtTokenProvider.issueToken(authentication);
        TokenDto token = TokenDto.builder()
                .grantType(accessToken.getGrantType())
                .accessToken(accessToken.getAccessToken())
                .refreshToken(issueRefreshToken(studentId))
                .build();
        log.info("Token info={}", token);
        return token;
    }
    public String issueRefreshToken(String studentId){
        RefreshToken token = refreshtokenRepository.save(
                RefreshToken.builder()
                        .id(studentId)
                        .refreshToken(UUID.randomUUID().toString())
                        .expiration(3)
                        .build()
        );
        return token.getRefreshToken();
    }

    public RefreshToken validRefreshToken(String studentId, String refreshToken) throws Exception{
        RefreshToken token = refreshtokenRepository.findById(studentId).orElseThrow(() ->  new Exception("다시 로그인하세요"));
        if(token.getRefreshToken() == null){
            return null;
        }else{
            // refreshtoken 1일 미만 남았을 때 요청하면 2일으로 초기화
            if(token.getExpiration() <= 1){
                token.setExpiration(2);
                refreshtokenRepository.save(token);
            }
            //  Req토큰이 DB토큰과 같은지 비교
            if(!token.getRefreshToken().equals(refreshToken))return null;
            else return token;
        }
    }
    public TokenDto refreshAccessToken(TokenDto token) throws Exception{
        Claims claims = jwtTokenProvider.parseClaims(token.getAccessToken());
        log.info("Claims info= {}",claims);
        Member member = memberRepository.findById(claims.getSubject()).orElseThrow(()->
                new BadCredentialsException("잘못된 계정입니다."));
        log.info("Member info= {}",member);
        RefreshToken refreshToken = validRefreshToken(member.getId(),token.getRefreshToken());
        log.info("RefreshToken info= {}",refreshToken);

        if(refreshToken != null){
            return TokenDto.builder()
                    .grantType("Bearer ")
                    .accessToken(jwtTokenProvider.issueToken(jwtTokenProvider.getAuthentication(token.getAccessToken())).getAccessToken())
                    .refreshToken(refreshToken.getRefreshToken())
                    .build();
        } else {
            throw new Exception("로그인을 해주세요");
        }
    }
}
