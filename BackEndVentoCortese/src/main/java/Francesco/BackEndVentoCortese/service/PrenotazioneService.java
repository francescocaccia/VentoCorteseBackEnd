package Francesco.BackEndVentoCortese.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Francesco.BackEndVentoCortese.entities.Appartamentini;
import Francesco.BackEndVentoCortese.entities.Cliente;
import Francesco.BackEndVentoCortese.entities.Prenotazione;
import Francesco.BackEndVentoCortese.exception.BookingConflictException;
import Francesco.BackEndVentoCortese.exception.PrenotazioneNotFoundException;
import Francesco.BackEndVentoCortese.exception.UnauthorizedException;
import Francesco.BackEndVentoCortese.payload.PrenotazionePayload;
import Francesco.BackEndVentoCortese.repository.AppartamentiniRepository;
import Francesco.BackEndVentoCortese.repository.ClienteRepository;
import Francesco.BackEndVentoCortese.repository.PrenotazioneRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PrenotazioneService {

	@Autowired
	private PrenotazioneRepository prenotazioneRepository;
	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private AppartamentiniRepository appartamentiniRepository;

	public Prenotazione inserisciPrenotazione(PrenotazionePayload prenotazionePayload) throws Exception {
		log.info("Tentativo di inserire una nuova prenotazione...");

		if (prenotazionePayload.getDataInizio().after(prenotazionePayload.getDataFine())) {
			log.error("Data inizio dopo data fine!");
			throw new Exception("Data inizio non può essere dopo data fine!");
		}

		Appartamentini appartamento = appartamentiniRepository.findById(prenotazionePayload.getIdAppartamentino())
				.orElse(null);
		if (appartamento == null) {
			log.error("Appartamento con ID {} non trovato!", prenotazionePayload.getIdAppartamentino());
			throw new Exception("Appartamento non trovato!");
		}

		List<Prenotazione> prenotazioniEsistenti = prenotazioneRepository
				.findByAppartamentinoAndDataInizioLessThanEqualAndDataFineGreaterThanEqual(appartamento,
						prenotazionePayload.getDataFine(), prenotazionePayload.getDataInizio());

		if (!prenotazioniEsistenti.isEmpty()) {
			log.error("L'appartamento è già prenotato per le date specificate!");
			throw new Exception("L'appartamento è già prenotato per le date specificate!");
		}

		Cliente cliente = clienteRepository.findById(prenotazionePayload.getIdCliente()).orElse(null);
		if (cliente == null) {
			log.error("Cliente con ID {} non trovato!", prenotazionePayload.getIdCliente());
			throw new Exception("Cliente non trovato!");
		}

		String codicePrenotazione = UUID.randomUUID().toString();
		log.info("Generato codice prenotazione: {}", codicePrenotazione);

		Prenotazione nuovaPrenotazione = new Prenotazione(null, prenotazionePayload.getDataInizio(),
				prenotazionePayload.getDataFine(), cliente, false, 0.0, appartamento, codicePrenotazione);

		log.info("Salvataggio nuova prenotazione nel database...");
		return prenotazioneRepository.save(nuovaPrenotazione);
	}

//PUT+++++++++++++++++PUT++++++++++++++++PUT+++++++++++PUT
	public Prenotazione modificaPrenotazione(Long idPrenotazione, Long idCliente, Date nuovaDataInizio,
			Date nuovaDataFine) throws PrenotazioneNotFoundException {
		// Trova la prenotazione esistente
		Prenotazione prenotazioneEsistente = prenotazioneRepository.findById(idPrenotazione).orElse(null);
		if (prenotazioneEsistente == null) {
			// gestisci l'errore come preferisci, ad esempio lanciando un'eccezione
			throw new PrenotazioneNotFoundException();
		}

		// Verifica che la prenotazione esista e che l'ID del cliente corrisponda
		if (prenotazioneEsistente == null || !prenotazioneEsistente.getCliente().getId().equals(idCliente)) {
			// Genera un errore o ritorna null
			throw new UnauthorizedException("Non hai il permesso di modificare questa prenotazione.");
		}

		// Controlla se le nuove date sono disponibili
		List<Prenotazione> prenotazioniInConflitto = prenotazioneRepository
				.findByAppartamentinoAndDataInizioLessThanEqualAndDataFineGreaterThanEqual(
						prenotazioneEsistente.getAppartamentino(), nuovaDataInizio, nuovaDataFine);
		for (Prenotazione p : prenotazioniInConflitto) {
			if (!p.getIdPrenotazione().equals(idPrenotazione)) {
				// C'è un conflitto
				throw new BookingConflictException("Le date selezionate sono già prenotate.");
			}
		}

		// Modifica la prenotazione e salvala
		prenotazioneEsistente.setDataInizio(nuovaDataInizio);
		prenotazioneEsistente.setDataFine(nuovaDataFine);
		prenotazioneRepository.save(prenotazioneEsistente);

		return prenotazioneEsistente;
	}

	public List<Prenotazione> getAllPrenotazioni() {
		return prenotazioneRepository.findAll();
	}

	public Prenotazione getPrenotazioneById(Long idPrenotazione) throws PrenotazioneNotFoundException {
		Optional<Prenotazione> prenotazioneOptional = prenotazioneRepository.findById(idPrenotazione);
		if (prenotazioneOptional.isPresent()) {
			return prenotazioneOptional.get();
		} else {
			throw new PrenotazioneNotFoundException("Prenotazione con ID " + idPrenotazione + " non trovata.");
		}
	}

	public void deletePrenotazione(Long idPrenotazione) throws PrenotazioneNotFoundException {
		// Controlla se la prenotazione esiste
		if (!prenotazioneRepository.existsById(idPrenotazione)) {
			throw new PrenotazioneNotFoundException();
		}

		prenotazioneRepository.deleteById(idPrenotazione);
	}

}
