package br.edu.ifc.autoxerifsystem.axslocal.gui;

import br.edu.ifc.autoxerifsystem.axslocal.dao.DAOAcesso;
import br.edu.ifc.autoxerifsystem.axslocal.dao.DAODigital;
import br.edu.ifc.autoxerifsystem.axslocal.dao.DAOPermisao;
import br.edu.ifc.autoxerifsystem.axslocal.dao.DAOSala;
import br.edu.ifc.autoxerifsystem.axslocal.dao.hibernate.HBAcesso;
import br.edu.ifc.autoxerifsystem.axslocal.dao.hibernate.HBDigital;
import br.edu.ifc.autoxerifsystem.axslocal.dao.hibernate.HBPermisao;
import br.edu.ifc.autoxerifsystem.axslocal.dao.hibernate.HBSala;
import br.edu.ifc.autoxerifsystem.axslocal.model.Acesso;
import br.edu.ifc.autoxerifsystem.axslocal.model.Digital;
import br.edu.ifc.autoxerifsystem.axslocal.model.Mensagem;
import br.edu.ifc.autoxerifsystem.axslocal.model.Sala;
import com.digitalpersona.uareu.Engine;
import com.digitalpersona.uareu.UareUException;
import com.digitalpersona.uareu.UareUGlobal;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dionathan Luan de Vargas
 * @since 06/09/2019
 *
 */
public class Servidor {

    //static ServerSocket variable
    private static ServerSocket server;
    //socket server port on which it will listen
    private static final int port = 9876;

    public static void main(String args[]) throws IOException, ClassNotFoundException, SQLException {

        server = new ServerSocket(port);
        Sala sala = null;
        while (true) {
            System.out.println("Aguardando por requisições de clientes...");
            //creating socket and waiting for client connection
            Socket cliente = server.accept();

            ObjectInputStream is = new ObjectInputStream(cliente.getInputStream());
            Mensagem mensagem = (Mensagem) is.readObject();

            DAODigital digitalDAO = new HBDigital();
            Digital digital = null;
            List<Digital> listaDigitais = digitalDAO.list();

            try {
                int target_falsematch_rate = Engine.PROBABILITY_ONE / 100000; //target rate is 0.00001
                Engine engine = UareUGlobal.GetEngine();
                for (Digital d : listaDigitais) {
                    d.binaryToFmd();
                    int falsematch_rate = engine.Compare(mensagem.getDigital(), 0, d.getFmdDigital(), 0);
                    if (falsematch_rate < target_falsematch_rate) {
                        digital = d;
                        break;
                    }
                }
            } catch (UareUException e) {
                System.out.println("Engine.CreateFmd()" + e);
            }
            mensagem.setHorario(Calendar.getInstance());
            if (digital == null) {
                mensagem.setMensagem("Digital não encontrada");
                mensagem.setUsuario(null);
            } else {
                mensagem.setUsuario(digital.getUsuario());

                DAOSala salaDao = new HBSala();
                sala = salaDao.getSalaByLeitor(mensagem.getSerial());
                if (sala == null) {
                    mensagem.setMensagem("Sala não encontrada");
                } else {
                    if (mensagem.getUsuario().getTipoUsuario().getDescricao().equals("Técnico da Automação")) {
                        mensagem.setMensagem("Técnico da Automação tem acesso a todas as portas");
                        mensagem.setOpenDoor(true);
                    } else if (mensagem.getUsuario().getTipoUsuario().getDescricao().equals("Professor da Automação")
                            && !sala.getNome().equals("Almoxarifado da Automação")) {
                        mensagem.setMensagem("Professor da Automação tem acesso a todos os laboratórios");
                        mensagem.setOpenDoor(true);
                    } else {
                        DAOPermisao permisaoDao = new HBPermisao();
                        permisaoDao.gerPermisao(mensagem, sala);
                    }
                }
            }

            System.out.println("##########################################");
            mensagem.showDetails();
            System.out.println("##########################################");

            if (mensagem.isOpenDoor()) {
                Date hora = mensagem.getHorario().getTime();
                Acesso acesso = new Acesso(sala, mensagem.getUsuario(), mensagem.getMensagem(), hora);
                System.out.println("##########################################");
                System.out.println(acesso);
                System.out.println("##########################################");
                DAOAcesso acessoDao = new HBAcesso();
                acessoDao.persistir(acesso);
            }

            ObjectOutputStream os = new ObjectOutputStream(cliente.getOutputStream());
            os.writeObject(mensagem);

            cliente.close();
        }
    }
}
