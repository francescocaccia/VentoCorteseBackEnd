package Francesco.BackEndVentoCortese.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Francesco.BackEndVentoCortese.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	Optional<Cliente> findByEmail(String email);

	Optional<Cliente> findByEmailAndPassword(String email, String password);
}
