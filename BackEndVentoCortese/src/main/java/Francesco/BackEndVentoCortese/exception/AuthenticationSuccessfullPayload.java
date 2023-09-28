package Francesco.BackEndVentoCortese.exception;

public class AuthenticationSuccessfullPayload {

	private String nome;
	private String token;

	// Costruttore
	public AuthenticationSuccessfullPayload(String nome, String token) {
		this.nome = nome;
		this.token = token;
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
