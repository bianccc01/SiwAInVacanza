package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.model.Destinazione;
import it.uniroma3.siw.service.DestinazioneService;

@Controller
public class DestinazioneController {
	
	@Autowired 
	private DestinazioneService destinazioneService;
	
	@GetMapping("/formNewDestinazione")
	public String formNewDestinazione(Model model) {
		model.addAttribute("destinazione", new Destinazione());
		return "formNewDestinazione.html";
	}
	
	@GetMapping("/allDestinazioni")
	public String allDestinazioni(Model model) {
		model.addAttribute("destinazioni",this.destinazioneService.allDestinazioni());
		return "destinazioni.html";
	}
	
	

}
