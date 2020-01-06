package br.edu.ifc.autoxerifsystem.axslocal.model;

import com.digitalpersona.uareu.Fmd;
import com.digitalpersona.uareu.UareUException;
import com.digitalpersona.uareu.UareUGlobal;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Dionathan Luan de Vargas
 * @since 01/10/2019
 *
 */
public class Mensagem implements Serializable {

    private boolean openDoor;
    private Usuario usuario;
    private String serial;
    private Calendar horario;
    private String mensagem;

    private byte[] fmdBinDigital;

    public Mensagem() {
        openDoor = false;
        usuario = null;
        horario = null;
        serial = "";
        mensagem = "";
    }

    public boolean isOpenDoor() {
        return openDoor;
    }

    public void setOpenDoor(boolean openDoor) {
        this.openDoor = openDoor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public byte[] getFmdBinDigital() {
        return fmdBinDigital;
    }

    public void setFmdBinDigital(byte[] fmdBinDigital) {
        this.fmdBinDigital = fmdBinDigital;
    }

    public String getSerial() {
        return serial;
    }

    public Calendar getHorario() {
        return horario;
    }

    public void setHorario(Calendar horario) {
        this.horario = horario;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Fmd getDigital() {
        Fmd fmdDigital = null;
        try {
            fmdDigital = UareUGlobal.GetImporter().ImportFmd(fmdBinDigital, Fmd.Format.ANSI_378_2004, Fmd.Format.ANSI_378_2004);
        } catch (UareUException e) {
            System.out.println("Ocorreu um erro na conversão de dados");
        }
        return fmdDigital;
    }

    public void setDigital(Fmd fmdDigital) {
        fmdBinDigital = fmdDigital.getData();
    }

    public void showDetails() {
        System.out.println("Porta: " + openDoor);
        System.out.println("Usuario: " + usuario);
        System.out.println("Serial: " + serial);
        System.out.println("Mensagem: " + mensagem);
        System.out.println("Horário: " + horario);
        System.out.println("Digital: " + fmdBinDigital);
    }
}
