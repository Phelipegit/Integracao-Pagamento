package PhelipeProject.Integracao_Pagamento.service.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.function.Function;


@Component
public class JwtService {

    @Value("${JWT_SECRET}")
    private String jwt_secret;

    @Value("${JWT_EXPIRATION}")
    private Long jwt_expiration;

    public String generateToken(String username,String role) {
        return Jwts.builder()
                .subject(username)
                .claim("roles", List.of(role))
                .issuedAt(new Date())
                .signWith(getkey())
                .expiration(new Date(System.currentTimeMillis() + jwt_expiration))
                .compact();
    }

    public Boolean isValidToken(String token) {
        return getClaims(token,Claims::getExpiration).after(new Date());
    }

    public String extractUsername(String token) {
        return getClaims(token,Claims::getSubject);
    }

    private <T> T getClaims(String token,Function<Claims,T> function) {
        Claims claims = Jwts
                .parser()
                .verifyWith(getkey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return function.apply(claims);
    }

    private SecretKey getkey() {
        return Keys.hmacShaKeyFor(jwt_secret.getBytes());
    }
}
