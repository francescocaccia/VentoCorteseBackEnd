package Francesco.BackEndVentoCortese.payload;

import Francesco.BackEndVentoCortese.entities.Cliente;
import lombok.Data;

@Data
public class ClienteResponse {

	private String nome;
	private String email;
	private Long idCliente;
	private String token; // Se desideri includere il token nella risposta

	// Considera quali altri campi potresti voler includere qui in base al tuo
	// progetto attuale.

	public static ClienteResponse creaResponse(Cliente cliente, String token) {
		ClienteResponse clienteResponse = new ClienteResponse();
		clienteResponse.setIdCliente(cliente.getIdCliente());
		clienteResponse.setEmail(cliente.getEmail());
		clienteResponse.setNome(cliente.getNome());
		clienteResponse.setToken(token); // Se desideri includere il token nella risposta

		// Aggiungi la trasformazione per altri campi se necessario.

		return clienteResponse;
	}
}
