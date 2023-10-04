package Francesco.BackEndVentoCortese.auth;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import Francesco.BackEndVentoCortese.entities.Cliente;
import Francesco.BackEndVentoCortese.exception.UnauthorizedException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTTools {

	@Value("${spring.application.jwt.secret}")
	private String secret;

	private int expiration;

	@Value("${spring.application.jwt.expirationindays}")
	public void setExpiration(String expirationInDays) {
		this.expiration = Integer.parseInt(expirationInDays) * 24 * 60 * 60 * 1000;
	}

	public String createToken(Cliente user) {
		String token = Jwts.builder().setSubject(user.getEmail()).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(Keys.hmacShaKeyFor(secret.getBytes())).compact();
		return token;
	}

	public void isTokenValid(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build().parse(token);
		} catch (MalformedJwtException e) {
			throw new UnauthorizedException("Il token non è valido");
		} catch (ExpiredJwtException e) {
			throw new UnauthorizedException("Il token è scaduto");
		} catch (Exception e) {
			throw new UnauthorizedException("Problemi col token, per favore effettua di nuovo il login");
		}
	}

	public String extractSubject(String token) {
		return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build().parseClaimsJws(token)
				.getBody().getSubject();
	}
}
