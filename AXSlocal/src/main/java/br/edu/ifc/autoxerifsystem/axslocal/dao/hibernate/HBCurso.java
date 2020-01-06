package br.edu.ifc.autoxerifsystem.axslocal.dao.hibernate;

import br.edu.ifc.autoxerifsystem.axslocal.dao.DAOCurso;
import br.edu.ifc.autoxerifsystem.axslocal.model.Curso;

public class HBCurso extends HBDAO<Curso> implements DAOCurso {

    @Override
    protected String getClazz() {
        return Curso.class.getSimpleName();
    }

    @Override
    public Curso getCurso(String nome) {
//        Query query = Hibernate4Util_UnicaSessao.getSessionFactory().createQuery("from Curso ret where ret.nome = ?");
//        query.setString(0, nome);
//        return (Curso) query.uniqueResult();
        return null;
    }
}
