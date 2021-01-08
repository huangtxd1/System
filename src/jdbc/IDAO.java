package jdbc;

import charactor.Intermediary;
import charactor.User;

import java.util.List;

public interface IDAO {

    //增加
    public void add(Intermediary intermediary);
    //修改
    public void update(Intermediary intermediary);
    //删除
    public void delete(int id);
    //获取
    public Intermediary get(int id);
    //查询
    public List<Intermediary> list();
    //分页查询
    public List<Intermediary> list(int start, int count);
    //查重
    public boolean duplicateChecking(String name);
}
