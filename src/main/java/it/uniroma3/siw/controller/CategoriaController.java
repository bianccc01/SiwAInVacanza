package it.uniroma3.siw.controller;

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
	
	@GetMapping("/admin/formNewCategoria")
	public String formNewCategoria(Model model) {
		model.addAttribute("categoria", new Categoria());
		return "admin/formNewCategoria.html";
	}
	
	@GetMapping("/categorie")
	public String Getcategorie(Model model) {
		model.addAttribute("categorie",this.categoriaService.allCategorie());
		return "categorie.html";
	}
	
	@PostMapping("/admin/newCategoria")
	public String newCategoria(@Valid @ModelAttribute("categoria") Categoria categ, BindingResult bindingResult, Model model) {
		
		//this.categoriaValidator.validate(categ, bindingResult);
		if (!bindingResult.hasErrors()) {
			this.categoriaService.saveCategoria(categ); 
			model.addAttribute("categoria", categ);
			return "admin/categorie.html";
		} else {
			return "admin/formNewCategoria.html"; 
		}
	}
	
	@GetMapping("/categoria/{id}")
	public String getCategoria(@PathVariable("id") Long id, Model model) {
		model.addAttribute("categoria", this.categoriaService.findCategoriaById(id));
		return "categoria.html";
	}
	
	@GetMapping("/admin/categoria/{id}")
	public String removeCategoria(@PathVariable("id") Long id, Model model) {
		Set<Categoria> categorie=this.categoriaService.allCategorie();
		Categoria categ=this.categoriaService.findCategoriaById(id);
		categorie.remove(categ);
		return "categorie.html";
		
	}
	
	
	
	
	//aggiungi destinazione
	//rimuovi destinazione
	
	

}
