package Francesco.BackEndVentoCortese.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Francesco.BackEndVentoCortese.entities.Cliente;
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

}
