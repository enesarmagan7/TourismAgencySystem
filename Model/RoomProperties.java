package TourismAgencySystem.Model;

import TourismAgencySystem.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomProperties {
    private int id;
    private String property;
    private int room_id;
    private String bed;
    private int area;

    public RoomProperties(int id, String property, int room_id, String bed, int area) {
        this.id = id;
        this.property = property;
        this.room_id = room_id;
        this.bed = bed;
        this.area = area;
    }
public RoomProperties(){

}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }
    public static boolean add(String property,int room_id,String bed,int area){
        String query="INSERT INTO room_properties(room_property,room_id,bed,area) VALUES(?,?,?,?)";
        try{
            PreparedStatement pr= DBConnector.getInstace().prepareStatement(query);
            pr.setString(1,property);
            pr.setInt(2,room_id);
            pr.setString(3,bed);
            pr.setInt(4,area);
           return pr.executeUpdate()!=-1;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    public static ArrayList<RoomProperties> getListByRoomID(int id){
        ArrayList <RoomProperties> getList=new ArrayList<>();
        String sql="SELECT * FROM room_properties WHERE room_id=?";
        RoomProperties obj=null;
        try{
            PreparedStatement pr=DBConnector.getInstace().prepareStatement(sql);
            pr.setInt(1,id);
            ResultSet rs=pr.executeQuery();
            while(rs.next()){
                obj=new RoomProperties();
                obj.setRoom_id(rs.getInt("room_id"));
                obj.setArea(rs.getInt("area"));
                obj.setBed(rs.getString("bed"));
                obj.setProperty(rs.getString("room_property"));
                obj.setId(rs.getInt("id"));
                getList.add(obj);
            }
        }catch(SQLException e){
           e.printStackTrace();
        }
        return getList;
    }
}
