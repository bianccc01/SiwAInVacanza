package it.uniroma3.siw.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Categoria;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Destinazione;
import it.uniroma3.siw.model.Image;
import it.uniroma3.siw.model.Prenotazione;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.DestinazioneService;
import it.uniroma3.siw.service.PrenotazioneService;
import it.uniroma3.siw.service.UserService;

@Controller
public class PrenotazioneController {
	
	@Autowired
	private PrenotazioneService prenotazioneService;
	
	@Autowired
	private CredentialsService credentialsService;
	
	@Autowired
	private DestinazioneService destinazioneService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/authenticated/formNewPrenotazione/{idDestinazione}")
	public String formNewPrenotazione(@PathVariable ("idDestinazione") Long idDestinazione , Model model) {
		Prenotazione prenotazione = new Prenotazione();
		model.addAttribute("idDestinazione",idDestinazione);
		model.addAttribute("destinazione",this.destinazioneService.findDestinazioneById(idDestinazione));
		model.addAttribute("prenotazione", prenotazione);
		return "authenticated/formNewPrenotazione.html";
	}

	@GetMapping("/authenticated/prenotazioni")
	public String allPrenotazioni(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("prenotazioni",this.prenotazioneService.getPrenotazioneUser(authentication));
		return "authenticated/prenotazioni.html";
	}
	
	@PostMapping("/authenticated/newPrenotazione/{idDestinazione}")
	public String newPrenotazione(@ModelAttribute("prenotazione") Prenotazione prenotazione, @PathVariable("idDestinazione") Long idDestinazione,
			Model model) throws IOException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		prenotazione.getPeriodo().getPrenotazioni().add(prenotazione);
		prenotazioneService.inizializzaPrenotazione(prenotazione, this.userService.getUserAuthentication(authentication), idDestinazione);
		model.addAttribute("prenotazione", prenotazione);
		model.addAttribute("periodo",prenotazione.getPeriodo());
		return "authenticated/riepilogoPrenotazione.html";
	}
	
	
	@GetMapping("/authenticated/eliminaPrenotazione/{idPrenotazione}")
	public String eliminaPrenotazione(@PathVariable("idPrenotazione") Long idPrenotazione, Model model) {
		this.prenotazioneService.eliminaPrenotazione(idPrenotazione);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("prenotazioni",this.prenotazioneService.getPrenotazioneUser(authentication));
		return "authenticated/prenotazioni.html";
		
	}

	

}
