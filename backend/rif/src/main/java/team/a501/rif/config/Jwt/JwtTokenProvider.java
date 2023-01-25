package team.a501.rif.config.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import team.a501.rif.domain.auth.Token;

import java.security.Key;
import java.security.PublicKey;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider {
    private final Key key;
    private final long expire;
    public JwtTokenProvider(@Value("${security.jwt.token.secret}") String secretKey,
                            @Value("${security.jwt.token.expire}")long expire) {
        this.expire = expire;
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    // accesstoken 발행하는 메서드
    public Token issueToken(Authentication authentication){
        final long now = new Date().getTime();
        final Date accessExpire = new Date(now+expire);

        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining());
        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(new Date(now))
                .setExpiration(accessExpire)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        return Token.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .build();
    }

    // JWT 토큰 복호화 하여 토큰 정보 꺼내기
    public Authentication getAuthentication(String accessToken){
        // 토큰 복호화해서 권한 확인
        Claims claims = parseClaims(accessToken);
        if(claims.get("auth") == null){
            throw new RuntimeException("권한 정보가 없는 토큰");
        }


    }

    private Claims parseClaims(String accessToken){
        try{
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        }catch (ExpiredJwtException e){
            return e.getClaims();
        }
    }
}
