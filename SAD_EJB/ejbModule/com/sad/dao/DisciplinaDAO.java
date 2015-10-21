package com.sad.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.sad.entity.Disciplina;

@Stateless
public class DisciplinaDAO {
	
	@PersistenceContext
	private EntityManager manager;

	public String inserir(Disciplina disciplina) {
		
		try {
			manager.persist(disciplina);			
			return "Disciplina "+disciplina.getCodigo()+" - "+disciplina.getNome()+" inserida.";
		} catch (PersistenceException e) {
			return "Erro ao inserir os dados"+e;
		}		
			
	}
	
		
	@SuppressWarnings("unchecked")
	public List<Disciplina> listar() {
		
		return manager.createQuery("SELECT d FROM Disciplina d ORDER BY d.nome").getResultList();
	}
	
	
	public String remover(Long id) {
				
		try {
			Disciplina disciplina = manager.find(Disciplina.class, id);			
			manager.remove(disciplina);			
			return "Disciplina "+disciplina.getCodigo()+" - "+disciplina.getNome()+" removida.";
		} catch (PersistenceException e) {
			return "Erro ao remover os dados"+e;
		}	
	}
	
	public String alterar(Disciplina disciplina) {
		
		try {
			Disciplina disciplinaDetached = manager.find(Disciplina.class, disciplina.getId());
			
			Disciplina disciplinaManaged = manager.merge(disciplinaDetached);					
			disciplinaManaged.setCodigo(disciplina.getCodigo());
			disciplinaManaged.setNome(disciplina.getNome());					
			
			return "Disciplina "+disciplina.getCodigo()+" - "+disciplina.getNome()+" alterada.";
		} catch (PersistenceException e) {
			return "Erro ao remover os dados"+e;
		}	
	}

}
