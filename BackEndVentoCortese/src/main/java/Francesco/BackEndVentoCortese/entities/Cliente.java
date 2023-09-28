package Francesco.BackEndVentoCortese.entities;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "cliente")
@Data
public class Cliente implements UserDetails {
	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCliente;

	@Column
	private String nome;

	@Column
	private String email;

	@Column
	private String password;

	@Column
	private String telefono;

	@OneToMany(mappedBy = "cliente")
	@JsonIgnore
	private Set<Recensione> recensioni = new HashSet<>();

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "cliente")
	private Set<Prenotazione> prenotazioni = new HashSet<>();

	public Long getId() {
		return this.idCliente;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.emptyList(); // Nessuna autorità specifica
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true; // Supponendo che non gestisci la scadenza degli account
	}

	@Override
	public boolean isAccountNonLocked() {
		return true; // Supponendo che non gestisci account bloccati
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true; // Supponendo che non gestisci la scadenza delle credenziali
	}

	@Override
	public boolean isEnabled() {
		return true; // Supponendo che tutti gli utenti sono sempre abilitati
	}

}
