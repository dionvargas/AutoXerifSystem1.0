package br.edu.ifc.autoxerifsystem.axslocal.dao.hibernate;

import org.hibernate.Query;

import br.edu.ifc.autoxerifsystem.axslocal.dao.DAOTipoUsuario;
import br.edu.ifc.autoxerifsystem.axslocal.model.TipoUsuario;
import br.edu.ifc.autoxerifsystem.axslocal.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HBTipoUsuario extends HBDAO<TipoUsuario> implements DAOTipoUsuario {

    @Override
    protected String getClazz() {
        return TipoUsuario.class.getSimpleName();
    }

    @Override
    public TipoUsuario getTipoUsuario(String descricao) {
        Session sessao = null;
        Transaction transacao = null;
        Query consulta = null;
        TipoUsuario resultado = null;

        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
            consulta = sessao.createQuery("from " + getClazz() + " where descricao = ?");
            consulta.setString(0, descricao);
            resultado = (TipoUsuario) consulta.uniqueResult();
            transacao.commit();
        } catch (HibernateException e) {
            System.out.println("N�o foi poss�vel selecionar TipoUsuario. Erro: " + e.getMessage());
        } finally {
            return resultado;
        }
    }
}
