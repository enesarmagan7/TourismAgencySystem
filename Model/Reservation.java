package TourismAgencySystem.Model;

import TourismAgencySystem.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Reservation {
    private int id;
    private String name;
    private String phone;

    private String email;
    private String note;
    private String checkin_date;
    private String checkout_date;
    private int adult_count;
    private int child_count;
    private int total_price;
    private int room_id;

    public Reservation(int id, String name, String phone, String email, String note, String checkin_date, String checkout_date, int adult_count, int child_count, int total_price) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.note = note;
        this.checkin_date = checkin_date;
        this.checkout_date = checkout_date;
        this.adult_count = adult_count;
        this.child_count = child_count;
        this.total_price = total_price;
    }
    public Reservation(){

    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public static boolean delete(int selectedreservation) {
        String query="DELETE FROM reservation_info WHERE id=?";
        try{
            PreparedStatement pr=DBConnector.getInstace().prepareStatement(query);
            pr.setInt(1,selectedreservation);
            return pr.executeUpdate()!=-1;
        }catch (SQLException e){
            e.getMessage();

        }
        return true;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCheckin_date() {
        return checkin_date;
    }

    public void setCheckin_date(String checkin_date) {
        this.checkin_date = checkin_date;
    }

    public String getCheckout_date() {
        return checkout_date;
    }

    public void setCheckout_date(String checkout_date) {
        this.checkout_date = checkout_date;
    }

    public int getAdult_count() {
        return adult_count;
    }

    public void setAdult_count(int adult_count) {
        this.adult_count = adult_count;
    }

    public int getChild_count() {
        return child_count;
    }

    public void setChild_count(int child_count) {
        this.child_count = child_count;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }
    public static ArrayList<Reservation>getList(){
        ArrayList<Reservation>reservations=new ArrayList<>();
        Reservation obj=null;
        String sql="SELECT* FROM reservation_info";
        try{
            PreparedStatement pr= DBConnector.getInstace().prepareStatement(sql);
            ResultSet rs=pr.executeQuery();
            while(rs.next()){
                obj=new Reservation();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setEmail(rs.getString("email"));
                obj.setNote(rs.getString("note"));
                obj.setPhone(rs.getString("phone"));
                obj.setAdult_count(rs.getInt("adult_count"));
                obj.setCheckin_date(rs.getString("checkin_date"));
                obj.setCheckout_date(rs.getString("checkout_date"));
                obj.setChild_count(rs.getInt("child_count"));
                obj.setTotal_price(rs.getInt("total_price"));
                obj.setRoom_id(rs.getInt("room_id"));
                reservations.add(obj);
            }

        }catch (SQLException e){

        }
        return reservations;
    }
    public static boolean add(int room_id,String name,String email,String note,String phone,String checkin_date,String checkout_date,int adult_count,int child_count,int total_price){
        String sql="INSERT INTO reservation_info(room_id,name,phone,email,note,checkin_date,checkout_date,adult_count,child_count,total_price) VALUES(?,?,?,?,?,?,?,?,?,?);";
        try {
            PreparedStatement pr = DBConnector.getInstace().prepareStatement(sql);
            pr.setInt(1, room_id);
            pr.setString(2, name);
            pr.setString(3, phone);
            pr.setString(4, email);
            pr.setString(5, note);
            pr.setString(6, checkin_date);
            pr.setString(7, checkout_date);
            pr.setInt(8, adult_count);
            pr.setInt(9, child_count);
            pr.setInt(10, total_price);

            int response=pr.executeUpdate();
            return response!=-1;
        }catch(SQLException e){

        }
        return true;
    }
    public static int getReservationByRoomID(int room_id){
        String sql="SELECT* FROM reservation_info WHERE room_id=?";
        int id=0;
        try{
            PreparedStatement pr=DBConnector.getInstace().prepareStatement(sql);
            pr.setInt(1,room_id);
            ResultSet rs=pr.executeQuery();

            while (rs.next()){
                id=rs.getInt("room_id");
            }
        }catch (SQLException e){


        }
        return id;
    }
}
