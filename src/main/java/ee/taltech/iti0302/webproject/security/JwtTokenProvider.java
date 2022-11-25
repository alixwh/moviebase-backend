package ee.taltech.iti0302.webproject.security;

import ee.taltech.iti0302.webproject.account.AccountRole;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {
    public static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(String username, int id, AccountRole role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        claims.put("id", id);
        claims.put("accountRole", role);
        long currentTimeMillis = System.currentTimeMillis();
        long tokenDuration = 600000;
        return Jwts.builder()
                .setSubject("subject")
                .addClaims(claims)
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(currentTimeMillis + tokenDuration))
                .signWith(key)
                .compact();
    }


}
