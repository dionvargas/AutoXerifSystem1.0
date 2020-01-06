package br.edu.ifc.autoxerifsystem.axslocal.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Dionathan Luan de Vargas
 * @since 16/09/2019
 *
 */
@Entity
@Table(name = "PERMISAO")
public class Permisao implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "PERMISAO_ID")
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SALA_ID", nullable = false)
    private Sala sala;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USUARIO_ID", nullable = false)
    private Usuario usuario;

    @Column(name = "DIA_SEMANA", length = 20, nullable = false)
    private String diaSemana;

    @Column(name = "HORA_ENTRADA", nullable = true)
    @Temporal(TemporalType.TIME)
    private Date entrada;

    @Column(name = "HORA_SAIDA", nullable = true)
    @Temporal(TemporalType.TIME)
    private Date saida;

    public Permisao() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public Date getEntrada() {
        return entrada;
    }

    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    public Date getSaida() {
        return saida;
    }

    public void setSaida(Date saida) {
        this.saida = saida;
    }

    @Override
    public String toString() {
        return "Permisao{" + "id=" + id + ", sala=" + sala + ", usuario=" + usuario + ", diaSemana=" + diaSemana + ", entrada=" + entrada + ", saida=" + saida + '}';
    }
}
