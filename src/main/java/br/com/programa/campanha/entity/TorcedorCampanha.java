package br.com.programa.campanha.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TORCEDORCAMPANHA")
public class TorcedorCampanha implements Serializable{

	private static final long serialVersionUID = 19766198342L;

	
	@Id
	@Column(name = "ID_TORCEDORCAMPANHA")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull(message = "O Email é obrigatório!")
	@Column(name = "EMAIL")
	private String email;
	
	@NotNull(message = "O Nome do Time é obrigatório!")
	@Column(name = "TIME_CORACAO")
	private String nomeTime;
	
	@NotNull(message = "O Código da Campanha é obrigatório!")
	@Column(name = "ID_CAMPANHA")
	private Long idCampanha;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomeTime() {
		return nomeTime;
	}

	public void setNomeTime(String nomeTime) {
		this.nomeTime = nomeTime;
	}

	public Long getIdCampanha() {
		return idCampanha;
	}

	public void setIdCampanha(Long idCampanha) {
		this.idCampanha = idCampanha;
	}
}
