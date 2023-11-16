package TourismAgencySystem.Model;

import TourismAgencySystem.Helper.DBConnector;
import TourismAgencySystem.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Room {
    int id;
    private  int season_id;
    private int adult_price;
    private int child_price;
    private int hotel_type_id;
    private int hotel_id;
    private int stock;
    private String room_type;
    public Room(){

    }

    public Room(int id,String room_type,int stock,int season_id, int adult_price, int child_price, int hotel_type_id, int hotel_id) {
        this.room_type=room_type;
        this.stock=stock;
        this.id=id;
        this.season_id = season_id;
        this.adult_price = adult_price;
        this.child_price = child_price;
        this.hotel_type_id = hotel_type_id;
        this.hotel_id = hotel_id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeason_id() {
        return season_id;
    }

    public void setSeason_id(int season_id) {
        this.season_id = season_id;
    }

    public int getAdult_price() {
        return adult_price;
    }

    public void setAdult_price(int adult_price) {
        this.adult_price = adult_price;
    }

    public int getChild_price() {
        return child_price;
    }

    public void setChild_price(int child_price) {
        this.child_price = child_price;
    }

    public int getHotel_type_id() {
        return hotel_type_id;
    }

    public void setHotel_type_id(int hotel_type_id) {
        this.hotel_type_id = hotel_type_id;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }
    public static ArrayList<Room> getList(){
        ArrayList<Room> getRoomList=new ArrayList<>();
        Room obj=null;
        String query="SELECT* FROM room ";
        try {
            Statement st = DBConnector.getInstace().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                obj = new Room();
                obj.setId(rs.getInt("id"));
                obj.setRoom_type(rs.getString("room_type"));
                obj.setStock(rs.getInt("stock"));
                obj.setSeason_id(rs.getInt("season_id"));
                obj.setAdult_price(rs.getInt("adult_price"));
                obj.setChild_price(rs.getInt("child_price"));
                obj.setHotel_type_id(rs.getInt("hotel_type_id"));
                obj.setHotel_id(rs.getInt("hotel_id"));
                getRoomList.add(obj);
            }
        }
            catch(SQLException ex){
                ex.printStackTrace();
            }
        return getRoomList;
    }
    public static boolean add(String room_type,int stock,int season_id,int adult_price,int child_price,int hotel_type_id,int hotel_id){
        String sql="INSERT INTO room(room_type,stock,season_id,adult_price,child_price,hotel_type_id,hotel_id) VALUES(?,?,?,?,?,?,?)";
        try{
            PreparedStatement pr=DBConnector.getInstace().prepareStatement(sql);
            pr.setString(1,room_type);
            pr.setInt(2,stock);
            pr.setInt(3,season_id);
            pr.setInt(4,adult_price);
            pr.setInt(5,child_price);
            pr.setInt(6,hotel_type_id);
            pr.setInt(7,hotel_id);
            int response=pr.executeUpdate();
            if(response==-1){
                Helper.showMsg("error");
            }
            return response!=-1;
        }catch (SQLException e){
           e.getMessage();
        }
        return true;
    }
}
