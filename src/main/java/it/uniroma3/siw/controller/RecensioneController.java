package it.uniroma3.siw.controller;



import java.util.ArrayList;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.controller.validation.RecensioneValidator;
import it.uniroma3.siw.model.Destinazione;
import it.uniroma3.siw.model.Recensione;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.DestinazioneService;
import it.uniroma3.siw.service.RecensioneService;
import it.uniroma3.siw.service.UserService;


@Controller
public class RecensioneController {
	
	@Autowired 
	private RecensioneService recensioneService;
	@Autowired 
	private RecensioneValidator recensioneValidator;
	
	@Autowired
	private DestinazioneService destinazioneService;
	
	@Autowired
	private UserService userService;

	@GetMapping("/authenticated/formNewRecensione/{destId}")
	public String formNewRecensione(@PathVariable("destId") Long destId,Model model) {
		Destinazione dest = this.destinazioneService.findDestinazioneById(destId);
		if(dest!=null) {
			model.addAttribute("recensione", new Recensione());
			model.addAttribute("destinazione", dest);
		}
		return "formNewRecensione.html";
	}
	
	@GetMapping("/guest/recensioni/{destId}")
	public String getRecensioni(Model model,@PathVariable("destId") Long destId) {
		Destinazione dest=this.destinazioneService.findDestinazioneById(destId);
		model.addAttribute("recensioni",this.recensioneService.allRecensioniDestinazione(dest));
		model.addAttribute("destinazione", dest);
		return "guest/recensioni.html";
	}

	
	@PostMapping("/authenticated/newRecensione/{destId}/{userId}")
	public String newRecensione(@Valid @ModelAttribute("recensione") Recensione rec, BindingResult bindingResult, 
			@PathVariable("destId") Long destId, @PathVariable("userId") Long userId, Model model) {
		
		Destinazione dest = this.destinazioneService.findDestinazioneById(destId);
		User user = this.userService.getUser(userId);
		if(dest!=null && user!=null) {
			rec.setDestinazione(dest);
			rec.setUtente(user);
			this.recensioneValidator.validate(rec, bindingResult);
			if (!bindingResult.hasErrors()) {
				this.recensioneService.saveRecensione(rec, dest, user);
			}
			else
				return "authenticated/formNewRecensione.html";
		}
		
		return "guest/destinazione.html";
	}

	
	@GetMapping("/guest/recensione/{id}")
	public String getRecensione(@PathVariable("id") Long id, Model model) {
		Recensione rec=this.recensioneService.findRecensioneById(id);
		model.addAttribute("recensione", rec);
		return "guest/recensione.html";
	}
	
	@GetMapping("/admin/rimuoviRecensione/{id}")
	public String removeRecensioneAdmin(@PathVariable("id") Long id, Model model) {
		Set<Recensione> recensioni=this.recensioneService.allRecensioni();
		Recensione rec=this.recensioneService.findRecensioneById(id);
		recensioni.remove(rec);
		return "admin/adminRecensioni.html";
		
	}
	
	@GetMapping("/authenticated/rimuoviRecensione/{recId}/{guestId}")
	public String removeRecensione(@PathVariable("id") Long id,@PathVariable("guestId") Long userId, Model model) {
		Set<Recensione> recensioni=this.recensioneService.allRecensioni();
		Recensione rec=this.recensioneService.findRecensioneById(id);
		if(rec.getUtente()==this.userService.getUser(userId))
			recensioni.remove(rec);
		return "guest/Recensioni.html";
		
	}
	


}
