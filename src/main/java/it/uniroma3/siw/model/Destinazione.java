package it.uniroma3.siw.model;

import java.util.ArrayList;

import java.util.List;
import java.util.Objects;


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
	
	@OneToMany(mappedBy="destinazione")
	private List<Recensione> recensioni= new ArrayList<>();
	
	@OneToMany(mappedBy="destinazione" ,cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Image> images = new ArrayList<>();
	
	@OneToMany(mappedBy="destinazionePrenotata")
	private List<Prenotazione> prenotazioni = new ArrayList<>();
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Destinazione other = (Destinazione) obj;
		return Objects.equals(nome, other.nome);
	}
	
	public void addImage(Image image) {
		this.images.add(image);
		image.setDestinazione(this);
	}
	
	public List<Image> getImages(){
		return this.images;
	}
	
	public String getFirstImage() {
		return this.images.get(0).getBase64Image();
	}
	
	public Long getFirstImageId() {
		return this.images.get(0).getId();
	}
	
	public List<Recensione> getRecensioni() {
		return recensioni;
	}

	public void setRecensione(List<Recensione> rec) {
		this.recensioni = rec;
	}

	

	public List<Prenotazione> getPrenotazioni() {
		return prenotazioni;
	}

	public void setPrenotazioni(List<Prenotazione> prenotazioni) {
		this.prenotazioni = prenotazioni;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}
	
	

}
