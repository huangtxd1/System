package gui.user;

import charactor.House;
import gui.Main;
import gui.intermediary.AcceptAppointment;
import jdbc.HouseDAO;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class HouseTableModel extends AbstractTableModel {

    String[] columnNames = new String[] {"id", "房屋名称", "房屋面积", "房屋地点", "房屋价格", "房屋样式", "收藏数", "是否可预约"};

    public List<House> houses = setHouses();

    public List<House> setHouses(){
        if (Main.search == 1){
            return HR_1_1.houses;
        }
        if (Main.search == 2){
            return HR_2_1.houses;
        }
        if(Main.search == 3){
            return MyCollection.houses;
        }
        if(Main.search == 4){
            return MyAppointment.houses;
        }
        if (Main.search == 5){
            return  new HouseDAO().list();
        }
        if (Main.search == 6){
            return AcceptAppointment.houses;
        }
        return new HouseDAO().list();
    }

    @Override
    public int getRowCount() {
        return houses.size();
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
        House h = houses.get(rowIndex);
        if (columnIndex == 0){
            return h.id;
        }
        if (columnIndex == 1){
            return h.name;
        }
        if(columnIndex == 2){
            return h.area;
        }
        if(columnIndex == 3){
            return h.place;
        }
        if (columnIndex == 4){
            return  h.price;
        }
        if (columnIndex == 5){
            return  h.format;
        }
        if (columnIndex == 6){
            return h.collections;
        }
        if (columnIndex == 7){
            return h.visit;
        }
        return null;
    }
}
