package gui.administarators;

import charactor.Discuss;
import charactor.User;
import jdbc.UserOrAdministratorsDAO;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class UserTableModel extends AbstractTableModel {

    String[] columnNames = new String[] {"–Ú∫≈", "”√ªß√˚", "√‹¬Î"};

    public List<User> users = new UserOrAdministratorsDAO().list();

    @Override
    public int getRowCount() {
        return users.size();
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
        User user = users.get(rowIndex);
        if(columnIndex == 0){
            return user.id;
        }
        if (columnIndex == 1){
            return user.name;
        }
        if (columnIndex ==2){
            return user.password;
        }
        return null;
    }
}
