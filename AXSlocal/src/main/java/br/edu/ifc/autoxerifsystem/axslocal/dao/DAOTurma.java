package br.edu.ifc.autoxerifsystem.axslocal.dao;

import br.edu.ifc.autoxerifsystem.axslocal.model.Turma;

public interface DAOTurma extends DAOBase<Turma> {

    /**
     * Busca Turma por nome
     */
    public Turma getTurma(String nome);

}
