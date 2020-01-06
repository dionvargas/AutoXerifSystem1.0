package br.edu.ifc.autoxerifsystem.axslocal.gui;

import br.edu.ifc.autoxerifsystem.axslocal.dao.DAOPermisao;
import br.edu.ifc.autoxerifsystem.axslocal.dao.DAOSala;
import br.edu.ifc.autoxerifsystem.axslocal.dao.DAOUsuario;
import br.edu.ifc.autoxerifsystem.axslocal.dao.hibernate.HBPermisao;
import br.edu.ifc.autoxerifsystem.axslocal.dao.hibernate.HBSala;
import br.edu.ifc.autoxerifsystem.axslocal.dao.hibernate.HBUsuario;
import br.edu.ifc.autoxerifsystem.axslocal.model.Permisao;
import br.edu.ifc.autoxerifsystem.axslocal.model.Sala;
import br.edu.ifc.autoxerifsystem.axslocal.model.Usuario;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Dionathan Luan de Vargas
 * @since 27/09/2019
 *
 */
public class FrmPermisao extends javax.swing.JFrame {

    private Permisao permisao;
    private Local father;
    private Boolean edit;

    DefaultComboBoxModel comboUsuario = new DefaultComboBoxModel();
    DefaultComboBoxModel comboSala = new DefaultComboBoxModel();
    DefaultComboBoxModel comboDiaSemana = new DefaultComboBoxModel();

    List<Usuario> listaUsuario;
    List<Sala> listaSala;

    public FrmPermisao(Local father) {
        initComponents();

        this.permisao = new Permisao();
        this.father = father;
        this.edit = true;

        init();

        comboUsuario.setSelectedItem(null);
        comboSala.setSelectedItem(null);
        comboDiaSemana.setSelectedItem(null);
    }

    public FrmPermisao(Local father, Permisao permisao, Boolean edit) {
        initComponents();

        this.father = father;
        this.permisao = permisao;
        this.edit = edit;

        init();

        tfId.setText(Integer.toString(permisao.getId()));
        tfId.setEnabled(false);

        comboDiaSemana.setSelectedItem(permisao.getDiaSemana());
        comboSala.setSelectedItem(permisao.getSala());
        comboUsuario.setSelectedItem(permisao.getUsuario());

        Calendar calEntrada = Calendar.getInstance();
        calEntrada.setTime(permisao.getEntrada());
        Calendar calSaida = Calendar.getInstance();
        calSaida.setTime(permisao.getSaida());

        tfHoraEntrada.setText(Integer.toString(calEntrada.get(Calendar.HOUR_OF_DAY)));
        tfMinutoEntrada.setText(Integer.toString(calEntrada.get(Calendar.MINUTE)));
        tfHoraSaida.setText(Integer.toString(calSaida.get(Calendar.HOUR_OF_DAY)));
        tfMinutoSaida.setText(Integer.toString(calSaida.get(Calendar.MINUTE)));

        if (!edit) {
            cbxDiaSemana.setEnabled(false);
            cbxSala.setEnabled(false);
            cbxUsuario.setEnabled(false);
            tfHoraEntrada.setEnabled(false);
            tfMinutoEntrada.setEnabled(false);
            tfHoraSaida.setEnabled(false);
            tfMinutoSaida.setEnabled(false);
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

        comboUsuario = (DefaultComboBoxModel) cbxUsuario.getModel();
        carregaComboBoxUsuario();

        comboSala = (DefaultComboBoxModel) cbxSala.getModel();
        carregaComboBoxSala();

        comboDiaSemana = (DefaultComboBoxModel) cbxDiaSemana.getModel();
        carregaComboBoxDiaSemana();

        tfId.setEnabled(false);
    }

    private void carregaComboBoxUsuario() {
        DAOUsuario usuarioDao = new HBUsuario();
        listaUsuario = usuarioDao.list();
        comboUsuario.removeAllElements();
        comboUsuario.addElement(null);
        for (Usuario user : listaUsuario) {
            comboUsuario.addElement(user);
        }
    }

    private void carregaComboBoxSala() {
        DAOSala salaDao = new HBSala();
        listaSala = salaDao.list();
        comboSala.removeAllElements();
        comboSala.addElement(null);
        for (Sala sala : listaSala) {
            comboSala.addElement(sala);
        }
    }

    private void carregaComboBoxDiaSemana() {
        comboDiaSemana.removeAllElements();
        comboDiaSemana.addElement(null);
        comboDiaSemana.addElement("Domingo");
        comboDiaSemana.addElement("Segunda");
        comboDiaSemana.addElement("Terça");
        comboDiaSemana.addElement("Quarta");
        comboDiaSemana.addElement("Quinta");
        comboDiaSemana.addElement("Sexta");
        comboDiaSemana.addElement("Sábado");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LabelId = new javax.swing.JLabel();
        tfId = new javax.swing.JTextField();
        LabelNome = new javax.swing.JLabel();
        Btn = new javax.swing.JButton();
        cbxSala = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        cbxUsuario = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cbxDiaSemana = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        tfHoraEntrada = new javax.swing.JTextField();
        tfMinutoEntrada = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        tfHoraSaida = new javax.swing.JTextField();
        tfMinutoSaida = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        LabelId.setText("ID");

        LabelNome.setText("Sala");

        Btn.setText("Salvar");
        Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnActionPerformed(evt);
            }
        });

        cbxSala.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("Usuário");

        cbxUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Dia da semana");

        cbxDiaSemana.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Entrada"));

        tfHoraEntrada.setText("00");

        tfMinutoEntrada.setText("00");

        jLabel3.setText(":");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(tfHoraEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfMinutoEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(tfHoraEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(tfMinutoEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel3))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Saída"));

        tfHoraSaida.setText("00");

        tfMinutoSaida.setText("00");

        jLabel4.setText(":");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(tfHoraSaida, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfMinutoSaida, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(tfHoraSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(tfMinutoSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel4))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Btn))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 92, Short.MAX_VALUE))
                            .addComponent(cbxDiaSemana, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(LabelNome)
                            .addComponent(LabelId))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfId)
                            .addComponent(cbxSala, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxUsuario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                    .addComponent(jLabel1)
                    .addComponent(cbxUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxSala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelNome))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxDiaSemana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                DAOPermisao daoPermisao = new HBPermisao();

                Calendar calEntrada = Calendar.getInstance();
                calEntrada.set(Calendar.HOUR_OF_DAY, Integer.parseInt(tfHoraEntrada.getText()));
                calEntrada.set(Calendar.MINUTE, Integer.parseInt(tfMinutoEntrada.getText()));
                Date entrada = calEntrada.getTime();
                permisao.setEntrada(entrada);

                Calendar calSaida = Calendar.getInstance();
                calSaida.set(Calendar.HOUR_OF_DAY, Integer.parseInt(tfHoraSaida.getText()));
                calSaida.set(Calendar.MINUTE, Integer.parseInt(tfMinutoSaida.getText()));
                Date saida = calSaida.getTime();
                permisao.setSaida(saida);

                permisao.setSala((Sala) comboSala.getSelectedItem());
                permisao.setUsuario((Usuario) comboUsuario.getSelectedItem());
                permisao.setDiaSemana((String) comboDiaSemana.getSelectedItem());
                daoPermisao.persistir(permisao);
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
    private javax.swing.JComboBox<String> cbxDiaSemana;
    private javax.swing.JComboBox<String> cbxSala;
    private javax.swing.JComboBox<String> cbxUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField tfHoraEntrada;
    private javax.swing.JTextField tfHoraSaida;
    private javax.swing.JTextField tfId;
    private javax.swing.JTextField tfMinutoEntrada;
    private javax.swing.JTextField tfMinutoSaida;
    // End of variables declaration//GEN-END:variables
}
