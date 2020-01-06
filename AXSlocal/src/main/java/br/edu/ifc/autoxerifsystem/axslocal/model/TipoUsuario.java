package br.edu.ifc.autoxerifsystem.axslocal.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Dionathan Luan de Vargas
 * @since 21/12/2017
 *
 */
@Entity
@Table(name = "TIPO_USUARIO")
public class TipoUsuario implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "TIPOUSUARIO_ID")
    private int id;

    @Column(name = "DESCRICAO", length = 100, nullable = false)
    private String descricao;

    public TipoUsuario() {
    }

    public TipoUsuario(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }

}
