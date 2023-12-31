package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.*;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<Image, Long> {
	
	List<Image> findAllByDestinazioneAndIdNot(Destinazione d, Long id);

}
