package it.uniroma3.siw.controller.validation;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Prenotazione;
import it.uniroma3.siw.repository.PrenotazioneRepository;

@Component
public class PrenotazioneValidator implements Validator {
	@Autowired
	private PrenotazioneRepository prenotazioneRepository;

	@Override
	public void validate(Object o, Errors errors) {
		Prenotazione pren = (Prenotazione)o;
		if (pren.getUser()!=null && pren.getDestinazionePrenotata()!=null &&  pren.getPeriodo()!=null &&
				prenotazioneRepository.existsByUserAndDestinazionePrenotataAndPeriodo(pren.getUser(),pren.getDestinazionePrenotata(),pren.getPeriodo())) {
			errors.reject("prenotazione.duplicate");
		}
	}
	@Override
	public boolean supports(Class<?> aClass) {
		return Prenotazione.class.equals(aClass);
	}
}
