package com.sad.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.sad.bean.service.DisciplinaBeanRemote;
import com.sad.dao.DisciplinaDAO;
import com.sad.entity.Disciplina;

/**
 * Session Bean implementation class DisciplinaBean
 */
@Stateless
public class DisciplinaBean implements DisciplinaBeanRemote {

	@EJB
	private DisciplinaDAO disciplinaDAO;
	
	@Override
	public String inserir(Disciplina disciplina) {
		
		return disciplinaDAO.inserir(disciplina);
		
	}

	@Override
	public List<Disciplina> listar() {		
		return disciplinaDAO.listar();
	}

	@Override
	public String remover(Long id) {

		return disciplinaDAO.remover(id);
	}

	@Override
	public String alterar(Disciplina disciplina) {
		
		return disciplinaDAO.alterar(disciplina);		
	}

}
