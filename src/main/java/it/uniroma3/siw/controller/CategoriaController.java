package it.uniroma3.siw.controller;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Categoria;
import it.uniroma3.siw.service.CategoriaService;

@Controller
public class CategoriaController {
	
	@Autowired 
	private CategoriaService categoriaService;
	@Autowired
	//private CategoriaValidator categoriaValidator;
	
	@GetMapping("/")
	public String index() {
		return "index.html";
	}
	
	@GetMapping("/formNewCategoria")
	public String formNewCategoria(Model model) {
		model.addAttribute("categoria", new Categoria());
		return "formNewCategoria.html";
	}
	
	@GetMapping("/categorie")
	public String Getcategorie(Model model) {
		model.addAttribute("categorie",this.categoriaService.allCategorie());
		return "categorie.html";
	}
	
	@PostMapping("/newCategoria")
	public String newCategoria(@Valid @ModelAttribute("categoria") Categoria categ, BindingResult bindingResult, Model model) {
		
		//this.categoriaValidator.validate(categ, bindingResult);
		if (!bindingResult.hasErrors()) {
			this.categoriaService.saveCategoria(categ); 
			model.addAttribute("categoria", categ);
			return "index.html";
		} else {
			return "/formNewCategoria.html"; 
		}
	}
	
	
	
	
	//rimuovi categoria
	//aggiungi destinazione
	//rimuovi destinazione
	
	

}
