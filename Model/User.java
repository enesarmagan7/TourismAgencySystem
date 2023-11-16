package TourismAgencySystem.Model;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class User {
    private int id;
    private String name;
    private String uname;
    private String pass;
    private String type;

    public User(int id, String name, String uname, String pass, String type) {
        this.id = id;
        this.name = name;
        this.uname = uname;
        this.pass = pass;
        this.type = type;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static User getFetch(String uname, String pass){
        User obj = null;
        String query = "SELECT * FROM user WHERE uname=? AND pass=?";
        if (uname.contains("@")){
            query = "SELECT * FROM user WHERE email=? AND pass=?";
        }
        try {
            PreparedStatement pr = TourismAgencySystem.Helper.DBConnector.getInstace().prepareStatement(query);
            pr.setString(1,uname);
            pr.setString(2,pass);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                switch(rs.getString("type")) {

                    case "admin":
                        obj = new Admin();
                        break;
                    case "employee":
                        obj = new Employee();
                        break;
                    default:
                        obj = new User();

                }
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setPass(rs.getString("pass"));
                obj.setUname(rs.getString("uname"));
                obj.setType(rs.getString("type"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return obj;
    }
    public static ArrayList<User> getList() {
        ArrayList<User> userList = new ArrayList<>();
        String query = "SELECT* FROM user";
      User obj;
        try {
            Statement st = TourismAgencySystem.Helper.DBConnector.getInstace().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                obj = new User();
                obj.setId(rs.getInt("ID"));
                obj.setName(rs.getString("name"));
                obj.setPass(rs.getString("uname"));
                obj.setUname(rs.getString("pass"));
                obj.setType(rs.getString("type"));
                userList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }
    public static boolean add(String name, String username, String password, String type) {
        String query = "INSERT INTO user(name,uname,pass,type) VALUES(?,?,?,?)";
        User findUser = User.controlUser(username);
        if (findUser != null) {
            JOptionPane.showMessageDialog(null, "Lütfen Tüm Alanları Doldurun", "Hata", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        try {
            PreparedStatement pr = TourismAgencySystem.Helper.DBConnector.getInstace().prepareStatement(query);

            pr.setString(1, name);
            pr.setString(2, username);
            pr.setString(3, password);
            pr.setString(4, type);

            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public static User controlUser(String username) {
        User obj = null;
        String sql = "SELECT* FROM user WHERE uname = ?";
        try {
            PreparedStatement pr = TourismAgencySystem.Helper.DBConnector.getInstace().prepareStatement(sql);
            pr.setString(1, username);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = new User();
                obj.setId(rs.getInt("ID"));
                obj.setName(rs.getString("name"));
                obj.setPass(rs.getString("uname"));
                obj.setUname(rs.getString("pass"));
                obj.setType(rs.getString("type"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }
    public static boolean delete(int id) {
        String query = "DELETE FROM user WHERE ID = ?";
        try {
            PreparedStatement pr = TourismAgencySystem.Helper.DBConnector.getInstace().prepareStatement(query);
            pr.setInt(1, id);

            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static boolean update(int ID ,String name,String username,String pass,String type){
        String query="UPDATE user SET name=?,uname=?,pass=?,type=? WHERE ID=?";
       User findUser = User.controlUser(username);
        if (findUser != null && findUser.getId()!=ID) {
            JOptionPane.showMessageDialog(null, "Bu Kullanıcı var", "Hata", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        try {
            PreparedStatement pr=TourismAgencySystem.Helper.DBConnector.getInstace().prepareStatement(query);
            pr.setString(1,name);
            pr.setString(2,username);
            pr.setString(3,pass);
            pr.setString(4,type);
            pr.setInt(5,ID);
            return pr.executeUpdate()!=1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static ArrayList<User> searchUserList(String query){
        ArrayList<User> userList = new ArrayList<>();

       User obj;
        try {
            Statement st = TourismAgencySystem.Helper.DBConnector.getInstace().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                obj = new User();
                obj.setId(rs.getInt("ID"));
                obj.setName(rs.getString("name"));
                obj.setPass(rs.getString("uname"));
                obj.setUname(rs.getString("pass"));
                obj.setType(rs.getString("type"));
                userList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }
    public static String search(String name,String username,String type){
        String query="SELECT * FROM user WHERE uname LIKE '%{{uname}}%' AND name LIKE '%{{name}}%'";
        query=query.replace("{{uname}}",username);
        query=query.replace("{{name}}",name);
        if(!type.isEmpty()) {
            query+="OR type='{{type}}'";
            query = query.replace("{{type}}", type);
        }
        return query;
    }


}
