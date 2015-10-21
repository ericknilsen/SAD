package com.sad.bean;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.sad.bean.service.AvaliacaoBeanRemote;
import com.sad.dao.AvaliacaoDAO;
import com.sad.dao.QuestaoDAO;
import com.sad.entity.Aluno;
import com.sad.entity.Avaliacao;
import com.sad.entity.Questao;
import com.sad.entity.RespostaQuestao;

/**
 * Session Bean implementation class AvaliacaoBean
 */
@Stateless
public class AvaliacaoBean implements AvaliacaoBeanRemote {

	@EJB
	private AvaliacaoDAO avaliacaoDAO;
	
	@EJB
	private QuestaoDAO questaoDAO;

	@Override
	public String inserir(Avaliacao avaliacao) {
					
		return avaliacaoDAO.inserir(avaliacao);
	}

	@Override
	public List<Avaliacao> buscarAvaliacoesPorTurma(Long idTurma) {
				
		return avaliacaoDAO.buscarAvaliacoesPorTurma(idTurma);
	}
	
	@Override
	public List<Avaliacao> buscarAvaliacoesPorTurmaAluno(Long idTurma, Long idAluno) {
	
		List<Avaliacao> listaAvaliacoes = avaliacaoDAO.buscarAvaliacoesPorTurma(idTurma);
		
		for (Avaliacao avaliacao : listaAvaliacoes) {
			avaliacao = this.atualizaAvaliacao(avaliacao, idAluno);			
		}
		
		return listaAvaliacoes;
	}
	
	private Avaliacao atualizaAvaliacao(Avaliacao avaliacao, Long idAluno) {
		
		avaliacao.setRendimento(this.calculaRendimentoAvaliacao(avaliacao, idAluno));
		
		return avaliacao;
	}
	
	private String calculaRendimentoAvaliacao(Avaliacao avaliacao, Long idAluno) {
		
		List<Questao> listaQuestoes = questaoDAO.buscarQuestoesPorAvaliacao(avaliacao.getId(), idAluno);
		
		int contAcertos = 0;
		for (Questao questao : listaQuestoes) {
			if(questao.getAlternativaGabarito().getId().equals(questao.getIdAlternativaResposta()))
				++contAcertos;
			
		}
		
		Double valor = (double) contAcertos/(double)listaQuestoes.size();
		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.format(valor);
						
		return nf.format(valor);
	}
	

	@Override
	public List<Avaliacao> listar() {
		// TODO Auto-generated method stub
		return avaliacaoDAO.listar();
	}

	@Override
	public String remover(Long id) {
		
		return avaliacaoDAO.remover(id);
	}

	@Override
	public String alterar(Avaliacao avaliacao) {
		
		return avaliacaoDAO.alterar(avaliacao);
	}

	@Override
	public String responde(Collection<RespostaQuestao> listaRespostaAvaliacao) {
		
		return avaliacaoDAO.responde(listaRespostaAvaliacao);
	}

	@Override
	public Avaliacao buscaAvaliacaoAtualPorTurmaAluno(Long idTurma) {
		
		return avaliacaoDAO.buscaAvaliacaoAtualPorTurmaAluno(idTurma);
		
	}

	@Override
	public Avaliacao buscaAvaliacao(Long idAvaliacao) {
		
		return avaliacaoDAO.buscaAvaliacao(idAvaliacao);
	}

}
