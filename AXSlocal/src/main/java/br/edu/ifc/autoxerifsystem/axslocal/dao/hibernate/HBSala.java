package br.edu.ifc.autoxerifsystem.axslocal.dao.hibernate;

import br.edu.ifc.autoxerifsystem.axslocal.dao.DAOSala;
import br.edu.ifc.autoxerifsystem.axslocal.model.Sala;
import br.edu.ifc.autoxerifsystem.axslocal.util.HibernateUtil;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HBSala extends HBDAO<Sala> implements DAOSala {

    @Override
    protected String getClazz() {
        return Sala.class.getSimpleName();
    }

    @Override
    public List<Sala> getSala(String nome) {
        Session sessao = null;
        Transaction transacao = null;
        Query consulta = null;
        List<Sala> resultado = null;

        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
            consulta = sessao.createQuery("from " + getClazz() + " where nome = ?");
            consulta.setString(0, nome);
            resultado = consulta.list();
            transacao.commit();
        } catch (HibernateException e) {
            System.out.println("N�o foi poss�vel selecionar Salas. Erro: " + e.getMessage());
        } finally {
            return resultado;
        }
    }

    @Override
    public Sala getSalaByLeitor(String leitor) {

        Session sessao = null;
        Transaction transacao = null;
        Sala resultado = null;

        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.getTransaction();
            transacao.begin();

            CriteriaBuilder builder = sessao.getCriteriaBuilder();
            CriteriaQuery<Sala> query = builder.createQuery(Sala.class);
            Root<Sala> root = query.from(Sala.class);

            Predicate predicate = builder.and();
            predicate = builder.and(predicate, builder.like(root.<String>get("leitor"), leitor));

            TypedQuery<Sala> typedQuery = sessao.createQuery(query.select(root).where(predicate).distinct(true));
            resultado = typedQuery.getSingleResult();
            transacao.commit();
        } catch (HibernateException e) {
            System.out.println("Não foi possível selecionar Usuario. Erro: " + e.getMessage());
        } finally {
            return resultado;
        }
    }
}
