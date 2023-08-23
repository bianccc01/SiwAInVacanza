package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Periodo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private int maxPartecipanti;

	@ManyToMany
	private List<Destinazione> destinazioni;

	@OneToMany(mappedBy="periodo")
	private List<Prenotazione> prenotazione;

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

	public int getMaxPartecipanti() {
		return maxPartecipanti;
	}

	public void setMaxPartecipanti(int maxPartecipanti) {
		this.maxPartecipanti = maxPartecipanti;
	}

	public List<Destinazione> getDestinazioni() {
		return destinazioni;
	}

	public void setDestinazioni(List<Destinazione> destinazioni) {
		this.destinazioni = destinazioni;
	}

	public List<Prenotazione> getPrenotazione() {
		return prenotazione;
	}

	public void setPrenotazione(List<Prenotazione> prenotazione) {
		this.prenotazione = prenotazione;
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

	@Override
	public int hashCode() {
		return Objects.hash(destinazioni, id, maxPartecipanti, partenza, prenotazione, ritorno);
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
		return Objects.equals(destinazioni, other.destinazioni) && Objects.equals(id, other.id)
				&& maxPartecipanti == other.maxPartecipanti && Objects.equals(partenza, other.partenza)
				&& Objects.equals(prenotazione, other.prenotazione) && Objects.equals(ritorno, other.ritorno);
	}
	
	

}
