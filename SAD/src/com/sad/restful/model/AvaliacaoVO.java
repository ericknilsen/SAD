package com.sad.restful.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AvaliacaoVO {
	
	private Long id;
	
	private String dataCriacao;
	
	private Integer status;
	
	private List<QuestaoVO> listaQuestoes;
	
	private Long idTurma;	
	
	private Long idAluno;	

	private String rendimento;


	public String getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(String dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
			
	public List<QuestaoVO> getListaQuestoes() {
		return listaQuestoes;
	}

	public void setListaQuestoes(List<QuestaoVO> listaQuestoes) {
		this.listaQuestoes = listaQuestoes;
	}

	public Long getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(Long idTurma) {
		this.idTurma = idTurma;
	}

	public Long getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Long idAluno) {
		this.idAluno = idAluno;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRendimento() {
		return rendimento;
	}

	public void setRendimento(String rendimento) {
		this.rendimento = rendimento;
	}


	
	

}
