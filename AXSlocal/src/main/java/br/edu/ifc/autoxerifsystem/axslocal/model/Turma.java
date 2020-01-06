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
@Table(name = "TURMA")
public class Turma implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "TURMA_ID")
    private int id;

    @Column(name = "SEMESTRE", length = 10, nullable = false)
    private String semestre;

    public Turma() {
    }

    public Turma(String descricao) {
        this.semestre = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    @Override
    public String toString() {
        return semestre;
    }

}
