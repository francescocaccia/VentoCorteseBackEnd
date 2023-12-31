package Francesco.BackEndVentoCortese.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "recensioni")
@Data
public class Recensione {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRecensione;

	@Column
	@NotNull
	private int numeroStelle;

	@Column(nullable = true, length = 500)
	private String contenutoRecensione;

	@ManyToOne
	@JoinColumn(name = "idCliente")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "idAppartamentino")
	private Appartamentini appartamentino;

}
