package com.sad.restful.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TurmaVO {
	
	private Long id;
	
	private String semestre;
	
	private Long idDisciplina;
	
	private String codigo;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}			
	
	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public Long getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdDisciplina(Long idDisciplina) {
		this.idDisciplina = idDisciplina;
	}

	
	
	

}
