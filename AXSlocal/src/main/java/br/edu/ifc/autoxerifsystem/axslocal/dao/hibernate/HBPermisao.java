package br.edu.ifc.autoxerifsystem.axslocal.dao.hibernate;

import br.edu.ifc.autoxerifsystem.axslocal.dao.DAOPermisao;
import br.edu.ifc.autoxerifsystem.axslocal.model.Mensagem;
import br.edu.ifc.autoxerifsystem.axslocal.model.Permisao;
import br.edu.ifc.autoxerifsystem.axslocal.model.Sala;
import br.edu.ifc.autoxerifsystem.axslocal.model.Usuario;
import br.edu.ifc.autoxerifsystem.axslocal.util.HibernateUtil;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
 * @since 16/09/2019
 *
 */
public class HBPermisao extends HBDAO<Permisao> implements DAOPermisao {

    @Override
    protected String getClazz() {
        return Permisao.class.getSimpleName();
    }

    @Override
    public List<Permisao> getByUsuario(Usuario usuario) {
        Session sessao;
        Transaction transacao = null;
        List<Permisao> resultado = null;

        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.getTransaction();
            transacao.begin();

            CriteriaBuilder builder = sessao.getCriteriaBuilder();
            CriteriaQuery<Permisao> query = builder.createQuery(Permisao.class);
            Root<Permisao> root = query.from(Permisao.class);

            Predicate predicate = builder.and();
            predicate = builder.and(predicate, builder.equal(root.get("usuario"), usuario));

            TypedQuery<Permisao> typedQuery = sessao.createQuery(query.select(root).where(predicate).distinct(true));
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
    public Mensagem gerPermisao(Mensagem mensagem, Sala sala) {
        Session sessao;
        Transaction transacao = null;
        List<Permisao> resultado = null;

        mensagem.setMensagem("Acesso negado");

        String diaSemana = "";
        switch (mensagem.getHorario().get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SUNDAY:
                diaSemana = "Domingo";
                break;
            case Calendar.MONDAY:
                diaSemana = "Segunda";
                break;
            case Calendar.TUESDAY:
                diaSemana = "Terça";
                break;
            case Calendar.WEDNESDAY:
                diaSemana = "Quarta";
                break;
            case Calendar.THURSDAY:
                diaSemana = "Quinta";
                break;
            case Calendar.FRIDAY:
                diaSemana = "Sexta";
                break;
            case Calendar.SATURDAY:
                diaSemana = "Sábado";
                break;
        }

        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.getTransaction();
            transacao.begin();

            CriteriaBuilder builder = sessao.getCriteriaBuilder();
            CriteriaQuery<Permisao> query = builder.createQuery(Permisao.class);
            Root<Permisao> root = query.from(Permisao.class);

            Predicate predicate = builder.and();
            predicate = builder.and(predicate, builder.equal(root.get("usuario"), mensagem.getUsuario()));
            predicate = builder.and(predicate, builder.equal(root.get("diaSemana"), diaSemana));
            predicate = builder.and(predicate, builder.equal(root.get("sala"), sala));

            TypedQuery<Permisao> typedQuery = sessao.createQuery(query.select(root).where(predicate).distinct(true));
            resultado = typedQuery.getResultList();
            transacao.commit();
        } catch (HibernateException e) {
            if (transacao != null) {
                transacao.rollback();
            }
        } finally {

            for (Permisao p : resultado) {

                Calendar calAgora = mensagem.getHorario();
                calAgora.set(Calendar.YEAR, 0);
                calAgora.set(Calendar.MONTH, 0);
                calAgora.set(Calendar.DAY_OF_MONTH, 0);
                calAgora.set(Calendar.MILLISECOND, 0);

                Calendar calEntrada = Calendar.getInstance();
                calEntrada.setTime(p.getEntrada());
                calEntrada.set(Calendar.YEAR, 0);
                calEntrada.set(Calendar.MONTH, 0);
                calEntrada.set(Calendar.DAY_OF_MONTH, 0);
                calEntrada.set(Calendar.MILLISECOND, 0);

                Calendar calSaida = Calendar.getInstance();
                calSaida.setTime(p.getSaida());
                calSaida.set(Calendar.YEAR, 0);
                calSaida.set(Calendar.MONTH, 0);
                calSaida.set(Calendar.DAY_OF_MONTH, 0);
                calSaida.set(Calendar.MILLISECOND, 0);

                if (calEntrada.before(calAgora) && calSaida.after(calAgora)) {
                    mensagem.setMensagem("Bem vindo!");
                    mensagem.setOpenDoor(true);
                    return mensagem;
                }
            }
            return mensagem;
        }
    }
}
