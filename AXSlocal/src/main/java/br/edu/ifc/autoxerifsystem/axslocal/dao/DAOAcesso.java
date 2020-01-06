package br.edu.ifc.autoxerifsystem.axslocal.dao;

import br.edu.ifc.autoxerifsystem.axslocal.model.Acesso;
import br.edu.ifc.autoxerifsystem.axslocal.model.Usuario;
import java.util.List;

public interface DAOAcesso extends DAOBase<Acesso> {

    //Retorna lista de usuarios que podem ser respons�veis por salas
    public List<Acesso> getByUsuario(Usuario usuario);

}
