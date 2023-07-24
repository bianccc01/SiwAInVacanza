package it.uniroma3.siw.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Destinazione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String descrizione;
	
	@ManyToOne
	private Categoria categoria;
	

}
