package br.edu.ifc.autoxerifsystem.axslocal.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
 * @since 01/10/2019
 *
 */
@Entity
@Table(name = "ACESSO")
public class Acesso implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ACESSO_ID")
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SALA_ID", nullable = false)
    private Sala sala;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USUARIO_ID", nullable = false)
    private Usuario usuario;

    @Column(name = "REGRA", nullable = false)
    private String regra;

    @Column(name = "HORA_ENTRADA", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date entrada;

    public Acesso() {
    }

    public Acesso(Sala sala, Usuario usuario, String regra, Date entrada) {
        this.sala = sala;
        this.usuario = usuario;
        this.regra = regra;
        this.entrada = entrada;
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

    public String getRegra() {
        return regra;
    }

    public void setRegra(String regra) {
        this.regra = regra;
    }

    public String getData() {
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String date = simpleDateFormat.format(entrada);
        return date;
    }

    public String getHora() {
        String pattern = "HH:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String hora = simpleDateFormat.format(entrada);
        return hora;
    }

    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    @Override
    public String toString() {
        return "Acesso{" + "id=" + id + ", sala=" + sala + ", usuario=" + usuario + ", regra=" + regra + ", entrada=" + entrada + '}';
    }
}
