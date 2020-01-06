package br.edu.ifc.autoxerifsystem.axslocal.gui;

import br.edu.ifc.autoxerifsystem.axslocal.dao.DAOSala;
import br.edu.ifc.autoxerifsystem.axslocal.dao.DAOUsuario;
import br.edu.ifc.autoxerifsystem.axslocal.dao.hibernate.HBSala;
import br.edu.ifc.autoxerifsystem.axslocal.dao.hibernate.HBUsuario;
import br.edu.ifc.autoxerifsystem.axslocal.model.Sala;
import br.edu.ifc.autoxerifsystem.axslocal.model.Usuario;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Dionathan Luan de Vargas
 * @since 27/09/2019
 *
 */
public class FrmSala extends javax.swing.JFrame {

    private Sala sala;
    private Local father;
    private Boolean edit;

    DefaultComboBoxModel comboResponsavel = new DefaultComboBoxModel();

    List<Usuario> listaResponsavel;

    public FrmSala(Local father) {
        initComponents();

        this.sala = new Sala();
        this.father = father;
        this.edit = true;

        init();

        comboResponsavel.setSelectedItem(null);
    }

    public FrmSala(Local father, Sala sala, Boolean edit) {
        initComponents();

        this.father = father;
        this.sala = sala;
        this.edit = edit;

        init();

        tfId.setText(Integer.toString(sala.getId()));

        tfNumero.setText(sala.getNumero());
        tfNome.setText(sala.getNome());
        tfIP.setText(sala.getIp());
        tfLeitor.setText(sala.getLeitor());
        comboResponsavel.setSelectedItem(sala.getResponsavel());

        if (!edit) {
            tfNumero.setEnabled(false);
            tfNome.setEnabled(false);
            tfIP.setEnabled(false);
            tfLeitor.setEnabled(false);
            cbxResponsavel.setEnabled(false);
        }
    }

    private void init() {
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                close();
            }
        });

        this.father.setEnabled(false);

        comboResponsavel = (DefaultComboBoxModel) cbxResponsavel.getModel();
        carregaComboBoxResponsavel();

        tfId.setEnabled(false);
    }

    private void carregaComboBoxResponsavel() {
        DAOUsuario usuarioDao = new HBUsuario();
        listaResponsavel = usuarioDao.getResponsaveis();
        comboResponsavel.removeAllElements();
        comboResponsavel.addElement(null);
        for (Usuario user : listaResponsavel) {
            comboResponsavel.addElement(user);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LabelId = new javax.swing.JLabel();
        tfId = new javax.swing.JTextField();
        Btn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cbxResponsavel = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tfNumero = new javax.swing.JTextField();
        tfNome = new javax.swing.JTextField();
        tfIP = new javax.swing.JTextField();
        tfLeitor = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        LabelId.setText("ID");

        Btn.setText("Salvar");
        Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnActionPerformed(evt);
            }
        });

        jLabel1.setText("Responsável");

        cbxResponsavel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("Número");

        jLabel6.setText("Nome");

        jLabel7.setText("Ip");

        jLabel8.setText("Leitor");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Btn))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel1))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel7))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(LabelId)
                                .addGap(60, 60, 60)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfId)
                            .addComponent(tfNome)
                            .addComponent(tfIP)
                            .addComponent(tfLeitor)
                            .addComponent(cbxResponsavel, 0, 265, Short.MAX_VALUE)
                            .addComponent(tfNumero))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelId))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tfLeitor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Btn)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private Boolean _validate() {
        return true;
    }

    private void BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnActionPerformed
        if (edit) {
            if (_validate()) {
                DAOSala daoSala = new HBSala();
                sala.setNumero(tfNumero.getText());
                sala.setNome(tfNome.getText());
                sala.setIp(tfIP.getText());
                sala.setLeitor(tfLeitor.getText());
                sala.setResponsavel((Usuario) comboResponsavel.getSelectedItem());
                daoSala.persistir(sala);
                close();
            }
        } else
            close();
    }//GEN-LAST:event_BtnActionPerformed

    private void close() {
        father.setEnabled(true);
        father.setFocusable(true);
        father.repaint();
        dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn;
    private javax.swing.JLabel LabelId;
    private javax.swing.JComboBox<String> cbxResponsavel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField tfIP;
    private javax.swing.JTextField tfId;
    private javax.swing.JTextField tfLeitor;
    private javax.swing.JTextField tfNome;
    private javax.swing.JTextField tfNumero;
    // End of variables declaration//GEN-END:variables
}
