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

import com.sad.bean.service.AssuntoBeanRemote;
import com.sad.entity.Assunto;
import com.sad.entity.Disciplina;
import com.sad.restful.model.AssuntoVO;

@Stateless
@Path("/assuntos")
public class AssuntoResource {


	@EJB
	private AssuntoBeanRemote assuntoBean;
	 
	@POST
	@Consumes("application/json")
	@Produces("text/plain")
	public String adicionaAssunto(AssuntoVO assuntoVO) {		
		
		Assunto assunto = new Assunto();
		assunto.setDescricao(assuntoVO.getDescricao());
		
		Disciplina disciplina = new Disciplina();
		disciplina.setId(assuntoVO.getIdDisciplina());				
		assunto.setDisciplina(disciplina);			
		
		return assuntoBean.inserir(assunto);
	}
	
	@GET
	@Produces("application/json")
	public Collection<AssuntoVO> listaAssuntos() {		
		
		List<Assunto> listaAssuntos = assuntoBean.listar();
		List<AssuntoVO> listaAssuntosVO = new ArrayList<AssuntoVO>();
		
		for (Assunto assunto : listaAssuntos) {
			AssuntoVO assuntoVO = new AssuntoVO();
			
			assuntoVO.setId(assunto.getId());
			assuntoVO.setDescricao(assunto.getDescricao());
			assuntoVO.setIdDisciplina(assunto.getDisciplina().getId());
			
			listaAssuntosVO.add(assuntoVO);
		}		
		
				
		return listaAssuntosVO;
	}
	
	
	
	@Path("{id}")
	@DELETE	
	@Produces("text/plain")
	public String removeAssunto(@PathParam("id") Long id) {		
		
		return assuntoBean.remover(id);
	}
	
	
	@PUT
	@Consumes("application/json")
	@Produces("text/plain")
	public String alteraAssunto(AssuntoVO assuntoVO) {		
		
		Assunto assunto = new Assunto();
		assunto.setId(assuntoVO.getId());		
		assunto.setDescricao(assuntoVO.getDescricao());
		
		Disciplina disciplina = new Disciplina();
		disciplina.setId(assuntoVO.getIdDisciplina());
		assunto.setDisciplina(disciplina);		
		
		return assuntoBean.alterar(assunto);
	}
	
	@Path("d/{idDisciplina}")
	@GET
	@Produces("application/json")
	public Collection<AssuntoVO> buscaAssuntosPorDisciplina(@PathParam("idDisciplina") Long idDisciplina) {				
				
		List<Assunto> listaAssuntos = assuntoBean.buscarAssuntosPorDisciplina(idDisciplina);		
		
		List<AssuntoVO> listaAssuntosVO = new ArrayList<AssuntoVO>();
				
		for (Assunto assunto : listaAssuntos) {
			
			AssuntoVO assuntoVO = new AssuntoVO();			
			
			assuntoVO.setId(assunto.getId());
			assuntoVO.setDescricao(assunto.getDescricao());
			assuntoVO.setIdDisciplina(assunto.getDisciplina().getId());
					
			listaAssuntosVO.add(assuntoVO);			
		}
				
		return listaAssuntosVO;
	}
	
	@Path("t/{idTurma}")
	@GET
	@Produces("application/json")
	public Collection<AssuntoVO> buscaAssuntosPorTurma(@PathParam("idTurma") Long idTurma) {
						
		List<Assunto> listaAssuntos = assuntoBean.buscarAssuntosPorTurma(idTurma);		
		
		List<AssuntoVO> listaAssuntosVO = new ArrayList<AssuntoVO>();
				
		for (Assunto assunto : listaAssuntos) {
			
			AssuntoVO assuntoVO = new AssuntoVO();			
			
			assuntoVO.setId(assunto.getId());
			assuntoVO.setDescricao(assunto.getDescricao());
			assuntoVO.setIdDisciplina(assunto.getDisciplina().getId());
					
			listaAssuntosVO.add(assuntoVO);			
		}
				
		return listaAssuntosVO;
	}

}
