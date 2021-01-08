package jdbc;

import charactor.Discuss;

import java.util.List;

public interface DDAO {

    public void add(Discuss discuss);

    public void addAnswer(Discuss discuss, String answer);

    public List<Discuss> houseIDGet(int houseID);

    public List<Discuss> getAll();

    public boolean isAnswer(Discuss discuss);
}
