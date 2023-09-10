package it.uniroma3.siw.repository;



import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Destinazione;
import it.uniroma3.siw.model.Prenotazione;
import it.uniroma3.siw.model.User;

public interface PrenotazioneRepository extends CrudRepository<Prenotazione,Long> {
	
	public Iterable<Prenotazione> findAll();
	
	public Iterable<Prenotazione> findAllByUser(User user);

	public boolean existsByNomeAndCognomeAndUserAndDestinazionePrenotata(String nome, String cognome, User user,
			Destinazione destinazionePrenotata);
}
