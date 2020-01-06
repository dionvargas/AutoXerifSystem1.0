package br.edu.ifc.autoxerifsystem.axslocal.dao.hibernate;

import org.hibernate.Query;

import br.edu.ifc.autoxerifsystem.axslocal.dao.DAOTurma;
import br.edu.ifc.autoxerifsystem.axslocal.model.Turma;

public class HBTurma extends HBDAO<Turma> implements DAOTurma {

    @Override
    protected String getClazz() {
        return Turma.class.getSimpleName();
    }

    @Override
    public Turma getTurma(String nome) {
        //Query query = getSession().createQuery("from Curso usr where usr.nome = ?");
        //query.setString(0, nome);
        //return (Curso) query.uniqueResult();
        return null;
    }
}
