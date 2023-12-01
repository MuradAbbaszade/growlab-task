package az.growlabtask.util;

import az.growlabtask.entity.Role;
import az.growlabtask.entity.User;
import az.growlabtask.repository.UserRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtUtil {
    private final UserRepository userRepository;
    @Value("${jwt.token.validity}")
    public long TOKEN_VALIDITY;

    @Value("${jwt.signing.key}")
    public String SIGNING_KEY;

    @Value("${jwt.authorities.key}")
    public String AUTHORITIES_KEY;
    @Value("${jwt.token.refresh}")
    private Integer EXPIRE_REFRESH_DURATION;

    public String generateToken(Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName()).get();
        String authorities = user.getRoleSet()
                .stream()
                .map(Role::getRole)
                .collect(Collectors.joining(","));
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim(AUTHORITIES_KEY, authorities)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY))
                .signWith(key())
                .compact();
    }
    public String generateRefreshTokenFromUsername(String username) {
        return Jwts.builder().setSubject(username).setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + EXPIRE_REFRESH_DURATION)).signWith(SignatureAlgorithm.HS512, SIGNING_KEY)
                .compact();
    }
    public String generateTokenFromUsername(String username) {
        return Jwts.builder().setSubject(username).setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + TOKEN_VALIDITY)).signWith(SignatureAlgorithm.HS512, SIGNING_KEY)
                .compact();
    }
    public Claims extractClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(SIGNING_KEY).build().parseClaimsJws(token).getBody();
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SIGNING_KEY));
    }


    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
            return true;
        } catch (MalformedJwtException e) {
            log.info("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.info("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.info("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty: {}", e.getMessage());
        } catch (SignatureException e) {
            log.info("JWT signature is not supported: {}", e.getMessage());
        }

        return false;
    }

    public String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }
        return null;
    }
}
