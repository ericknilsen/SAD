package com.sad.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.sad.entity.Aluno;
import com.sad.entity.Assunto;
import com.sad.entity.Disciplina;
import com.sad.entity.Questao;
import com.sad.entity.Turma;

@Stateless
public class AlunoDAO {
	
	@PersistenceContext
	private EntityManager manager;

	public String inserir(Aluno aluno) {
		
		try {						
			manager.persist(aluno);			
			return "Aluno "+aluno.getMatricula()+" - "+aluno.getNome()+" inserido.";
		} catch (PersistenceException e) {
			return "Erro ao inserir dados"+e;
		}		
			
	}
	
		
	@SuppressWarnings("unchecked")
	public List<Aluno> listar() {
		
		return manager.createQuery("SELECT a FROM Aluno a").getResultList();
	}
	
	
	public String remover(Long id) {
				
		try {
			Aluno aluno = manager.find(Aluno.class, id);			
			manager.remove(aluno);			
			return "Aluno removido.";
		} catch (PersistenceException e) {
			return "Erro ao remover dados"+e;
		}	
	}
	
	public String alterar(Aluno aluno) {
		
		try {
			Aluno alunoDetached = manager.find(Aluno.class, aluno.getId());
			
			Aluno alunoManaged = manager.merge(alunoDetached);
			
			alunoManaged.setMatricula(aluno.getMatricula());
			alunoManaged.setNome(aluno.getNome());
			alunoManaged.setTurma(aluno.getTurma());
			
			return "Aluno alterado.";
		} catch (PersistenceException e) {
			return "Erro ao alterar dados"+e;
		}	
	}	
	

	
	public List<Aluno> buscarAlunosPorTurma(Long idTurma) {
		
		String query = "SELECT a FROM Aluno a WHERE a.turma.id="+idTurma+"ORDER BY a.nome";		
				
		return manager.createQuery(query).getResultList();
	}


	public Aluno buscarAlunoPorMatricula(String matricula) {
		String query = "SELECT a FROM Aluno a WHERE a.matricula='"+matricula+"'";		
		
		return (Aluno) manager.createQuery(query).getSingleResult();
	}


	public Aluno buscar(Long id) {
		
		return manager.find(Aluno.class, id);
	}

}
