package Francesco.BackEndVentoCortese.auth;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import Francesco.BackEndVentoCortese.entities.Cliente;
import Francesco.BackEndVentoCortese.exception.UnauthorizedException;
import Francesco.BackEndVentoCortese.service.ClienteService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {
	@Autowired
	ClienteService clienteService;
	@Autowired
	private JWTTools jwtTools;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if (!request.getMethod().equalsIgnoreCase("OPTIONS")) {
			String authHeader = request.getHeader("Authorization");

			if (authHeader == null || !authHeader.startsWith("Bearer "))
				throw new UnauthorizedException("Per favore aggiungi il token all'authorization header");

			String accessToken = authHeader.substring(7);

			jwtTools.isTokenValid(accessToken); // Usiamo l'istanza jwtTools

			String email = jwtTools.extractSubject(accessToken); // Usiamo l'istanza jwtTools

			Cliente cliente = clienteService.findByEmail(email);

			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(cliente, null,
					cliente.getAuthorities());
			authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

			SecurityContextHolder.getContext().setAuthentication(authToken);

			filterChain.doFilter(request, response);
		} else {
			filterChain.doFilter(request, response);
		}

	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		AntPathMatcher pathMatcher = new AntPathMatcher();
		String servletPath = request.getServletPath();

		// Specifica gli endpoint liberi che non richiedono autenticazione
		String[] freeEndpoints = { "/appartamentini/**", "/appartamentini/all/**", "/appartamentini/create",
				"/prenotazioni", "/cliente" };

		// Controlla se il servletPath corrisponde a uno degli endpoint liberi
		for (String endpoint : freeEndpoints) {
			if (pathMatcher.match(endpoint, servletPath)) {
				return true; // Non filtrare la richiesta per gli endpoint liberi
			}
		}

		return false; // Filtra la richiesta per gli altri endpoint
	}

}
