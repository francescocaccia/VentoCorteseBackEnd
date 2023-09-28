package Francesco.BackEndVentoCortese.exception;

public class PrenotazioneNotFoundException extends RuntimeException {
	public PrenotazioneNotFoundException() {
		super("Prenotazione non trovata.");
	}

	public PrenotazioneNotFoundException(String message) {
		super(message);
	}
}
