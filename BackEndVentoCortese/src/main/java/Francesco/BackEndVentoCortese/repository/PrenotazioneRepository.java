package Francesco.BackEndVentoCortese.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Francesco.BackEndVentoCortese.entities.Appartamentini;
import Francesco.BackEndVentoCortese.entities.Prenotazione;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {

	List<Prenotazione> findByAppartamentinoAndDataInizioLessThanEqualAndDataFineGreaterThanEqual(
			Appartamentini appartamentino, Date dataFine, Date dataInizio);

	List<Prenotazione> findByAppartamentinoAndDataInizioLessThanEqualAndDataFineGreaterThanEqualAndIdPrenotazioneNot(
			Appartamentini appartamentino, Date dataFine, Date dataInizio, Long idPrenotazione);

}