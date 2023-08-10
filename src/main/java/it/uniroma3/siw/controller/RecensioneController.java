package it.uniroma3.siw.controller;

import java.io.IOException;

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
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Recensione;
import it.uniroma3.siw.service.RecensioneService;


@Controller
public class RecensioneController {
	
	@Autowired 
	private RecensioneService recensioneService;

	@GetMapping("/authenticated/formNewRecensione")
	public String formNewRecensione(Model model) {
		model.addAttribute("recensione", new Recensione());
		return "formNewRecensione.html";
	}
	
	@GetMapping("/guest/recensioni")
	public String getRecensioni(Model model) {
		model.addAttribute("recensioni",this.recensioneService.allRecensioni());
		return "guest/recensioni.html";
	}

	
	@PostMapping("/authenticated/newRecensione")
	public String newRecensione(@ModelAttribute("recensione") Recensione recensione, 
			@RequestParam("file") MultipartFile file, Model model) throws IOException {

		this.recensioneService.saveRecensione(recensione);
		//this.recensioneService.newImagesCat(file, recensione);

		model.addAttribute("recensioni", this.recensioneService.allRecensioni());
		return "guest/recensioni.html";
	}

	
	@GetMapping("/guest/recensione/{id}")
	public String getRecensione(@PathVariable("id") Long id, Model model) {
		Recensione rec=this.recensioneService.findRecensioneById(id);
		model.addAttribute("recensione", rec);
		return "guest/recensione.html";
	}
	
	/*@GetMapping("/admin/updateCategoria/{id}")
	public String updateActors(@PathVariable("id") Long id, Model model) {

		//List<Destinazione> notDestinazioni = this.destinazioniNotCategoria(id);
		//model.addAttribute("notDestinazioni", notDestinazioni);
		model.addAttribute("categoria", this.categoriaService.findCategoriaById(id));

		return "admin/adminCategoria.html";
	}*/
	
	@GetMapping("/admin/rimuoviRecensione/{id}")
	public String removeRecensione(@PathVariable("id") Long id, Model model) {
		Set<Recensione> recensioni=this.recensioneService.allRecensioni();
		Recensione rec=this.recensioneService.findRecensioneById(id);
		recensioni.remove(rec);
		return "admin/adminCategorie.html";
		
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
