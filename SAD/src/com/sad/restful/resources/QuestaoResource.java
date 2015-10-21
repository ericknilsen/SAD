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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.sad.bean.service.QuestaoBeanRemote;
import com.sad.entity.Alternativa;
import com.sad.entity.Assunto;
import com.sad.entity.Disciplina;
import com.sad.entity.Questao;
import com.sad.restful.model.AlternativaVO;
import com.sad.restful.model.AssuntoVO;
import com.sad.restful.model.AvaliacaoVO;
import com.sad.restful.model.QuestaoVO;

@Stateless
@Path("/questoes")
public class QuestaoResource {

	@EJB
	private QuestaoBeanRemote questaoBean;
	
	private QuestaoVO montarQuestaoVO(Questao questao) {
		
		QuestaoVO questaoVO = new QuestaoVO();
		
		questaoVO.setEnunciado(questao.getEnunciado());		
		questaoVO.setGabarito(questao.getAlternativaGabarito().getLetra());
		questaoVO.setIdAlternativaResposta(questao.getIdAlternativaResposta());
						
		questaoVO.setId(questao.getId());
		questaoVO.setIdDisciplina(questao.getDisciplina().getId());
		
		List<Alternativa> listaAlternativas = questao.getListaAlternativas();
		List<AlternativaVO> listaAlternativasVO = new ArrayList<AlternativaVO>();
		for (Alternativa alternativa : listaAlternativas) {
			AlternativaVO alternativaVO = new AlternativaVO();
			alternativaVO.setId(alternativa.getId());
			alternativaVO.setLetra(alternativa.getLetra());			
			alternativaVO.setSentenca(alternativa.getSentenca());
			alternativaVO.setJustificativa(alternativa.getJustificativa());
			
			listaAlternativasVO.add(alternativaVO);
		}		
		questaoVO.setListaAlternativas(listaAlternativasVO);
						
		List<AssuntoVO> listaAssuntosVO = new ArrayList<AssuntoVO>();	
		for (Assunto assunto : questao.getListaAssuntos()) {
			AssuntoVO assuntoVO = new AssuntoVO();
			assuntoVO.setId(assunto.getId());
			assuntoVO.setDescricao(assunto.getDescricao());
			assuntoVO.setIdDisciplina(assunto.getDisciplina().getId());
			listaAssuntosVO.add(assuntoVO);
		}		
		questaoVO.setListaAssuntos(listaAssuntosVO);		
		
		return questaoVO;
	}
	
	private Questao montaQuestao(QuestaoVO questaoVO) {	
		
		Questao questao = new Questao();
		if(questaoVO.getId() != null) {
			questao.setId(questaoVO.getId());
		}		
		
		questao.setEnunciado(questaoVO.getEnunciado());	
		
		questao.setGabarito(questaoVO.getGabarito());
		
		Disciplina disciplina = new Disciplina();
		disciplina.setId(questaoVO.getIdDisciplina());			
		questao.setDisciplina(disciplina);
				
		List<AlternativaVO> listaAlternativasVO = questaoVO.getListaAlternativas();
				
		if(listaAlternativasVO != null) {
			List<Alternativa> listaAlternativas = new ArrayList<Alternativa>();
			for (AlternativaVO alternativaVO : listaAlternativasVO) {
				Alternativa alternativa = new Alternativa();
				alternativa.setQuestao(questao);
				alternativa.setId(alternativaVO.getId());
				alternativa.setLetra(alternativaVO.getLetra());				
				alternativa.setSentenca(alternativaVO.getSentenca());
				alternativa.setJustificativa(alternativaVO.getJustificativa());				
							
				listaAlternativas.add(alternativa);			
			}		
			questao.setListaAlternativas(listaAlternativas);
		}
		
		List<AssuntoVO> listaAssuntosVO = questaoVO.getListaAssuntos();
						
		if(listaAssuntosVO != null) {
			List<Assunto> listaAssuntos = new ArrayList<Assunto>();
			for (AssuntoVO assuntoVO : listaAssuntosVO) {
				Assunto assunto = new Assunto();
				assunto.setId(assuntoVO.getId());
				assunto.setDescricao(assuntoVO.getDescricao());
				assunto.setDisciplina(disciplina);
				List<Questao> listaQuestoes = new ArrayList<Questao>();
				listaQuestoes.add(questao);
				assunto.setListaQuestoes(listaQuestoes);
				
				listaAssuntos.add(assunto);					
			}
			questao.setListaAssuntos(listaAssuntos);
		}		
								
		return questao;
	}	
	
	private Collection<QuestaoVO> montaListaQuestoesVO(Collection<Questao> listaQuestoes) {				
				
		Collection<QuestaoVO> listaQuestoesVO = new ArrayList<QuestaoVO>();
				
		for (Questao questao : listaQuestoes) {								
			listaQuestoesVO.add(this.montarQuestaoVO(questao));			
		}
				
		return listaQuestoesVO;
	}	
	 
	@POST
	@Consumes("application/json")
	@Produces("text/plain")
	public String gravaQuestao(QuestaoVO questaoVO) {	
		
		return questaoBean.gravar(this.montaQuestao(questaoVO));
	}
	
	@Path("a")
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Collection<QuestaoVO> buscaQuestoesPorAssuntos(QuestaoVO questaoVO) {		
		
		Collection<Questao> listaQuestoes = questaoBean.buscarQuestoesPorAssuntos(this.montaQuestao(questaoVO));
				
		return this.montaListaQuestoesVO(listaQuestoes);
	}
	
	@Path("d/{idDisciplina}")
	@GET
	@Produces("application/json")
	public Collection<QuestaoVO> buscaQuestoesPorDisciplina(@PathParam("idDisciplina") Long idDisciplina) {				
				
		List<Questao> listaQuestoes = questaoBean.buscarQuestoesPorDisciplina(idDisciplina);		
			
		return this.montaListaQuestoesVO(listaQuestoes);
	}
	
	@Path("questoes_avaliacao")
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Collection<QuestaoVO> buscaQuestoesPorAvaliacao(AvaliacaoVO avaliacaoVO) {
								
		List<Questao> listaQuestoes = questaoBean.buscarQuestoesPorAvaliacao(avaliacaoVO.getId(), avaliacaoVO.getIdAluno());		
		
		List<QuestaoVO> listaQuestoesVO = new ArrayList<QuestaoVO>();
				
		for (Questao questao : listaQuestoes) {								
			listaQuestoesVO.add(this.montarQuestaoVO(questao));			
		}
				
		return listaQuestoesVO;
	}
	
	
//	@Path("a/{idAvaliacao}")
//	@GET
//	@Produces("application/json")
//	public Collection<QuestaoVO> buscaQuestoesPorAvaliacao(@PathParam("idAvaliacao") Long idAvaliacao) {				
//				
//		List<Questao> listaQuestoes = questaoBean.buscarQuestoesPorAvaliacao(idAvaliacao);		
//		
//		List<QuestaoVO> listaQuestoesVO = new ArrayList<QuestaoVO>();
//				
//		for (Questao questao : listaQuestoes) {								
//			listaQuestoesVO.add(this.montarQuestaoVO(questao));			
//		}
//				
//		return listaQuestoesVO;
//	}
	
	@Path("{id}")
	@DELETE
	@Consumes("application/json")
	@Produces("text/plain")
	public String removeQuestao(@PathParam("id") Long id) {		
		
		return questaoBean.remover(id);
	}
	

	@Path("q/{id}")
	@GET
	@Produces("application/json")
	public QuestaoVO buscaQuestao(@PathParam("id") Long id) {		
		
		Questao questao = questaoBean.buscarQuestao(id);			
				
		return this.montarQuestaoVO(questao);
	}

}
