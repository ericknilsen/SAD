package com.sad.bean.service;

import java.util.List;

import javax.ejb.Remote;

import com.sad.entity.Disciplina;

@Remote
public interface DisciplinaBeanRemote {
	
	public String inserir(Disciplina disciplina);
	
	public List<Disciplina> listar(); 
	
	public String remover(Long id);
	
	public String alterar(Disciplina disciplina);

}
