package com.sad.bean.service;

import java.util.List;

import javax.ejb.Remote;

import com.sad.entity.Aluno;

@Remote
public interface AlunoBeanRemote {

	String inserir(Aluno montaAluno);

	List<Aluno> listar();

	String remover(Long id);

	String alterar(Aluno montaAluno);

	List<Aluno> buscarAlunosPorTurma(Long idTurma);

	List<Aluno> buscarAlunoPorMatricula(String matricula);

	Aluno buscar(Long id);
	

}
