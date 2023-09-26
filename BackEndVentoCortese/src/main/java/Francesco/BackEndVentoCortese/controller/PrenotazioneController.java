package Francesco.BackEndVentoCortese.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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

}
