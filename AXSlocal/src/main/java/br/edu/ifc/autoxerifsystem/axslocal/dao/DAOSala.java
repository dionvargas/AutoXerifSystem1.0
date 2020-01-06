package br.edu.ifc.autoxerifsystem.axslocal.dao;

import br.edu.ifc.autoxerifsystem.axslocal.model.Sala;
import java.util.List;

public interface DAOSala extends DAOBase<Sala> {

    //Busca Sala por nome
    public List<Sala> getSala(String nome);

    //Busca Sala pelo ID do Leitor
    public Sala getSalaByLeitor(String id);

}
