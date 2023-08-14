package it.uniroma3.siw.controller.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Destinazione;
import it.uniroma3.siw.repository.DestinazioneRepository;


public class DestinazioneValidator implements Validator {
	@Autowired
	private DestinazioneRepository destinazioneRepository;

	@Override
	public void validate(Object o, Errors errors) {
		Destinazione destinazione = (Destinazione)o;
		if (destinazione.getNome()!=null && destinazioneRepository.existsByNome(destinazione.getNome())) {
			errors.reject("destinazione.duplicate");
		}
	}
	@Override
	public boolean supports(Class<?> aClass) {
		return Destinazione.class.equals(aClass);
	}

}
