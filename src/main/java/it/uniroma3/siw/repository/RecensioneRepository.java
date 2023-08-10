package it.uniroma3.siw.repository;


import java.util.Set;


import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Recensione;
import it.uniroma3.siw.model.User;


public interface RecensioneRepository extends CrudRepository<Recensione,Long> {
	
	public boolean existsByUtente(User utente);
	
	public Set<Recensione> findAll();
	

}
