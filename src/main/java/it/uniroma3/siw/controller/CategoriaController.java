package it.uniroma3.siw.controller;

import java.io.IOException;

import java.util.List;
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

import it.uniroma3.siw.controller.validation.CategoriaValidator;
import it.uniroma3.siw.model.Categoria;
import it.uniroma3.siw.model.Destinazione;
import it.uniroma3.siw.service.CategoriaService;
import it.uniroma3.siw.service.DestinazioneService;

@Controller
public class CategoriaController {
	
	@Autowired 
	private CategoriaService categoriaService;
	
	@Autowired
	private CategoriaValidator categoriaValidator;
	
	@Autowired
	private DestinazioneService destinazioneService;
	
	
	@GetMapping("/admin/formNewCategoria")
	public String formNewCategoria(Model model) {
		model.addAttribute("categoria", new Categoria());
		return "admin/formNewCategoria.html";
	}
	
	@GetMapping("/guest/categorie")
	public String Getcategorie(Model model) {
		model.addAttribute("categorie",this.categoriaService.allCategorie());
		return "guest/categorie.html";
	}

	
	@PostMapping("/admin/newCategoria")
	public String newCategoria(@Valid @ModelAttribute("categoria") Categoria categoria, BindingResult bindingResult, 
			@RequestParam("file") MultipartFile file, Model model) throws IOException {
		
		this.categoriaValidator.validate(categoria, bindingResult);
		
		if (!bindingResult.hasErrors()) {
			this.categoriaService.saveCategoria(categoria);
			this.categoriaService.newImagesCat(file, categoria);

			model.addAttribute("categorie", this.categoriaService.allCategorie());
			return "admin/adminCategorie.html";
		}
		else {
			return "/admin/formNewCategoria";
		}
	}

	
	@GetMapping("/guest/categoria/{id}")
	public String getCategoria(@PathVariable("id") Long id, Model model) {
		Categoria categ=this.categoriaService.findCategoriaById(id);
		model.addAttribute("categoria", categ);
		return "categoria.html";
	}
	
	@GetMapping("/admin/updateCategoria/{id}")
	public String updateCategoria(@PathVariable("id") Long id, Model model) {

		List<Destinazione> notDestinazioni = this.destinazioneService.findDestinazioniNotInCategoria();
		model.addAttribute("notDestinazioni", notDestinazioni);
		model.addAttribute("categoria", this.categoriaService.findCategoriaById(id));

		return "admin/adminCategoria.html";
	}
	
	@GetMapping("/admin/rimuoviCategoria/{id}")
	public String removeCategoria(@PathVariable("id") Long id, Model model) {
		Categoria categ=this.categoriaService.findCategoriaById(id);
		this.categoriaService.deleteCategoria(categ);
		model.addAttribute("categorie",this.categoriaService.allCategorie());
		return "admin/adminCategorie.html";
		
	}
	
	@GetMapping("/admin/categorie")
	public String getCategorieAdmin(Model model) {
		model.addAttribute("categorie",this.categoriaService.allCategorie());
		return "admin/adminCategorie.html";
	}
	
	@PostMapping(value="/admin/addDestinazioneToCategoria/{categoriaId}")
	public String addDestinazioneToCategoria(@PathVariable("categoriaId") Long categoriaId,@RequestParam String nome, Model model) {
		Categoria categ = this.categoriaService.findCategoriaById(categoriaId);
		Destinazione dest = this.destinazioneService.findDestinazioneByNome(nome);
		List<Destinazione> destinazioni = categ.getDestinazioni();
		if(dest!=null) {
		if(!destinazioni.contains(dest)) {
			destinazioni.add(dest);
			dest.setCategoria(categ);
			this.categoriaService.saveCategoria(categ);
			this.destinazioneService.saveDestinazione(dest);
		}
		
		List<Destinazione> notDestinazioni = this.destinazioneService.findDestinazioniNotInCategoria(); 
		
		model.addAttribute("categoria", categ);
		model.addAttribute("notDestinazioni", notDestinazioni);

		return "admin/adminCategoria.html";
		}
		
		else {
			
			return "redirect:/admin/updateCategoria/"+ categoriaId;
		}
	}
	
	@PostMapping(value="/admin/removeDestinazioneToCategoria/{categoriaId}")
	public String removeDestinazioneToCategoria(@PathVariable("categoriaId") Long categoriaId,@RequestParam String nome, Model model) {
		Categoria categ = this.categoriaService.findCategoriaById(categoriaId);
		Destinazione dest = this.destinazioneService.findDestinazioneByNome(nome);
		if(dest!=null) {
		List<Destinazione> destinazioni = categ.getDestinazioni();
		if(destinazioni.contains(dest)) {
			destinazioni.remove(dest);
			dest.setCategoria(null);
			this.categoriaService.saveCategoria(categ);
			this.destinazioneService.saveDestinazione(dest);
		}
		
		List<Destinazione> notDestinazioni = this.destinazioneService.findDestinazioniNotInCategoria();
		
		model.addAttribute("categoria", categ);
		model.addAttribute("notDestinazioni", notDestinazioni);

		return "admin/adminCategoria.html";
		}
		else {
			
			return "redirect:/admin/updateCategoria/"+ categoriaId;
		}
	}
	
	
	

}
