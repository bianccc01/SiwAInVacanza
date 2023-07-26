package it.uniroma3.siw.service;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Destinazione;
import it.uniroma3.siw.model.Image;
import it.uniroma3.siw.repository.DestinazioneRepository;
import it.uniroma3.siw.repository.ImageRepository;

@Service
public class DestinazioneService {
	
	@Autowired
	private DestinazioneRepository destinazioneRepository;
	
	@Autowired
	private ImageRepository imageRepository;
	
	
	@Transactional
	public Iterable<Destinazione> allDestinazioni(){
		return this.destinazioneRepository.findAll();
	}
	
	@Transactional
	public Destinazione findDestinazioneById(Long id) {
		return this.destinazioneRepository.findById(id).get();
	}
	
	@Transactional
	public List<Destinazione> searchDestinazioniByNome(String nome){
		return this.destinazioneRepository.findByNomeContainingIgnoreCase(nome);
	}
	
	@Transactional
	public void saveDestinazione(Destinazione d) {
		this.destinazioneRepository.save(d);
	}
	
	@Transactional
	public void newImagesDest(MultipartFile[] files, Destinazione destinazione) throws IOException {

		for (MultipartFile file : files) {
			byte[] imageData = file.getBytes();
			String imageName = file.getOriginalFilename();

			Image image = new Image();
			image.setName(imageName);
			image.setBytes(imageData);


			destinazione.addImage(image);
		}
		
		this.imageRepository.saveAll(destinazione.getImages());
	}

}
