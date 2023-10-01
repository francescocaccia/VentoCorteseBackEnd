package Francesco.BackEndVentoCortese.payload;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ImmaginePayload {
	@NotNull(message = "è obbligatorio inserire almeno un immagine")
	private String immagine1;
	private String immagine2;
	private String immagine3;
}
