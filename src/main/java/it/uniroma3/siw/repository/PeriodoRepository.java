package it.uniroma3.siw.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import org.springframework.data.repository.CrudRepository;
import it.uniroma3.siw.model.Destinazione;
import it.uniroma3.siw.model.Periodo;

public interface PeriodoRepository extends CrudRepository<Periodo,Long> {

	boolean existsByPartenzaAndRitorno(LocalDate partenza, LocalDate ritorno);
	
	public Set<Periodo> findAll();
	
	public List<Periodo> findAllByDestinazioniNotContaining(Destinazione destinazione);
	
	
}
