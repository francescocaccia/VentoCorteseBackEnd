package Francesco.BackEndVentoCortese.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Francesco.BackEndVentoCortese.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	Cliente findByEmail(String email);

}
