package br.edu.ifc.autoxerifsystem.axslocal.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Dionathan Luan de Vargas
 * @since 10/09/2019
 *
 */
@Entity
@Table(name = "SALA")
public class Sala implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "SALA_ID")
    private int id;

    @Column(name = "NUMERO", length = 10, nullable = false)
    private String numero;

    @Column(name = "NOME", length = 100, nullable = false)
    private String nome;

    @Column(name = "IP", length = 20, nullable = true)
    private String ip;

    @Column(name = "LEITOR", length = 50, nullable = true)
    private String leitor;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USUARIO_ID", nullable = true)
    private Usuario responsavel;

    public Sala() {
    }

    public Sala(String numero, String nome) {
        this.numero = numero;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLeitor() {
        return leitor;
    }

    public void setLeitor(String leitor) {
        this.leitor = leitor;
    }

    public Usuario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Usuario responsavel) {
        this.responsavel = responsavel;
    }

    @Override
    public String toString() {
        return numero + " - " + nome;
    }

}
