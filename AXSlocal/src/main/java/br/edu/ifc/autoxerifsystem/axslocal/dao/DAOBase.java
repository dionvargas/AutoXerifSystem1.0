package br.edu.ifc.autoxerifsystem.axslocal.dao;

import java.util.List;

public interface DAOBase<T> {

    public List<T> list();

    public boolean persistir(T objeto);

    public boolean excluir(T objeto);

    public T get(Long id);
}
