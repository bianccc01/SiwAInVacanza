package it.uniroma3.siw.controller.validation;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Periodo;
import it.uniroma3.siw.model.Prenotazione;
import it.uniroma3.siw.repository.PeriodoRepository;
import it.uniroma3.siw.repository.PrenotazioneRepository;

@Component
public class PeriodoValidator implements Validator {
	@Autowired
	private PeriodoRepository periodoRepository;

	@Override
	public void validate(Object o, Errors errors) {
		Periodo per = (Periodo)o;
		if (per.getPartenza()!=null && per.getRitorno()!=null &&  
				periodoRepository.existsByPartenzaAndRitorno(per.getPartenza(),per.getRitorno())) {
			errors.reject("periodo.duplicate");
		}
	}
	@Override
	public boolean supports(Class<?> aClass) {
		return Periodo.class.equals(aClass);
	}
}
