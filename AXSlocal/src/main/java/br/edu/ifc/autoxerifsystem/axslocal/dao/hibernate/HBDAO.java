package br.edu.ifc.autoxerifsystem.axslocal.dao.hibernate;

import br.edu.ifc.autoxerifsystem.axslocal.dao.DAOBase;
import br.edu.ifc.autoxerifsystem.axslocal.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Transaction;

public abstract class HBDAO<T> implements DAOBase<T> {

    protected abstract String getClazz();

    @Override
    public List<T> list() {
        Session sessao;
        Transaction transacao = null;
        Query consulta;
        List<T> resultado = null;

        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.getTransaction();
            transacao.begin();

            consulta = sessao.createQuery("from " + getClazz());
            resultado = consulta.list();
            transacao.commit();
        } catch (HibernateException e) {
            if (transacao != null) {
                transacao.rollback();
            }
        } finally {
            return resultado;
        }
    }

    @Override
    public boolean persistir(T objeto) {
        boolean resultado = false;
        Session sessao = null;
        Transaction transacao = null;

        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.getTransaction();
            transacao.begin();
            sessao.saveOrUpdate(objeto);
            transacao.commit();
            if (sessao != null) {
                sessao.close();
            }
            resultado = true;
        } catch (Exception e) {
            if (transacao != null) {
                transacao.rollback();
            }
            resultado = false;
        } finally {
            return resultado;
        }
    }

    @Override
    public boolean excluir(T objeto) {
        boolean retorno = false;
        Session sessao;
        Transaction transacao;

        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
            sessao.delete(objeto);
            transacao.commit();
            retorno = true;
        } catch (Exception e) {
            retorno = false;
        } finally {
            return retorno;
        }
    }

    @Override
    public T get(Long id) {
        Session sessao;
        Transaction transacao;
        Query consulta;
        T resultado = null;

        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
            consulta = sessao.createQuery("from " + getClazz() + " where id = :id");
            consulta.setLong("id", id);
            resultado = (T) consulta.uniqueResult();
            transacao.commit();
        } catch (HibernateException e) {
            resultado = null;
            throw new HibernateException(e);
        } finally {
            return resultado;
        }
    }
}