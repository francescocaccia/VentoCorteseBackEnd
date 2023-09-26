package Francesco.BackEndVentoCortese.payload;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrenotazionePayload {

	private Long idPrenotazione;

	private Date dataInizio;

	private Date dataFine;

	// Assumo che il cliente possa essere identificato tramite un ID
	private Long idCliente;

	private boolean confermata;

	private double importoTotale;

	// Assumo che l'appartamento possa essere identificato tramite un ID
	private Long idAppartamentino;

	private String codicePrenotazione;

}
