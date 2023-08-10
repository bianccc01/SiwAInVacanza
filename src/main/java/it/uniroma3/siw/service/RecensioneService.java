package it.uniroma3.siw.service;


import java.io.IOException;

import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Image;
import it.uniroma3.siw.model.Recensione;
import it.uniroma3.siw.repository.ImageRepository;
import it.uniroma3.siw.repository.RecensioneRepository;

@Service
public class RecensioneService {

	@Autowired
	private RecensioneRepository recensioneRepository;

	@Autowired
	private ImageRepository imageRepository;

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
	public void newImagesCat(MultipartFile file, Recensione recensione) throws IOException {

		byte[] imageData = file.getBytes();
		String imageName = file.getOriginalFilename();

		Image image = new Image();
		image.setName(imageName);
		image.setBytes(imageData);

		this.imageRepository.save(image);


		recensione.setImage(image);


	}


}
