package it.uniroma3.siw.repository;


import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Categoria;

public interface CategoriaRepository extends CrudRepository<Categoria,Long> {
	
	public boolean existsByNome(String nome);
	
	public Set<Categoria> findAll();

}
