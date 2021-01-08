package jdbc;

import charactor.House;

import java.sql.*;
import java.util.*;

public class HouseDAO implements HDAO{

    public HouseDAO(){
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

            String sql = "select count(*) from house";

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
    public void add(House house) {

        String sql = "insert into house values(null,?,?,?,?,?,?,?)";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setString(1, house.name);
            ps.setInt(2, house.area);
            ps.setString(3,house.place);
            ps.setInt(4,house.price);
            ps.setString(5,house.format);
            ps.setInt(6,0);
            ps.setBoolean(7,true);

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                house.id = id;
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override
    public void update(House house) {

        String sql = "update house set name= ?, area = ?, place = ?, price = ?, format = ? where id = ?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setString(1, house.name);
            ps.setInt(2,house.area);
            ps.setString(3, house.place);
            ps.setInt(4, house.price);
            ps.setString(5,house.format);
            ps.setInt(6,house.id);

            ps.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override
    public void addCollection(House house) {

        String sql = "update house set collections = ? where id = ?";
        house.collections = house.collections + 1;
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1,house.collections);
            ps.setInt(2, house.id);

            ps.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override
    public void deleteCollection(House house) {

        String sql = "update house set collections = ? where id = ?";
        house.collections = house.collections - 1;
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1,house.collections);
            ps.setInt(2, house.id);

            ps.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override
    public void changeAppointment(House house) {

        String sql = "update house set visit = ? where id = ?";
        house.visit = !house.visit;
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setBoolean(1,house.visit);
            ps.setInt(2, house.id);

            ps.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override
    public boolean isAppointment(House house) {

        String sql = "select * from house where id = ?";
        try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){

            ps.setInt(1,house.id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                return rs.getBoolean(8);
            }
        }catch (SQLException e){

            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<House> getAppointment() {

        List<House> houses = new ArrayList<>();

        String sql = "select * from house where visit = ?";
        try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){

            ps.setBoolean(1,false);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                House house = new House();
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int area = rs.getInt(3);
                String place = rs.getString(4);
                int price = rs.getInt(5);
                String format = rs.getString(6);
                int collections = rs.getInt(7);
                house.name = name;
                house.area = area;
                house.place = place;
                house.price = price;
                house.format = format;
                house.collections = collections;
                house.visit = false;
                house.id = id;
                houses.add(house);
            }
        }catch (SQLException e){

            e.printStackTrace();
        }
        return houses;
    }

    @Override
    public void delete(int id) {

        try (Connection c = getConnection(); Statement s = c.createStatement();) {

            String sql = "delete from house where id = " + id;

            s.execute(sql);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override
    public House get(int id) {

        House house = null;

        try (Connection c = getConnection(); Statement s = c.createStatement();) {

            String sql = "select * from house where id = " + id;

            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                house = new House();
                String name = rs.getString(2);
                int area = rs.getInt(3);
                String place = rs.getString(4);
                int price = rs.getInt(5);
                String format = rs.getString(6);
                int collections = rs.getInt(7);
                boolean visit = rs.getBoolean(8);
                house.name = name;
                house.area = area;
                house.place = place;
                house.price = price;
                house.format = format;
                house.collections = collections;
                house.visit = visit;
                house.id = id;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return house;
    }

    @Override
    public List<House> list() {

        return list(0, Short.MAX_VALUE);
    }

    @Override
    public List<House> list(int start, int count){

        List<House> houses = new ArrayList<House>();

        String sql = "select * from house order by id desc limit ?,? ";

        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, start);
            ps.setInt(2, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                House house = new House();
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int area = rs.getInt(3);
                String place = rs.getString(4);
                int price = rs.getInt(5);
                String format = rs.getString(6);
                int collections = rs.getInt(7);
                boolean visit = rs.getBoolean(8);
                house.id = id;
                house.name = name;
                house.area = area;
                house.place = place;
                house.price = price;
                house.format = format;
                house.collections = collections;
                house.visit = visit;
                houses.add(house);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return houses;
    }

    @Override
    public List<House> list(int area1, int area2, String place, int price1, int price2, String format) {

        List<House> houses = new ArrayList<>();//between

        String sql = "select * from house where area between ? and ? and place = ? and price between ? and ? and format = ?";

        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, area1);
            ps.setInt(2,area2);
            ps.setString(3, place);
            ps.setInt(4,price1);
            ps.setInt(5,price2);
            ps.setString(6,format);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                House house = new House();
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int area = rs.getInt(3);
                int price = rs.getInt(5);
                int collections = rs.getInt(7);
                boolean visit = rs.getBoolean(8);
                house.id = id;
                house.name = name;
                house.area = area;
                house.place = place;
                house.price = price;
                house.format = format;
                house.collections = collections;
                house.visit = visit;
                houses.add(house);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return houses;
    }

    @Override
    public List<House> list(String name) {

        List<House> houses = new ArrayList<>();

        String sql = "select * from house where name = ?";

        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                House house = new House();
                int id = rs.getInt(1);
                int area = rs.getInt(3);
                String place = rs.getString(4);
                int price = rs.getInt(5);
                String format = rs.getString(6);
                int collections = rs.getInt(7);
                boolean visit = rs.getBoolean(8);
                house.id = id;
                house.name = name;
                house.area = area;
                house.place = place;
                house.price = price;
                house.format = format;
                house.collections = collections;
                house.visit = visit;
                houses.add(house);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return  houses;
    }
}
