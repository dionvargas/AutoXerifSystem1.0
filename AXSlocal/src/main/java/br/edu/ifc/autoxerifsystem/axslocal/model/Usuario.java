package br.edu.ifc.autoxerifsystem.axslocal.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Dionathan Luan de Vargas
 * @since 21/12/2017
 *
 */
@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "USUARIO_ID")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TIPOUSUARIO_ID", nullable = false)
    private TipoUsuario tipoUsuario;

    @Column(name = "NOME", length = 200, nullable = false)
    private String nome;

    @Column(name = "SENHA", nullable = false)
    private int senha;

    @Column(name = "CODIGO", nullable = false)
    private int codigo;

    @Column(name = "EMAIL", length = 150, nullable = false)
    private String email;

    @Column(name = "TELEFONE", length = 20, nullable = true)
    private String telefone;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CURSO_ID", nullable = true)
    private Curso curso;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TURMA_ID", nullable = true)
    private Turma turma;

    public Usuario() {
    }

    public Usuario(TipoUsuario tipoUsuario, String nome, int senha, int codigo, String email) {
        this.tipoUsuario = tipoUsuario;
        this.nome = nome;
        this.senha = senha;
        this.codigo = codigo;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getNome() {
        return nome;
    }

    public String getPrimeiroNome() {
        String[] arrOfStr = nome.split(" ");
        return arrOfStr[0];
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    @Override
    public String toString() {
        return nome;
    }
}