package Francesco.BackEndVentoCortese.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "prenotazioni")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPrenotazione;

    @Temporal(TemporalType.DATE) // Usa solo la data, non l'orario
    @Column(nullable = false)
    private Date dataInizio;

    @Temporal(TemporalType.DATE) // Usa solo la data, non l'orario
    @Column(nullable = false)
    private Date dataFine;

    @ManyToOne
    @JoinColumn(name = "idCliente") 
    private Cliente cliente;

    @Column(nullable = false)
    private boolean confermata;

    @Column(nullable = false)
    private double importoTotale;
    @ManyToOne
    @JoinColumn(name = "idAppartamentino") 
    private Appartamentini appartamentino;

}
