package br.com.ecoffee.model.marca;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Marca {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(columnDefinition = "serial")
	private Long idMarca;

	private String marca;

	public Long getIdMarca() {
		return idMarca;
	}

	public String getMarca() {
		return marca;
	}

}