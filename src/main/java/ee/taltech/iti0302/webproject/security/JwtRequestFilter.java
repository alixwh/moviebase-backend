package ee.taltech.iti0302.webproject.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Component
@AllArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        Optional<String> jwt = getToken(request);
        if (jwt.isEmpty()) {
            chain.doFilter(request, response);
            return;
        }
        Claims tokenBody = parseToken(jwt.get());
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(buildAuthToken(tokenBody));
        chain.doFilter(request, response);
    }

    private Optional<String> getToken(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            return Optional.empty();
        }
        return Optional.of(header.substring("Bearer ".length()));
    }

    private Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(JwtTokenProvider.key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private UsernamePasswordAuthenticationToken buildAuthToken(Claims claims) {
        return new UsernamePasswordAuthenticationToken(claims.get("username"),
                "",
                List.of(new SimpleGrantedAuthority("USER")));
    }
}
