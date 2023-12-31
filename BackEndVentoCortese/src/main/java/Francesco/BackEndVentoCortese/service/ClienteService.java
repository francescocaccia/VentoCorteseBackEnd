package Francesco.BackEndVentoCortese.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Francesco.BackEndVentoCortese.entities.Cliente;
import Francesco.BackEndVentoCortese.payload.ClientePayload;
import Francesco.BackEndVentoCortese.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente inserisciCliente(Cliente cliente) {

		return clienteRepository.save(cliente);
	}

	public Cliente convertiDaPayload(ClientePayload payload) {
		Cliente cliente = new Cliente();
		cliente.setNome(payload.getNome());
		cliente.setEmail(payload.getEmail());
		cliente.setTelefono(payload.getTelefono());
		return cliente;
	}

	public Optional<Cliente> findByEmail(String email) {
		return clienteRepository.findByEmail(email);
	}

	public Optional<Cliente> findById(Long id) {
		return clienteRepository.findById(id);
	}

}
