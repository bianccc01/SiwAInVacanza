package it.uniroma3.siw.model;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Periodo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToMany
	private List<Destinazione> destinazioni = new ArrayList<>();

	@OneToMany(mappedBy="periodo")
	private List<Prenotazione> prenotazioni = new ArrayList<>();

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate partenza;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate ritorno;
	
	private String stringaPeriodo;
	
	@NotNull
	private float moltiplicatore;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Destinazione> getDestinazioni() {
		return destinazioni;
	}

	public void setDestinazioni(List<Destinazione> destinazioni) {
		this.destinazioni = destinazioni;
	}

	public List<Prenotazione> getPrenotazioni() {
		return prenotazioni;
	}

	public void setPrenotazioni(List<Prenotazione> prenotazione) {
		this.prenotazioni = prenotazione;
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
	
	public float getMoltiplicatore() {
		return moltiplicatore;
	}

	public void setMoltiplicatore(float moltiplicatore) {
		this.moltiplicatore = moltiplicatore;
	}

	@Override
	public int hashCode() {
		return Objects.hash(partenza,ritorno);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Periodo other = (Periodo) obj;
		return Objects.equals(partenza, other.partenza) && Objects.equals(ritorno, other.ritorno);
	}
	

	public String getStringaPeriodo() {
		if(stringaPeriodo==null) {
			return "Ciao, e' null la stringa";
		}
		else return stringaPeriodo;
	}

	public void setStringaPeriodo(String stringaPeriodo) {
		this.stringaPeriodo = stringaPeriodo;
	}
	
	
	
	
	
	
	

}
