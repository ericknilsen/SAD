package com.sad.restful.resources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.sad.bean.service.AvaliacaoBeanRemote;
import com.sad.entity.Alternativa;
import com.sad.entity.Aluno;
import com.sad.entity.Avaliacao;
import com.sad.entity.Questao;
import com.sad.entity.RespostaQuestao;
import com.sad.entity.Turma;
import com.sad.restful.model.AvaliacaoVO;
import com.sad.restful.model.QuestaoVO;

@Stateless
@Path("/avaliacoes")
public class AvaliacaoResource {

	@EJB
	private AvaliacaoBeanRemote avaliacaoBean;
	
	private AvaliacaoVO montaAvaliacaoVO(Avaliacao avaliacao) {
			
		AvaliacaoVO avaliacaoVO = new AvaliacaoVO();
		avaliacaoVO.setId(avaliacao.getId());
		avaliacaoVO.setDataCriacao(avaliacao.getDataCriacao());
		avaliacaoVO.setStatus(avaliacao.getStatus());
								
		List<QuestaoVO> listaQuestoesVO = new ArrayList<QuestaoVO>();
		if(avaliacao.getListaQuestoes() != null) {
			for (Questao questao : avaliacao.getListaQuestoes()) {
				QuestaoVO questaoVO = new QuestaoVO();
				
				questaoVO.setId(questao.getId());
				questaoVO.setEnunciado(questao.getEnunciado());
				
				listaQuestoesVO.add(questaoVO);
			}					
		}
		
		avaliacaoVO.setListaQuestoes(listaQuestoesVO);		
		avaliacaoVO.setRendimento(avaliacao.getRendimento());
								
		return avaliacaoVO;
	}
	 
	private List<AvaliacaoVO> montaListaAvaliacoesVO(List<Avaliacao> listaAvaliacoes) {
		List<AvaliacaoVO> listaAvaliacoesVO = new ArrayList<AvaliacaoVO>();
		
		for (Avaliacao avaliacao : listaAvaliacoes) {						
			listaAvaliacoesVO.add(this.montaAvaliacaoVO(avaliacao));		
		}	
				
		return listaAvaliacoesVO;
	}
	
	private Avaliacao montaAvaliacao(AvaliacaoVO avaliacaoVO) {
		
		Avaliacao avaliacao = new Avaliacao();
		avaliacao.setId(avaliacaoVO.getId());
		if(avaliacaoVO.getStatus() != null)
			avaliacao.setStatus(avaliacaoVO.getStatus());
				
		Turma turma = new Turma();
		turma.setId(avaliacaoVO.getIdTurma());		
		avaliacao.setTurma(turma);
		
		if(avaliacaoVO.getListaQuestoes() != null) {
			List<Questao> listaQuestoes = new ArrayList<Questao>();
			for (QuestaoVO questaoVO : avaliacaoVO.getListaQuestoes()) {
				Questao questao = new Questao();			
				questao.setId(questaoVO.getId());				
				
				listaQuestoes.add(questao);
			}
			avaliacao.setListaQuestoes(listaQuestoes);	
		}
		
		
		return avaliacao;
	}
	
	private Collection<RespostaQuestao> montaRespostaAvaliacao(AvaliacaoVO avaliacaoVO) {
		
		Collection<RespostaQuestao> listaRespostaQuestao = new ArrayList<RespostaQuestao>();
			
		for (QuestaoVO questaoVO : avaliacaoVO.getListaQuestoes()) {
			RespostaQuestao respostaQuestao = new RespostaQuestao();
			
			Avaliacao avaliacao = new Avaliacao();
			avaliacao.setId(avaliacaoVO.getId());;
			respostaQuestao.setAvaliacao(avaliacao);
			
			Aluno aluno = new Aluno();			
			aluno.setId(avaliacaoVO.getIdAluno());
			respostaQuestao.setAluno(aluno);
			
			Questao questao = new Questao();
			questao.setId(questaoVO.getId());
			respostaQuestao.setQuestao(questao);
			
			Alternativa alternativaResposta = new Alternativa();
			alternativaResposta.setId(questaoVO.getIdAlternativaResposta());			
			respostaQuestao.setAlternativaResposta(alternativaResposta);
			
			listaRespostaQuestao.add(respostaQuestao);
		}		
		
		return listaRespostaQuestao;
	}
	
	@POST
	@Consumes("application/json")
	@Produces("text/plain")
	public String adicionaAvaliacao(AvaliacaoVO avaliacaoVO) {		
				
		return avaliacaoBean.inserir(this.montaAvaliacao(avaliacaoVO));		
	}
	
	@Path("t/{idTurma}/{idAluno}")
	@GET
	@Produces("application/json")
	public Collection<AvaliacaoVO> buscaAvaliacoesPorTurmaAluno(@PathParam("idTurma") Long idTurma, @PathParam("idAluno") Long idAluno) {						
						
		return this.montaListaAvaliacoesVO(avaliacaoBean.buscarAvaliacoesPorTurmaAluno(idTurma, idAluno));
	}
	
	
	@Path("t/{idTurma}")
	@GET
	@Produces("application/json")
	public Collection<AvaliacaoVO> buscaAvaliacoesPorTurma(@PathParam("idTurma") Long idTurma) {						
						
		return this.montaListaAvaliacoesVO(avaliacaoBean.buscarAvaliacoesPorTurma(idTurma));
	}

	
	@Path("turma_aluno/{idTurma}")
	@GET
	@Produces("application/json")
	public AvaliacaoVO buscaAvaliacaoAtualPorTurmaAluno(@PathParam("idTurma") Long idTurma) {						
				
		return this.montaAvaliacaoVO(avaliacaoBean.buscaAvaliacaoAtualPorTurmaAluno(idTurma));
	}		
	
	@Path("{idAvaliacao}")
	@GET
	@Produces("application/json")
	public AvaliacaoVO buscaAvaliacao(@PathParam("idAvaliacao") Long idAvaliacao) {						
				
		return this.montaAvaliacaoVO(avaliacaoBean.buscaAvaliacao(idAvaliacao));
	}		
	
	
	@GET
	@Produces("application/json")
	public Collection<AvaliacaoVO> listaAvaliacoes() {		
		
		return this.montaListaAvaliacoesVO(avaliacaoBean.listar());			
		
	}	
		
	@Path("{id}")
	@DELETE	
	@Produces("text/plain")
	public String removeAvaliacao(@PathParam("id") Long id) {		
		
		return avaliacaoBean.remover(id);
	}
	
	
	@PUT
	@Consumes("application/json")
	@Produces("text/plain")
	public String alteraAvaliacao(AvaliacaoVO avaliacaoVO) {
		
		System.out.println("avaliacaoVO.getId() "+avaliacaoVO.getId());				
		return avaliacaoBean.alterar(montaAvaliacao(avaliacaoVO));
	}
	
	@Path("/resposta")
	@PUT
	@Consumes("application/json")
	@Produces("text/plain")
	public String respondeAvaliacao(AvaliacaoVO avaliacaoVO) {		
						
		return avaliacaoBean.responde(montaRespostaAvaliacao(avaliacaoVO));
	}


}
