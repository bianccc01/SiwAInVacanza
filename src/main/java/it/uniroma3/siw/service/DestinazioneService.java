package it.uniroma3.siw.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Destinazione;
import it.uniroma3.siw.repository.DestinazioneRepository;

@Service
public class DestinazioneService {
	
	@Autowired
	private DestinazioneRepository destinazioneRepository;
	
	
	@Transactional
	public Iterable<Destinazione> allDestinazioni(){
		return this.destinazioneRepository.findAll();
	}
	
	@Transactional
	public List<Destinazione> searchDestinazioniByNome(String nome){
		return this.destinazioneRepository.findByNomeContainingIgnoreCase(nome);
	}
	
	@Transactional
	public void saveDestinazione(Destinazione d) {
		this.destinazioneRepository.save(d);
	}

}
