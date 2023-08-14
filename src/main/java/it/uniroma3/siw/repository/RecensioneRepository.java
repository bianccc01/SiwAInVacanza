package it.uniroma3.siw.repository;


import java.util.List;
import java.util.Set;


import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Destinazione;
import it.uniroma3.siw.model.Recensione;
import it.uniroma3.siw.model.User;


public interface RecensioneRepository extends CrudRepository<Recensione,Long> {
	
	public boolean existsByUtenteAndDestinazione(User utente, Destinazione destinazione);
	
	public Set<Recensione> findAll();
	
	public List<Recensione> findByDestinazione(Destinazione d);
	

}
