package Francesco.BackEndVentoCortese.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;




@Entity
@Table(name = "cliente")
@Data
public class Cliente {
	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long idUser;

	    @Column
	    private String nome;

	    @Column
	    private String email;

	    @Column
	    private String telefono;


	    @OneToMany(mappedBy = "cliente")
	    private Set<Recensione> recensioni = new HashSet<>();


	    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cliente")
	    private Set<Prenotazione> prenotazioni = new HashSet<>();

}
