package com.sad.bean.service;

import java.util.Collection;
import java.util.List;

import javax.ejb.Remote;

import com.sad.entity.Disciplina;
import com.sad.entity.Questao;

@Remote
public interface QuestaoBeanRemote {
	
	public Questao buscarQuestao(Long id);
	
	public String gravar(Questao questao);
	
	public List<Questao> listar();

	public List<Questao> buscarQuestoesPorDisciplina(Long idDisciplina);

	public String remover(Long id);

	public List<Questao> buscarQuestoesPorAvaliacao(Long idAvaliacao, Long idAluno);

	public Collection<Questao> buscarQuestoesPorAssuntos(Questao questao); 	

}
