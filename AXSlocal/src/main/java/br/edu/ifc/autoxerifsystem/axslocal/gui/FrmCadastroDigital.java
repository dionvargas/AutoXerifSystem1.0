package br.edu.ifc.autoxerifsystem.axslocal.gui;

import com.digitalpersona.uareu.Fid;
import com.digitalpersona.uareu.Reader;
import com.digitalpersona.uareu.ReaderCollection;
import com.digitalpersona.uareu.UareUException;
import com.digitalpersona.uareu.UareUGlobal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import br.edu.ifc.autoxerifsystem.axslocal.util.CaptureThread;
import br.edu.ifc.autoxerifsystem.axslocal.model.Digital;
import com.digitalpersona.uareu.Engine;
import com.digitalpersona.uareu.Fmd;

/**
 *
 * @author Dionathan Luan de Vargas
 * @since 22/03/2018
 */
public class FrmCadastroDigital extends javax.swing.JFrame implements ActionListener {

    private FrmUsuario frmPai;
    private ReaderCollection m_collection = null;
    private Reader reader = null;
    private CaptureThread captureThread;
    private ImagePanel m_image;
    private Fid fidDigital;
    private String dedo;
    private String mao;

    public FrmCadastroDigital(FrmUsuario frmPai, String mao, String dedo) {
        initComponents();

        m_image = new ImagePanel();
        m_image.setSize(pnlImage.getSize());
        pnlImage.add(m_image);

        this.frmPai = frmPai;
        this.dedo = dedo;
        this.mao = mao;

        try {
            m_collection = UareUGlobal.GetReaderCollection();
            m_collection.GetReaders();
            if (m_collection.isEmpty()) {
                lblMsg.setText("Nenhum leitor encontrado");
            } else {
                reader = m_collection.get(0);
                try {
                    reader.Open(Reader.Priority.COOPERATIVE);
                } catch (UareUException e) {
                    System.out.println("Reader.Open()" + e);
                }
                //start capture thread
                StartCaptureThread();
            }

        } catch (UareUException ex) {
            lblMsg.setText("UareUGlobal.destroyReaderCollection()" + ex.getMessage());
        }
    }

    private void close() {
        //close reader
        try {
            reader.Close();
        } catch (UareUException e) {
            System.out.println("Reader.Close()" + e);
        }
        if (frmPai != null) {
            frmPai.setEnabled(true);
        }
        dispose();
    }

    private void StartCaptureThread() {
        captureThread = new CaptureThread(reader, false, Fid.Format.ANSI_381_2004, Reader.ImageProcessing.IMG_PROC_DEFAULT);
        captureThread.start(this);
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
            //process result
            //event from capture thread
            CaptureThread.CaptureEvent evt = (CaptureThread.CaptureEvent) e;
            if (null != evt.capture_result) {
                //display image
                fidDigital = evt.capture_result.image;
                m_image.showImage(fidDigital, pnlImage.getSize());
                btnAceitar.setEnabled(true);
                btnRecusar.setEnabled(true);
                lblMsg.setText("Aceitar esta digital?");
            } else if (null != evt.exception) {
                //exception during capture
                System.out.println("Capture" + evt.exception);
            } else if (null != evt.reader_status) {
                System.out.println(evt.reader_status);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblMsg = new javax.swing.JLabel();
        btnAceitar = new javax.swing.JButton();
        pnlImage = new javax.swing.JPanel();
        btnRecusar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblMsg.setText("Insira o seu dedo no leitor.");

        btnAceitar.setText("Aceitar");
        btnAceitar.setEnabled(false);
        btnAceitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceitarActionPerformed(evt);
            }
        });

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

        btnRecusar.setText("Recusar");
        btnRecusar.setEnabled(false);
        btnRecusar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecusarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnRecusar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAceitar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnlImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblMsg)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMsg)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceitar)
                    .addComponent(btnRecusar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblMsg.getAccessibleContext().setAccessibleName("Insira sua digital no leitor.");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRecusarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecusarActionPerformed
        btnAceitar.setEnabled(false);
        btnRecusar.setEnabled(false);
        lblMsg.setText("Insira o seu dedo no leitor.");
        StartCaptureThread();
    }//GEN-LAST:event_btnRecusarActionPerformed

    private void btnAceitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceitarActionPerformed
        Digital digital = new Digital();
        digital.setDedo(dedo);
        digital.setMao(mao);

        Engine engine = UareUGlobal.GetEngine();
        try {
            digital.setFmdDigital(engine.CreateFmd(fidDigital, Fmd.Format.ANSI_378_2004));
        } catch (UareUException e) {
            System.out.println("Engine.CreateFmd()" + e);
        }

        frmPai.addDigital(digital);
        close();
    }//GEN-LAST:event_btnAceitarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceitar;
    private javax.swing.JButton btnRecusar;
    private javax.swing.JLabel lblMsg;
    private javax.swing.JPanel pnlImage;
    // End of variables declaration//GEN-END:variables
}
