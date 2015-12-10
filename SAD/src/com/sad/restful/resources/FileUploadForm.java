package com.sad.restful.resources;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.FormParam;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

import com.sad.restful.model.AlunoVO;

public class FileUploadForm {

	public FileUploadForm() {
	}
	
	private byte[] data;
	
	private Long idTurma;

	public byte[] getData() {
		return data;
	}
	
	public List<AlunoVO> getAlunos() {
		
		String s = new String(this.getData());
		
		StringTokenizer parser = new StringTokenizer(s.trim(),",;",false);		
		
		List<AlunoVO> listaAlunosVO = new ArrayList<AlunoVO>();		
	    while (parser.hasMoreTokens()) {
	    	
		    String matricula = parser.nextToken();
		    String nome = parser.nextToken();		    
		    
		    AlunoVO alunoVO = new AlunoVO();
		    alunoVO.setIdTurma(this.getTurma());		    
		    alunoVO.setMatricula(matricula);
		    alunoVO.setNome(nome);		    
		    
		    listaAlunosVO.add(alunoVO);
		}
		
		return listaAlunosVO;
	}

	@FormParam("file")
	@PartType("application/octet-stream")
	public void setData(byte[] data) {
		this.data = data;
	}
	
	@FormParam("turma")
	@PartType("application/octet-stream")
	public void setTurma(Long idTurma) {
		this.idTurma = idTurma;
	}
	
	public Long getTurma() {
		return this.idTurma;
	}
	
}