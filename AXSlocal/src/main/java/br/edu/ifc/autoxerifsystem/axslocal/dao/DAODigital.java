package br.edu.ifc.autoxerifsystem.axslocal.dao;

import br.edu.ifc.autoxerifsystem.axslocal.model.Digital;
import br.edu.ifc.autoxerifsystem.axslocal.model.Usuario;
import java.util.List;

public interface DAODigital extends DAOBase<Digital> {

    //Traz todas as digitais do usuário
    public List<Digital> getDigitais(Usuario usuario);

    //Traz um Digital de um array
    public Digital getDigitalByArray(byte[] fmdBinDigital);

}
