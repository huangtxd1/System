package gui.user;

import charactor.Discuss;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class DiscussTableModel extends AbstractTableModel {

    String[] columnNames = new String[] {"���", "�������", "�û���", "����", "�ظ�"};

    public List<Discuss> discusses = HR_1_1AHR_2_1.discusses;

    @Override
    public int getRowCount() {
        return discusses.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    public String getColumnName(int columnIndex) {
        // TODO Auto-generated method stub
        return columnNames[columnIndex];
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Discuss discuss = discusses.get(rowIndex);
        if(columnIndex == 0){
            return discuss.id;
        }
        if (columnIndex == 1){
            return discuss.houseId;
        }
        if (columnIndex ==2){
            return discuss.userName;
        }
        if (columnIndex == 3){
            return discuss.problem;
        }
        if (columnIndex == 4){
            return discuss.answer;
        }
        return null;
    }
}
