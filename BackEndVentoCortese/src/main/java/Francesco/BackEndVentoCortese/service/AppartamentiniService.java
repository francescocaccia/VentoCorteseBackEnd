package Francesco.BackEndVentoCortese.service;

import Francesco.BackEndVentoCortese.entities.Appartamentini;
import Francesco.BackEndVentoCortese.payload.AppartamentiniPayload;
import Francesco.BackEndVentoCortese.repository.AppartamentiniRepository;
import lombok.extern.slf4j.Slf4j;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AppartamentiniService {

    @Autowired
    private AppartamentiniRepository appartamentiniRepository; 

    public List<Appartamentini> findAll() {
        return appartamentiniRepository.findAll();
    }

    public Optional<Appartamentini> getById(Long id) {
        return appartamentiniRepository.findById(id);
    }

    
    
    public void inserisciAppartamentino(AppartamentiniPayload appartamentiniPayload) {
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
    
    
    public boolean existsById(Long id) {
        return appartamentiniRepository.existsById(id);
    }
    
  
    public void deleteById(Long id) {
        appartamentiniRepository.deleteById(id);
    }






}
