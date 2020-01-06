package br.edu.ifc.autoxerifsystem.axslocal.gui;

import br.edu.ifc.autoxerifsystem.axslocal.dao.DAOCurso;
import br.edu.ifc.autoxerifsystem.axslocal.dao.hibernate.HBCurso;
import br.edu.ifc.autoxerifsystem.axslocal.model.Curso;

/**
 *
 * @author Dionathan Luan de Vargas
 * @since 27/09/2019
 *
 */
public class FrmCurso extends javax.swing.JFrame {

    private Curso curso;
    private Local father;
    private Boolean edit;

    public FrmCurso(Local father) {
        initComponents();

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                close();
            }
        });

        this.curso = new Curso();
        this.father = father;
        this.edit = true;

        tfId.setEnabled(false);
    }

    public FrmCurso(Local father, Curso curso, Boolean edit) {
        initComponents();

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                close();
            }
        });

        this.father = father;
        this.curso = curso;
        this.edit = edit;

        this.father.setEnabled(false);

        tfId.setText(Integer.toString(curso.getId()));
        tfId.setEnabled(false);
        tfAbreviatura.setText(curso.getAbreviatura());
        tfNome.setText(curso.getNome());

        if (!edit) {
            tfAbreviatura.setEnabled(false);
            tfNome.setEnabled(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LabelId = new javax.swing.JLabel();
        tfId = new javax.swing.JTextField();
        LabelAbreviatura = new javax.swing.JLabel();
        tfAbreviatura = new javax.swing.JTextField();
        LabelNome = new javax.swing.JLabel();
        tfNome = new javax.swing.JTextField();
        Btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        LabelId.setText("ID");

        LabelAbreviatura.setText("Abreviatura");

        LabelNome.setText("Nome");

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
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LabelAbreviatura)
                            .addComponent(LabelNome)
                            .addComponent(LabelId))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfId, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                            .addComponent(tfNome)
                            .addComponent(tfAbreviatura)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Btn)))
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
                    .addComponent(tfAbreviatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelAbreviatura))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelNome))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Btn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private Boolean _validate() {
        return true;
    }

    private void BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnActionPerformed
        if (edit) {
            if (_validate()) {
                DAOCurso daoCurso = new HBCurso();
                curso.setAbreviatura(tfAbreviatura.getText());
                curso.setNome(tfNome.getText());
                daoCurso.persistir(curso);
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
    private javax.swing.JLabel LabelAbreviatura;
    private javax.swing.JLabel LabelId;
    private javax.swing.JLabel LabelNome;
    private javax.swing.JTextField tfAbreviatura;
    private javax.swing.JTextField tfId;
    private javax.swing.JTextField tfNome;
    // End of variables declaration//GEN-END:variables
}
