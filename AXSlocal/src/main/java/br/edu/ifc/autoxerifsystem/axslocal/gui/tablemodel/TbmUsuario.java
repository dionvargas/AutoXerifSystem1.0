package br.edu.ifc.autoxerifsystem.axslocal.gui.tablemodel;

/**
 *
 * @author Dionathan
 * @since 25/04/2017
 *
 */
import br.edu.ifc.autoxerifsystem.axslocal.model.Curso;
import br.edu.ifc.autoxerifsystem.axslocal.model.TipoUsuario;
import br.edu.ifc.autoxerifsystem.axslocal.model.Turma;
import br.edu.ifc.autoxerifsystem.axslocal.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TbmUsuario extends AbstractTableModel {

    private List<Usuario> linhas;
    // Array com os nomes das colunas.
    private String[] colunas = new String[]{"#", "Nome", "Código", "Tipo de Usuário", "Email", "Telefone", "Curso", "Turma"};

    // Constantes representando o índice das colunas
    private static final int ID = 0;
    private static final int NOME = 1;
    private static final int CODIGO = 2;
    private static final int TIPO_USUARIO = 3;
    private static final int EMAIL = 4;
    private static final int TELEFONE = 5;
    private static final int CURSO = 6;
    private static final int TURMA = 7;

    public TbmUsuario() {
        linhas = new ArrayList<Usuario>();
    }

    public TbmUsuario(List<Usuario> listaDePrioridades) {
        linhas = new ArrayList<Usuario>(listaDePrioridades);
    }

    @Override
    public int getRowCount() {
        return linhas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case ID:
                return String.class;
            case NOME:
                return String.class;
            case CODIGO:
                return Integer.class;
            case TIPO_USUARIO:
                return TipoUsuario.class;
            case EMAIL:
                return String.class;
            case TELEFONE:
                return String.class;
            case CURSO:
                return Curso.class;
            case TURMA:
                return Turma.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Usuario usuario = linhas.get(rowIndex);

        switch (columnIndex) {
            case ID:
                return usuario.getId();
            case NOME:
                return usuario.getNome();
            case CODIGO:
                return usuario.getCodigo();
            case TIPO_USUARIO:
                return usuario.getTipoUsuario().getDescricao();
            case EMAIL:
                return usuario.getEmail();
            case TELEFONE:
                return usuario.getTelefone();
            case CURSO:
                if (usuario.getCurso() == null) {
                    return "";
                }
                return usuario.getCurso().getNome();
            case TURMA:
                if (usuario.getTurma() == null) {
                    return "";
                }
                return usuario.getTurma().getSemestre();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Usuario usuario = linhas.get(rowIndex);

        switch (columnIndex) {
            case ID:
                usuario.setNome((String) aValue);
                break;
            case NOME:
                usuario.setNome((String) aValue);
                break;
            case CODIGO:
                usuario.setCodigo((Integer) aValue);
                break;
            case TIPO_USUARIO:
                usuario.setTipoUsuario((TipoUsuario) aValue);
                break;
            case EMAIL:
                usuario.setEmail((String) aValue);
                break;
            case TELEFONE:
                usuario.setTelefone((String) aValue);
                break;
            case CURSO:
                usuario.setCurso((Curso) aValue);
                break;
            case TURMA:
                usuario.setTurma((Turma) aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }

        fireTableCellUpdated(rowIndex, columnIndex); // Notifica a atualização da célula
    }

    // Retorna o sócio referente a linha especificada
    public Usuario getUsuario(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    // Adiciona o usuário especificado ao modelo
    public void addUsuario(Usuario curso) {
        // Adiciona o registro.
        linhas.add(curso);

        // Pega a quantidade de registros e subtrai 1 para
        // achar o último índice. A subtração é necessária
        // porque os índices começam em zero.
        int ultimoIndice = getRowCount() - 1;

        // Notifica a mudança.
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    // Remove o usuário da linha especificada.
    public void removeUsuario(int indiceLinha) {
        // Remove o registro.
        linhas.remove(indiceLinha);

        // Notifica a mudança.
        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }

    // Adiciona uma lista de usuários no final da lista.
    public void addLista(List<Usuario> prioridades) {
        // Pega o tamanho antigo da tabela, que servirá
        // como índice para o primeiro dos novos registros
        int indice = getRowCount();

        // Adiciona os registros.
        linhas.addAll(prioridades);

        // Notifica a mudança.
        fireTableRowsInserted(indice, indice + prioridades.size());
    }

    // Remove todos os registros.
    public void limpar() {
        // Remove todos os elementos da lista de usuários.
        linhas.clear();

        // Notifica a mudança.
        fireTableDataChanged();
    }
}
