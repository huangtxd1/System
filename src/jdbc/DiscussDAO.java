package jdbc;

import charactor.Discuss;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiscussDAO implements DDAO{

    public DiscussDAO(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8", "root",
                "admin");
    }

    public int getTotal() {
        int total = 0;
        try (Connection c = getConnection(); Statement s = c.createStatement();) {

            String sql = "select count(*) from discuss";

            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }

            System.out.println("total:" + total);

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return total;
    }

    @Override
    public void add(Discuss discuss) {

        String sql = "insert into discuss values(null,?,?,?,null )";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, discuss.houseId);
            ps.setString(2, discuss.userName);
            ps.setString(3,discuss.problem);

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                discuss.id = id;
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override
    public void addAnswer(Discuss discuss, String answer) {
        //System.out.println("´«µÝºó£º" + discuss.id);
        String sql = "update discuss set answer = ? where id = ?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){
            //System.out.println(discuss.id);
            ps.setString(1,answer);
            ps.setInt(2,discuss.id);

            ps.execute();
        }catch (SQLException e){

            e.printStackTrace();
        }
    }

    @Override
    public List<Discuss> houseIDGet(int houseID) {

        List<Discuss> discusses = new ArrayList<>();

        String sql = "select * from discuss where houseid = ?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){

            ps.setInt(1,houseID);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Discuss discuss = new Discuss();
                discuss.id = rs.getInt(1);
                discuss.houseId = houseID;
                discuss.userName = rs.getString(3);
                discuss.problem = rs.getString(4);
                discuss.answer = rs.getString(5);
                discusses.add(discuss);
            }
        }catch (SQLException e){

            e.printStackTrace();
        }
        return discusses;
    }

    @Override
    public List<Discuss> getAll() {

        List<Discuss> discusses = new ArrayList<>();

        String sql = "select * from discuss";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Discuss discuss = new Discuss();
                discuss.id = rs.getInt(1);
                //System.out.println(discuss.id);
                discuss.houseId = rs.getInt(2);
                discuss.userName = rs.getString(3);
                discuss.problem = rs.getString(4);
                discuss.answer = rs.getString(5);
                if (discuss.answer == null){
                    discusses.add(discuss);
                }
            }
        }catch (SQLException e){

            e.printStackTrace();
        }
        return discusses;
    }

    @Override
    public boolean isAnswer(Discuss discuss) {

        String sql = "select * from discuss where id = ?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){

            ps.setInt(1,discuss.id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                if (rs.getString(5) == null){
                    return false;
                }
            }
        }catch (SQLException e){

            e.printStackTrace();
        }
        return true;
    }
}
