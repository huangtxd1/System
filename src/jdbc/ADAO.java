package jdbc;

import charactor.House;

import java.util.List;

public interface ADAO {

    public void add(String userName, House house);

    public void delete(String userName, House house);

    public List<House> get(String userName);

    public boolean isAppointment(String userName, House house);

    public String appointmentMan(House house);
}
