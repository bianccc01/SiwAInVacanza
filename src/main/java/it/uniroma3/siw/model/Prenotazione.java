package it.uniroma3.siw.model;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Prenotazione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String cognome;
	
	@ManyToOne
	private User user;
	
	private int nPartecipanti;
	
	@ManyToOne
	private Destinazione destinazionePrenotata;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate partenza;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate ritorno;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getnPartecipanti() {
		return nPartecipanti;
	}

	public void setnPartecipanti(int nPartecipanti) {
		this.nPartecipanti = nPartecipanti;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public LocalDate getPartenza() {
		return partenza;
	}

	public void setPartenza(LocalDate partenza) {
		this.partenza = partenza;
	}

	public LocalDate getRitorno() {
		return ritorno;
	}

	public void setRitorno(LocalDate ritorno) {
		this.ritorno = ritorno;
	}

	public Destinazione getDestinazionePrenotata() {
		return destinazionePrenotata;
	}

	public void setDestinazionePrenotata(Destinazione destinazionePrenotata) {
		this.destinazionePrenotata = destinazionePrenotata;
	}
	
	public String getNomeDestinazionePrenotata() {
		return this.destinazionePrenotata.getNome();
	}
	
	
	
	
	
	

}
