package jdbc;

import charactor.House;

import java.util.List;

public interface HDAO {
    //����
    public void add(House house);
    //�޸�
    public void update(House house);

    public void addCollection(House house);

    public void deleteCollection(House house);

    public void changeAppointment(House house);

    public boolean isAppointment(House house);

    public List<House> getAppointment();
    //ɾ��
    public void delete(int id);
    //��ȡ
    public House get(int id);
    //��ѯ
    public List<House> list();
    //��ҳ��ѯ
    public List<House> list(int start, int count);
    //��������ѯ
    public List<House> list(int area1, int area2, String place, int price1, int price2, String format);
    //�Է������Ʋ�ѯ
    public List<House> list(String name);
}
