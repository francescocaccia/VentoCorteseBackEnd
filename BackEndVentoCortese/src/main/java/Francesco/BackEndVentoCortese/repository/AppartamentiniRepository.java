package Francesco.BackEndVentoCortese.repository;
import Francesco.BackEndVentoCortese.entities.Appartamentini;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AppartamentiniRepository extends JpaRepository<Appartamentini, Long> {
	Appartamentini findByNome(String nome);

}
