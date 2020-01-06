
import br.edu.ifc.autoxerifsystem.axslocal.dao.DAOBase;
import br.edu.ifc.autoxerifsystem.axslocal.dao.hibernate.HBCurso;
import br.edu.ifc.autoxerifsystem.axslocal.dao.hibernate.HBSala;
import br.edu.ifc.autoxerifsystem.axslocal.dao.hibernate.HBTurma;
import br.edu.ifc.autoxerifsystem.axslocal.dao.hibernate.HBUsuario;
import br.edu.ifc.autoxerifsystem.axslocal.dao.hibernate.HBTipoUsuario;
import br.edu.ifc.autoxerifsystem.axslocal.model.Curso;
import br.edu.ifc.autoxerifsystem.axslocal.model.Sala;
import br.edu.ifc.autoxerifsystem.axslocal.model.TipoUsuario;
import br.edu.ifc.autoxerifsystem.axslocal.model.Turma;
import br.edu.ifc.autoxerifsystem.axslocal.model.Usuario;

public class popula {

    public static void main(String[] args) {

        DAOBase genericDao;

        genericDao = new HBCurso();
        Curso eca = new Curso("ECA", "Engenharia de Controle e Automação");
        Curso emec = new Curso("EMEC", "Engenharia de Mecânica");
        Curso tsai = new Curso("TSAI", "Técnico Subsequente em Automação Industrial");
        Curso emitai = new Curso("EMITAI", "Ensino Médio Integrado em Automação");
        Curso emitst = new Curso("EMITST", "Ensino Médio Integrado em Segurança do Trabalho");
        Curso emimec = new Curso("EMIMEC", "Ensino Médio Integrado em Mecânica");
        genericDao.persistir(eca);
        genericDao.persistir(emec);
        genericDao.persistir(tsai);
        genericDao.persistir(emitai);
        genericDao.persistir(emitst);
        genericDao.persistir(emimec);

        genericDao = new HBTurma();
        genericDao.persistir(new Turma("2011/2"));
        genericDao.persistir(new Turma("2012/1"));
        genericDao.persistir(new Turma("2012/2"));
        genericDao.persistir(new Turma("2013/1"));
        genericDao.persistir(new Turma("2013/2"));
        genericDao.persistir(new Turma("2014/1"));
        genericDao.persistir(new Turma("2014/2"));
        genericDao.persistir(new Turma("2015/1"));
        genericDao.persistir(new Turma("2015/2"));
        genericDao.persistir(new Turma("2016/1"));
        genericDao.persistir(new Turma("2016/2"));
        genericDao.persistir(new Turma("2017/1"));
        genericDao.persistir(new Turma("2017/2"));
        genericDao.persistir(new Turma("2018/1"));
        genericDao.persistir(new Turma("2018/2"));
        genericDao.persistir(new Turma("2019/1"));
        genericDao.persistir(new Turma("2019/2"));

        genericDao = new HBTipoUsuario();
        TipoUsuario admin = new TipoUsuario("Administrador");
        TipoUsuario prof = new TipoUsuario("Professor");
        TipoUsuario profAuto = new TipoUsuario("Professor da Automação");
        TipoUsuario tec = new TipoUsuario("Técnico da Automação");
        TipoUsuario tae = new TipoUsuario("TAE");
        TipoUsuario aluno = new TipoUsuario("Aluno");
        genericDao.persistir(admin);
        genericDao.persistir(prof);
        genericDao.persistir(profAuto);
        genericDao.persistir(tec);
        genericDao.persistir(tae);
        genericDao.persistir(aluno);

        genericDao = new HBSala();
        Sala info = new Sala("B204", "Laboratório de Informática Industrial");
        Sala projeto = new Sala("B205", "Laboratório de Projeto Integrador");
        Sala pesquisa = new Sala("B206", "Laboratório de Pesquisa em Automação");
        Sala almoxarifado = new Sala("B212", "Almoxarifado da Automação");
        Sala maquinas = new Sala("B213", "Laboratório de Acionamentos e Máquinas Elétricas");
        Sala eletro = new Sala("B214", "Laboratório de Eletroeletrônica");
        Sala hidro = new Sala("B215", "Laboratório de Hidráulica e Pneumática");
        genericDao.persistir(info);
        genericDao.persistir(projeto);
        genericDao.persistir(pesquisa);
        genericDao.persistir(almoxarifado);
        genericDao.persistir(maquinas);
        genericDao.persistir(eletro);
        genericDao.persistir(hidro);

        System.exit(0);
    }
}
