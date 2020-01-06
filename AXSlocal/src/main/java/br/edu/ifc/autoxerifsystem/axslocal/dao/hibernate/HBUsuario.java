package br.edu.ifc.autoxerifsystem.axslocal.dao.hibernate;

import br.edu.ifc.autoxerifsystem.axslocal.dao.DAOTipoUsuario;
import br.edu.ifc.autoxerifsystem.axslocal.dao.DAOUsuario;
import br.edu.ifc.autoxerifsystem.axslocal.model.TipoUsuario;
import br.edu.ifc.autoxerifsystem.axslocal.model.Usuario;
import br.edu.ifc.autoxerifsystem.axslocal.util.HibernateUtil;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.query.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HBUsuario extends HBDAO<Usuario> implements DAOUsuario {

    @Override
    protected String getClazz() {
        return Usuario.class.getSimpleName();
    }

    @Override
    public Usuario getUsuario(String nome) {

        Session sessao = null;
        Transaction transacao = null;
        Usuario resultado = null;

        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.getTransaction();
            transacao.begin();

            CriteriaBuilder builder = sessao.getCriteriaBuilder();
            CriteriaQuery<Usuario> query = builder.createQuery(Usuario.class);
            Root<Usuario> root = query.from(Usuario.class);

            Predicate predicate = builder.and();
            predicate = builder.and(predicate, builder.like(root.<String>get("nome"), nome));

            TypedQuery<Usuario> typedQuery = sessao.createQuery(query.select(root).where(predicate).distinct(true));
            resultado = typedQuery.getSingleResult();
            transacao.commit();
        } catch (HibernateException e) {
            System.out.println("N�o foi poss�vel selecionar Usuario. Erro: " + e.getMessage());
        } finally {
            return resultado;
        }
    }

    public Usuario getUsuario(int login, int senha) {

        Session sessao = null;
        Transaction transacao = null;
        Query consulta = null;
        Usuario resultado = null;

        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
            consulta = sessao.createQuery("from " + getClazz() + " where codigo = ? and senha = ?");
            consulta.setInteger(0, login);
            consulta.setInteger(1, senha);
            resultado = (Usuario) consulta.uniqueResult();
            transacao.commit();
        } catch (HibernateException e) {
            System.out.println("N�o foi poss�vel selecionar Usuario. Erro: " + e.getMessage());
        } finally {
            return resultado;
        }
    }

    @Override
    public List<Usuario> getResponsaveis() {
        Session sessao;
        Transaction transacao = null;
        List<Usuario> resultado = null;

        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.getTransaction();
            transacao.begin();

            CriteriaBuilder builder = sessao.getCriteriaBuilder();
            CriteriaQuery<Usuario> query = builder.createQuery(Usuario.class);
            Root<Usuario> root = query.from(Usuario.class);

            DAOTipoUsuario daoTipoUsuario = new HBTipoUsuario();
            TipoUsuario responsavel = daoTipoUsuario.getTipoUsuario("Professor da Automação");

            Predicate predicate = builder.and();
            predicate = builder.and(predicate, builder.equal(root.get("tipoUsuario"), responsavel));

            TypedQuery<Usuario> typedQuery = sessao.createQuery(query.select(root).where(predicate).distinct(true));
            resultado = typedQuery.getResultList();
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
    public Usuario getUsuario(int matricula) {
        Session sessao;
        Transaction transacao = null;
        Usuario resultado = null;

        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.getTransaction();
            transacao.begin();

            CriteriaBuilder builder = sessao.getCriteriaBuilder();
            CriteriaQuery<Usuario> query = builder.createQuery(Usuario.class);
            Root<Usuario> root = query.from(Usuario.class);

            Predicate predicate = builder.and();
            predicate = builder.and(predicate, builder.equal(root.get("codigo"), matricula));

            TypedQuery<Usuario> typedQuery = sessao.createQuery(query.select(root).where(predicate).distinct(true));
            resultado = typedQuery.getSingleResult();
            transacao.commit();
        } catch (HibernateException e) {
            if (transacao != null) {
                transacao.rollback();
            }
        } finally {
            return resultado;
        }
    }

}
