package br.edu.ifc.autoxerifsystem.axslocal.dao.hibernate;

import br.edu.ifc.autoxerifsystem.axslocal.dao.DAOAcesso;
import br.edu.ifc.autoxerifsystem.axslocal.model.Acesso;
import br.edu.ifc.autoxerifsystem.axslocal.model.Usuario;
import br.edu.ifc.autoxerifsystem.axslocal.util.HibernateUtil;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Dionathan Luan de Vargas
 * @since 01/10/2019
 *
 */
public class HBAcesso extends HBDAO<Acesso> implements DAOAcesso {

    @Override
    protected String getClazz() {
        return Acesso.class.getSimpleName();
    }

    @Override
    public List<Acesso> getByUsuario(Usuario usuario) {
        Session sessao;
        Transaction transacao = null;
        List<Acesso> resultado = null;

        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.getTransaction();
            transacao.begin();

            CriteriaBuilder builder = sessao.getCriteriaBuilder();
            CriteriaQuery<Acesso> query = builder.createQuery(Acesso.class);
            Root<Acesso> root = query.from(Acesso.class);

            Predicate predicate = builder.and();
            predicate = builder.and(predicate, builder.equal(root.get("usuario"), usuario));

            TypedQuery<Acesso> typedQuery = sessao.createQuery(query.select(root).where(predicate).distinct(true));
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
}
