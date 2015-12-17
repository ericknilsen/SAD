package com.sad.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.sad.entity.Aluno;
import com.sad.entity.Avaliacao;
import com.sad.entity.RespostaQuestao;

@Stateless
public class AvaliacaoDAO {
	
	@PersistenceContext
	private EntityManager manager;

	public String inserir(Avaliacao avaliacao) {
		
		try {						
			manager.persist(avaliacao);			
			return "Avaliacao inserida.";
		} catch (PersistenceException e) {
			return "Erro ao inserir dados"+e;
		}		
			
	}
	
		
	@SuppressWarnings("unchecked")
	public List<Avaliacao> listar() {
		
		return manager.createQuery("SELECT a FROM Avaliacao a").getResultList();
	}
	
	
	public String remover(Long id) {
				
		try {
			Avaliacao avaliacao = manager.find(Avaliacao.class, id);		
			manager.remove(avaliacao);			
			return "Avaliação removida.";
		} catch (PersistenceException e) {
			return "Erro ao remover dados"+e;
		}	
	}
	
	public String alterar(Avaliacao avaliacao) {
		
		try {
			
			Avaliacao avaliacaoDetached = manager.find(Avaliacao.class, avaliacao.getId());
			avaliacao.setTurma(avaliacaoDetached.getTurma());
			avaliacao.setListaQuestoes(avaliacaoDetached.getListaQuestoes());
			avaliacao.setDataCriacao(avaliacaoDetached.getDataCriacao());
			
			manager.merge(avaliacao);
			
			return "Avaliacao alterada.";
		} catch (PersistenceException e) {
			return "Erro ao alterar dados"+e;
		}	
	}	
	

	
	public List<Avaliacao> buscarAvaliacoesPorTurma(Long idTurma) {
		
		String query = "SELECT a FROM Avaliacao a WHERE a.turma.id="+idTurma;		
				
		return manager.createQuery(query).getResultList();
	}


	public String responde(Collection<RespostaQuestao> listaRespostaAvaliacao) {
		
		try {
			for (RespostaQuestao respostaQuestao : listaRespostaAvaliacao) {				
				manager.persist(respostaQuestao);
			}		
			
			return "Avaliacao respondida.";
		} catch (PersistenceException e) {
			return "Erro ao alterar dados"+e;
		}	
	}
	
//	public List<RespostaQuestao> buscaRespostasQuestoesPorAvaliacaoAluno(Long idAvaliacao, Long idAluno) {
//		
//		String query = "SELECT rq FROM RespostaQuestao rq WHERE rq.avaliacao.id="+idAvaliacao+" AND rq.aluno.id="+idAluno;
//		
//		ArrayList<RespostaQuestao> listaRespostasQuestoes = (ArrayList<RespostaQuestao>) manager.createQuery(query).getResultList();
//		
//		return listaRespostasQuestoes;
//	}


	public Avaliacao buscaAvaliacaoAtualPorTurmaAluno(Long idTurma) {
		String query = "SELECT a FROM Avaliacao a WHERE a.turma.id="+idTurma+" ORDER BY a.dataCriacao DESC";					
		
				
		ArrayList<Avaliacao> listaAvaliacoes = (ArrayList<Avaliacao>) manager.createQuery(query).getResultList();
		
		Avaliacao avaliacao = new Avaliacao();
		if(listaAvaliacoes.size() > 0)
			avaliacao =  listaAvaliacoes.get(0);
				
		return avaliacao;		
		
	}


	public Avaliacao buscaAvaliacao(Long idAvaliacao) {
						
		return manager.find(Avaliacao.class, idAvaliacao);
		
	}


	public List<Avaliacao> buscarRendimentoMedioAvaliacaoPorTurma(
			Long idTurma) {
		
		String query = "SELECT a FROM Avaliacao a WHERE a.turma.id="+idTurma;		
		
		return manager.createQuery(query).getResultList();
	}
	
	

}
