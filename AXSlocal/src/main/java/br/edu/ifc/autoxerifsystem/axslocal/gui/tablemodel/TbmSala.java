package br.edu.ifc.autoxerifsystem.axslocal.gui.tablemodel;

/**
 *
 * @author Dionathan
 * @since 25/04/2017
 *
 */
import br.edu.ifc.autoxerifsystem.axslocal.model.Sala;
import br.edu.ifc.autoxerifsystem.axslocal.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TbmSala extends AbstractTableModel {

    private List<Sala> linhas;
    // Array com os nomes das colunas.
    private String[] colunas = new String[]{"#", "Número", "Nome", "Responsável", "IP", "Leitor"};

    // Constantes representando o índice das colunas
    private static final int ID = 0;
    private static final int NUMERO = 1;
    private static final int NOME = 2;
    private static final int RESPONSAVEL = 3;
    private static final int IP = 4;
    private static final int LEITOR = 5;

    public TbmSala() {
        linhas = new ArrayList<Sala>();
    }

    public TbmSala(List<Sala> listaDePrioridades) {
        linhas = new ArrayList<Sala>(listaDePrioridades);
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
            case NUMERO:
                return String.class;
            case NOME:
                return String.class;
            case RESPONSAVEL:
                return Usuario.class;
            case IP:
                return String.class;
            case LEITOR:
                return String.class;
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
        Sala sala = linhas.get(rowIndex);

        switch (columnIndex) {
            case ID:
                return sala.getId();
            case NUMERO:
                return sala.getNumero();
            case NOME:
                return sala.getNome();
            case RESPONSAVEL:
                if (sala.getResponsavel() == null) {
                    return "";
                }
                return sala.getResponsavel().getNome();
            case IP:
                if (sala.getIp() == null) {
                    return "";
                }
                return sala.getIp();
            case LEITOR:
                if (sala.getLeitor() == null) {
                    return "";
                }
                return sala.getLeitor();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Sala sala = linhas.get(rowIndex);

        switch (columnIndex) {
            case ID:
                sala.setNome((String) aValue);
                break;
            case NUMERO:
                sala.setNumero((String) aValue);
                break;
            case NOME:
                sala.setNome((String) aValue);
                break;
            case RESPONSAVEL:
                sala.setResponsavel((Usuario) aValue);
                break;
            case IP:
                sala.setIp((String) aValue);
                break;
            case LEITOR:
                sala.setLeitor((String) aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }

        fireTableCellUpdated(rowIndex, columnIndex); // Notifica a atualização da célula
    }

    // Retorna o sócio referente a linha especificada
    public Sala getSala(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    // Adiciona o usuário especificado ao modelo
    public void addSala(Sala curso) {
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
    public void removeSala(int indiceLinha) {
        // Remove o registro.
        linhas.remove(indiceLinha);

        // Notifica a mudança.
        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }

    // Adiciona uma lista de usuários no final da lista.
    public void addLista(List<Sala> prioridades) {
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
