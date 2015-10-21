package com.sad.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.sad.bean.service.TurmaBeanRemote;
import com.sad.dao.TurmaDAO;
import com.sad.entity.Turma;

/**
 * Session Bean implementation class TurmaBean
 */
@Stateless
public class TurmaBean implements TurmaBeanRemote {

	@EJB
	private TurmaDAO turmaDAO;	
  
	@Override
	public String inserir(Turma turma) {	
		
		return turmaDAO.inserir(turma);		
		
	}

	@Override
	public List<Turma> listar() {
		
		return turmaDAO.listar();
	}

	@Override
	public List<Turma> buscarTurmasPorSemestreDisciplina(Turma turma) {
		
		return turmaDAO.buscarTurmasPorSemestreDisciplina(turma);		
	}

	@Override
	public String remover(Long id) {
		
		return turmaDAO.remover(id);
	}

	@Override
	public String alterar(Turma turma) {
		
		return turmaDAO.alterar(turma);
	}

}
