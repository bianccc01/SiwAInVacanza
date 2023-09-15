package it.uniroma3.siw.service;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Periodo;
import it.uniroma3.siw.model.Prenotazione;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.PrenotazioneRepository;
import it.uniroma3.siw.repository.UserRepository;

@Service
public class PrenotazioneService {
	
	@Autowired
	private PrenotazioneRepository prenotazioneRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CredentialsService credentialsService;
	
	@Autowired
	private DestinazioneService destinazioneService;
	
	@Autowired
	private PeriodoService periodoService;
	
	@Transactional
	public Prenotazione getPrenotazione(Long id) {
		return this.prenotazioneRepository.findById(id).get();
	}
	
	@Transactional
	public Iterable<Prenotazione> allPrenotazioni(){
		return this.prenotazioneRepository.findAll();
	}
	
	@Transactional
	public Iterable<Prenotazione> getPrenotazioneUser(Authentication auth){
		User user = this.credentialsService.getUser(auth);
		return this.prenotazioneRepository.findAllByUser(user);
	}
	
	@Transactional
	public void savePrenotazione(Prenotazione prenotazione) {
		this.prenotazioneRepository.save(prenotazione);
	}
	
	@Transactional
	public void inizializzaPrenotazione(Prenotazione prenotazione, User user, Long idDestinazione) {
		prenotazione.setDestinazionePrenotata(this.destinazioneService.findDestinazioneById(idDestinazione));
		prenotazione.inizializzaPrezzo();
		user.addPrenotazione(prenotazione);
		this.userRepository.save(user);
		this.savePrenotazione(prenotazione);
		
	}
	
	@Transactional
	public void eliminaPrenotazione(Long idPrenotazione) {
		Prenotazione prenotazione = this.prenotazioneRepository.findById(idPrenotazione).get();
		prenotazione.getUser().getPrenotazioni().remove(prenotazione);
		this.prenotazioneRepository.delete(prenotazione);
	}

}
