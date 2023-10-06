package Francesco.BackEndVentoCortese.payload;

import java.util.Set;

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
	private double metriQuadri;
	private double tariffa;
	private boolean disponibilita;
	private String descrizione;
	private Set<ImmaginePayload> immagini;

	@Override
	public String toString() {
		return "Appartamentini [idAppartamentino=" + idAppartamentino + ", nome=" + nome + ", numeroDiCamere="
				+ numeroDiCamere + ", capienzaMassima=" + capienzaMassima + ", metriQuadri=" + metriQuadri
				+ ", tariffa=" + tariffa + ", disponibilita=" + disponibilita + ", descrizione=" + descrizione + "]";
	}

}
