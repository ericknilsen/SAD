package com.sad.restful.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AlunoVO {
	
	private Long id;
	
	private String nome;
	
	private String matricula;
	
	private Long idTurma;

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

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Long getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(Long idTurma) {
		this.idTurma = idTurma;
	}
	
		
	
	

}
