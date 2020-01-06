package br.edu.ifc.autoxerifsystem.axslocal.gui;

import br.edu.ifc.autoxerifsystem.axslocal.dao.DAOTipoUsuario;
import br.edu.ifc.autoxerifsystem.axslocal.dao.hibernate.HBTipoUsuario;
import br.edu.ifc.autoxerifsystem.axslocal.model.TipoUsuario;

/**
 *
 * @author Dionathan Luan de Vargas
 * @since 27/09/2019
 *
 */
public class FrmTipoUsuario extends javax.swing.JFrame {

    private TipoUsuario tipoUsuario;
    private Local father;
    private Boolean edit;

    public FrmTipoUsuario(Local father) {
        initComponents();

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                close();
            }
        });

        this.tipoUsuario = new TipoUsuario();
        this.father = father;
        this.edit = true;

        tfId.setEnabled(false);
    }

    public FrmTipoUsuario(Local father, TipoUsuario tipoUsuario, Boolean edit) {
        initComponents();

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                close();
            }
        });

        this.father = father;
        this.tipoUsuario = tipoUsuario;
        this.edit = edit;

        this.father.setEnabled(false);

        tfId.setText(Integer.toString(tipoUsuario.getId()));
        tfId.setEnabled(false);
        tfDescricao.setText(tipoUsuario.getDescricao());

        if (!edit) {
            tfDescricao.setEnabled(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LabelId = new javax.swing.JLabel();
        tfId = new javax.swing.JTextField();
        LabelNome = new javax.swing.JLabel();
        tfDescricao = new javax.swing.JTextField();
        Btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        LabelId.setText("ID");

        LabelNome.setText("Descrição");

        Btn.setText("Salvar");
        Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 267, Short.MAX_VALUE)
                        .addComponent(Btn))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LabelId)
                            .addComponent(LabelNome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfDescricao)
                            .addComponent(tfId))))
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
                    .addComponent(tfDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelNome))
                .addGap(34, 34, 34)
                .addComponent(Btn)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private Boolean _validate() {
        return true;
    }

    private void BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnActionPerformed
        if (edit) {
            if (_validate()) {
                DAOTipoUsuario daoCurso = new HBTipoUsuario();
                tipoUsuario.setDescricao(tfDescricao.getText());
                daoCurso.persistir(tipoUsuario);
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
    private javax.swing.JLabel LabelNome;
    private javax.swing.JTextField tfDescricao;
    private javax.swing.JTextField tfId;
    // End of variables declaration//GEN-END:variables
}
