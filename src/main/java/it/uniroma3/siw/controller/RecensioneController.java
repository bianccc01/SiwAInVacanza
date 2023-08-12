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
		model.addAttribute("recensioni",this.recensioneService.allRecensioni());
		model.addAttribute("destinazioni",this.destinazioneService.allDestinazioni());
		return "guest/recensioni.html";
	}

	
	@PostMapping("/authenticated/newRecensione/{destId}/{userId}")
	public String newRecensione(@Valid @ModelAttribute("recensione") Recensione rec, BindingResult bindingResult, @PathVariable("destId") Long destId, @PathVariable("userId") Long userId, Model model) {
		Destinazione dest = this.destinazioneService.findDestinazioneById(destId);
		User user = this.userService.getUser(userId);
		if(dest!=null && user!=null) {
			rec.setDestinazione(dest);
			rec.setUser(user);
			//this.reviewValidator.validate(review, bindingResult);
			//if (!bindingResult.hasErrors()) {
				this.recensioneService.saveRecensione(rec, dest, user);
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
		if(rec.getUser()==this.userService.getUser(userId))
			recensioni.remove(rec);
		return "guest/Recensioni.html";
		
	}
	
	/*@GetMapping("/admin/categorie")
	public String getCategorieAdmin(Model model) {
		model.addAttribute("categorie",this.categoriaService.allCategorie());
		return "admin/adminCategorie.html";
	}*/
	
	/*@GetMapping(value="/admin/addDestinazioneToCategoria/{categoriaId}")
	public String addDestinazioneToCategoria(@PathVariable("categoriaId") Long categoriaId,@RequestParam String nomeDest, Model model) {
		Categoria categ = this.categoriaService.findCategoriaById(categoriaId);
		Destinazione dest = this.destinazioneService.searchDestinazioneByNome(nomeDest).get();
		List<Destinazione> destinazioni = categ.getDestinazioni();
		destinazioni.add(dest);
		this.destinazioneService.saveDestinazione(categ);
		
		List<Destinazione> notDestinazioni = destinazioniNotCategoria(categoriaId);  //come actorsToAdd
		
		model.addAttribute("categoria", categ);
		model.addAttribute("notDestinazioni", notDestinazioni);

		return "admin/adminCategoria.html";
	}
	
	@GetMapping(value="/admin/removeDestinazioneToCategoria/{categoriaId}")
	public String removeDestinazioneToCategoria(@PathVariable("categoriaId") Long categoriaId,@RequestParam String nomeDest, Model model) {
		Categoria categ = this.categoriaService.findCategoriaById(categoriaId);
		Destinazione dest = this.destinazioneService.searchDestinazioneByNome(nomeDest).get();
		List<Destinazione> destinazioni = categ.getDestinazioni();
		destinazioni.remove(dest);
		this.destinazioneService.saveDestinazione(categ);
		
		List<Destinazione> notDestinazioni = destinazioniNotCategoria(categoriaId);  
		
		model.addAttribute("categoria", categ);
		model.addAttribute("notDestinazioni", notDestinazioni);

		return "admin/adminCategoria.html";
	}
	
	private List<Destinazione> destinazioniNotCategoria(Long categoriaId) {
		List<Destinazione> notDestinazioni = new ArrayList<>();

		for (Destinazione a : destinazioneService.findDestinazioniNotInCategoria(categoriaId)) {
			notDestinazioni.add(a);
		}
		return notDestinazioni;
	}*/
	
	

}
