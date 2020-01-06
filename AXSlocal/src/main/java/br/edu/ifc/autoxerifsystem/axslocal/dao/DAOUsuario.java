package br.edu.ifc.autoxerifsystem.axslocal.dao;

import br.edu.ifc.autoxerifsystem.axslocal.model.Usuario;
import java.util.List;

public interface DAOUsuario extends DAOBase<Usuario> {

    //Busca Usuario por nome
    public Usuario getUsuario(String nome);

    //Retorna Usuario com login e senha
    public Usuario getUsuario(int login, int senha);

    //Retorna Usuario com a matricula
    public Usuario getUsuario(int matricula);

    //Retorna lista de usuarios que podem ser respons�veis por salas
    public List<Usuario> getResponsaveis();

}
