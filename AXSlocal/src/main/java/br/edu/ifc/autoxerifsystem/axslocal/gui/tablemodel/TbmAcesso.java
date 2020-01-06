package br.edu.ifc.autoxerifsystem.axslocal.gui.tablemodel;

/**
 *
 * @author Dionathan
 * @since 01/10/2017
 *
 */
import br.edu.ifc.autoxerifsystem.axslocal.model.Acesso;
import br.edu.ifc.autoxerifsystem.axslocal.model.Sala;
import br.edu.ifc.autoxerifsystem.axslocal.model.Usuario;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TbmAcesso extends AbstractTableModel {

    private List<Acesso> linhas;
    // Array com os nomes das colunas.
    private String[] colunas = new String[]{"#", "Sala", "Usuário", "Data", "Horário", "Regra"};

    // Constantes representando o índice das colunas
    private static final int ID = 0;
    private static final int SALA = 1;
    private static final int USUARIO = 2;
    private static final int DATA = 3;
    private static final int HORARIO = 4;
    private static final int REGRA = 5;

    public TbmAcesso() {
        linhas = new ArrayList<Acesso>();
    }

    public TbmAcesso(List<Acesso> listaDeAcessos) {
        linhas = new ArrayList<Acesso>(listaDeAcessos);
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
                return Long.class;
            case SALA:
                return Sala.class;
            case USUARIO:
                return Usuario.class;
            case DATA:
                return LocalDate.class;
            case HORARIO:
                return LocalTime.class;
            case REGRA:
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
        Acesso acesso = linhas.get(rowIndex);

        switch (columnIndex) {
            case ID:
                return acesso.getId();
            case SALA:
                return acesso.getSala().toString();
            case USUARIO:
                return acesso.getUsuario().getNome();
            case DATA:
                return acesso.getData();
            case HORARIO:
                return acesso.getHora();
            case REGRA:
                return acesso.getRegra();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Acesso acesso = linhas.get(rowIndex);

        switch (columnIndex) {
            case ID:
                acesso.setId((Integer) aValue);
                break;
            case SALA:
                acesso.setSala((Sala) aValue);
                break;
            case USUARIO:
                acesso.setUsuario((Usuario) aValue);
                break;
            case DATA:
                acesso.setEntrada((Date) aValue);
                break;
            case HORARIO:
                acesso.setEntrada((Date) aValue);
                break;
            case REGRA:
                acesso.setRegra((String) aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }

        fireTableCellUpdated(rowIndex, columnIndex); // Notifica a atualização da célula
    }

    // Retorna o sócio referente a linha especificada
    public Acesso getAcesso(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    // Adiciona o usuário especificado ao modelo
    public void addPermisao(Acesso acesso) {
        // Adiciona o registro.
        linhas.add(acesso);

        // Pega a quantidade de registros e subtrai 1 para
        // achar o último índice. A subtração é necessária
        // porque os índices começam em zero.
        int ultimoIndice = getRowCount() - 1;

        // Notifica a mudança.
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    // Remove o usuário da linha especificada.
    public void removePermisao(int indiceLinha) {
        // Remove o registro.
        linhas.remove(indiceLinha);

        // Notifica a mudança.
        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }

    // Adiciona uma lista de usuários no final da lista.
    public void addLista(List<Acesso> acesso) {
        // Pega o tamanho antigo da tabela, que servirá
        // como índice para o primeiro dos novos registros
        int indice = getRowCount();

        // Adiciona os registros.
        linhas.addAll(acesso);

        // Notifica a mudança.
        fireTableRowsInserted(indice, indice + acesso.size());
    }

    // Remove todos os registros.
    public void limpar() {
        // Remove todos os elementos da lista de usuários.
        linhas.clear();

        // Notifica a mudança.
        fireTableDataChanged();
    }
}
