package co.com.nisum.api.jwt;

import co.com.nisum.model.user.exceptionClass.EnumError;
import co.com.nisum.model.user.exceptionClass.InvalidRequestException;
import co.com.nisum.model.user.exceptionClass.TokenExpiredException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Configuration
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.subject}")
    private String subject;

    @Value("${jwt.accesstokenvalidity}")
    private Long accessTokenValidity;

    private static final String TOKENEXPIRADO ="El token ha expirado";
    private static final String AUTHENTICATIONFAILED="Fallo la Autenticacion ";

    public String generateToken(String secreto) {

        if (secreto.equals(secret)) {
            return Jwts.builder()
                    .setSubject(subject)
                    .setExpiration(new Date(System.currentTimeMillis() + accessTokenValidity))
                    .signWith(SignatureAlgorithm.HS256, secret)
                    .compact();
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            throw new InvalidRequestException(EnumError.ERROR_401.getCodigo(),AUTHENTICATIONFAILED);
        } catch (ExpiredJwtException e) {
            throw new TokenExpiredException(EnumError.ERROR_401.getCodigo(),TOKENEXPIRADO);
        }
    }
}
