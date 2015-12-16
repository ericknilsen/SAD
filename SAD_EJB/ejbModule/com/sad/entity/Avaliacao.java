package com.sad.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.Type;

import com.sad.util.Constantes;


@Entity
public class Avaliacao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "data_criacao")
	private String dataCriacao;
	
	private Integer status;		

	@ManyToMany(fetch = FetchType.EAGER)    
		@JoinTable(name="avaliacao_questao",
			joinColumns=@JoinColumn(name="avaliacao_id"),
			inverseJoinColumns=@JoinColumn(name="questao_id"))   
		@CollectionId(columns = @Column(name = "bag_id"), type = @Type(type = "long"), generator = "sequence") 
	@OrderBy
	private List<Questao> listaQuestoes;
			
	@ManyToOne
	@JoinColumn(name="id_turma", referencedColumnName="id",  nullable=false)
	private Turma turma;
	
	@Transient
	private String rendimento;
	
		
	public Avaliacao() {				
		this.status = Constantes.STATUS_AVALIACAO_ABERTA;
		this.dataCriacao = new SimpleDateFormat("dd/MM/yyyy").format(new Date()).toString();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}	

	public void setId(Long id) {
		this.id = id;
	}
	
	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}


	public List<Questao> getListaQuestoes() {
		return listaQuestoes;
	}

	public void setListaQuestoes(List<Questao> listaQuestoes) {
		this.listaQuestoes = listaQuestoes;
	}
	
	public String getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(String dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getRendimento() {
		return rendimento;
	}

	public void setRendimento(String rendimento) {
		this.rendimento = rendimento;
	}	



}
