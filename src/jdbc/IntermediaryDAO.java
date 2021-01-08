package jdbc;

import charactor.Intermediary;
import charactor.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IntermediaryDAO implements IDAO{

    public IntermediaryDAO(){
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

            String sql = "select count(*) from intermediary";

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
    public void add(Intermediary intermediary) {

        String sql = "insert into intermediary values(null,?,?)";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setString(1, intermediary.name);
            ps.setString(2, intermediary.password);

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                intermediary.id = id;
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override
    public void update(Intermediary intermediary) {

        String sql = "update intermediary set name= ?, password = ? where id = ?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setString(1, intermediary.name);
            ps.setString(2, intermediary.password);
            ps.setInt(4, intermediary.id);

            ps.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {

        try (Connection c = getConnection(); Statement s = c.createStatement();) {

            String sql = "delete from intermediary where id = " + id;

            s.execute(sql);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public void delete(Intermediary intermediary){

        try (Connection c = getConnection(); Statement s = c.createStatement();) {

            String sql = "delete from intermediary where name = " + intermediary.name;

            s.execute(sql);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override
    public Intermediary get(int id) {

        Intermediary intermediary = null;

        try (Connection c = getConnection(); Statement s = c.createStatement();) {

            String sql = "select * from intermediary where id = " + id;

            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                intermediary = new Intermediary();
                String name = rs.getString(2);
                String password = rs.getString(3);
                intermediary.name = name;
                intermediary.password = password;
                intermediary.id = id;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return intermediary;
    }

    @Override
    public List<Intermediary> list() {

        return list(0, Short.MAX_VALUE);
    }

    @Override
    public List<Intermediary> list(int start, int count) {

        List<Intermediary> intermediaries = new ArrayList<Intermediary>();

        String sql = "select * from intermediary order by id desc limit ?,? ";

        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, start);
            ps.setInt(2, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Intermediary intermediary = new Intermediary();
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String password = rs.getString(3);
                intermediary.id = id;
                intermediary.name = name;
                intermediary.password = password;
                intermediaries.add(intermediary);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return intermediaries;
    }

    @Override
    public boolean duplicateChecking(String name) {

        try (Connection c = getConnection(); Statement s = c.createStatement();) {

            String sql = "select * from intermediary where name = '" + name + "'";

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

            String sql = "select * from intermediary where name = '" + name + "' and password = '" + password + "'";

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
