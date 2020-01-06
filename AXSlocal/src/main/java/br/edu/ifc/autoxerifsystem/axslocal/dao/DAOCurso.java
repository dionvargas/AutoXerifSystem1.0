package br.edu.ifc.autoxerifsystem.axslocal.dao;

import br.edu.ifc.autoxerifsystem.axslocal.model.Curso;

public interface DAOCurso extends DAOBase<Curso> {

    /**
     * Busca Curso por nome
     */
    public Curso getCurso(String nome);

}
