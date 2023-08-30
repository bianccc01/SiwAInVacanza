package it.uniroma3.siw.service;




import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


import it.uniroma3.siw.model.Destinazione;

import it.uniroma3.siw.model.Recensione;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.DestinazioneRepository;

import it.uniroma3.siw.repository.RecensioneRepository;
import it.uniroma3.siw.repository.UserRepository;

@Service
public class RecensioneService {

	@Autowired
	private RecensioneRepository recensioneRepository;

	@Autowired
	private DestinazioneRepository destinazioneRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CredentialsService credentialsService;

	@Transactional
	public Set<Recensione> allRecensioni(){
		return this.recensioneRepository.findAll();
	}

	@Transactional
	public Recensione findRecensioneById(Long id) {
		return this.recensioneRepository.findById(id).get();
	}

	@Transactional
	public void saveRecensione(Recensione recensione) {
		this.recensioneRepository.save(recensione);
	}
	
	@Transactional
	public List<Recensione> allRecensioniDestinazione(Destinazione dest){
		return this.recensioneRepository.findByDestinazione(dest);
	}
	
	@Transactional
	public void saveRecensione(Recensione rec, Destinazione dest, User user) {
		this.recensioneRepository.save(rec);
		dest.getRecensioni().add(rec);
		user.getRecensioni().add(rec);
		this.destinazioneRepository.save(dest);
		this.userRepository.save(user);
	}

	public Recensione getRecensioneUtente(Authentication auth,Destinazione dest) {
		User user=this.credentialsService.getUser(auth);
		if(user==null) {
			return null;
		}
		return this.recensioneRepository.findByUtenteAndDestinazione(user,dest);
	}

	public void rimuoviRecensione(Recensione rec) {
		this.recensioneRepository.delete(rec);
	}

	public List<Recensione> getRecensioniDueNotUtente(Authentication auth, Destinazione dest) {
		User user=this.credentialsService.getUser(auth);
		if(user==null) {
			return null;
		}
		return this.recensioneRepository.findFirst2ByDestinazioneAndUtenteNot(dest,user);
		
	}
	
	public List<Recensione> getRecensioniNotUtente(Authentication auth, Destinazione dest) {
		User user=this.credentialsService.getUser(auth);
		if(user==null) {
			return null;
		}
		return this.recensioneRepository.findByDestinazioneAndUtenteNot(dest,user);
	}
	
	public List<Recensione> getRecensioniTre(Destinazione dest){
		return this.recensioneRepository.findFirst3ByDestinazione(dest);
	}



}
