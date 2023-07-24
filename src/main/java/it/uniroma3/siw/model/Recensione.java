package it.uniroma3.siw.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Recensione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@NotBlank
	private String titolo;
	
	@NotBlank
	private String testo;
	
	

}
