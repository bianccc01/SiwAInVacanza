package it.uniroma3.siw.controller;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;



import javax.validation.Valid;

import it.uniroma3.siw.controller.validation.DestinazioneValidator;
import it.uniroma3.siw.model.Categoria;
import it.uniroma3.siw.model.Destinazione;
import it.uniroma3.siw.model.Image;
import it.uniroma3.siw.model.Periodo;
import it.uniroma3.siw.service.CategoriaService;
import it.uniroma3.siw.service.DestinazioneService;
import it.uniroma3.siw.service.ImageService;
import it.uniroma3.siw.service.PeriodoService;
import it.uniroma3.siw.service.RecensioneService;


@Controller
public class DestinazioneController {

	@Autowired 
	private DestinazioneService destinazioneService;
	@Autowired 
	private DestinazioneValidator destinazioneValidator;
	@Autowired
	private RecensioneService recensioneService;
	
	@Autowired
	private PeriodoService periodoService;

	@Autowired
	private ImageService imageService;

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping("/admin/formNewDestinazione")
	public String formNewDestinazione(Model model) {
		model.addAttribute("destinazione", new Destinazione());
		model.addAttribute("categorie",this.categoriaService.allCategorie());
		return "admin/formNewDestinazione.html";
	}

	@GetMapping("/guest/allDestinazioni")
	public String allDestinazioni(Model model) {
		model.addAttribute("destinazioni",this.destinazioneService.allDestinazioni());
		return "guest/destinazioni.html";
	}
	
	@GetMapping("/admin/allDestinazioni")
	public String allDestinazioniAdmin(Model model) {
		model.addAttribute("destinazioni",this.destinazioneService.allDestinazioni());
		return "admin/adminDestinazioni.html";
	}

	@GetMapping("/guest/destinazioni/{idCategoria}")
	public String allDestinazioni(@PathVariable ("idCategoria") Long idCat, Model model) {
		Categoria categoria = this.categoriaService.findCategoriaById(idCat);
		model.addAttribute("destinazioni",categoria.getDestinazioni());
		model.addAttribute("categoria",categoria);
		return "guest/destinazioni.html";
	}

	@GetMapping("/guest/destinazione/{id}/{idImage}")
	public String destinazione(@PathVariable("id") Long id, @PathVariable("idImage") Long idImage, Model model) {
		Destinazione destinazione = this.destinazioneService.findDestinazioneById(id);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		Image image = this.imageService.getImage(idImage);
		model.addAttribute("recensioniNotUtente", this.recensioneService.getRecensioniNotUtente(authentication,destinazione));
		model.addAttribute("recDueNotUtente", this.recensioneService.getRecensioniDueNotUtente(authentication,destinazione));
		model.addAttribute("recUtente", this.recensioneService.getRecensioneUtente(authentication,destinazione));
		model.addAttribute("image",image);
		model.addAttribute("images",this.destinazioneService.allImagesExcept(destinazione, idImage));
		model.addAttribute("destinazione",destinazione);
		model.addAttribute("categoria",destinazione.getCategoria());
		model.addAttribute("recensioniTre", this.recensioneService.getRecensioniTre(destinazione));
		model.addAttribute("recensioni", this.recensioneService.allRecensioniDestinazione(destinazione));


		return "guest/destinazione.html";
	}


	@PostMapping("/admin/newDestinazione")
	public String newDestinazione(@Valid @ModelAttribute("destinazione") Destinazione destinazione, BindingResult bindingResult,
			@RequestParam("file") MultipartFile[] files, Model model) throws IOException {
		this.destinazioneValidator.validate(destinazione, bindingResult);
		if (!bindingResult.hasErrors()) {
			destinazione.getCategoria().addDestinazione(destinazione);
			this.destinazioneService.saveDestinazione(destinazione);
			this.destinazioneService.newImagesDest(files, destinazione);
			model.addAttribute("destinazioni", this.destinazioneService.allDestinazioni());
			return "admin/adminDestinazioni.html";
		}
		else {
			return "admin/formNewDestinazione";
		}

	}

	@PostMapping("/guest/cercaDestinazioni")
	public String cercaDestinazioni(Model model, @RequestParam String nome) {
		model.addAttribute("destinazioni", this.destinazioneService.searchDestinazioniByNome(nome));
		return "guest/destinazioni.html";
	}

	@GetMapping("/admin/destinazione/{id}/{idImage}")
	public String destinazioneAdmin(@PathVariable("id") Long id, @PathVariable("idImage") Long idImage, Model model) {
		Destinazione destinazione = this.destinazioneService.findDestinazioneById(id);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		Image image = this.imageService.getImage(idImage);
		model.addAttribute("recensioniNotUtente", this.recensioneService.getRecensioniNotUtente(authentication,destinazione));
		model.addAttribute("recUtente", this.recensioneService.getRecensioneUtente(authentication,destinazione));
		model.addAttribute("image",image);
		model.addAttribute("images",this.destinazioneService.allImagesExcept(destinazione, idImage));
		model.addAttribute("destinazione",destinazione);
		model.addAttribute("categoria",destinazione.getCategoria());
		model.addAttribute("recensioni", this.recensioneService.allRecensioniDestinazione(destinazione));


		return "admin/updateDestinazione.html";
	}
	
	@PostMapping("/admin/updateDescrizione/{destId}/{imageId}")
	public String modificaDescrizione(Model model, @RequestParam String descrizione, @PathVariable("destId") Long destId, 
			@PathVariable("imageId") Long imageId) {
		
		Destinazione destinazione = this.destinazioneService.findDestinazioneById(destId);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		destinazione.setDescrizione(descrizione);
		this.destinazioneService.saveDestinazione(destinazione);

		Image image = this.imageService.getImage(imageId);
		model.addAttribute("recensioniNotUtente", this.recensioneService.getRecensioniNotUtente(authentication,destinazione));
		model.addAttribute("recUtente", this.recensioneService.getRecensioneUtente(authentication,destinazione));
		model.addAttribute("image",image);
		model.addAttribute("images",this.destinazioneService.allImagesExcept(destinazione, imageId));
		model.addAttribute("destinazione",destinazione);
		model.addAttribute("categoria",destinazione.getCategoria());
		model.addAttribute("recensioni", this.recensioneService.allRecensioniDestinazione(destinazione));
		
		return "admin/updateDestinazione.html";
	}
	
	
	@GetMapping("/admin/listaPeriodiDestinazione/{idDestinazione}")
	public String getListaPeriodiDestinazione(@PathVariable ("idDestinazione") Long idDest, Model model) {
		Destinazione destinazione = this.destinazioneService.findDestinazioneById(idDest);
		model.addAttribute("periodi",this.periodoService.getPeriodiDaSelezionare(destinazione));
		model.addAttribute("periodiAggiunti",destinazione.getPeriodi());
		model.addAttribute("idDestinazione",idDest);
		return "admin/listaPeriodiDestinazione.html";
	}
	
	@GetMapping("/admin/addPeriodoToDestinazione/{idDestinazione}/{idPeriodo}")
	public String addPeriodiDestinazione(@PathVariable ("idDestinazione") Long idDest, @PathVariable ("idPeriodo") Long idPer, Model model) {
		Destinazione destinazione = this.destinazioneService.findDestinazioneById(idDest);
		this.periodoService.addPeriodoDestinazione(idPer, destinazione);
		model.addAttribute("periodi",this.periodoService.getPeriodiDaSelezionare(destinazione));
		model.addAttribute("periodiAggiunti",destinazione.getPeriodi());
		return "admin/listaPeriodiDestinazione.html";
	}
	
	@GetMapping("/admin/rmvPeriodoToDestinazione/{idDestinazione}/{idPeriodo}")
	public String rmvPeriodiDestinazione(@PathVariable ("idDestinazione") Long idDest, @PathVariable ("idPeriodo") Long idPer, Model model) {
		Destinazione destinazione = this.destinazioneService.findDestinazioneById(idDest);
		this.periodoService.rmvPeriodoDestinazione(idPer, destinazione);
		model.addAttribute("periodi",this.periodoService.getPeriodiDaSelezionare(destinazione));
		model.addAttribute("periodiAggiunti",destinazione.getPeriodi());
		return "admin/listaPeriodiDestinazione.html";
	}
	
	@PostMapping("/admin/updatePrezzoBase/{destId}/{imageId}")
	public String modificaPrezzoBase(Model model, @RequestParam Float prezzoBase,@PathVariable("destId") Long destId, 
			@PathVariable("imageId") Long imageId) {
		
		Destinazione destinazione = this.destinazioneService.findDestinazioneById(destId);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		destinazione.setPrezzoBase(prezzoBase);
		this.destinazioneService.saveDestinazione(destinazione);

		Image image = this.imageService.getImage(imageId);
		model.addAttribute("recensioniNotUtente", this.recensioneService.getRecensioniNotUtente(authentication,destinazione));
		model.addAttribute("recUtente", this.recensioneService.getRecensioneUtente(authentication,destinazione));
		model.addAttribute("image",image);
		model.addAttribute("images",this.destinazioneService.allImagesExcept(destinazione, imageId));
		model.addAttribute("destinazione",destinazione);
		model.addAttribute("categoria",destinazione.getCategoria());
		model.addAttribute("recensioni", this.recensioneService.allRecensioniDestinazione(destinazione));
		
		return "admin/updateDestinazione.html";
	}
}




