package it.uniroma3.siw.model;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.persistence.*;
import javax.transaction.Transactional;

@Entity
public class Image {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_entity_seq")
	    @SequenceGenerator(name = "my_entity_seq", sequenceName = "my_entity_sequence", initialValue = 1000)
	    private Long id;
	   
	   private String name;
	   
	   @Lob
	   private byte[] bytes;
	   
	   @Lob
	   private String base64Image;
	   
	   @OneToOne
	   private Categoria categoria;
	   
	   @ManyToOne
	   private Destinazione destinazione;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public byte[] getBytes() {
		return bytes;
	}
	
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	
	@Transactional
	public String getBase64Image() {
		return Base64.getEncoder().encodeToString(this.bytes);
	}
	
	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}
	
	@Transactional
	public String getDataImage() {
		return Base64.getEncoder().encodeToString(this.bytes);
	}


	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Destinazione getDestinazione() {
		return destinazione;
	}

	public void setDestinazione(Destinazione destinazione) {
		this.destinazione = destinazione;
	}
	   
	   
	
	

}
