package jdbc;

import charactor.User;

import java.util.List;

public interface UOADAO {

    //增加
    public void add(User user);
    //修改
    public void update(User user);
    //删除
    public void delete(int id);
    //获取
    public User get(int id);
    //查询
    public List<User> list();
    //分页查询
    public List<User> list(int start, int count);
    //查重
    public boolean duplicateChecking(String name);
}
