package it.uniroma3.siw.model;

import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Recensione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@NotBlank
	private String titolo;
	
	private String testo;
	
	@NotNull
	@Min(1)
	@Max(5)
	private int voto;
	
	@ManyToOne
	private User utente;
	
    @ManyToOne
    private Destinazione destinazione;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public int getVoto() {
		return voto;
	}

	public void setVoto(int voto) {
		this.voto = voto;
	}

	public User getUtente() {
		return utente;
	}

	public void setUtente(User user) {
		this.utente = user;
	}
	
	public Destinazione getDestinazione() {
		return destinazione;
	}

	public void setDestinazione(Destinazione dest) {
		this.destinazione = dest;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(utente, destinazione);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recensione other = (Recensione) obj;
		return Objects.equals(utente, other.utente)&&Objects.equals(destinazione, other.destinazione);
	}

}
