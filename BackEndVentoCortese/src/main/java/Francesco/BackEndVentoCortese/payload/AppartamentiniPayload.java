package Francesco.BackEndVentoCortese.payload;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AppartamentiniPayload {

    private Long idAppartamentino;
    private String nome;
    private int numeroDiCamere;
    private int capienzaMassima;
    private double tariffa;
    private boolean disponibilita;
    private String descrizione;
    private List<String> immagini;
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public List<String> getImmagini() {
		return immagini;
	}
	public void setImmagini(List<String> immagini) {
		this.immagini = immagini;
	}
	public boolean isDisponibilita() {
		return disponibilita;
	}
	public void setDisponibilita(boolean disponibilita) {
		this.disponibilita = disponibilita;
	}
	public int getCapienzaMassima() {
		return capienzaMassima;
	}
	public void setCapienzaMassima(int capienzaMassima) {
		this.capienzaMassima = capienzaMassima;
	}
	public double getTariffa() {
		return tariffa;
	}
	public void setTariffa(double tariffa) {
		this.tariffa = tariffa;
	}
	public int getNumeroDiCamere() {
		return numeroDiCamere;
	}
	public void setNumeroDiCamere(int numeroDiCamere) {
		this.numeroDiCamere = numeroDiCamere;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getIdAppartamentino() {
		return idAppartamentino;
	}
	public void setIdAppartamentino(Long idAppartamentino) {
		this.idAppartamentino = idAppartamentino;
	}

}
