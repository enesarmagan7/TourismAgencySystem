package TourismAgencySystem.Model;

import TourismAgencySystem.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HotelType {
    //Değişkenkenlerin tanımlanması
    private int id;
    private String type;
    private int hotel_id;

    public HotelType(int id, String type, int hotel_id) {
        this.id = id;
        this.type = type;
        this.hotel_id = hotel_id;
    }
    public HotelType(){

    }
    //Otel tipi ekleme
public static boolean add(String type,int hotel_id){
        String sql="INSERT INTO type_hotel(type,hotel_id) VALUES(?,?)";
        try{
            PreparedStatement pr= DBConnector.getInstace().prepareStatement(sql);
            pr.setString(1,type);
            pr.setInt(2,hotel_id);
            return pr.executeUpdate()!=-1;

        } catch (SQLException e){
            e.printStackTrace();
        }
        return true;
}

//getter ve setter metotları

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }
    //Otel tiplerinin id ye göre alınması
    public static ArrayList<HotelType> getListByHotelID(int id) {
        ArrayList<HotelType> hotelTypeList=new ArrayList<>();
        HotelType obj;
        String query="SELECT* FROM  type_hotel WHERE hotel_id=? ";
        try{
            PreparedStatement pr= DBConnector.getInstace().prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs=pr.executeQuery();
            while(rs.next()){
                obj=new HotelType();
                obj.setHotel_id(rs.getInt("id"));
                obj.setType(rs.getString("type"));
                obj.setHotel_id(rs.getInt("hotel_id"));
                hotelTypeList.add(obj);

            }
        } catch (SQLException e){
           e.printStackTrace();
        }
        return hotelTypeList;
    }
    public static HotelType getFetch(int type_Id) {
        String query="SELECT * FROM type_hotel WHERE id=?";
        HotelType obj=null;
        try{
            PreparedStatement pr= DBConnector.getInstace().prepareStatement(query);
            pr.setInt(1,type_Id);
            ResultSet rs=pr.executeQuery();
            if(rs.next()){
                obj=new HotelType(rs.getInt("id"),rs.getString("type"),rs.getInt("hotel_id"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return obj;
    }
}
