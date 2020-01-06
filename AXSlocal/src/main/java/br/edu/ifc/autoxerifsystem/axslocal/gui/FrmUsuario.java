package br.edu.ifc.autoxerifsystem.axslocal.gui;

import br.edu.ifc.autoxerifsystem.axslocal.dao.DAOCurso;
import br.edu.ifc.autoxerifsystem.axslocal.dao.DAODigital;
import br.edu.ifc.autoxerifsystem.axslocal.dao.DAOTipoUsuario;
import br.edu.ifc.autoxerifsystem.axslocal.dao.DAOTurma;
import br.edu.ifc.autoxerifsystem.axslocal.dao.DAOUsuario;
import br.edu.ifc.autoxerifsystem.axslocal.dao.hibernate.HBCurso;
import br.edu.ifc.autoxerifsystem.axslocal.dao.hibernate.HBDigital;
import br.edu.ifc.autoxerifsystem.axslocal.dao.hibernate.HBTipoUsuario;
import br.edu.ifc.autoxerifsystem.axslocal.dao.hibernate.HBTurma;
import br.edu.ifc.autoxerifsystem.axslocal.dao.hibernate.HBUsuario;
import br.edu.ifc.autoxerifsystem.axslocal.model.Curso;
import br.edu.ifc.autoxerifsystem.axslocal.model.Digital;
import br.edu.ifc.autoxerifsystem.axslocal.model.TipoUsuario;
import br.edu.ifc.autoxerifsystem.axslocal.model.Turma;
import br.edu.ifc.autoxerifsystem.axslocal.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Dionathan Luan de Vargas
 * @since 27/09/2019
 *
 */
public class FrmUsuario extends javax.swing.JFrame {

    private Usuario usuario;
    private Local father;
    private Boolean edit;

    DefaultComboBoxModel comboTipoUsuario = new DefaultComboBoxModel();
    DefaultComboBoxModel comboCurso = new DefaultComboBoxModel();
    DefaultComboBoxModel comboTurma = new DefaultComboBoxModel();

    List<TipoUsuario> listaTipoUsuario;
    List<Curso> listaCurso;
    List<Turma> listaTurma;
    List<Digital> listDigital;

    public FrmUsuario(Local father) {
        initComponents();

        this.usuario = new Usuario();
        this.father = father;
        this.edit = true;

        listDigital = new ArrayList();

        init();
    }

    public FrmUsuario(Local father, Usuario usuario, Boolean edit) {
        initComponents();

        this.father = father;
        this.usuario = usuario;
        this.edit = edit;

        DAODigital digitalDao = new HBDigital();
        listDigital = digitalDao.getDigitais(usuario);

        init();

        tfId.setText(Integer.toString(usuario.getId()));
        tfCodigo.setText(Integer.toString(usuario.getCodigo()));
        tfNome.setText(usuario.getNome());
        comboTipoUsuario.setSelectedItem(usuario.getTipoUsuario());
        tfEmail.setText(usuario.getEmail());
        tfTelefone.setText(usuario.getTelefone());
        comboCurso.setSelectedItem(usuario.getCurso());
        comboTurma.setSelectedItem(usuario.getTurma());

        if (!edit) {
            //tfAbreviatura.setEnabled(false);
            tfCodigo.setEnabled(false);
            tfNome.setEnabled(false);
            cbxTipoUsuario.setEnabled(false);
            tfEmail.setEnabled(false);
            tfTelefone.setEnabled(false);
            cbxCurso.setEnabled(false);
            cbxTurma.setEnabled(false);
            btnDPo.setEnabled(false);
            btnDIn.setEnabled(false);
            btnDMe.setEnabled(false);
            btnDAn.setEnabled(false);
            btnDMi.setEnabled(false);
            btnEPo.setEnabled(false);
            btnEIn.setEnabled(false);
            btnEMe.setEnabled(false);
            btnEAn.setEnabled(false);
            btnEMi.setEnabled(false);
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

        comboTipoUsuario = (DefaultComboBoxModel) cbxTipoUsuario.getModel();
        carregaComboBoxTipoUsuario();

        comboCurso = (DefaultComboBoxModel) cbxCurso.getModel();
        carregaComboBoxCurso();

        comboTurma = (DefaultComboBoxModel) cbxTurma.getModel();
        carregaComboBoxTurma();

        atualizaCheck();

        tfId.setEnabled(false);
    }

    private void carregaComboBoxTipoUsuario() {
        DAOTipoUsuario tipoUsuarioDao = new HBTipoUsuario();
        listaTipoUsuario = tipoUsuarioDao.list();
        comboTipoUsuario.removeAllElements();
        comboTipoUsuario.addElement(null);
        for (TipoUsuario tipoUsuario : listaTipoUsuario) {
            comboTipoUsuario.addElement(tipoUsuario);
        }
    }

    private void carregaComboBoxCurso() {
        DAOCurso cursoDao = new HBCurso();
        listaCurso = cursoDao.list();
        comboCurso.removeAllElements();
        comboCurso.addElement(null);
        for (Curso curso : listaCurso) {
            comboCurso.addElement(curso);
        }
    }

    private void carregaComboBoxTurma() {
        DAOTurma turmaDao = new HBTurma();
        listaTurma = turmaDao.list();
        comboTurma.removeAllElements();
        comboTurma.addElement(null);
        for (Turma turma : listaTurma) {
            comboTurma.addElement(turma);
        }
    }

    private void atualizaCheck() {

//        System.out.println("#############");
//        for (Digital d : listDigital) {
//            System.out.println("Mão: " + d.getMao());
//            System.out.println("Dedo: " + d.getDedo());
//            System.out.println("Usuario: " + d.getUsuario().getNome());
//        }
//        System.out.println("#############");
        for (Digital d : listDigital) {
            if ("Direita".equals(d.getMao())) {
                if (null != d.getDedo()) {
                    switch (d.getDedo()) {
                        case "Polegar":
                            cbDPo.setSelected(true);
                            cbDPo.repaint();
                            btnDPo.setEnabled(false);
                            btnDPo.repaint();
                            break;
                        case "Indicador":
                            cbDIn.setSelected(true);
                            cbDIn.repaint();
                            btnDIn.setEnabled(false);
                            btnDIn.repaint();
                            break;
                        case "Médio":
                            cbDMe.setSelected(true);
                            cbDMe.repaint();
                            btnDMe.setEnabled(false);
                            btnDMe.repaint();
                            break;
                        case "Anelar":
                            cbDAn.setSelected(true);
                            cbDAn.repaint();
                            btnDAn.setEnabled(false);
                            btnDAn.repaint();
                            break;
                        case "Mínimo":
                            cbDMi.setSelected(true);
                            cbDMi.repaint();
                            btnDMi.setEnabled(false);
                            btnDMi.repaint();
                            break;
                        default:
                            break;
                    }
                }
            } else if ("Esquerda".equals(d.getMao())) {
                if (null != d.getDedo()) {
                    switch (d.getDedo()) {
                        case "Polegar":
                            cbEPo.setSelected(true);
                            cbEPo.repaint();
                            btnEPo.setEnabled(false);
                            btnEPo.repaint();
                            break;
                        case "Indicador":
                            cbEIn.setSelected(true);
                            cbEIn.repaint();
                            btnEIn.setEnabled(false);
                            btnEIn.repaint();
                            break;
                        case "Médio":
                            cbEMe.setSelected(true);
                            cbEMe.repaint();
                            btnEMe.setEnabled(false);
                            btnEMe.repaint();
                            break;
                        case "Anelar":
                            cbEAn.setSelected(true);
                            cbEAn.repaint();
                            btnEAn.setEnabled(false);
                            btnEAn.repaint();
                            break;
                        case "Mínimo":
                            cbEMi.setSelected(true);
                            cbEMi.repaint();
                            btnEMi.setEnabled(false);
                            btnEMi.repaint();
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    public void addDigital(Digital digital) {
        digital.fmbToBinary();
        listDigital.add(digital);
        atualizaCheck();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnlImage = new javax.swing.JPanel();
        Btn = new javax.swing.JButton();
        LabelId = new javax.swing.JLabel();
        tfId = new javax.swing.JTextField();
        LabelNome = new javax.swing.JLabel();
        tfNome = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        cbDPo = new javax.swing.JCheckBox();
        btnDPo = new javax.swing.JButton();
        cbDIn = new javax.swing.JCheckBox();
        btnDIn = new javax.swing.JButton();
        cbDMe = new javax.swing.JCheckBox();
        btnDMe = new javax.swing.JButton();
        cbDAn = new javax.swing.JCheckBox();
        btnDAn = new javax.swing.JButton();
        cbDMi = new javax.swing.JCheckBox();
        btnDMi = new javax.swing.JButton();
        cbxCurso = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        LabelNome1 = new javax.swing.JLabel();
        tfCodigo = new javax.swing.JTextField();
        LabelNome2 = new javax.swing.JLabel();
        tfEmail = new javax.swing.JTextField();
        LabelNome3 = new javax.swing.JLabel();
        tfTelefone = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cbxTipoUsuario = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cbxTurma = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        cbEPo = new javax.swing.JCheckBox();
        btnEPo = new javax.swing.JButton();
        cbEIn = new javax.swing.JCheckBox();
        btnEIn = new javax.swing.JButton();
        cbEMe = new javax.swing.JCheckBox();
        btnEMe = new javax.swing.JButton();
        cbEAn = new javax.swing.JCheckBox();
        btnEAn = new javax.swing.JButton();
        cbEMi = new javax.swing.JCheckBox();
        btnEMi = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        pnlImage.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlImageLayout = new javax.swing.GroupLayout(pnlImage);
        pnlImage.setLayout(pnlImageLayout);
        pnlImageLayout.setHorizontalGroup(
            pnlImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 128, Short.MAX_VALUE)
        );
        pnlImageLayout.setVerticalGroup(
            pnlImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 168, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        Btn.setText("Salvar");
        Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnActionPerformed(evt);
            }
        });

        LabelId.setText("ID");

        LabelNome.setText("Nome");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Mão Direita"));

        cbDPo.setText("Polegar");
        cbDPo.setEnabled(false);

        btnDPo.setText("Cadastrar");
        btnDPo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDPoActionPerformed(evt);
            }
        });

        cbDIn.setText("Indicador");
        cbDIn.setEnabled(false);

        btnDIn.setText("Cadastrar");
        btnDIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDInActionPerformed(evt);
            }
        });

        cbDMe.setText("Médio");
        cbDMe.setEnabled(false);

        btnDMe.setText("Cadastrar");
        btnDMe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDMeActionPerformed(evt);
            }
        });

        cbDAn.setText("Anelar");
        cbDAn.setEnabled(false);

        btnDAn.setText("Cadastrar");
        btnDAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDAnActionPerformed(evt);
            }
        });

        cbDMi.setText("Mínimo");
        cbDMi.setEnabled(false);

        btnDMi.setText("Cadastrar");
        btnDMi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDMiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbDIn)
                    .addComponent(cbDPo)
                    .addComponent(cbDMe)
                    .addComponent(cbDAn)
                    .addComponent(cbDMi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnDMi)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDPo)
                            .addComponent(btnDIn)
                            .addComponent(btnDMe)
                            .addComponent(btnDAn))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbDPo)
                    .addComponent(btnDPo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbDIn)
                    .addComponent(btnDIn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDMe)
                    .addComponent(cbDMe))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDAn)
                    .addComponent(cbDAn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDMi)
                    .addComponent(cbDMi)))
        );

        cbxCurso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("Curso");

        LabelNome1.setText("Código");

        LabelNome2.setText("E-mail");

        LabelNome3.setText("Telefone");

        jLabel2.setText("Tipo de Usuário");

        cbxTipoUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Turma");

        cbxTurma.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Mão Esquerda"));

        cbEPo.setText("Polegar");
        cbEPo.setEnabled(false);

        btnEPo.setText("Cadastrar");
        btnEPo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEPoActionPerformed(evt);
            }
        });

        cbEIn.setText("Indicador");
        cbEIn.setEnabled(false);

        btnEIn.setText("Cadastrar");
        btnEIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEInActionPerformed(evt);
            }
        });

        cbEMe.setText("Médio");
        cbEMe.setEnabled(false);

        btnEMe.setText("Cadastrar");
        btnEMe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEMeActionPerformed(evt);
            }
        });

        cbEAn.setText("Anelar");
        cbEAn.setEnabled(false);

        btnEAn.setText("Cadastrar");
        btnEAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEAnActionPerformed(evt);
            }
        });

        cbEMi.setText("Mínimo");
        cbEMi.setEnabled(false);

        btnEMi.setText("Cadastrar");
        btnEMi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEMiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbEIn)
                    .addComponent(cbEPo)
                    .addComponent(cbEMe)
                    .addComponent(cbEAn)
                    .addComponent(cbEMi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnEMi)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEPo)
                            .addComponent(btnEIn)
                            .addComponent(btnEMe)
                            .addComponent(btnEAn))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbEPo)
                    .addComponent(btnEPo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbEIn)
                    .addComponent(btnEIn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEMe)
                    .addComponent(cbEMe))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEAn)
                    .addComponent(cbEAn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEMi)
                    .addComponent(cbEMi)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Btn))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(LabelNome)
                                            .addComponent(LabelId))
                                        .addGap(5, 5, 5))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfNome)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(tfId, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(LabelNome1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfCodigo))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cbxCurso, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbxTurma, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(LabelNome2)
                                    .addComponent(LabelNome3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxTipoUsuario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LabelId)
                            .addComponent(tfId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LabelNome1)
                            .addComponent(tfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LabelNome)
                            .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxTipoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LabelNome2)
                            .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(LabelNome3)
                            .addComponent(tfTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(cbxTurma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                DAOUsuario daoUsuario = new HBUsuario();
                usuario.setCodigo(Integer.parseInt(tfCodigo.getText()));
                usuario.setNome(tfNome.getText());
                usuario.setTipoUsuario((TipoUsuario) comboTipoUsuario.getSelectedItem());
                usuario.setEmail(tfEmail.getText());
                usuario.setTelefone(tfTelefone.getText());
                usuario.setCurso((Curso) comboCurso.getSelectedItem());
                usuario.setTurma((Turma) comboTurma.getSelectedItem());
                daoUsuario.persistir(usuario);
                usuario = daoUsuario.getUsuario(usuario.getCodigo());
                DAODigital digitalDao = new HBDigital();
                for (Digital d : listDigital) {
                    d.setUsuario(usuario);
                    digitalDao.persistir(d);
                }
                close();
            }
        } else
            close();
    }//GEN-LAST:event_BtnActionPerformed

    private void btnDPoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDPoActionPerformed
        FrmCadastroDigital frmCadastroDigital = new FrmCadastroDigital(this, "Direita", "Polegar");
        frmCadastroDigital.setVisible(true);
    }//GEN-LAST:event_btnDPoActionPerformed

    private void btnDInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDInActionPerformed
        FrmCadastroDigital frmCadastroDigital = new FrmCadastroDigital(this, "Direita", "Indicador");
        frmCadastroDigital.setVisible(true);
    }//GEN-LAST:event_btnDInActionPerformed

    private void btnDMeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDMeActionPerformed
        FrmCadastroDigital frmCadastroDigital = new FrmCadastroDigital(this, "Direita", "Médio");
        frmCadastroDigital.setVisible(true);
    }//GEN-LAST:event_btnDMeActionPerformed

    private void btnDAnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDAnActionPerformed
        FrmCadastroDigital frmCadastroDigital = new FrmCadastroDigital(this, "Direita", "Anelar");
        frmCadastroDigital.setVisible(true);
    }//GEN-LAST:event_btnDAnActionPerformed

    private void btnDMiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDMiActionPerformed
        FrmCadastroDigital frmCadastroDigital = new FrmCadastroDigital(this, "Direita", "Mínimo");
        frmCadastroDigital.setVisible(true);
    }//GEN-LAST:event_btnDMiActionPerformed

    private void btnEPoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEPoActionPerformed
        FrmCadastroDigital frmCadastroDigital = new FrmCadastroDigital(this, "Esquerda", "Polegar");
        frmCadastroDigital.setVisible(true);
    }//GEN-LAST:event_btnEPoActionPerformed

    private void btnEInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEInActionPerformed
        FrmCadastroDigital frmCadastroDigital = new FrmCadastroDigital(this, "Esquerda", "Indicador");
        frmCadastroDigital.setVisible(true);
    }//GEN-LAST:event_btnEInActionPerformed

    private void btnEMeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEMeActionPerformed
        FrmCadastroDigital frmCadastroDigital = new FrmCadastroDigital(this, "Esquerda", "Médio");
        frmCadastroDigital.setVisible(true);
    }//GEN-LAST:event_btnEMeActionPerformed

    private void btnEAnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEAnActionPerformed
        FrmCadastroDigital frmCadastroDigital = new FrmCadastroDigital(this, "Esquerda", "Anelar");
        frmCadastroDigital.setVisible(true);
    }//GEN-LAST:event_btnEAnActionPerformed

    private void btnEMiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEMiActionPerformed
        FrmCadastroDigital frmCadastroDigital = new FrmCadastroDigital(this, "Esquerda", "Mínimo");
        frmCadastroDigital.setVisible(true);
    }//GEN-LAST:event_btnEMiActionPerformed

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
    private javax.swing.JLabel LabelNome1;
    private javax.swing.JLabel LabelNome2;
    private javax.swing.JLabel LabelNome3;
    private javax.swing.JButton btnDAn;
    private javax.swing.JButton btnDIn;
    private javax.swing.JButton btnDMe;
    private javax.swing.JButton btnDMi;
    private javax.swing.JButton btnDPo;
    private javax.swing.JButton btnEAn;
    private javax.swing.JButton btnEIn;
    private javax.swing.JButton btnEMe;
    private javax.swing.JButton btnEMi;
    private javax.swing.JButton btnEPo;
    private javax.swing.JCheckBox cbDAn;
    private javax.swing.JCheckBox cbDIn;
    private javax.swing.JCheckBox cbDMe;
    private javax.swing.JCheckBox cbDMi;
    private javax.swing.JCheckBox cbDPo;
    private javax.swing.JCheckBox cbEAn;
    private javax.swing.JCheckBox cbEIn;
    private javax.swing.JCheckBox cbEMe;
    private javax.swing.JCheckBox cbEMi;
    private javax.swing.JCheckBox cbEPo;
    private javax.swing.JComboBox<String> cbxCurso;
    private javax.swing.JComboBox<String> cbxTipoUsuario;
    private javax.swing.JComboBox<String> cbxTurma;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel pnlImage;
    private javax.swing.JTextField tfCodigo;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfId;
    private javax.swing.JTextField tfNome;
    private javax.swing.JTextField tfTelefone;
    // End of variables declaration//GEN-END:variables
}
