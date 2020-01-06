package br.edu.ifc.autoxerifsystem.axslocal.dao.hibernate;

import br.edu.ifc.autoxerifsystem.axslocal.dao.DAODigital;
import br.edu.ifc.autoxerifsystem.axslocal.model.Digital;
import br.edu.ifc.autoxerifsystem.axslocal.model.Usuario;
import br.edu.ifc.autoxerifsystem.axslocal.util.HibernateUtil;
import com.digitalpersona.uareu.Engine;
import com.digitalpersona.uareu.Fmd;
import com.digitalpersona.uareu.UareUException;
import com.digitalpersona.uareu.UareUGlobal;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class HBDigital extends HBDAO<Digital> implements DAODigital {

    @Override
    protected String getClazz() {
        return Digital.class.getSimpleName();
    }

    @Override
    public List<Digital> list() {
        Session sessao;
        Transaction transacao = null;
        Query consulta;
        List<Digital> resultado = null;

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
            for (Digital d : resultado) {
                d.binaryToFmd();
            }
            return resultado;
        }
    }

    @Override
    public Digital get(Long id) {
        Session sessao;
        Transaction transacao;
        Query consulta;
        Digital resultado = null;

        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
            consulta = sessao.createQuery("from " + getClazz() + " where id = :id");
            consulta.setLong("id", id);
            resultado = (Digital) consulta.uniqueResult();
            resultado.binaryToFmd();
            transacao.commit();
        } catch (HibernateException e) {
            resultado = null;
            throw new HibernateException(e);
        } finally {
            return resultado;
        }
    }

    @Override
    public List<Digital> getDigitais(Usuario usuario) {
        Session sessao;
        Transaction transacao = null;
        List<Digital> resultado = null;

        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.getTransaction();
            transacao.begin();

            CriteriaBuilder builder = sessao.getCriteriaBuilder();
            CriteriaQuery<Digital> query = builder.createQuery(Digital.class);
            Root<Digital> root = query.from(Digital.class);

            Predicate predicate = builder.and();
            predicate = builder.and(predicate, builder.equal(root.get("usuario"), usuario));

            TypedQuery<Digital> typedQuery = sessao.createQuery(query.select(root).where(predicate).distinct(true));
            resultado = typedQuery.getResultList();
            transacao.commit();
        } catch (HibernateException e) {
            if (transacao != null) {
                transacao.rollback();
            }
        } finally {
            for (Digital d : resultado) {
                d.binaryToFmd();
            }
            return resultado;
        }
    }

    @Override
    public Digital getDigitalByArray(byte[] fmdBinDigital) {
        Digital resultado = null;

        Engine engine = UareUGlobal.GetEngine();
        Fmd fmd = null;
        int target_falsematch_rate = Engine.PROBABILITY_ONE / 100000; //target rate is 0.00001
        System.out.println("Chegou aqui");
        try {
            fmd = UareUGlobal.GetImporter().ImportFmd(fmdBinDigital, Fmd.Format.ANSI_378_2004, Fmd.Format.ANSI_378_2004);
            System.out.println("Chegou aqui também");
        } catch (UareUException e) {
            System.out.println("Ocorreu um erro na conversão de dados");
        }

        List<Digital> digitais = list();
        try {
            for (Digital d : digitais) {
                d.binaryToFmd();
                int falsematch_rate;

                falsematch_rate = engine.Compare(fmd, 0, d.getFmdDigital(), 0);

                if (falsematch_rate < target_falsematch_rate) {
                    resultado = d;
                    return resultado;
                }

            }
        } catch (UareUException ex) {
            Logger.getLogger(HBDigital.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resultado;

    }
}
