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
 * @since 20/12/2017
 *
 */
@Entity
@Table(name = "CURSO")
public class Curso implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "CURSO_ID")
    private int id;

    @Column(name = "ABREVIATURA", length = 10, nullable = false)
    private String abreviatura;

    @Column(name = "NOME", length = 100, nullable = false)
    private String nome;

    public Curso() {
    }

    public Curso(String nome, String descricao) {
        this.abreviatura = nome;
        this.nome = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return abreviatura + " - " + nome;
    }

}
