package jdbc;

import charactor.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserOrAdministratorsDAO implements UOADAO{

    public UserOrAdministratorsDAO(){
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

            String sql = "select count(*) from user";

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
    public void add(User user) {

        String sql = "insert into user values(null,?,?)";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setString(1, user.name);
            ps.setString(2, user.password);

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                user.id = id;
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {

        String sql = "update user set name= ?, password = ? where id = ?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setString(1, user.name);
            ps.setString(2, user.password);
            ps.setInt(4, user.id);

            ps.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {

        try (Connection c = getConnection(); Statement s = c.createStatement();) {

            String sql = "delete from user where id = " + id;

            s.execute(sql);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override
    public User get(int id) {

        User user = null;

        try (Connection c = getConnection(); Statement s = c.createStatement();) {

            String sql = "select * from user where id = " + id;

            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                user = new User();
                String name = rs.getString(2);
                String password = rs.getString(3);
                user.name = name;
                user.password = password;
                user.id = id;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> list() {

        return list(0, Short.MAX_VALUE);
    }

    @Override
    public List<User> list(int start, int count) {

        List<User> users = new ArrayList<User>();

        String sql = "select * from user order by id desc limit ?,? ";

        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, start);
            ps.setInt(2, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String password = rs.getString(3);
                user.id = id;
                user.name = name;
                user.password = password;
                users.add(user);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean duplicateChecking(String name) {

        try (Connection c = getConnection(); Statement s = c.createStatement();) {

            String sql = "select * from user where name = '" + name + "'";

            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                return false;
            }

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }

    public boolean duplicateChecking(String name , String password) {

        try (Connection c = getConnection(); Statement s = c.createStatement();) {

            String sql = "select * from user where name = '" + name + "' and password = '" + password + "'";

            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                return false;
            }

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }
}
