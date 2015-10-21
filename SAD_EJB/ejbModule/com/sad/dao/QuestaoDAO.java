package com.sad.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.sad.entity.Alternativa;
import com.sad.entity.Assunto;
import com.sad.entity.Avaliacao;
import com.sad.entity.Questao;
import com.sad.entity.RespostaQuestao;

@Stateless
public class QuestaoDAO {
	
	@PersistenceContext
	private EntityManager manager;

		
	public String gravar(Questao questao) {
		
		try {			
			
			if(questao.getId() != null) {
				List<Alternativa> listaAlternativasManaged = new ArrayList<Alternativa>();
				for (Alternativa alternativa : questao.getListaAlternativas()) {
					Alternativa alternativaDetached = manager.find(Alternativa.class, alternativa.getId());			
					Alternativa alternativaManaged = manager.merge(alternativaDetached);	
					alternativaManaged.setSentenca(alternativa.getSentenca());
					alternativaManaged.setJustificativa(alternativa.getJustificativa());
					
					listaAlternativasManaged.add(alternativaManaged);
				}			
				questao.setListaAlternativas(listaAlternativasManaged);			
			}	
									
			Questao questaoManaged = manager.merge(questao);
			
			for (Alternativa alternativa : questaoManaged.getListaAlternativas()) {				
				if(alternativa.getLetra().equals(questao.getGabarito())) {
					questaoManaged.setAlternativaGabarito(alternativa);
				}							
			}
						
			return "Questão gravada.";
		} catch (PersistenceException e) {
			return "Erro ao gravar dados"+e;
		}		
					
	}

	public Questao buscar(Long id) {		
		
		return manager.find(Questao.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Questao> listar() {
		
		return manager.createQuery("SELECT q FROM Questao q").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Questao> buscarQuestoesPorDisciplina(Long idDisciplina) {
						
		return manager.createQuery("SELECT q FROM Questao q WHERE q.disciplina.id="+idDisciplina).getResultList();
	}
	
	
	@SuppressWarnings("unchecked")
	public Collection<Questao> buscarQuestoesPorAssuntos(Questao questao) {		
				
		List<Assunto> listaAssuntos = questao.getListaAssuntos();
		
//		Long idDisciplina = listaAssuntos.get(0).getDisciplina().getId();
//		
//		List<Questao> listaQuestoes = manager.createQuery("SELECT q FROM Questao q WHERE q.disciplina.id="+idDisciplina).getResultList();
		
		List<Questao> listaQuestoes = manager.createQuery("SELECT q FROM Questao q").getResultList();
		
		Collection<Questao> listaQuestoesPorDisciplinaAssuntos = new ArrayList<Questao>();
		for (Questao questaoAtual : listaQuestoes) {		
			boolean achouAssunto = false;
			for (Assunto assuntoQuestao : questaoAtual.getListaAssuntos()) {
				for (Assunto assunto : listaAssuntos) {					
					if(assuntoQuestao.getId().equals(assunto.getId())) {						
						achouAssunto = true;	
					} 
				}
			}
			if(achouAssunto) 				
				listaQuestoesPorDisciplinaAssuntos.add(questaoAtual);				
			
		}				
		
		return listaQuestoesPorDisciplinaAssuntos;
	}		
	
//	@SuppressWarnings("unchecked")
//	public List<Questao> buscarQuestoesPorAvaliacao(Long idAvaliacao) {
//		
//		Avaliacao avaliacao =  manager.find(Avaliacao.class, idAvaliacao);		
//		
//		List<Questao> listaQuestoesPorDisciplina = manager.createQuery("SELECT q FROM Questao q WHERE q.disciplina.id="+avaliacao.getTurma().getDisciplina().getId()).getResultList();
//		
//		List<Questao> listaQuestoesPorAssunto = new ArrayList<Questao>();
//		for (Questao questao : listaQuestoesPorDisciplina) {			
//			int cont = 0;
//			for (Assunto assuntoQuestao : questao.getListaAssuntos()) {				
//				for (Assunto assuntoAvaliacao : avaliacao.getListaAssuntos()) 
//					if(assuntoQuestao.getId().equals(assuntoAvaliacao.getId())) {					
//						++cont;						
//					}				
//			}
//			if(cont == questao.getListaAssuntos().size()) 
//				listaQuestoesPorAssunto.add(questao);
//		}	
//						
//		return listaQuestoesPorAssunto;
//	}	
	
	@SuppressWarnings("unchecked")
	private Boolean existeRespostaAvaliacao(Long idAvaliacao, Long idAluno) {
		
		List<RespostaQuestao> listaRespostaQuestao = manager.createQuery("SELECT rq FROM RespostaQuestao rq WHERE rq.avaliacao.id="+idAvaliacao+" AND rq.aluno.id="+idAluno).getResultList();
		
		return listaRespostaQuestao.size() > 0;
	}
	
	private Questao atualizaQuestao(Long idAvaliacao, Long idAluno, Questao questao) {
		
		RespostaQuestao respostaQuestao = (RespostaQuestao) manager.createQuery("SELECT rq FROM RespostaQuestao rq WHERE rq.avaliacao.id="+idAvaliacao+" AND rq.aluno.id="+idAluno+" AND rq.questao.id="+questao.getId()).getSingleResult();
		questao.setIdAlternativaResposta(respostaQuestao.getAlternativaResposta().getId());
		
		return questao;
	}	

	@SuppressWarnings("unchecked")
	public List<Questao> buscarQuestoesPorAvaliacao(Long idAvaliacao, Long idAluno) {
		
		Avaliacao avaliacao =  manager.find(Avaliacao.class, idAvaliacao);
		List<Questao> listaQuestoes = avaliacao.getListaQuestoes();		
		
		if(this.existeRespostaAvaliacao(idAvaliacao, idAluno)) {
			for (Questao questao : listaQuestoes) {
				questao = this.atualizaQuestao(idAvaliacao, idAluno, questao);
			}
		}
						
		return listaQuestoes;
	}	
	
	

//	@SuppressWarnings("unchecked")
//	public List<Questao> buscarQuestoesPorAvaliacao(Long idAvaliacao) {
//		
//		Avaliacao avaliacao =  manager.find(Avaliacao.class, idAvaliacao);		
//		
//		List<Questao> listaQuestoesPorDisciplina = manager.createQuery("SELECT q FROM Questao q WHERE q.disciplina.id="+avaliacao.getTurma().getDisciplina().getId()).getResultList();
//		List<Questao> listaQuestoesPorAvaliacao = new ArrayList<Questao>();
//		for (Questao questao : listaQuestoesPorDisciplina) {
//			boolean achouAvaliacao = false;
//			for (Avaliacao avaliacaoQuestao : questao.getListaAvaliacoes()) {
//				if(avaliacaoQuestao.getId().equals(idAvaliacao)) {
//					achouAvaliacao = true;
//				}					
//			}
//			if(achouAvaliacao)
//				listaQuestoesPorAvaliacao.add(questao);	
//		}	
//	
//						
//		return listaQuestoesPorAvaliacao;
//	}		
	
	public String remover(Long id) {
		
		try {
			Questao questao = manager.find(Questao.class, id);			
			manager.remove(questao);			
			return "Questao removida.";
		} catch (PersistenceException e) {
			return "Erro ao remover os dados"+e;
		}	
	}

}
