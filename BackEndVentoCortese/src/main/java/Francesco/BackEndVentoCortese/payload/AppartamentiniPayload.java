package Francesco.BackEndVentoCortese.payload;

import java.util.Set;

import Francesco.BackEndVentoCortese.entities.Immagine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AppartamentiniPayload {

	private Long idAppartamentino;
	private String nome;
	private int numeroDiCamere;
	private int capienzaMassima;
	private double tariffa;
	private boolean disponibilita;
	private String descrizione;
	private Set<Immagine> immagini;

	@Override
	public String toString() {
		return "AppartamentiniPayload [idAppartamentino=" + idAppartamentino + ", nome=" + nome + ", numeroDiCamere="
				+ numeroDiCamere + ", capienzaMassima=" + capienzaMassima + ", tariffa=" + tariffa + ", disponibilita="
				+ disponibilita + ", descrizione=" + descrizione + ", immagini=" + immagini + "]";
	}

}
