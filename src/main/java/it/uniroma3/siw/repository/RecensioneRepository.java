package it.uniroma3.siw.repository;


import java.util.List;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Destinazione;
import it.uniroma3.siw.model.Recensione;
import it.uniroma3.siw.model.User;


public interface RecensioneRepository extends CrudRepository<Recensione,Long> {
	
	public boolean existsByUtenteAndDestinazione(User utente, Destinazione destinazione);
	
	public Set<Recensione> findAll();
	
	public List<Recensione> findByDestinazione(Destinazione d);

	public Recensione findByUtenteAndDestinazione(User user,Destinazione dest);

	public List<Recensione> findByDestinazioneAndUtenteNot(Destinazione dest, User user);
	
	/*@Query(value="select * "
			+ "from recensione r "
			+ "where r.destinazione = :dest "
			+ "and r.utente <> :user ", nativeQuery=true)
	public List<Recensione> findRecensioniNotUtente(@Param("dest") Destinazione dest, @Param("user") User user);*/
	

}
