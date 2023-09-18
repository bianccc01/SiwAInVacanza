package it.uniroma3.siw.service;


import java.io.IOException;

import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import it.uniroma3.siw.model.Categoria;
import it.uniroma3.siw.model.Destinazione;
import it.uniroma3.siw.model.Image;
import it.uniroma3.siw.repository.CategoriaRepository;
import it.uniroma3.siw.repository.DestinazioneRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ImageService imageService;

	@Autowired
	private DestinazioneRepository destinazioneRepository;

	@Transactional
	public Set<Categoria> allCategorie(){
		return this.categoriaRepository.findAll();
	}

	@Transactional
	public Categoria findCategoriaById(Long id) {
		return this.categoriaRepository.findById(id).get();
	}

	@Transactional
	public void saveCategoria(Categoria categoria) {
		this.categoriaRepository.save(categoria);
	}

	@Transactional
	public void newImagesCat(MultipartFile file, Categoria categoria) throws IOException {

		byte[] imageData = file.getBytes();
		String imageName = file.getOriginalFilename();

		Image image = new Image();
		image.setName(imageName);
		image.setBytes(imageData);

		categoria.setImage(image);

		this.imageService.saveImage(image);

	}




	@Transactional
	public void deleteCategoria(Categoria categ) {
		if(!categ.getDestinazioni().isEmpty()) {
			for(Destinazione d:categ.getDestinazioni()) {
				d.setCategoria(null);
				this.destinazioneRepository.save(d);
			}
		}
		this.categoriaRepository.delete(categ);
	}



}
