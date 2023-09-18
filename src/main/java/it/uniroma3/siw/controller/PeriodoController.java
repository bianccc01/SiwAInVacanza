package it.uniroma3.siw.controller;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.controller.validation.PeriodoValidator;
import it.uniroma3.siw.model.Periodo;
import it.uniroma3.siw.service.DestinazioneService;
import it.uniroma3.siw.service.PeriodoService;

@Controller
public class PeriodoController {

	@Autowired
	private PeriodoService periodoService;
	
	@Autowired
	private DestinazioneService destinazioneService;
	
	@Autowired
	private PeriodoValidator periodoValidator;

	@GetMapping("/admin/formNewPeriodo")
	public String formNewPeriodo(Model model) {
		model.addAttribute("periodo", new Periodo());
		return "admin/formNewPeriodo.html";
	}

	@PostMapping("/admin/newPeriodo")
	public String newPeriodo(@ModelAttribute("periodo") Periodo periodo,BindingResult bindingResult, Model model) throws IOException{
		this.periodoValidator.validate(periodo, bindingResult);
		if (!bindingResult.hasErrors()) {
			this.periodoService.savePeriodo(periodo);
			model.addAttribute("destinazioni",this.destinazioneService.allDestinazioni());
			return "admin/adminDestinazioni.html";
		}
		else
			return "/admin/formNewPeriodo";
	}






}
