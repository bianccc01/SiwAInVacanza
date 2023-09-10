package it.uniroma3.siw.repository;



import java.time.LocalDate;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Destinazione;
import it.uniroma3.siw.model.Prenotazione;
import it.uniroma3.siw.model.User;

public interface PeriodoRepository extends CrudRepository<Prenotazione,Long> {

	boolean existsByPartenzaAndRitorno(LocalDate partenza, LocalDate ritorno);
	
	
}
