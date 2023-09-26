package Francesco.BackEndVentoCortese.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Francesco.BackEndVentoCortese.entities.Appartamentini;
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
		appartamentino.setTariffa(appartamentiniPayload.getTariffa());
		appartamentino.setDisponibilita(appartamentiniPayload.isDisponibilita());
		appartamentino.setDescrizione(appartamentiniPayload.getDescrizione());
		appartamentino.setImmagini(appartamentiniPayload.getImmagini());

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
