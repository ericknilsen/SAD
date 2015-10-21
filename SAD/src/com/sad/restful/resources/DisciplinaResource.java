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

import com.sad.bean.service.DisciplinaBeanRemote;
import com.sad.entity.Disciplina;
import com.sad.restful.model.DisciplinaVO;

@Stateless
@Path("/disciplinas")
public class DisciplinaResource {


	@EJB
	private DisciplinaBeanRemote disciplinaBean;
	 
	@POST
	@Consumes("application/json")
	@Produces("text/plain")
	public String adicionaDisciplina(DisciplinaVO disciplinaVO) {		
		
		Disciplina disciplina = new Disciplina();
		disciplina.setCodigo(disciplinaVO.getCodigo());
		disciplina.setNome(disciplinaVO.getNome());
		
		return disciplinaBean.inserir(disciplina);
	}
	
	
	@GET
	@Produces("application/json")
	public Collection<DisciplinaVO> listaDisciplinas() {		
		
		List<Disciplina> listaDisciplinas = disciplinaBean.listar();			
			
		List<DisciplinaVO> listaDisciplinasVO = new ArrayList<DisciplinaVO>();
				
		for (Disciplina disciplina : listaDisciplinas) {
			DisciplinaVO disciplinaVO = new DisciplinaVO();			
			
			disciplinaVO.setId(disciplina.getId());
			disciplinaVO.setCodigo(disciplina.getCodigo());
			disciplinaVO.setNome(disciplina.getNome());
			
			listaDisciplinasVO.add(disciplinaVO);
			
		}
				
		return listaDisciplinasVO;
	}
	
	@Path("{id}")
	@DELETE
	@Consumes("application/json")
	@Produces("text/plain")
	public String removerDisciplina(@PathParam("id") Long id) {		
		
		return disciplinaBean.remover(id);
	}
	
	
	@PUT
	@Consumes("application/json")
	@Produces("text/plain")
	public String alteraDisciplina(DisciplinaVO disciplinaVO) {		
		
		Disciplina disciplina = new Disciplina();
		disciplina.setId(disciplinaVO.getId());
		disciplina.setCodigo(disciplinaVO.getCodigo());
		disciplina.setNome(disciplinaVO.getNome());
		
		return disciplinaBean.alterar(disciplina);
	}

}
