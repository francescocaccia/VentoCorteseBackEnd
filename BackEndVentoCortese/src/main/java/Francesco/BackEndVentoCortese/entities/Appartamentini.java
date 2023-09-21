package Francesco.BackEndVentoCortese.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Appartamentini {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAppartamentino;

    @Column(nullable = false)
    private String nome;

    @OneToMany(mappedBy = "appartamentino", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Prenotazione> prenotazioni;


    @Column(nullable = false)
    private int numeroDiCamere;

    @Column(nullable = false)
    private int capienzaMassima;

    @Column(nullable = false)
    private double tariffa;

    @Column(nullable = false)
    private boolean disponibilita;

    @Column(length = 1000) 
    private String descrizione;

    @ElementCollection
    private List<String> immagini;

    @OneToMany(mappedBy = "appartamentino", fetch = FetchType.LAZY)
    private List<Recensione> recensioni;

    public void setIdAppartamentino(Long idAppartamentino) {
        this.idAppartamentino = idAppartamentino;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPrenotazioni(List<Prenotazione> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }

    public void setNumeroDiCamere(int numeroDiCamere) {
        this.numeroDiCamere = numeroDiCamere;
    }

    public void setCapienzaMassima(int capienzaMassima) {
        this.capienzaMassima = capienzaMassima;
    }

    public void setTariffa(double tariffa) {
        this.tariffa = tariffa;
    }

    public void setDisponibilita(boolean disponibilita) {
        this.disponibilita = disponibilita;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setImmagini(List<String> immagini) {
        this.immagini = immagini;
    }

    public void setRecensioni(List<Recensione> recensioni) {
        this.recensioni = recensioni;
    }
}

