package it.uniroma3.siw.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Prenotazione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@ManyToOne
	private User user;
	
	@NotBlank
	private int nPartecipanti;
	
	

}
