package Francesco.BackEndVentoCortese.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Francesco.BackEndVentoCortese.entities.Cliente;
import Francesco.BackEndVentoCortese.payload.ClienteResponse;
import Francesco.BackEndVentoCortese.service.ClienteService;

@RestController
@RequestMapping("/clienti")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@PostMapping("/add")
	public Cliente inserisciCliente(@RequestBody Cliente cliente) {
		return clienteService.inserisciCliente(cliente);
	}

	@GetMapping("/me")
	public ClienteResponse getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Cliente clienteLoggato = (Cliente) authentication.getPrincipal();
		ClienteResponse clienteResponse = ClienteResponse.creaResponse(clienteLoggato, null); // Ancora una volta,
																								// impostato su null per
																								// il token. Modifica se
																								// necessario.
		return clienteResponse;
	}
}
