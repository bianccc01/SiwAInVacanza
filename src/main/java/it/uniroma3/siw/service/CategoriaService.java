package it.uniroma3.siw.service;


import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Categoria;
import it.uniroma3.siw.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Transactional
	public Set<Categoria> allCategorie(){
		return this.categoriaRepository.findAll();
	}
	
	@Transactional
	public Categoria findCategoriaById(Long id) {
		return this.categoriaRepository.findById(id).get();
	}
	
	@Transactional
	public void saveCategoria(Categoria categoria) {
		this.categoriaRepository.save(categoria);
	}
	
	

}
