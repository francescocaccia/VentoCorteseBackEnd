package Francesco.BackEndVentoCortese.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Francesco.BackEndVentoCortese.entities.Cliente;
import Francesco.BackEndVentoCortese.exception.AuthenticationSuccessfullPayload;
import Francesco.BackEndVentoCortese.exception.BadRequestException;
import Francesco.BackEndVentoCortese.exception.UnauthorizedException;
import Francesco.BackEndVentoCortese.payload.ClientePayload;
import Francesco.BackEndVentoCortese.service.ClienteService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	ClienteService clienteService;

	@Autowired
	private JWTTools jwtTools;

	@Autowired
	private PasswordEncoder bcrypt;

	@PostMapping("/register")
	public ResponseEntity<Cliente> register(@RequestBody @Validated ClientePayload body) throws Exception {

		// Converti il ClientePayload in un oggetto Cliente
		Cliente clienteToRegister = clienteService.convertiDaPayload(body);

		clienteService.findByEmail(clienteToRegister.getEmail()).ifPresent(user -> {
			throw new BadRequestException("Email " + user.getEmail() + " già utilizzata!");
		});

		// Imposta la password codificata sul cliente
		clienteToRegister.setPassword(bcrypt.encode(body.getPassword()));

		// Salva il cliente nel database e ottieni il cliente creato
		Cliente createdCliente = clienteService.inserisciCliente(clienteToRegister);

		return new ResponseEntity<>(createdCliente, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<AuthenticationSuccessfullPayload> login(@RequestBody ClientePayload body)
			throws NotFoundException {

		Cliente userOpt = clienteService.findByEmail(body.getEmail()).orElseThrow(() -> new NotFoundException());

		Long id = userOpt.getId();
		String plainPW = body.getPassword();
		String hashedPW = userOpt.getPassword();

		if (!bcrypt.matches(plainPW, hashedPW)) {
			throw new UnauthorizedException("Credenziali non valide!");
		}

		String token;
		try {
			token = jwtTools.createToken(userOpt);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UnauthorizedException("Impossibile generare il token. Per favore prova di nuovo.");
		}
		System.out.println(id);
		return new ResponseEntity<>(new AuthenticationSuccessfullPayload(userOpt.getNome(), token, id), HttpStatus.OK);
	}
}
