package TourismAgencySystem.Wiew;

import TourismAgencySystem.Helper.Config;
import TourismAgencySystem.Helper.DBConnector;
import TourismAgencySystem.Helper.Helper;
import TourismAgencySystem.Model.Reservation;
import TourismAgencySystem.Model.Room;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class ReservationGUI extends JFrame{
    private int room_id;
    private int adult_number;
    private int child_number;
    private String check_in;
    private String check_out;
    private int day;
    private JPanel wrapper;
    private JTextArea area_adres;
    private JTextField fld_phone;
    private JTextField fld_hotel_property;
    private JTextField fld_room_type;
    private JTextField fld_room_property;
    private JTextField fld_adult_number;
    private JTextField fld_child_number;
    private JTextField fld_season_start;
    private JTextField fld_season_end;
    private JTextField fld_price_fortwodays;
    private JTextField fld_total_price;
    private JTextField fld_name;
    private JTextField fld_phone_user;
    private JTextField fld_email_user;
    private JTextArea fld_note;
    private JButton btn_save_reservation;
    private JTextField fld_hotelname;
    private   String hotelname;
     private   String adres;
    private  String phone;
    private String otel_propert;
    private   String room_type;
    private  String season_start;
     private  String season_end;
    private  String room_property;


    public ReservationGUI(int room_id,int adult_number,int child_number,String check_in,String check_out){
        this.adult_number=adult_number;
        this.child_number=child_number;
        this.check_in=check_in;
        this.check_out=check_out;
        this.room_id=room_id;
        this.day=day;
        add(wrapper);
        setSize(1200,400);
        setLocation(Helper.screenCenter("x",getSize()),Helper.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        setRezervation(room_id,adult_number,child_number,check_in,check_out);


        btn_save_reservation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Rezervasyonun eklenmesi
                int price=Integer.parseInt((String) fld_total_price.getText());


                if(fld_name.getText().trim().isEmpty()||fld_phone_user.getText().trim().isEmpty()||fld_email_user.getText().trim().isEmpty()){
                    Helper.showMsg("Lütfen Boş alanları doldurun");
                }
                  else  {
                    if (Room.getRoomByID(room_id).getStock() <= 1) {

                        Room.uptadeStock(room_id, 0);

                    } else {
                        Room.uptadeStock(room_id, Room.getRoomByID(room_id).getStock() - 1);
                    }

                    if (Reservation.add(room_id, (String) fld_name.getText(), (String) fld_email_user.getText(), (String) fld_note.getText(), (String) fld_phone_user.getText(), check_in, check_out, adult_number, child_number, price)) {
                        Helper.showMsg("Rezervasyon yapıldı");
                        dispose();
                    } else {
                        Helper.showMsg("hata");
                    }
                }




            }
        });
    }
    //Rezervasyon ekleme metodu
    public void setRezervation(int hotel_id,int adult_number,int child_number,String check_in,String check_out){
        String sql="SELECT o.name , o.address , o.phone, o.hotel_property ," +
                " r.room_type, p.room_property, r.adult_price, r.child_price, s.season_start, s.season_end " +
                "FROM Room r JOIN hotel o ON r.hotel_id = o.id JOIN Season s ON r.season_id = s.id " +
                "JOIN room_properties p ON r.id=p.room_id " +
                "WHERE r.id = ?;";
        try{
            PreparedStatement pr= DBConnector.getInstace().prepareStatement(sql);
            pr.setInt(1,hotel_id);
            ResultSet rs=pr.executeQuery();
            while(rs.next()){
                hotelname=rs.getString("name");

                fld_hotelname.setText(hotelname);
                 adres=rs.getString("address");
                area_adres.setText(adres);
               phone=rs.getString("phone");
                fld_phone.setText(phone);
               otel_propert = rs.getString("hotel_property");
                fld_hotel_property.setText(otel_propert);
                 room_type=rs.getString("room_type");
                fld_room_type.setText(room_type);
                room_property=rs.getString("room_property");
                fld_room_property.setText(room_property);
                 season_start=rs.getString("season_start");
                fld_season_start.setText(season_start);
                season_end = rs.getString("season_end");
                fld_season_end.setText(season_end);

                // Gün sayısını almak için String tipteki tarih verimizi formatter yardımıyla LocalDate e çevirdik


                 fld_adult_number.setText(String.valueOf(adult_number));
                 fld_child_number.setText(String.valueOf(child_number));

                int adult_price=rs.getInt("adult_price");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate checkin = LocalDate.parse(check_in, formatter);
                LocalDate checkout = LocalDate.parse(check_out, formatter);

                long day = ChronoUnit.DAYS.between(checkin, checkout);
                int child_price=rs.getInt("child_price");

                int pricefortwodays=2*(adult_number*adult_price+child_number*child_price);
                fld_price_fortwodays.setText(String.valueOf(pricefortwodays));
                int totalPrice=(int)day*(adult_number*adult_price+child_number*child_price);
                fld_total_price.setText(String.valueOf(totalPrice));
            }
        }catch (SQLException e){

        }
    }


}
