package it.uniroma3.siw.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import it.uniroma3.siw.model.Categoria;
import it.uniroma3.siw.model.Destinazione;

public interface DestinazioneRepository extends CrudRepository<Destinazione,Long> {
	
	public boolean existsByNome(String nome);
	
	public Iterable<Destinazione> findAll();
	
	public List<Destinazione> findByNomeContainingIgnoreCase(String nome);
	
	public Destinazione findByNome(String nome);
	
	public List<Destinazione> findByCategoriaIsNull();

	public void findByCategoria(Categoria categoria);
	
	

}
