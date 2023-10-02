package Francesco.BackEndVentoCortese.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@Table(name = "immagini")
public class Immagine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCard;

	@Column
	@NotNull
	private String immagine1;

	@Column
	private String immagine2;

	@Column
	private String immagine3;

	@ManyToOne
	@JoinColumn(name = "idAppartamentino")
	@JsonIgnore
	private Appartamentini appartamentini;

}
