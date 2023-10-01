package Francesco.BackEndVentoCortese.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Francesco.BackEndVentoCortese.entities.Immagine;

@Repository
public interface ImmagineRepository extends JpaRepository<Immagine, Long> {
}
