package br.edu.ifc.autoxerifsystem.axslocal.gui.tablemodel;

/**
 *
 * @author Dionathan
 * @since 25/04/2017
 *
 */
import br.edu.ifc.autoxerifsystem.axslocal.model.Curso;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TbmCurso extends AbstractTableModel {

    private List<Curso> linhas;
    // Array com os nomes das colunas.
    private String[] colunas = new String[]{"#", "Abreviatura", "Nome"};

    // Constantes representando o índice das colunas
    private static final int ID = 0;
    private static final int ABREVIATURA = 1;
    private static final int NOME = 2;

    public TbmCurso() {
        linhas = new ArrayList<Curso>();
    }

    public TbmCurso(List<Curso> listaDePrioridades) {
        linhas = new ArrayList<Curso>(listaDePrioridades);
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
            case ABREVIATURA:
                return String.class;
            case NOME:
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
        Curso curso = linhas.get(rowIndex);

        switch (columnIndex) {
            case ID:
                return curso.getId();
            case ABREVIATURA:
                return curso.getAbreviatura();
            case NOME:
                return curso.getNome();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Curso curso = linhas.get(rowIndex);

        switch (columnIndex) {
            case ID:
                curso.setNome((String) aValue);
                break;
            case ABREVIATURA:
                curso.setAbreviatura((String) aValue);
                break;
            case NOME:
                curso.setNome((String) aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }

        fireTableCellUpdated(rowIndex, columnIndex); // Notifica a atualização da célula
    }

    // Retorna o sócio referente a linha especificada
    public Curso getCurso(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    // Adiciona o usuário especificado ao modelo
    public void addCurso(Curso curso) {
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
    public void removeCurso(int indiceLinha) {
        // Remove o registro.
        linhas.remove(indiceLinha);

        // Notifica a mudança.
        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }

    // Adiciona uma lista de usuários no final da lista.
    public void addLista(List<Curso> prioridades) {
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