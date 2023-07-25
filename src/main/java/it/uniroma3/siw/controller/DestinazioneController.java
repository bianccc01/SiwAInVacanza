package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.model.Destinazione;
import it.uniroma3.siw.service.DestinazioneService;

public class DestinazioneController {
	
	@Autowired 
	private DestinazioneService destinazioneService;
	
	@GetMapping("/")
	public String index() {
		return "index.html";
	}
	
	@GetMapping("/formNewDestinazione")
	public String formNewDestinazione(Model model) {
		model.addAttribute("destinazione", new Destinazione());
		return "formNewDestinazione.html";
	}
	
	

}
