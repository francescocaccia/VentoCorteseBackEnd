package Francesco.BackEndVentoCortese.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Francesco.BackEndVentoCortese.entities.Appartamentini;
import Francesco.BackEndVentoCortese.entities.Immagine;
import Francesco.BackEndVentoCortese.payload.AppartamentiniPayload;
import Francesco.BackEndVentoCortese.repository.AppartamentiniRepository;

@Service

public class AppartamentiniService {

	@Autowired
	private AppartamentiniRepository appartamentiniRepository;

	public List<Appartamentini> getAll() {
		List<Appartamentini> allAppartamentini = appartamentiniRepository.findAll();
		return allAppartamentini;
	}

	public void inserisciAppartamentino(AppartamentiniPayload appartamentiniPayload) {

		if (esisteAppartamentino(appartamentiniPayload.getNome())) {
			throw new IllegalArgumentException(
					"Appartamentino con il nome " + appartamentiniPayload.getNome() + " esiste già.");
		}

		Appartamentini appartamentino = new Appartamentini();

		appartamentino.setNome(appartamentiniPayload.getNome());
		appartamentino.setNumeroDiCamere(appartamentiniPayload.getNumeroDiCamere());
		appartamentino.setCapienzaMassima(appartamentiniPayload.getCapienzaMassima());
		appartamentino.setMetriQuadri(appartamentiniPayload.getMetriQuadri());
		appartamentino.setTariffa(appartamentiniPayload.getTariffa());
		appartamentino.setDisponibilita(appartamentiniPayload.isDisponibilita());
		appartamentino.setDescrizione(appartamentiniPayload.getDescrizione());

		Set<Immagine> immaginiEntities = appartamentiniPayload.getImmagini().stream().map(payload -> {
			Immagine immagine = new Immagine();
			immagine.setImmagine1(payload.getImmagine1());
			immagine.setImmagine2(payload.getImmagine2());
			immagine.setImmagine3(payload.getImmagine3());
			immagine.setAppartamentini(appartamentino); // Imposta la relazione
			return immagine;
		}).collect(Collectors.toSet());

		appartamentino.setImmagini(immaginiEntities);

		appartamentiniRepository.save(appartamentino);
	}

	public boolean esisteAppartamentino(String nome) {
		return appartamentiniRepository.findByNome(nome) != null;
	}

	public boolean existsById(Long id) {
		return appartamentiniRepository.existsById(id);
	}

	public void deleteById(Long id) {
		appartamentiniRepository.deleteById(id);
	}

}
