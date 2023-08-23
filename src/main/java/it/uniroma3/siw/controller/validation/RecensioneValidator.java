package it.uniroma3.siw.controller.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Recensione;
import it.uniroma3.siw.repository.RecensioneRepository;

@Component
public class RecensioneValidator implements Validator {
	
	@Autowired
	private RecensioneRepository recensioneRepository;

	@Override
	public void validate(Object o, Errors errors) {
		Recensione recensione = (Recensione)o;
		if (recensione.getUtente()!=null && recensione.getDestinazione()!=null 
				&& recensioneRepository.existsByUtenteAndDestinazione(recensione.getUtente(),recensione.getDestinazione())) {
			errors.reject("recensione.duplicate");
		}
	}
	@Override
	public boolean supports(Class<?> aClass) {
		return Recensione.class.equals(aClass);
	}

}
