package br.com.programa.campanha.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CAMPANHA")
public class Campanha implements Serializable{

	private static final long serialVersionUID = 1976619413370688342L;
	
	@Id
	@Column(name = "ID_CAMPANHA")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull(message = "O Nome da Campanha é obrigatório!")
	@Column(name = "NOME_CAMPANHA")
	private String nomeCampanha;
	
	@NotNull(message = "O Código do Time é obrigatório!")
	@Column(name = "ID_TIME_CORACAO")
	private Long idTimeCoracao;
	
	@NotNull(message = "A Data de Início é obrigatória!")
	@Column(name = "DATA_INICIO")
	private Calendar dataInicio;
	
	@NotNull(message = "A Data de Fim é obrigatória!")
	@Column(name = "DATA_FIM")
	private Calendar dataFim;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCampanha() {
		return nomeCampanha;
	}

	public void setNomeCampanha(String nomeCampanha) {
		this.nomeCampanha = nomeCampanha;
	}

	public Long getIdTimeCoracao() {
		return idTimeCoracao;
	}

	public void setIdTimeCoracao(Long idTimeCoracao) {
		this.idTimeCoracao = idTimeCoracao;
	}

	public Calendar getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Calendar getDataFim() {
		return dataFim;
	}

	public void setDataFim(Calendar dataFim) {
		this.dataFim = dataFim;
	}
}
