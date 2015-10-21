package com.sad.bean;

import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.sad.bean.service.QuestaoBeanRemote;
import com.sad.dao.QuestaoDAO;
import com.sad.entity.Questao;

/**
 * Session Bean implementation class QuestaoBean
 */
@Stateless
public class QuestaoBean implements QuestaoBeanRemote {

	@EJB
	private QuestaoDAO questaoDAO;
	   
    
	@Override
	public Questao buscarQuestao(Long id) {			
		
		return questaoDAO.buscar(id);			
	}


	@Override
	public String gravar(Questao questao) {
		
		return questaoDAO.gravar(questao);		
	}


	@Override
	public List<Questao> listar() {
		
		return questaoDAO.listar();
	}


	@Override
	public List<Questao> buscarQuestoesPorDisciplina(Long idDisciplina) {
		
		return questaoDAO.buscarQuestoesPorDisciplina(idDisciplina);
	}
	
	@Override
	public List<Questao> buscarQuestoesPorAvaliacao(Long idAvaliacao, Long idAluno) {
		
		return questaoDAO.buscarQuestoesPorAvaliacao(idAvaliacao, idAluno);
	}


	@Override
	public String remover(Long id) {
		
		return questaoDAO.remover(id);
	}


	@Override
	public Collection<Questao> buscarQuestoesPorAssuntos(Questao questao) {
	
		return questaoDAO.buscarQuestoesPorAssuntos(questao);
	}
    
    

}
