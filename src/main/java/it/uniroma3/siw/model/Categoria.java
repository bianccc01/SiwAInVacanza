package it.uniroma3.siw.model;

import java.util.ArrayList;
import java.util.List;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;  //id
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String descrizione;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Image image;
	
	@OneToMany(mappedBy="categoria")
	private List<Destinazione> destinazioni = new ArrayList<>();

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

	public List<Destinazione> getDestinazioni() {
		return destinazioni;
	}

	public void setDestinazioni(List<Destinazione> destinazioni) {
		this.destinazioni = destinazioni;
	}
	
	public Image getImage() {
		return image;
	}
	
	public String getBase64Image() {
		return this.image.getBase64Image();
	}

	public void setImage(Image image) {
		this.image = image;
		image.setCategoria(this);
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
		Categoria other = (Categoria) obj;
		return Objects.equals(nome, other.nome);
	}
	
	public void addDestinazione(Destinazione destinazione) {
		this.destinazioni.add(destinazione);
	}
	
	
	
}
