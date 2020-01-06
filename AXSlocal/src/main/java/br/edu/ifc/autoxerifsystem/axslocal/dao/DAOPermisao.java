package br.edu.ifc.autoxerifsystem.axslocal.dao;

import br.edu.ifc.autoxerifsystem.axslocal.model.Mensagem;
import br.edu.ifc.autoxerifsystem.axslocal.model.Permisao;
import br.edu.ifc.autoxerifsystem.axslocal.model.Sala;
import br.edu.ifc.autoxerifsystem.axslocal.model.Usuario;
import java.util.List;

public interface DAOPermisao extends DAOBase<Permisao> {

    //Retorna lista de usuarios que podem ser respons�veis por salas
    public List<Permisao> getByUsuario(Usuario usuario);

    //Verifica as permisão de acesso da mensagem
    public Mensagem gerPermisao(Mensagem mensagem, Sala sala);

}
