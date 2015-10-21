package com.sad.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.sad.bean.service.AssuntoBeanRemote;
import com.sad.dao.AssuntoDAO;
import com.sad.entity.Assunto;

/**
 * Session Bean implementation class AssuntoBean
 */
@Stateless(mappedName="AssuntoBean")
public class AssuntoBean implements AssuntoBeanRemote {

	@EJB
	private AssuntoDAO assuntoDAO;
	
	@Override
	public String inserir(Assunto assunto) {
			
		return assuntoDAO.inserir(assunto);
	}

	@Override
	public List<Assunto> listar() {
		
		return assuntoDAO.listar();
	}

	@Override
	public String remover(Long id) {
		
		return assuntoDAO.remover(id);
	}

	@Override
	public String alterar(Assunto assunto) {
		
		return assuntoDAO.alterar(assunto);
	}
	
	@Override
	public List<Assunto> buscarAssuntosPorDisciplina(Long idDisciplina) {
		
		return assuntoDAO.buscarAssuntosPorDisciplina(idDisciplina);
		
	}

	@Override
	public List<Assunto> buscarAssuntosPorTurma(Long idTurma) {
		
		//return assuntoDAO.listarAssuntosPorDisciplina(idDisciplina);
		return assuntoDAO.buscarAssuntosPorTurma(idTurma);
	}

}
