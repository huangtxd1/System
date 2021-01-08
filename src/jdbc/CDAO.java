package jdbc;

import charactor.House;

import java.util.List;

public interface CDAO {

    public void add(String userName, House house);

    public void delete(String userName, House house);

    public List<House> get(String userName);

    public boolean isCollection(String userName, House house);
}
