package jdbc;

import charactor.Intermediary;
import charactor.User;

import java.util.List;

public interface IDAO {

    //����
    public void add(Intermediary intermediary);
    //�޸�
    public void update(Intermediary intermediary);
    //ɾ��
    public void delete(int id);
    //��ȡ
    public Intermediary get(int id);
    //��ѯ
    public List<Intermediary> list();
    //��ҳ��ѯ
    public List<Intermediary> list(int start, int count);
    //����
    public boolean duplicateChecking(String name);
}
