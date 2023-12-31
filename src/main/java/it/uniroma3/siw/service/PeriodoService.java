package it.uniroma3.siw.service;



import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.uniroma3.siw.model.Destinazione;
import it.uniroma3.siw.model.Periodo;
import it.uniroma3.siw.repository.DestinazioneRepository;
import it.uniroma3.siw.repository.PeriodoRepository;

@Service
public class PeriodoService {

	@Autowired
	private DestinazioneRepository destinazioneRepository;

	@Autowired
	private PeriodoRepository periodoRepository;
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL");

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
		periodo.setStringaPeriodo(periodo.getPartenza().format(formatter) + " - " + periodo.getRitorno().format(formatter));
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
	
	@Transactional
	public void addPeriodoDestinazione(Long idPer, Destinazione destinazione) {
		Periodo periodo = this.periodoRepository.findById(idPer).get();
		periodo.getDestinazioni().add(destinazione);
		destinazione.getPeriodi().add(periodo);
		this.destinazioneRepository.save(destinazione);
		this.periodoRepository.save(periodo);
	}
	
	@Transactional
	public void rmvPeriodoDestinazione(Long idPer, Destinazione destinazione) {
		Periodo periodo = this.periodoRepository.findById(idPer).get();
		periodo.getDestinazioni().remove(destinazione);
		destinazione.getPeriodi().remove(periodo);
		this.destinazioneRepository.save(destinazione);
		this.periodoRepository.save(periodo);
	}

	



}
