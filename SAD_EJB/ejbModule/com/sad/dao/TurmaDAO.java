package com.sad.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.sad.entity.Assunto;
import com.sad.entity.Disciplina;
import com.sad.entity.Questao;
import com.sad.entity.Turma;

@Stateless
public class TurmaDAO {
	
	@PersistenceContext
	private EntityManager manager;

	public String inserir(Turma turma) {
		
		try {
			Disciplina disciplinaDetached = manager.find(Disciplina.class, turma.getDisciplina().getId());
			
			turma.setCodigo(turma.getSemestre()+": "+disciplinaDetached.getCodigo()+" - "+disciplinaDetached.getNome());			
			
			manager.persist(turma);			
			return "Turma inserida.";
		} catch (PersistenceException e) {
			return "Erro ao inserir dados"+e;
		}		
			
	}
	
		
	@SuppressWarnings("unchecked")
	public List<Turma> listar() {
		
		return manager.createQuery("SELECT t FROM Turma t").getResultList();
	}
	
	
	public String remover(Long id) {
				
		try {
			Turma turma = manager.find(Turma.class, id);			
			manager.remove(turma);			
			return "Turma removida.";
		} catch (PersistenceException e) {
			return "Erro ao remover dados"+e;
		}	
	}
	
	public String alterar(Turma turma) {
		
		try {
			Turma turmaDetached = manager.find(Turma.class, turma.getId());
			
			Turma turmaManaged = manager.merge(turmaDetached);
			turmaManaged.setDisciplina(turma.getDisciplina());
			turmaManaged.setSemestre(turma.getSemestre());			
			
			return "Turma alterada.";
		} catch (PersistenceException e) {
			return "Erro ao alterar dados"+e;
		}	
	}	
	

	
	public List<Turma> buscarTurmasPorSemestreDisciplina(Turma turma) {
		
		String query = "SELECT t FROM Turma t WHERE ";
		
		boolean flag = false;
		if(turma.getSemestre() != null) {
			query += "t.semestre='"+turma.getSemestre()+"'";
			flag = true;
		}
		
		if(turma.getDisciplina().getId() != null) {
			if(flag)
				query += " AND ";
			
			query += "t.disciplina.id="+turma.getDisciplina().getId();
		}		
		
		return manager.createQuery(query).getResultList();
	}

}
