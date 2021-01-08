package gui.intermediary;

import charactor.Discuss;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class DiscussTableModel_a extends AbstractTableModel {

    String[] columnNames = new String[] {"序号", "房屋序号", "用户名", "评论", "回复"};

    public List<Discuss> discusses = AnswerUser.discusses;

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

