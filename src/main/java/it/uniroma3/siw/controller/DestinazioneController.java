package it.uniroma3.siw.controller;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Destinazione;
import it.uniroma3.siw.service.DestinazioneService;
import net.bytebuddy.asm.Advice.This;

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

	@GetMapping("/destinazione/{id}")
	public String destinazione(@PathVariable ("id") Long id, Model model) {
		model.addAttribute("destinazione",this.destinazioneService.findDestinazioneById(id));
		return "destinazione.html";
	}

	@PostMapping("/newDestinazione")
	public String newDestinazione(@ModelAttribute("destinazione") Destinazione destinazione, 
			@RequestParam("file") MultipartFile[] files, Model model) throws IOException {

		this.destinazioneService.saveDestinazione(destinazione);
		this.destinazioneService.newImagesDest(files, destinazione);

		model.addAttribute("destinazioni", this.destinazioneService.allDestinazioni());
		return "destinazioni.html";

	}
	
	@PostMapping("/cercaDestinazioni")
	public String cercaDestinazioni(Model model, @RequestParam String nome) {
		model.addAttribute("destinazioni", this.destinazioneService.searchDestinazioniByNome(nome));
		return "destinazioni.html";
	}
}




