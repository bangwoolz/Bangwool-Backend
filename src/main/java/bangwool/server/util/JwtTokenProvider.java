package bangwool.server.util;

import bangwool.server.exception.unauthorized.InvalidTokenException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {
    @Value("${JWT_SECRET_KEY}")
    private String JWT_SECRET_KEY;

    @Value("${JWT_EXPIRED_IN}")
    private long JWT_EXPIRED_IN;

    public String createToken(String principal, long userId) {       //pricipal : 유저의 이메일이 인자로 들어옴
        log.info("JWT key={}", JWT_SECRET_KEY);

        Claims claims = Jwts.claims().setSubject(principal);
        Date now = new Date();
        Date validity = new Date(now.getTime() + JWT_EXPIRED_IN);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .claim("userId", userId)
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY)
                .compact();
    }

    public boolean isExpiredToken(String token) throws InvalidTokenException {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(JWT_SECRET_KEY).build()
                    .parseClaimsJws(token);
            return claims.getBody().getExpiration().before(new Date());

        } catch (ExpiredJwtException e) {
            return true;

        } catch (UnsupportedJwtException e) {
            log.error("지원되지 않는 토큰 형식입니다.");
            throw e;
        } catch (MalformedJwtException e) {
            log.error("토큰이 올바르게 구성되지 않았습니다.");
            throw e;
        } catch (IllegalArgumentException e) {
            log.error("유효하지 않은 토큰입니다.");
            throw e;
        } catch (JwtException e) {
            log.error("[JwtTokenProvider.validateAccessToken]", e);
            throw e;
        }
    }

    public String getPrincipal(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(JWT_SECRET_KEY).build()
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

}
