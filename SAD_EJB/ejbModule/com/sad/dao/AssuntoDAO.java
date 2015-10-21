package com.sad.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.sad.entity.Assunto;
import com.sad.entity.Disciplina;
import com.sad.entity.Questao;

@Stateless
public class AssuntoDAO {
	
	@PersistenceContext
	private EntityManager manager;

	public String inserir(Assunto assunto) {
		
		try {
			Disciplina disciplinaDetached = manager.find(Disciplina.class, assunto.getDisciplina().getId());
			
			assunto.setDisciplina(disciplinaDetached);			
			manager.persist(assunto);			
			return "Assunto "+assunto.getDescricao()+" inserido.";
		} catch (PersistenceException e) {
			return "Erro ao inserir dados"+e;
		}		
			  
	}
	
		
	@SuppressWarnings("unchecked")
	public List<Assunto> listar() {
		
		return manager.createQuery("SELECT a FROM Assunto a ORDER BY a.descricao").getResultList();
	}
	
	
	public String remover(Long id) {
				
		try {
			Assunto assunto = manager.find(Assunto.class, id);			
			manager.remove(assunto);			
			return "Assunto "+assunto.getDescricao()+" removido.";
		} catch (PersistenceException e) {
			return "Erro ao remover dados"+e;
		}	
	}
	
	public String alterar(Assunto assunto) {
		
		try {
			Assunto assuntoDetached = manager.find(Assunto.class, assunto.getId());
			
			Disciplina disciplinaDetached = manager.find(Disciplina.class, assunto.getDisciplina().getId());
					
			Assunto assuntoManaged = manager.merge(assuntoDetached);		
			assuntoManaged.setDescricao(assunto.getDescricao());
			assuntoManaged.setDisciplina(disciplinaDetached);
			
			return "Assunto "+assunto.getDescricao()+" alterado.";
		} catch (PersistenceException e) {
			return "Erro ao alterar dados"+e;
		}	
	}
	
	public List<Assunto> buscarAssuntosPorDisciplina(Long idDisciplina) {
		
		return manager.createQuery("SELECT a FROM Assunto a WHERE a.disciplina.id="+idDisciplina).getResultList();
	}
	
	public List<Assunto> buscarAssuntosPorTurma(Long idTurma) {
		
		Disciplina disciplina = (Disciplina) manager.createQuery("SELECT d FROM Turma t LEFT JOIN t.disciplina d WHERE t.id="+idTurma).getSingleResult();
		
		return manager.createQuery("SELECT a FROM Assunto a WHERE a.disciplina.id="+disciplina.getId()).getResultList();
	}

}
