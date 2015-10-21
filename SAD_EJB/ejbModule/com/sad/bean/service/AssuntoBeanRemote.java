package com.sad.bean.service;

import java.util.List;

import javax.ejb.Remote;

import com.sad.entity.Assunto;

@Remote
public interface AssuntoBeanRemote {
	
	public String inserir(Assunto assunto);
	
	public List<Assunto> listar(); 
	
	public String remover(Long id);
	
	public String alterar(Assunto assunto);

	public List<Assunto> buscarAssuntosPorTurma(Long idTurma);

	public List<Assunto> buscarAssuntosPorDisciplina(Long idDisciplina);
	

}
