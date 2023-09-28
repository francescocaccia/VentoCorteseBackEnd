package Francesco.BackEndVentoCortese.payload;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PrenotazionePayload {

	private Long idPrenotazione;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date dataInizio;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date dataFine;

	private Long idCliente;

	private boolean confermata;

	private double importoTotale;

	private Long idAppartamentino;

	private String codicePrenotazione;

}
