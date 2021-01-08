package jdbc;

import charactor.House;

import java.util.List;

public interface HDAO {
    //增加
    public void add(House house);
    //修改
    public void update(House house);

    public void addCollection(House house);

    public void deleteCollection(House house);

    public void changeAppointment(House house);

    public boolean isAppointment(House house);

    public List<House> getAppointment();
    //删除
    public void delete(int id);
    //获取
    public House get(int id);
    //查询
    public List<House> list();
    //分页查询
    public List<House> list(int start, int count);
    //按条件查询
    public List<House> list(int area1, int area2, String place, int price1, int price2, String format);
    //以房屋名称查询
    public List<House> list(String name);
}
