package com.sad.bean.service;

import java.util.Collection;
import java.util.List;

import javax.ejb.Remote;

import com.sad.entity.Aluno;
import com.sad.entity.Avaliacao;
import com.sad.entity.RespostaQuestao;

@Remote
public interface AvaliacaoBeanRemote {

	String inserir(Avaliacao avaliacao);

	List<Avaliacao> buscarAvaliacoesPorTurmaAluno(Long idTurma, Long idAluno);
	
	List<Avaliacao> buscarAvaliacoesPorTurma(Long idTurma);

	List<Avaliacao> listar();

	String remover(Long id);

	String alterar(Avaliacao avaliacao);

	String responde(Collection<RespostaQuestao> listaRespostaAvaliacao);

	Avaliacao buscaAvaliacaoAtualPorTurmaAluno(Long idTurma);

	Avaliacao buscaAvaliacao(Long idAvaliacao);
	
	
			

}
