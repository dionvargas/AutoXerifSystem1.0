package br.edu.ifc.autoxerifsystem.axslocal.gui;

import br.edu.ifc.autoxerifsystem.axslocal.util.CaptureThread;
import br.edu.ifc.autoxerifsystem.axslocal.model.Mensagem;
import com.digitalpersona.uareu.Engine;
import com.digitalpersona.uareu.Fid;
import com.digitalpersona.uareu.Fmd;
import com.digitalpersona.uareu.Reader;
import com.digitalpersona.uareu.ReaderCollection;
import com.digitalpersona.uareu.UareUException;
import com.digitalpersona.uareu.UareUGlobal;
import com.pi4j.component.lcd.LCDTextAlignment;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.component.lcd.impl.GpioLcdDisplay;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dionathan Luan de Vargas
 * @since 06/09/2019
 *
 */
public class Estacao extends javax.swing.JFrame implements ActionListener {

    public final static int LCD_ROW_1 = 0;
    public final static int LCD_ROW_2 = 1;

    private ReaderCollection m_collection = null;
    private Reader reader = null;
    private CaptureThread captureThread;
    private ImagePanel m_image;
    private Fid digitalFID;
    private Fmd digitalFMD;
    private String serial;
    private GpioController gpio;
    private GpioLcdDisplay lcd;
    private GpioPinDigitalOutput bobina;
    private GpioPinDigitalInput emAula;

    public Estacao() {
        initComponents();

        jtaConsole.append("Iniciando\n");

        // create gpio controller
        gpio = GpioFactory.getInstance();
        // provision gpio pin #01 as an output pin and turn on
        GpioPinDigitalInput abrirPorta = gpio.provisionDigitalInputPin(RaspiPin.GPIO_27, // PIN NUMBER
                "Abrir a Porta", // PIN FRIENDLY NAME (optional)
                PinPullResistance.PULL_DOWN); // PIN RESISTANCE (optional)

        // create and register gpio pin listener
        abrirPorta.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                try {
                    jtaConsole.append("Porta aberta por dentro\n");
                    lcd.writeln(LCD_ROW_1, "Porta aberta", LCDTextAlignment.ALIGN_CENTER);
                    lcd.writeln(LCD_ROW_2, "por dentro", LCDTextAlignment.ALIGN_CENTER);
                    bobina.pulse(500);
                    Thread.sleep(1000);
                    jtaConsole.append("Insira o dedo no leitor\n");
                    lcd.writeln(LCD_ROW_1, "Insira o dedo", LCDTextAlignment.ALIGN_CENTER);
                    lcd.writeln(LCD_ROW_2, "no leitor", LCDTextAlignment.ALIGN_CENTER);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Estacao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        // provision gpio pin #01 as an output pin and turn on
        emAula = gpio.provisionDigitalInputPin(RaspiPin.GPIO_28, // PIN NUMBER
                "Em aula", // PIN FRIENDLY NAME (optional)
                PinPullResistance.PULL_DOWN); // PIN RESISTANCE (optional)

        // create and register gpio pin listener
        emAula.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                if (emAula.isHigh()) {
                    jtaConsole.append("Em aula\n");
                    lcd.writeln(LCD_ROW_1, "MODO:", LCDTextAlignment.ALIGN_CENTER);
                    lcd.writeln(LCD_ROW_2, "Em aula", LCDTextAlignment.ALIGN_CENTER);
                } else {
                    jtaConsole.append("Insira o dedo no leitor\n");
                    lcd.writeln(LCD_ROW_1, "Insira o dedo", LCDTextAlignment.ALIGN_CENTER);
                    lcd.writeln(LCD_ROW_2, "no leitor", LCDTextAlignment.ALIGN_CENTER);
                }
            }
        });

        bobina = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_26, "Bobina", PinState.LOW);

        // set shutdown state for this pin
        bobina.setShutdownOptions(true, PinState.LOW);

        // initialize LCD
        lcd = new GpioLcdDisplay(2, // number of row supported by LCD
                16, // number of columns supported by LCD
                RaspiPin.GPIO_09, // LCD RS pin
                RaspiPin.GPIO_08, // LCD strobe pin
                RaspiPin.GPIO_07, // LCD data bit D4
                RaspiPin.GPIO_15, // LCD data bit D5
                RaspiPin.GPIO_16, // LCD data bit D6
                RaspiPin.GPIO_01); // LCD data bit D7

        lcd.clear();
        lcd.writeln(LCD_ROW_1, "AutoXerifSystem", LCDTextAlignment.ALIGN_CENTER);
        lcd.writeln(LCD_ROW_2, "Iniciando...", LCDTextAlignment.ALIGN_CENTER);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        m_image = new ImagePanel();
        m_image.setSize(pnlImage.getSize());
        pnlImage.add(m_image);

        try {
            m_collection = UareUGlobal.GetReaderCollection();
            m_collection.GetReaders();
            if (m_collection.isEmpty()) {
                jtaConsole.append("Nenhum leitor encontrado\n");
                lcd.writeln(LCD_ROW_1, "Nenhum leitor", LCDTextAlignment.ALIGN_CENTER);
                lcd.writeln(LCD_ROW_2, "encontrado", LCDTextAlignment.ALIGN_CENTER);
            } else {
                reader = m_collection.get(0);
                serial = reader.GetDescription().serial_number;
                jtaConsole.append("Leitor: " + serial + "\n");
                try {
                    reader.Open(Reader.Priority.COOPERATIVE);
                } catch (UareUException e) {
                    jtaConsole.append("Reader.Open()" + e + "\n");
                }
                //start capture thread
                StartCaptureThread();

            }

        } catch (UareUException ex) {
            jtaConsole.append("UareUGlobal.destroyReaderCollection()" + ex.getMessage() + "\n");
        }
    }

    private void close() {
        //close reader
        try {
            reader.Close();
            gpio.shutdown();
        } catch (UareUException e) {
            jtaConsole.append("Reader.Close()" + e + "\n");
        }
        dispose();
    }

    private void StartCaptureThread() {
        captureThread = new CaptureThread(reader, false, Fid.Format.ANSI_381_2004, Reader.ImageProcessing.IMG_PROC_DEFAULT);
        captureThread.start(this);
        if (emAula.isHigh()) {
            jtaConsole.append("Em aula\n");
            lcd.writeln(LCD_ROW_1, "MODO:", LCDTextAlignment.ALIGN_CENTER);
            lcd.writeln(LCD_ROW_2, "Em aula", LCDTextAlignment.ALIGN_CENTER);
        } else {
            jtaConsole.append("Insira o dedo no leitor\n");
            lcd.writeln(LCD_ROW_1, "Insira o dedo", LCDTextAlignment.ALIGN_CENTER);
            lcd.writeln(LCD_ROW_2, "no leitor", LCDTextAlignment.ALIGN_CENTER);
        }

    }

    private void StopCaptureThread() {
        if (null != captureThread) {
            captureThread.cancel();
        }
    }

    private void WaitForCaptureThread() {
        if (null != captureThread) {
            captureThread.join(1000);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(CaptureThread.ACT_CAPTURE)) {

            lcd.writeln(LCD_ROW_1, "Processando", LCDTextAlignment.ALIGN_CENTER);
            lcd.writeln(LCD_ROW_2, "Aguarde...", LCDTextAlignment.ALIGN_CENTER);

            CaptureThread.CaptureEvent evt = (CaptureThread.CaptureEvent) e;
            if (null != evt.capture_result) {

                if (emAula.isHigh()) {
                    jtaConsole.append("Porta aberta por fora\n");
                    lcd.writeln(LCD_ROW_1, "Porta aberta", LCDTextAlignment.ALIGN_CENTER);
                    lcd.writeln(LCD_ROW_2, "por fora", LCDTextAlignment.ALIGN_CENTER);
                    bobina.pulse(500);
                } else {
                    Mensagem mensagem = new Mensagem();

                    digitalFID = evt.capture_result.image;
                    m_image.showImage(digitalFID, pnlImage.getSize());

                    Engine engine = UareUGlobal.GetEngine();
                    try {
                        digitalFMD = engine.CreateFmd(digitalFID, Fmd.Format.ANSI_378_2004);
                    } catch (UareUException ex) {
                        Logger.getLogger(Estacao.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    jtaConsole.append("FID: " + digitalFID.getData() + "\n");
                    jtaConsole.append("FMD: " + digitalFMD.getData() + "\n");

                    mensagem.setSerial(serial);
                    mensagem.setDigital(digitalFMD);

                    try {
                        //Transmitir para o servidor
                        sendToServer(mensagem);
                    } catch (Exception ex) {
                        Logger.getLogger(Estacao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else if (null != evt.exception) {
                //exception during capture
                jtaConsole.append("Capture" + evt.exception + "\n");
            } else if (null != evt.reader_status) {
                jtaConsole.append(evt.reader_status.toString() + "\n");
            }
            StartCaptureThread();
        }
    }

    private void sendToServer(Mensagem mensagem) throws InterruptedException, ClassNotFoundException {
        //get the localhost IP address, if server is running on some other IP, you need to use that
        Socket cliente = null;

        try {
            cliente = new Socket("191.52.40.14", 9876);
            ObjectOutputStream os = new ObjectOutputStream(cliente.getOutputStream());
            os.writeObject(mensagem);
            System.out.println("O cliente se conectou ao servidor!");

            ObjectInputStream is = new ObjectInputStream(cliente.getInputStream());
            mensagem = (Mensagem) is.readObject();

            Time horario = new Time(mensagem.getHorario().getTimeInMillis());

            jtaConsole.append("\n######################\n");
            jtaConsole.append("Usuario: " + mensagem.getUsuario() + "\n");
            jtaConsole.append("Horário: " + horario + "\n");
            jtaConsole.append("Mensagem: " + mensagem.getMensagem() + "\n");
            jtaConsole.append("Abrir Porta: " + mensagem.isOpenDoor() + "\n");
            jtaConsole.append("######################\n\n");

        } catch (IOException ex) {
            jtaConsole.append("Erro ao abrir o socket\n");
            lcd.writeln(LCD_ROW_1, "Sem acesso", LCDTextAlignment.ALIGN_CENTER);
            lcd.writeln(LCD_ROW_2, "ao servidor", LCDTextAlignment.ALIGN_CENTER);
            Thread.sleep(3000);
        }

        if (mensagem.isOpenDoor()) {
            bobina.pulse(500);
        }

        if (mensagem.getUsuario() == null) {
            lcd.writeln(LCD_ROW_1, "Usuario:", LCDTextAlignment.ALIGN_CENTER);
            lcd.writeln(LCD_ROW_2, "Desconhecido", LCDTextAlignment.ALIGN_CENTER);
        } else {
            lcd.writeln(LCD_ROW_1, "Usuario:", LCDTextAlignment.ALIGN_CENTER);
            lcd.writeln(LCD_ROW_2, mensagem.getUsuario().getPrimeiroNome(), LCDTextAlignment.ALIGN_CENTER);
        }
        Thread.sleep(1500);

        if (mensagem.isOpenDoor()) {
            lcd.clear();
            lcd.writeln(LCD_ROW_1, "Bem", LCDTextAlignment.ALIGN_CENTER);
            lcd.writeln(LCD_ROW_2, "Vindo", LCDTextAlignment.ALIGN_CENTER);
        } else {
            lcd.clear();
            lcd.writeln(LCD_ROW_1, "Acesso", LCDTextAlignment.ALIGN_CENTER);
            lcd.writeln(LCD_ROW_2, "Negado", LCDTextAlignment.ALIGN_CENTER);
        }
        Thread.sleep(1000);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        pnlImage = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtaConsole = new javax.swing.JTextArea();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlImage.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlImageLayout = new javax.swing.GroupLayout(pnlImage);
        pnlImage.setLayout(pnlImageLayout);
        pnlImageLayout.setHorizontalGroup(
            pnlImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 228, Short.MAX_VALUE)
        );
        pnlImageLayout.setVerticalGroup(
            pnlImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 296, Short.MAX_VALUE)
        );

        jtaConsole.setEditable(false);
        jtaConsole.setColumns(20);
        jtaConsole.setRows(5);
        jScrollPane2.setViewportView(jtaConsole);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Estacao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jtaConsole;
    private javax.swing.JPanel pnlImage;
    // End of variables declaration//GEN-END:variables
}
