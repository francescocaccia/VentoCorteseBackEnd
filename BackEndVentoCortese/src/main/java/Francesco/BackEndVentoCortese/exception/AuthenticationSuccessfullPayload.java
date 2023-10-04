package Francesco.BackEndVentoCortese.exception;

import lombok.Data;

@Data
public class AuthenticationSuccessfullPayload {

	private String nome;
	private String token;
	private Long idCliente;

	// Costruttore
	public AuthenticationSuccessfullPayload(String nome, String token, Long idCliente) {
		this.nome = nome;
		this.token = token;
		this.idCliente = idCliente;
	}

	public AuthenticationSuccessfullPayload(String nome2, String token2) {

	}

	// Getters e Setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
