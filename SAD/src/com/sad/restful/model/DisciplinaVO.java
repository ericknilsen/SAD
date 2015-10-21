package com.sad.restful.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DisciplinaVO {
	
	private Long id;
	
	private String codigo;
	
	private String nome;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	

}
