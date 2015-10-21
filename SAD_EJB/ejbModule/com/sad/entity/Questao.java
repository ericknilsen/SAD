package com.sad.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.Type;

@Entity
public class Questao implements Serializable {	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue	
	private Long id;
	
	private String enunciado;
	
	@Transient
	private String gabarito;
	
	@Transient
	private Long idAlternativaResposta;
	
	@OneToOne
	@JoinColumn(name="id_alternativa_gabarito")
	private Alternativa alternativaGabarito;
	
	public Alternativa getAlternativaGabarito() {
		return alternativaGabarito;
	}


	public void setAlternativaGabarito(Alternativa alternativaGabarito) {
		this.alternativaGabarito = alternativaGabarito;
	}


	@ManyToOne	
	@JoinColumn(name="id_disciplina", referencedColumnName="id",  nullable=false)
	private Disciplina disciplina;
	
	@OneToMany(mappedBy="questao", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	//@OrderBy("id ASC")
	@OrderBy("letra ASC")
	private List<Alternativa> listaAlternativas;
	
    @ManyToMany(fetch = FetchType.EAGER)    
    @JoinTable(name="questao_assunto",
    	joinColumns=@JoinColumn(name="questao_id"),
    	inverseJoinColumns=@JoinColumn(name="assunto_id"))   
    @CollectionId(columns = @Column(name = "bag_id"), type = @Type(type = "long"), generator = "sequence")  			
	private List<Assunto> listaAssuntos;
    
    
	@ManyToMany(mappedBy="listaQuestoes")	
	private List<Avaliacao> listaAvaliacoes;
		
	
	public Long getId() {
		return id;
	}


	public List<Assunto> getListaAssuntos() {
		return listaAssuntos;
	}


	public void setListaAssuntos(List<Assunto> listaAssuntos) {
		this.listaAssuntos = listaAssuntos;
	}


	public void setId(Long id) {
		this.id = id;
	}

	
	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public List<Alternativa> getListaAlternativas() {
		return listaAlternativas;
	}

	public void setListaAlternativas(List<Alternativa> listaAlternativas) {
		this.listaAlternativas = listaAlternativas;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public List<Avaliacao> getListaAvaliacoes() {
		return listaAvaliacoes;
	}


	public void setListaAvaliacoes(List<Avaliacao> listaAvaliacoes) {
		this.listaAvaliacoes = listaAvaliacoes;
	}


	public String getGabarito() {
		return gabarito;
	}


	public void setGabarito(String gabarito) {
		this.gabarito = gabarito;
	}


	public Long getIdAlternativaResposta() {
		return idAlternativaResposta;
	}


	public void setIdAlternativaResposta(Long idAlternativaResposta) {
		this.idAlternativaResposta = idAlternativaResposta;
	}

	
	

}
