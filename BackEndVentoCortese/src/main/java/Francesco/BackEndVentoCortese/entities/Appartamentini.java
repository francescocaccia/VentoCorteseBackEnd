package Francesco.BackEndVentoCortese.entities;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Appartamentini {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAppartamentino;

	@Column
	@NotNull
	private String nome;

	@OneToMany(mappedBy = "appartamentino", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Prenotazione> prenotazioni;

	@Column
	@NotNull
	private int numeroDiCamere;

	@Column
	@NotNull
	private int capienzaMassima;

	@Column
	@NotNull
	private double tariffa;

	@Column
	@NotNull
	private boolean disponibilita;

	@Column(length = 1000)
	private String descrizione;

	@OneToMany(mappedBy = "appartamentini", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Immagine> immagini;

	@OneToMany(mappedBy = "appartamentino", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Recensione> recensioni;

	@Override
	public String toString() {
		return "Appartamentini [idAppartamentino=" + idAppartamentino + ", nome=" + nome + ", numeroDiCamere="
				+ numeroDiCamere + ", capienzaMassima=" + capienzaMassima + ", tariffa=" + tariffa + ", disponibilita="
				+ disponibilita + ", descrizione=" + descrizione + "]";
	}

}
