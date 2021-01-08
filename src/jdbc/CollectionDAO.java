package jdbc;

import charactor.House;
import charactor.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CollectionDAO implements CDAO{

    public CollectionDAO(){
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

            String sql = "select count(*) from collection";

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
    public void add(String userName, House house) {

        String sql = "insert into collection values(null,?,?)";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setString(1, userName);
            ps.setInt(2, house.id);

            ps.execute();
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override
    public void delete(String userName, House house) {

        String sql = "delete from collection where username = ? and houseid = ?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setString(1,userName);
            ps.setInt(2,house.id);

            ps.execute();
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public void delete(House house){

        String sql = "delete from collection where houseid = ?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1,house.id);

            ps.execute();
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public void delete(User user){

        String sql = "delete from collection where username = ?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setString(1,user.name);

            ps.execute();
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override
    public List<House> get(String userName) {

        List<House> houses = new ArrayList<>();

        String sql = "select * from collection where username = ?";
        try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){

            ps.setString(1,userName);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                int houseid = rs.getInt(3);
                houses.add(new HouseDAO().get(houseid));
            }
        }catch (SQLException e){

            e.printStackTrace();
        }
        return houses;
    }

    @Override
    public boolean isCollection(String userName, House house) {

        String sql = "select * from collection where username = ? and houseid = ?";
        try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){

            ps.setString(1,userName);
            ps.setInt(2,house.id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                return true;
            }

            return false;
        }catch (SQLException e){

            e.printStackTrace();
        }
        return false;
    }
}
