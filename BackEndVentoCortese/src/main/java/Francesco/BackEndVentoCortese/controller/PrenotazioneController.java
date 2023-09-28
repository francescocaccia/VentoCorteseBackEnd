package Francesco.BackEndVentoCortese.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Francesco.BackEndVentoCortese.entities.Prenotazione;
import Francesco.BackEndVentoCortese.payload.PrenotazionePayload;
import Francesco.BackEndVentoCortese.service.PrenotazioneService;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

	@Autowired
	private PrenotazioneService prenotazioneService;

	@PostMapping("/add")
	public ResponseEntity<Prenotazione> inserisciPrenotazione(@RequestBody PrenotazionePayload prenotazionePayload) {
		try {
			Prenotazione nuovaPrenotazione = prenotazioneService.inserisciPrenotazione(prenotazionePayload);
			return new ResponseEntity<>(nuovaPrenotazione, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{idPrenotazione}")
	public ResponseEntity<Prenotazione> modificaPrenotazione(@PathVariable Long idPrenotazione,
			@RequestBody PrenotazionePayload payload) {

		try {
			Prenotazione prenotazioneAggiornata = prenotazioneService.modificaPrenotazione(idPrenotazione,
					payload.getIdCliente(), payload.getDataInizio(), payload.getDataFine());
			return ResponseEntity.ok(prenotazioneAggiornata);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(null);
		}
	}

	// Endpoint per ottenere tutte le prenotazioni
	@GetMapping("/all")
	public ResponseEntity<List<Prenotazione>> getAllPrenotazioni() {
		try {
			List<Prenotazione> prenotazioni = prenotazioneService.getAllPrenotazioni();
			if (prenotazioni.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(prenotazioni, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Endpoint per ottenere una prenotazione in base all'ID
	@GetMapping("/{idPrenotazione}")
	public ResponseEntity<Prenotazione> getPrenotazioneById(@PathVariable Long idPrenotazione) {
		try {
			Prenotazione prenotazione = prenotazioneService.getPrenotazioneById(idPrenotazione);
			if (prenotazione != null) {
				return new ResponseEntity<>(prenotazione, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Endpoint per eliminare una prenotazione in base all'ID
	@DeleteMapping("/{idPrenotazione}")
	public ResponseEntity<HttpStatus> deletePrenotazione(@PathVariable Long idPrenotazione) {
		try {
			prenotazioneService.deletePrenotazione(idPrenotazione);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}