package com.sad.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.Type;


@Entity
@Table(name = "resposta_questao")
public class RespostaQuestao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne	
	@JoinColumn(name="id_avaliacao", referencedColumnName="id",  nullable=false)
	private Avaliacao avaliacao;
	
	@ManyToOne	
	@JoinColumn(name="id_aluno", referencedColumnName="id",  nullable=false)
	private Aluno aluno;
	
	@ManyToOne	
	@JoinColumn(name="id_questao", referencedColumnName="id",  nullable=false)
	private Questao questao;
	
	@ManyToOne	
	@JoinColumn(name="id_alternativa", referencedColumnName="id",  nullable=false)
	private Alternativa alternativaResposta;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

	public Alternativa getAlternativaResposta() {
		return alternativaResposta;
	}

	public void setAlternativaResposta(Alternativa alternativaResposta) {
		this.alternativaResposta = alternativaResposta;
	}
		

			



}
