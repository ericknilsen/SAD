package com.sad.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.sad.bean.service.AlunoBeanRemote;
import com.sad.dao.AlunoDAO;
import com.sad.dao.AssuntoDAO;
import com.sad.entity.Aluno;

/**
 * Session Bean implementation class AlunoBean
 */
@Stateless
public class AlunoBean implements AlunoBeanRemote {

	@EJB
	private AlunoDAO alunoDAO;
	
	@Override
	public String inserir(Aluno aluno) {
		
		return alunoDAO.inserir(aluno);
	}

	@Override
	public List<Aluno> listar() {
		
		return null;
	}

	@Override
	public String remover(Long id) {
		
		return alunoDAO.remover(id);
	}

	@Override
	public String alterar(Aluno aluno) {
		
		return alunoDAO.alterar(aluno);
	}

	@Override
	public List<Aluno> buscarAlunosPorTurma(Long idTurma) {
		
		return alunoDAO.buscarAlunosPorTurma(idTurma);
	}

	@Override
	public Aluno buscarAlunoPorMatricula(String matricula) {

		return alunoDAO.buscarAlunoPorMatricula(matricula);
	}

	@Override
	public Aluno buscar(Long id) {
		
		return alunoDAO.buscar(id);
	}


}
