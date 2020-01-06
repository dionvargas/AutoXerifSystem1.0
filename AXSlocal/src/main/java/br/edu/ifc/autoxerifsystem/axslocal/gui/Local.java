package br.edu.ifc.autoxerifsystem.axslocal.gui;

import br.edu.ifc.autoxerifsystem.axslocal.dao.DAOAcesso;
import br.edu.ifc.autoxerifsystem.axslocal.gui.tablemodel.TbmCurso;
import br.edu.ifc.autoxerifsystem.axslocal.gui.tablemodel.TbmTipoUsuario;
import br.edu.ifc.autoxerifsystem.axslocal.gui.tablemodel.TbmUsuario;
import br.edu.ifc.autoxerifsystem.axslocal.gui.tablemodel.TbmTurma;
import br.edu.ifc.autoxerifsystem.axslocal.dao.DAOCurso;
import br.edu.ifc.autoxerifsystem.axslocal.dao.DAOPermisao;
import br.edu.ifc.autoxerifsystem.axslocal.dao.DAOSala;
import br.edu.ifc.autoxerifsystem.axslocal.dao.DAOTipoUsuario;
import br.edu.ifc.autoxerifsystem.axslocal.dao.DAOTurma;
import br.edu.ifc.autoxerifsystem.axslocal.dao.DAOUsuario;
import br.edu.ifc.autoxerifsystem.axslocal.dao.hibernate.HBAcesso;
import br.edu.ifc.autoxerifsystem.axslocal.dao.hibernate.HBCurso;
import br.edu.ifc.autoxerifsystem.axslocal.dao.hibernate.HBPermisao;
import br.edu.ifc.autoxerifsystem.axslocal.dao.hibernate.HBSala;
import br.edu.ifc.autoxerifsystem.axslocal.dao.hibernate.HBTipoUsuario;
import br.edu.ifc.autoxerifsystem.axslocal.dao.hibernate.HBTurma;
import br.edu.ifc.autoxerifsystem.axslocal.dao.hibernate.HBUsuario;
import br.edu.ifc.autoxerifsystem.axslocal.gui.tablemodel.TbmAcesso;
import br.edu.ifc.autoxerifsystem.axslocal.gui.tablemodel.TbmPermisao;
import br.edu.ifc.autoxerifsystem.axslocal.gui.tablemodel.TbmSala;
import br.edu.ifc.autoxerifsystem.axslocal.model.Acesso;
import br.edu.ifc.autoxerifsystem.axslocal.model.Curso;
import br.edu.ifc.autoxerifsystem.axslocal.model.Permisao;
import br.edu.ifc.autoxerifsystem.axslocal.model.Sala;
import br.edu.ifc.autoxerifsystem.axslocal.model.TipoUsuario;
import br.edu.ifc.autoxerifsystem.axslocal.model.Turma;
import br.edu.ifc.autoxerifsystem.axslocal.model.Usuario;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Dionathan Luan de Vargas
 * @since 27/09/2019
 *
 */
public class Local extends javax.swing.JFrame {

    AbstractTableModel model;

    public Local() {
        initComponents();

        DAOAcesso AcessoDao = new HBAcesso();
        List<Acesso> listaAcesso = AcessoDao.list();

        model = new TbmAcesso(listaAcesso);
        tablePrincipal.setModel(model);
        tablePrincipal.setCellSelectionEnabled(false);
        tablePrincipal.setRowSelectionAllowed(true);
        tablePrincipal.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tablePrincipal.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        btnAbrir.setEnabled(true);
        btnEdit.setEnabled(true);

        tablePrincipal.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPricipal = new javax.swing.JScrollPane();
        tablePrincipal = new javax.swing.JTable();
        btnAbrir = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        manuBar = new javax.swing.JMenuBar();
        manuArquivo = new javax.swing.JMenu();
        manuUltimosAcessos = new javax.swing.JMenuItem();
        manuSair = new javax.swing.JMenuItem();
        menuCurso = new javax.swing.JMenu();
        menuNovoCurso = new javax.swing.JMenuItem();
        menuListaCurso = new javax.swing.JMenuItem();
        menuPermisao = new javax.swing.JMenu();
        menuNovaPermisao = new javax.swing.JMenuItem();
        menuListaPermisoes = new javax.swing.JMenuItem();
        menuSala = new javax.swing.JMenu();
        menuNovaSala = new javax.swing.JMenuItem();
        menuListaSalas = new javax.swing.JMenuItem();
        manuTipoUsuario = new javax.swing.JMenu();
        menuNovoTipoUsuario = new javax.swing.JMenuItem();
        menuListaTipoUsuario = new javax.swing.JMenuItem();
        menuTurma = new javax.swing.JMenu();
        menuNovaTurma = new javax.swing.JMenuItem();
        menuListaTurmas = new javax.swing.JMenuItem();
        menuUsuario = new javax.swing.JMenu();
        menuNovoUsuario = new javax.swing.JMenuItem();
        manuListaUsuarios = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("AutoXerifSystem");

        tablePrincipal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        panelPricipal.setViewportView(tablePrincipal);

        btnAbrir.setText("Abrir");
        btnAbrir.setEnabled(false);
        btnAbrir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnAbrirMouseReleased(evt);
            }
        });

        btnEdit.setText("Editar");
        btnEdit.setEnabled(false);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        manuArquivo.setText("Arquivo");

        manuUltimosAcessos.setText("Últimos Acessos");
        manuUltimosAcessos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manuUltimosAcessosActionPerformed(evt);
            }
        });
        manuArquivo.add(manuUltimosAcessos);

        manuSair.setText("Sair");
        manuSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                manuSairMouseReleased(evt);
            }
        });
        manuArquivo.add(manuSair);

        manuBar.add(manuArquivo);

        menuCurso.setText("Curso");

        menuNovoCurso.setText("Novo Curso...");
        menuNovoCurso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                menuNovoCursoMouseReleased(evt);
            }
        });
        menuCurso.add(menuNovoCurso);

        menuListaCurso.setText("Listar Cursos");
        menuListaCurso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                menuListaCursoMouseReleased(evt);
            }
        });
        menuCurso.add(menuListaCurso);

        manuBar.add(menuCurso);

        menuPermisao.setText("Permisão");

        menuNovaPermisao.setText("Nova Permisão...");
        menuNovaPermisao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNovaPermisaoActionPerformed(evt);
            }
        });
        menuPermisao.add(menuNovaPermisao);

        menuListaPermisoes.setText("Listar Permisões");
        menuListaPermisoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuListaPermisoesActionPerformed(evt);
            }
        });
        menuPermisao.add(menuListaPermisoes);

        manuBar.add(menuPermisao);

        menuSala.setText("Sala");

        menuNovaSala.setText("Nova Sala...");
        menuNovaSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNovaSalaActionPerformed(evt);
            }
        });
        menuSala.add(menuNovaSala);

        menuListaSalas.setText("Listar Salas");
        menuListaSalas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuListaSalasActionPerformed(evt);
            }
        });
        menuSala.add(menuListaSalas);

        manuBar.add(menuSala);

        manuTipoUsuario.setText("Tipo de Usuário");

        menuNovoTipoUsuario.setText("Novo Tipo de Usuário...");
        menuNovoTipoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNovoTipoUsuarioActionPerformed(evt);
            }
        });
        manuTipoUsuario.add(menuNovoTipoUsuario);

        menuListaTipoUsuario.setText("Listar Tipos de Usuário");
        menuListaTipoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuListaTipoUsuarioActionPerformed(evt);
            }
        });
        manuTipoUsuario.add(menuListaTipoUsuario);

        manuBar.add(manuTipoUsuario);

        menuTurma.setText("Turma");

        menuNovaTurma.setText("Nova Turma...");
        menuNovaTurma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNovaTurmaActionPerformed(evt);
            }
        });
        menuTurma.add(menuNovaTurma);

        menuListaTurmas.setText("Listar Turmas");
        menuListaTurmas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuListaTurmasActionPerformed(evt);
            }
        });
        menuTurma.add(menuListaTurmas);

        manuBar.add(menuTurma);

        menuUsuario.setText("Usuário");

        menuNovoUsuario.setText("Novo Usuário...");
        menuNovoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNovoUsuarioActionPerformed(evt);
            }
        });
        menuUsuario.add(menuNovoUsuario);

        manuListaUsuarios.setText("Lista Usuários");
        manuListaUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manuListaUsuariosActionPerformed(evt);
            }
        });
        menuUsuario.add(manuListaUsuarios);

        manuBar.add(menuUsuario);

        setJMenuBar(manuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelPricipal)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 715, Short.MAX_VALUE)
                        .addComponent(btnEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAbrir)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelPricipal, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAbrir)
                    .addComponent(btnEdit))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void manuSairMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_manuSairMouseReleased
        System.out.println("Saindo");
        this.setVisible(false);
        System.exit(0);
    }//GEN-LAST:event_manuSairMouseReleased

    private void menuListaCursoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuListaCursoMouseReleased
        DAOCurso cursoDao = new HBCurso();
        List<Curso> listaCursos = cursoDao.list();

        model = new TbmCurso(listaCursos);
        tablePrincipal.setModel(model);
        tablePrincipal.setCellSelectionEnabled(false);
        tablePrincipal.setRowSelectionAllowed(true);
        tablePrincipal.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tablePrincipal.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        btnAbrir.setEnabled(true);
        btnEdit.setEnabled(true);

        tablePrincipal.repaint();
    }//GEN-LAST:event_menuListaCursoMouseReleased

    private void menuNovoCursoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuNovoCursoMouseReleased
        FrmCurso frmCurso = new FrmCurso(this);
        frmCurso.setVisible(true);
    }//GEN-LAST:event_menuNovoCursoMouseReleased

    private void btnAbrirMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAbrirMouseReleased

        if (tablePrincipal.getModel().getClass().equals(TbmCurso.class)) {
            TbmCurso tbmCurso = (TbmCurso) model;
            Curso curso = tbmCurso.getCurso(tablePrincipal.getSelectedRow());
            FrmCurso frmCurso = new FrmCurso(this, curso, false);
            frmCurso.setVisible(true);
        } else if (tablePrincipal.getModel().getClass().equals(TbmTipoUsuario.class)) {
            TbmTipoUsuario tbmTipoUsuario = (TbmTipoUsuario) model;
            TipoUsuario tipoUsuario = tbmTipoUsuario.getTipoUsuario(tablePrincipal.getSelectedRow());
            FrmTipoUsuario frmTipoUsuario = new FrmTipoUsuario(this, tipoUsuario, false);
            frmTipoUsuario.setVisible(true);
        } else if (tablePrincipal.getModel().getClass().equals(TbmTurma.class)) {
            TbmTurma tbmTurma = (TbmTurma) model;
            Turma turma = tbmTurma.getTurma(tablePrincipal.getSelectedRow());
            FrmTurma frmTurma = new FrmTurma(this, turma, false);
            frmTurma.setVisible(true);
        } else if (tablePrincipal.getModel().getClass().equals(TbmSala.class)) {
            TbmSala tbmSala = (TbmSala) model;
            Sala sala = tbmSala.getSala(tablePrincipal.getSelectedRow());
            FrmSala frmSala = new FrmSala(this, sala, false);
            frmSala.setVisible(true);
        } else if (tablePrincipal.getModel().getClass().equals(TbmPermisao.class)) {
            TbmPermisao tbmPermisao = (TbmPermisao) model;
            Permisao permisao = tbmPermisao.getPermisao(tablePrincipal.getSelectedRow());
            FrmPermisao frmPermisao = new FrmPermisao(this, permisao, false);
            frmPermisao.setVisible(true);
        } else if (tablePrincipal.getModel().getClass().equals(TbmUsuario.class)) {
            TbmUsuario tbmUsuario = (TbmUsuario) model;
            Usuario usuario = tbmUsuario.getUsuario(tablePrincipal.getSelectedRow());
            FrmUsuario frmUsuario = new FrmUsuario(this, usuario, false);
            frmUsuario.setVisible(true);
        }
    }//GEN-LAST:event_btnAbrirMouseReleased

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        if (tablePrincipal.getModel().getClass().equals(TbmCurso.class)) {
            TbmCurso tbmCurso = (TbmCurso) model;
            Curso curso = tbmCurso.getCurso(tablePrincipal.getSelectedRow());
            FrmCurso frmCurso = new FrmCurso(this, curso, true);
            frmCurso.setVisible(true);
        } else if (tablePrincipal.getModel().getClass().equals(TbmTipoUsuario.class)) {
            TbmTipoUsuario tbmTipoUsuario = (TbmTipoUsuario) model;
            TipoUsuario tipoUsuario = tbmTipoUsuario.getTipoUsuario(tablePrincipal.getSelectedRow());
            FrmTipoUsuario frmTipoUsuario = new FrmTipoUsuario(this, tipoUsuario, true);
            frmTipoUsuario.setVisible(true);
        } else if (tablePrincipal.getModel().getClass().equals(TbmTurma.class)) {
            TbmTurma tbmTurma = (TbmTurma) model;
            Turma turma = tbmTurma.getTurma(tablePrincipal.getSelectedRow());
            FrmTurma frmTurma = new FrmTurma(this, turma, true);
            frmTurma.setVisible(true);
        } else if (tablePrincipal.getModel().getClass().equals(TbmSala.class)) {
            TbmSala tbmSala = (TbmSala) model;
            Sala sala = tbmSala.getSala(tablePrincipal.getSelectedRow());
            FrmSala frmSala = new FrmSala(this, sala, true);
            frmSala.setVisible(true);
        } else if (tablePrincipal.getModel().getClass().equals(TbmPermisao.class)) {
            TbmPermisao tbmPermisao = (TbmPermisao) model;
            Permisao permisao = tbmPermisao.getPermisao(tablePrincipal.getSelectedRow());
            FrmPermisao frmPermisao = new FrmPermisao(this, permisao, true);
            frmPermisao.setVisible(true);
        } else if (tablePrincipal.getModel().getClass().equals(TbmUsuario.class)) {
            TbmUsuario tbmUsuario = (TbmUsuario) model;
            Usuario usuario = tbmUsuario.getUsuario(tablePrincipal.getSelectedRow());
            FrmUsuario frmUsuario = new FrmUsuario(this, usuario, true);
            frmUsuario.setVisible(true);
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void menuListaTipoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuListaTipoUsuarioActionPerformed
        DAOTipoUsuario tipoUsuarioDao = new HBTipoUsuario();
        List<TipoUsuario> listaTipoUsuario = tipoUsuarioDao.list();

        model = new TbmTipoUsuario(listaTipoUsuario);
        tablePrincipal.setModel(model);
        tablePrincipal.setCellSelectionEnabled(false);
        tablePrincipal.setRowSelectionAllowed(true);
        tablePrincipal.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tablePrincipal.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        btnAbrir.setEnabled(true);
        btnEdit.setEnabled(true);

        tablePrincipal.repaint();
    }//GEN-LAST:event_menuListaTipoUsuarioActionPerformed

    private void menuNovoTipoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNovoTipoUsuarioActionPerformed
        FrmTipoUsuario frmTipoUsuario = new FrmTipoUsuario(this);
        frmTipoUsuario.setVisible(true);
    }//GEN-LAST:event_menuNovoTipoUsuarioActionPerformed

    private void menuNovaTurmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNovaTurmaActionPerformed
        FrmTurma frmTurma = new FrmTurma(this);
        frmTurma.setVisible(true);
    }//GEN-LAST:event_menuNovaTurmaActionPerformed

    private void menuListaTurmasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuListaTurmasActionPerformed
        DAOTurma turmaDao = new HBTurma();
        List<Turma> listaTurma = turmaDao.list();

        model = new TbmTurma(listaTurma);
        tablePrincipal.setModel(model);
        tablePrincipal.setCellSelectionEnabled(false);
        tablePrincipal.setRowSelectionAllowed(true);
        tablePrincipal.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tablePrincipal.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        btnAbrir.setEnabled(true);
        btnEdit.setEnabled(true);

        tablePrincipal.repaint();
    }//GEN-LAST:event_menuListaTurmasActionPerformed

    private void manuListaUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manuListaUsuariosActionPerformed
        DAOUsuario usuarioDao = new HBUsuario();
        List<Usuario> listaUsuario = usuarioDao.list();

        model = new TbmUsuario(listaUsuario);
        tablePrincipal.setModel(model);
        tablePrincipal.setCellSelectionEnabled(false);
        tablePrincipal.setRowSelectionAllowed(true);
        tablePrincipal.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tablePrincipal.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        btnAbrir.setEnabled(true);
        btnEdit.setEnabled(true);

        tablePrincipal.repaint();
    }//GEN-LAST:event_manuListaUsuariosActionPerformed

    private void menuNovaPermisaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNovaPermisaoActionPerformed
        FrmPermisao frmPermisao = new FrmPermisao(this);
        frmPermisao.setVisible(true);
    }//GEN-LAST:event_menuNovaPermisaoActionPerformed

    private void menuNovaSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNovaSalaActionPerformed
        FrmSala frmSala = new FrmSala(this);
        frmSala.setVisible(true);
    }//GEN-LAST:event_menuNovaSalaActionPerformed

    private void menuListaSalasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuListaSalasActionPerformed
        DAOSala salaDao = new HBSala();
        List<Sala> listaSala = salaDao.list();

        model = new TbmSala(listaSala);
        tablePrincipal.setModel(model);
        tablePrincipal.setCellSelectionEnabled(false);
        tablePrincipal.setRowSelectionAllowed(true);
        tablePrincipal.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tablePrincipal.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        btnAbrir.setEnabled(true);
        btnEdit.setEnabled(true);

        tablePrincipal.repaint();
    }//GEN-LAST:event_menuListaSalasActionPerformed

    private void menuListaPermisoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuListaPermisoesActionPerformed
        DAOPermisao permisaoDao = new HBPermisao();
        List<Permisao> listaPermisao = permisaoDao.list();

        model = new TbmPermisao(listaPermisao);
        tablePrincipal.setModel(model);
        tablePrincipal.setCellSelectionEnabled(false);
        tablePrincipal.setRowSelectionAllowed(true);
        tablePrincipal.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tablePrincipal.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        btnAbrir.setEnabled(true);
        btnEdit.setEnabled(true);

        tablePrincipal.repaint();
    }//GEN-LAST:event_menuListaPermisoesActionPerformed

    private void menuNovoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNovoUsuarioActionPerformed
        FrmUsuario frmUsuario = new FrmUsuario(this);
        frmUsuario.setVisible(true);
    }//GEN-LAST:event_menuNovoUsuarioActionPerformed

    private void manuUltimosAcessosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manuUltimosAcessosActionPerformed
        DAOAcesso AcessoDao = new HBAcesso();
        List<Acesso> listaAcesso = AcessoDao.list();

        model = new TbmAcesso(listaAcesso);
        tablePrincipal.setModel(model);
        tablePrincipal.setCellSelectionEnabled(false);
        tablePrincipal.setRowSelectionAllowed(true);
        tablePrincipal.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tablePrincipal.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        btnAbrir.setEnabled(true);
        btnEdit.setEnabled(true);

        tablePrincipal.repaint();
    }//GEN-LAST:event_manuUltimosAcessosActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Local.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Local.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Local.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Local.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Local().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrir;
    private javax.swing.JButton btnEdit;
    private javax.swing.JMenu manuArquivo;
    private javax.swing.JMenuBar manuBar;
    private javax.swing.JMenuItem manuListaUsuarios;
    private javax.swing.JMenuItem manuSair;
    private javax.swing.JMenu manuTipoUsuario;
    private javax.swing.JMenuItem manuUltimosAcessos;
    private javax.swing.JMenu menuCurso;
    private javax.swing.JMenuItem menuListaCurso;
    private javax.swing.JMenuItem menuListaPermisoes;
    private javax.swing.JMenuItem menuListaSalas;
    private javax.swing.JMenuItem menuListaTipoUsuario;
    private javax.swing.JMenuItem menuListaTurmas;
    private javax.swing.JMenuItem menuNovaPermisao;
    private javax.swing.JMenuItem menuNovaSala;
    private javax.swing.JMenuItem menuNovaTurma;
    private javax.swing.JMenuItem menuNovoCurso;
    private javax.swing.JMenuItem menuNovoTipoUsuario;
    private javax.swing.JMenuItem menuNovoUsuario;
    private javax.swing.JMenu menuPermisao;
    private javax.swing.JMenu menuSala;
    private javax.swing.JMenu menuTurma;
    private javax.swing.JMenu menuUsuario;
    private javax.swing.JScrollPane panelPricipal;
    private javax.swing.JTable tablePrincipal;
    // End of variables declaration//GEN-END:variables
}
