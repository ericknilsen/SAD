package com.sad.restful.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AssuntoVO {
	
	private Long id;
	
	private String descricao;
	
	private Boolean checked;
	
	private Long idDisciplina;

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

	public Long getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdDisciplina(Long idDisciplina) {
		this.idDisciplina = idDisciplina;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	
	
	

}
