package it.uniroma3.siw.service;


import java.io.IOException;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Destinazione;
import it.uniroma3.siw.model.Image;
import it.uniroma3.siw.model.Recensione;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.DestinazioneRepository;
import it.uniroma3.siw.repository.ImageRepository;
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



}
