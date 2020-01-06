package br.edu.ifc.autoxerifsystem.axslocal.dao;

import br.edu.ifc.autoxerifsystem.axslocal.model.TipoUsuario;

public interface DAOTipoUsuario extends DAOBase<TipoUsuario> {

    /**
     * Busca TipoUsuario por nome
     */
    public TipoUsuario getTipoUsuario(String nome);

}
