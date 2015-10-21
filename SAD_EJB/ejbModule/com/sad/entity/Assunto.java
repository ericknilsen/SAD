package com.sad.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
public class Assunto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
		
	private String descricao;
		
	@ManyToOne
	@JoinColumn(name="id_disciplina", referencedColumnName="id",  nullable=false)
	private Disciplina disciplina;
	
	@ManyToMany(mappedBy="listaAssuntos")	
	private List<Questao> listaQuestoes;
	
//	@ManyToMany(mappedBy="listaAssuntos")	
//	private List<Avaliacao> listaAvaliacoes;

	public List<Questao> getListaQuestoes() {
		return listaQuestoes;
	}

	public void setListaQuestoes(List<Questao> listaQuestoes) {
		this.listaQuestoes = listaQuestoes;
	}	
	
//	public List<Avaliacao> getListaAvaliacoes() {
//		return listaAvaliacoes;
//	}
//
//	public void setListaAvaliacoes(List<Avaliacao> listaAvaliacoes) {
//		this.listaAvaliacoes = listaAvaliacoes;
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	
}
