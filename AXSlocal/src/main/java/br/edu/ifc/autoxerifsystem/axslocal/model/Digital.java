package br.edu.ifc.autoxerifsystem.axslocal.model;

import com.digitalpersona.uareu.Fmd;
import com.digitalpersona.uareu.UareUException;
import com.digitalpersona.uareu.UareUGlobal;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Dionathan Luan de Vargas
 * @since 20/12/2017
 *
 */
@Entity
@Table(name = "DIGITAL")
public class Digital implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "DIGITAL_ID")
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USUARIO_ID", nullable = false)
    private Usuario usuario;

    @Column(name = "MAO", length = 20, nullable = false)
    private String Mao;

    @Column(name = "DEDO", length = 20, nullable = false)
    private String Dedo;

    @Column(name = "DIGITAL", length = 500)
    private byte[] fmdBinDigital;

    @Transient
    private Fmd fmdDigital;

    public Digital() {
    }

    public Digital(long id, Usuario usuario, String Mao, String Dedo, byte[] fmdBinDigital, byte[] fidBinDigital) {
        this.id = id;
        this.usuario = usuario;
        this.Mao = Mao;
        this.Dedo = Dedo;
        this.fmdBinDigital = fmdBinDigital;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getMao() {
        return Mao;
    }

    public void setMao(String Mao) {
        this.Mao = Mao;
    }

    public String getDedo() {
        return Dedo;
    }

    public void setDedo(String Dedo) {
        this.Dedo = Dedo;
    }

    public byte[] getFmdBinDigital() {
        return fmdBinDigital;
    }

    public void setFmdBinDigital(byte[] fmdBinDigital) {
        this.fmdBinDigital = fmdBinDigital;
    }

    public Fmd getFmdDigital() {
        return fmdDigital;
    }

    public void setFmdDigital(Fmd fmdDigital) {
        this.fmdDigital = fmdDigital;
    }

    public void fmbToBinary() {
        this.fmdBinDigital = this.fmdDigital.getData();
    }

    public void binaryToFmd() {
        try {
            this.fmdDigital = UareUGlobal.GetImporter().ImportFmd(this.fmdBinDigital, Fmd.Format.ANSI_378_2004, Fmd.Format.ANSI_378_2004);
        } catch (UareUException e) {
            System.out.println("Ocorreu um erro na conversão de dados");
        }
    }

}
