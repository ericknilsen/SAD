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

import com.sad.bean.service.AlunoBeanRemote;
import com.sad.entity.Aluno;
import com.sad.entity.Assunto;
import com.sad.entity.Turma;
import com.sad.restful.model.AlunoVO;
import com.sad.restful.model.AssuntoVO;


@Stateless
@Path("/alunos")
public class AlunoResource {

	@EJB
	private AlunoBeanRemote alunoBean;
	
	private AlunoVO montaAlunoVO(Aluno aluno) {
				
		AlunoVO alunoVO = new AlunoVO();
			
		alunoVO.setId(aluno.getId());
		alunoVO.setIdTurma(aluno.getTurma().getId());
		alunoVO.setMatricula(aluno.getMatricula());
		alunoVO.setNome(aluno.getNome());	
						
		return alunoVO;
	}
	 
	private List<AlunoVO> montaListaAlunosVO(List<Aluno> listaAlunos) {
		List<AlunoVO> listaAlunosVO = new ArrayList<AlunoVO>();
		
		for (Aluno aluno : listaAlunos) {					
			listaAlunosVO.add(this.montaAlunoVO(aluno));		
		}				
				
		return listaAlunosVO;
	}
	
	private Aluno montaAluno(AlunoVO alunoVO) {
		
		Aluno aluno = new Aluno();
		aluno.setId(alunoVO.getId());
		aluno.setMatricula(alunoVO.getMatricula());
		aluno.setNome(alunoVO.getNome());
		
		Turma turma = new Turma();
		turma.setId(alunoVO.getIdTurma());		
		aluno.setTurma(turma);
		
		return aluno;
	}
	
	@POST
	@Consumes("application/json")
	@Produces("text/plain")
	public String adicionaAluno(AlunoVO alunoVO) {		
				
		return alunoBean.inserir(this.montaAluno(alunoVO));
	}
	
	@Path("t/{idTurma}")
	@GET
	@Produces("application/json")
	public Collection<AlunoVO> buscaAlunosPorTurma(@PathParam("idTurma") Long idTurma) {						
						
		return this.montaListaAlunosVO(alunoBean.buscarAlunosPorTurma(idTurma));
	}
	
	@Path("m/{matricula}")
	@GET
	@Produces("application/json")
	public AlunoVO buscaAlunoPorMatricula(@PathParam("matricula") String matricula) {						
						
		return this.montaAlunoVO(alunoBean.buscarAlunoPorMatricula(matricula));
	}	
	
	@GET
	@Produces("application/json")
	public Collection<AlunoVO> listaAlunos() {		
		
		return montaListaAlunosVO(alunoBean.listar());			
		
	}	
		
	@Path("{id}")
	@DELETE	
	@Produces("text/plain")
	public String removeAluno(@PathParam("id") Long id) {		
		
		return alunoBean.remover(id);
	}
	
	
	@PUT
	@Consumes("application/json")
	@Produces("text/plain")
	public String alteraAluno(AlunoVO alunoVO) {		
				
		return alunoBean.alterar(montaAluno(alunoVO));
	}
	
	@Path("{id}")
	@GET	
	@Produces("application/json")
	public AlunoVO buscaAluno(@PathParam("id") Long id) {		
		
		return this.montaAlunoVO(alunoBean.buscar(id));
	}


}
