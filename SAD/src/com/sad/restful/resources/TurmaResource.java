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
import javax.ws.rs.QueryParam;

import com.sad.bean.service.AssuntoBeanRemote;
import com.sad.bean.service.TurmaBeanRemote;
import com.sad.entity.Assunto;
import com.sad.entity.Disciplina;
import com.sad.entity.Turma;
import com.sad.restful.model.AssuntoVO;
import com.sad.restful.model.TurmaVO;

@Stateless
@Path("/turmas")
public class TurmaResource {
	
	@EJB
	private TurmaBeanRemote turmaBean;
	 
	private List<TurmaVO> montaListaTurmasVO(List<Turma> listaTurmas) {
		List<TurmaVO> listaTurmasVO = new ArrayList<TurmaVO>();
		
		for (Turma turma : listaTurmas) {
			TurmaVO turmaVO = new TurmaVO();
			
			turmaVO.setId(turma.getId());
			turmaVO.setIdDisciplina(turma.getDisciplina().getId());
			turmaVO.setSemestre(turma.getSemestre());
			turmaVO.setCodigo(turma.getCodigo());
			
			listaTurmasVO.add(turmaVO);
		}		
		
				
		return listaTurmasVO;
	}
	
	private Turma montaTurma(TurmaVO turmaVO) {
		Turma turma = new Turma();
		
		turma.setId(turmaVO.getId());
		Disciplina disciplina = new Disciplina();
		disciplina.setId(turmaVO.getIdDisciplina());
		turma.setDisciplina(disciplina);
		turma.setSemestre(turmaVO.getSemestre());
		
		return turma;
	}
	
	@POST
	@Consumes("application/json")
	@Produces("text/plain")
	public String adicionaTurma(TurmaVO turmaVO) {		
				
		return turmaBean.inserir(this.montaTurma(turmaVO));
	}
	

	
	@GET
	@Produces("application/json")
	public Collection<TurmaVO> listaTurmas() {		
		
		return montaListaTurmasVO(turmaBean.listar());			
		
	}

	
	@Path("t")
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Collection<TurmaVO> buscaTurmasPorSemestreDisciplina(TurmaVO turmaVO) {			
		
		return montaListaTurmasVO(turmaBean.buscarTurmasPorSemestreDisciplina(this.montaTurma(turmaVO)));	
	}	
	
		
	@Path("{id}")
	@DELETE	
	@Produces("text/plain")
	public String removeTurma(@PathParam("id") Long id) {		
		
		return turmaBean.remover(id);
	}
	
	
	@PUT
	@Consumes("application/json")
	@Produces("text/plain")
	public String alteraTurma(TurmaVO turmaVO) {		
				
		return turmaBean.alterar(montaTurma(turmaVO));
	}


}
