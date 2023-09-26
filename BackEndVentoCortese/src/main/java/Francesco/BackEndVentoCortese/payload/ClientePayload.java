package Francesco.BackEndVentoCortese.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClientePayload {

	@NotBlank(message = "Il nome è obbligatorio")
	@Size(min = 2, max = 50, message = "Il nome deve avere tra 2 e 50 caratteri")
	private String nome;

	@NotBlank(message = "L'email è obbligatoria")
	@Email(message = "L'email non è valida")
	private String email;

	@NotBlank(message = "Il telefono è obbligatorio")
	private String telefono;

}
