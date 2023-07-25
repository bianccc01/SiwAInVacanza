package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.model.Categoria;
import it.uniroma3.siw.model.Destinazione;
import it.uniroma3.siw.service.CategoriaService;
import it.uniroma3.siw.service.DestinazioneService;

public class CategoriaController {
	
	@Autowired 
	private CategoriaService categoriaService;
	
	@GetMapping("/")
	public String index() {
		return "index.html";
	}
	
	@GetMapping("/formNewDestinazione")
	public String formNewDestinazione(Model model) {
		model.addAttribute("categoria", new Categoria());
		return "formNewCategoria.html";
	}
	
	@GetMapping("/categorie")
	public String Getcategorie(Model model) {
		model.addAttribute("categorie",this.categoriaService.allCategorie());
		return "listaCategorie.html";
	}
	
	

}
