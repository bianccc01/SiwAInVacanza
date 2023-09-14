package it.uniroma3.siw.service;


import java.io.IOException;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import it.uniroma3.siw.model.Categoria;
import it.uniroma3.siw.model.Destinazione;
import it.uniroma3.siw.model.Image;
import it.uniroma3.siw.model.Periodo;
import it.uniroma3.siw.model.Prenotazione;
import it.uniroma3.siw.repository.CategoriaRepository;
import it.uniroma3.siw.repository.DestinazioneRepository;
import it.uniroma3.siw.repository.PeriodoRepository;

@Service
public class PeriodoService {

	@Autowired
	private DestinazioneRepository destinazioneRepository;

	@Autowired
	private PeriodoRepository periodoRepository;

	@Transactional
	public Set<Periodo> allPeriodi(){
		return this.periodoRepository.findAll();
	}

	@Transactional
	public Periodo findPeriodoById(Long id) {
		return this.periodoRepository.findById(id).get();
	}

	@Transactional
	public void savePeriodo(Periodo periodo) {
		this.periodoRepository.save(periodo);
	}


	@Transactional
	public void deletePeriodo(Periodo periodo) {

		if(!periodo.getDestinazioni().isEmpty()) {
			for(Destinazione d: periodo.getDestinazioni()) {
				d.getPeriodi().remove(periodo);
				this.destinazioneRepository.save(d);
			}
			this.periodoRepository.delete(periodo);
		}
	}
	
	@Transactional
	public List<Periodo> getPeriodiDaSelezionare(Destinazione dest) {
		return this.periodoRepository.findAllByDestinazioniNotContaining(dest);
	}

	public void addPeriodoDestinazione(Long idPer, Destinazione destinazione) {
		Periodo periodo = this.periodoRepository.findById(idPer).get();
		periodo.getDestinazioni().add(destinazione);
		destinazione.getPeriodi().add(periodo);
		this.destinazioneRepository.save(destinazione);
		this.periodoRepository.save(periodo);
	}



}
