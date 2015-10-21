package com.sad.bean.service;

import java.util.List;

import javax.ejb.Remote;

import com.sad.entity.Turma;


@Remote
public interface TurmaBeanRemote {

	String inserir(Turma turma);

	List<Turma> listar();

	List<Turma> buscarTurmasPorSemestreDisciplina(Turma turma);

	String remover(Long id);

	String alterar(Turma turma);
	

}
