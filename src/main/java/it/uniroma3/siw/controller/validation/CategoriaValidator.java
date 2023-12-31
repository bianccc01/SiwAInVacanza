package it.uniroma3.siw.controller.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Categoria;
import it.uniroma3.siw.repository.CategoriaRepository;

@Component
public class CategoriaValidator implements Validator {
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	public void validate(Object o, Errors errors) {
		Categoria categoria = (Categoria)o;
		if (categoria.getNome()!=null && categoriaRepository.existsByNome(categoria.getNome())) {
			errors.reject("categoria.duplicate");
		}
	}
	@Override
	public boolean supports(Class<?> aClass) {
		return Categoria.class.equals(aClass);
	}
}
