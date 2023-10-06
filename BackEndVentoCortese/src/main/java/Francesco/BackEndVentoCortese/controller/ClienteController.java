package Francesco.BackEndVentoCortese.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		ClienteResponse clienteResponse = ClienteResponse.creaResponse(clienteLoggato, null);

		return clienteResponse;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
		return clienteService.findById(id).map(cliente -> ResponseEntity.ok(cliente))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/me/dettagli")
	public ResponseEntity<Cliente> getLoggedClienteDetails() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Cliente clienteLoggato = (Cliente) authentication.getPrincipal();
		Long clienteId = clienteLoggato.getId(); // Supponendo che tu abbia un metodo getId() nella tua entità Cliente
		return getClienteById(clienteId);
	}

}
