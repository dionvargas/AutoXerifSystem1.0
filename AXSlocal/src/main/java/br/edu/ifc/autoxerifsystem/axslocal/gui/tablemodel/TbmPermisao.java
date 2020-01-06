package br.edu.ifc.autoxerifsystem.axslocal.gui.tablemodel;

/**
 *
 * @author Dionathan
 * @since 25/04/2017
 *
 */
import br.edu.ifc.autoxerifsystem.axslocal.model.Permisao;
import br.edu.ifc.autoxerifsystem.axslocal.model.Sala;
import br.edu.ifc.autoxerifsystem.axslocal.model.Usuario;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TbmPermisao extends AbstractTableModel {

    private List<Permisao> linhas;
    // Array com os nomes das colunas.
    private String[] colunas = new String[]{"#", "Sala", "Usuário", "Dia da semana", "Hora de entrada", "Hora de saída"};

    // Constantes representando o índice das colunas
    private static final int ID = 0;
    private static final int SALA = 1;
    private static final int USUARIO = 2;
    private static final int DIA_SEMANA = 3;
    private static final int HORA_ENTRADA = 4;
    private static final int HORA_SAIDA = 5;

    public TbmPermisao() {
        linhas = new ArrayList<Permisao>();
    }

    public TbmPermisao(List<Permisao> listaDePrioridades) {
        linhas = new ArrayList<Permisao>(listaDePrioridades);
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
            case SALA:
                return Sala.class;
            case USUARIO:
                return Usuario.class;
            case DIA_SEMANA:
                return String.class;
            case HORA_ENTRADA:
                return LocalTime.class;
            case HORA_SAIDA:
                return LocalTime.class;
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
        Permisao permisao = linhas.get(rowIndex);

        switch (columnIndex) {
            case ID:
                return permisao.getId();
            case SALA:
                return permisao.getSala().toString();
            case USUARIO:
                return permisao.getUsuario().getNome();
            case DIA_SEMANA:
                return permisao.getDiaSemana();
            case HORA_ENTRADA:
                Calendar calEntrada = Calendar.getInstance();
                calEntrada.setTime(permisao.getEntrada());
                String horaEntrada = "";
                if (calEntrada.get(Calendar.HOUR_OF_DAY) < 10) {
                    horaEntrada = "0" + calEntrada.get(Calendar.HOUR_OF_DAY);
                } else {
                    horaEntrada = "" + calEntrada.get(Calendar.HOUR_OF_DAY);
                }
                if (calEntrada.get(Calendar.MINUTE) < 10) {
                    horaEntrada += ":0" + calEntrada.get(Calendar.MINUTE);
                } else {
                    horaEntrada += ":" + calEntrada.get(Calendar.MINUTE);
                }
                return horaEntrada;
            case HORA_SAIDA:
                Calendar calSaida = Calendar.getInstance();
                calSaida.setTime(permisao.getSaida());
                String horaSaida = "";
                if (calSaida.get(Calendar.HOUR_OF_DAY) < 10) {
                    horaSaida = "0" + calSaida.get(Calendar.HOUR_OF_DAY);
                } else {
                    horaSaida = "" + calSaida.get(Calendar.HOUR_OF_DAY);
                }
                if (calSaida.get(Calendar.MINUTE) < 10) {
                    horaSaida += ":0" + calSaida.get(Calendar.MINUTE);
                } else {
                    horaSaida += ":" + calSaida.get(Calendar.MINUTE);
                }
                return horaSaida;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Permisao permisao = linhas.get(rowIndex);

        switch (columnIndex) {
            case ID:
                permisao.setId((Integer) aValue);
                break;
            case SALA:
                permisao.setSala((Sala) aValue);
                break;
            case USUARIO:
                permisao.setUsuario((Usuario) aValue);
                break;
            case DIA_SEMANA:
                permisao.setDiaSemana((String) aValue);
                break;
            case HORA_ENTRADA:
                permisao.setEntrada((Date) aValue);
                break;
            case HORA_SAIDA:
                permisao.setSaida((Date) aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }

        fireTableCellUpdated(rowIndex, columnIndex); // Notifica a atualização da célula
    }

    // Retorna o sócio referente a linha especificada
    public Permisao getPermisao(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    // Adiciona o usuário especificado ao modelo
    public void addPermisao(Permisao permisao) {
        // Adiciona o registro.
        linhas.add(permisao);

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
    public void addLista(List<Permisao> prioridades) {
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
