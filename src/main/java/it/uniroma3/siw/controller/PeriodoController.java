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

import it.uniroma3.siw.model.Periodo;
import it.uniroma3.siw.service.DestinazioneService;
import it.uniroma3.siw.service.PeriodoService;

@Controller
public class PeriodoController {

	@Autowired
	private PeriodoService periodoService;
	
	@Autowired
	private DestinazioneService destinazioneService;

	@GetMapping("/admin/formNewPeriodo")
	public String formNewPeriodo(Model model) {
		model.addAttribute("periodo", new Periodo());
		return "admin/formNewPeriodo.html";
	}

	@PostMapping("/admin/newPeriodo")
	public String newPeriodo(@ModelAttribute("periodo") Periodo periodo, Model model){
		this.periodoService.savePeriodo(periodo);
		model.addAttribute("destinazioni",this.destinazioneService.allDestinazioni());
		return "admin/adminDestinazioni.html";
	}






}
