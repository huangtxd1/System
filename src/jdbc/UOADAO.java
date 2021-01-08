package jdbc;

import charactor.User;

import java.util.List;

public interface UOADAO {

    //����
    public void add(User user);
    //�޸�
    public void update(User user);
    //ɾ��
    public void delete(int id);
    //��ȡ
    public User get(int id);
    //��ѯ
    public List<User> list();
    //��ҳ��ѯ
    public List<User> list(int start, int count);
    //����
    public boolean duplicateChecking(String name);
}
