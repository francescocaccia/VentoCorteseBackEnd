package Francesco.BackEndVentoCortese.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Francesco.BackEndVentoCortese.entities.Appartamentini;
import Francesco.BackEndVentoCortese.payload.AppartamentiniPayload;
import Francesco.BackEndVentoCortese.service.AppartamentiniService;




@RestController
@RequestMapping("/appartamentini")
public class AppartamentiniController {

 

    @Autowired
    private AppartamentiniService appartamentiniService;
    @PostMapping("/create")    public ResponseEntity<Void> inserisciAppartamentino(@RequestBody AppartamentiniPayload appartamentiniPayload) {    	appartamentiniService.inserisciAppartamentino(appartamentiniPayload);        return new ResponseEntity<>(HttpStatus.CREATED);    }
 

    
    
    
    
    @GetMapping("/all")
    public ResponseEntity<List<Appartamentini>> getAll() {
        List<Appartamentini> response = appartamentiniService.findAll();
        System.out.println(response); 
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Appartamentini> getById(@PathVariable("id") Long id) {
        Optional<Appartamentini> ristoranteOptional = appartamentiniService.getById(id);

        if (ristoranteOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Appartamentini appartamentini = ristoranteOptional.get();
        return ResponseEntity.ok(appartamentini);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppartamentiniById(@PathVariable("id") Long id) {
        if (!appartamentiniService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        appartamentiniService.deleteById(id); 
        return ResponseEntity.noContent().build();
    }

}

