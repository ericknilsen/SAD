package com.sad.restful.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class QuestaoVO {
	
	private Long id;
	
	private String enunciado;
		
	private String gabarito;
	
	private Long idDisciplina;
	
	private List<AlternativaVO> listaAlternativas;
	
	private List<AssuntoVO> listaAssuntos;
	
	private Long idAlternativaResposta;
		
	public List<AssuntoVO> getListaAssuntos() {
		return listaAssuntos;
	}
	public void setListaAssuntos(List<AssuntoVO> listaAssuntos) {
		this.listaAssuntos = listaAssuntos;
	}
	public Long getId() {
		return id;
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
	public List<AlternativaVO> getListaAlternativas() {
		return listaAlternativas;
	}
	public void setListaAlternativas(List<AlternativaVO> listaAlternativas) {
		this.listaAlternativas = listaAlternativas;
	}
	public Long getIdDisciplina() {
		return idDisciplina;
	}
	public void setIdDisciplina(Long idDisciplina) {
		this.idDisciplina = idDisciplina;
	}
	public Long getIdAlternativaResposta() {
		return idAlternativaResposta;
	}
	public void setIdAlternativaResposta(Long idAlternativaResposta) {
		this.idAlternativaResposta = idAlternativaResposta;
	}
	public String getGabarito() {
		return gabarito;
	}
	public void setGabarito(String gabarito) {
		this.gabarito = gabarito;
	}


}