package br.edu.ifc.autoxerifsystem.axslocal.gui.tablemodel;

/**
 *
 * @author Dionathan
 * @since 25/04/2017
 *
 */
import br.edu.ifc.autoxerifsystem.axslocal.model.Turma;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TbmTurma extends AbstractTableModel {

    private List<Turma> linhas;
    // Array com os nomes das colunas.
    private String[] colunas = new String[]{"#", "Semestre"};

    // Constantes representando o índice das colunas
    private static final int ID = 0;
    private static final int SEMESTRE = 1;

    public TbmTurma() {
        linhas = new ArrayList<Turma>();
    }

    public TbmTurma(List<Turma> listaDePrioridades) {
        linhas = new ArrayList<Turma>(listaDePrioridades);
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
            case SEMESTRE:
                return String.class;
            default:
                // Não deve ocorrer, pois só existem 6 colunas
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Turma entidade = linhas.get(rowIndex);

        switch (columnIndex) {
            case ID:
                return entidade.getId();
            case SEMESTRE:
                return entidade.getSemestre();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Turma entidade = linhas.get(rowIndex);

        switch (columnIndex) {
            case ID:
                entidade.setId((Integer) aValue);
                break;
            case SEMESTRE:
                entidade.setSemestre((String) aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }

        fireTableCellUpdated(rowIndex, columnIndex); // Notifica a atualização da célula
    }

    // Retorna o sócio referente a linha especificada
    public Turma getTurma(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    // Adiciona o usuário especificado ao modelo
    public void addTurma(Turma entidade) {
        // Adiciona o registro.
        linhas.add(entidade);

        // Pega a quantidade de registros e subtrai 1 para
        // achar o último índice. A subtração é necessária
        // porque os índices começam em zero.
        int ultimoIndice = getRowCount() - 1;

        // Notifica a mudança.
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    // Remove o usuário da linha especificada.
    public void removeTurma(int indiceLinha) {
        // Remove o registro.
        linhas.remove(indiceLinha);

        // Notifica a mudança.
        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }

    // Adiciona uma lista de usuários no final da lista.
    public void addLista(List<Turma> entidades) {
        // Pega o tamanho antigo da tabela, que servirá
        // como índice para o primeiro dos novos registros
        int indice = getRowCount();

        // Adiciona os registros.
        linhas.addAll(entidades);

        // Notifica a mudança.
        fireTableRowsInserted(indice, indice + entidades.size());
    }

    // Remove todos os registros.
    public void limpar() {
        // Remove todos os elementos da lista de usuários.
        linhas.clear();

        // Notifica a mudança.
        fireTableDataChanged();
    }
}
