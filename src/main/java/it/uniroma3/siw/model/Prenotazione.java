package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.Objects;

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

	@NotBlank
	private int nPartecipanti;

	@ManyToOne
	private Destinazione destinazionePrenotata;

	@ManyToOne
	private Periodo periodo;

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


	public Destinazione getDestinazionePrenotata() {
		return destinazionePrenotata;
	}

	public void setDestinazionePrenotata(Destinazione destinazionePrenotata) {
		this.destinazionePrenotata = destinazionePrenotata;
	}

	public String getNomeDestinazionePrenotata() {
		return this.destinazionePrenotata.getNome();
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}
	
	public float getPrezzo(){
		return this.destinazionePrenotata.getPrezzoBase() * this.periodo.getMoltiplicatore() * this.nPartecipanti;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cognome, user, destinazionePrenotata, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prenotazione other = (Prenotazione) obj;
		return Objects.equals(cognome, other.cognome)
				&& Objects.equals(destinazionePrenotata, other.destinazionePrenotata) && Objects.equals(user, other.user)
				&& Objects.equals(nome, other.nome);
	}









}
