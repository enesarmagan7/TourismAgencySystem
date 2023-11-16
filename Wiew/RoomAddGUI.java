package TourismAgencySystem.Wiew;

import TourismAgencySystem.Helper.Config;
import TourismAgencySystem.Helper.Helper;
import TourismAgencySystem.Helper.Item;
import TourismAgencySystem.Model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RoomAddGUI extends JFrame {
    private final Employee employee;
    private JPanel wrapper;
    private JComboBox cmb_hotelname;
    private JComboBox cmb_room_type;
    private JTextField fld_stock;
    private JComboBox cmb_pansiyon;
    private JComboBox cmb_season;
    private JTextField fld_adult_price;
    private JTextField fld_child_price;
    private JTextField fld_bed;
    private JTextField fld_area;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JRadioButton radioButton4;
    private JRadioButton radioButton5;
    private JButton odaEkleButton;

    public RoomAddGUI(Employee employee){

        this.employee = employee;
        add(wrapper);
        setSize(1200,400);
        setLocation(Helper.screenCenter("x",getSize()),Helper.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        radioButton1.setText("1");
        radioButton1.setText("2");
        radioButton1.setText("3");
        radioButton1.setText("4");
        radioButton1.setText("5");
        loadhotelNameCombo();
        laodhotelTypeCombo();
        laodSeasonCombo();
        cmb_hotelname.addActionListener(event->{

            laodhotelTypeCombo();
            laodSeasonCombo();

            cmb_pansiyon.setSelectedIndex(0);
            cmb_season.setSelectedIndex(0);
        });
        odaEkleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
         if(Helper.isFieldEmpty(fld_adult_price)|| Helper.isFieldEmpty(fld_child_price)|| Helper.isFieldEmpty(fld_stock)|| Helper.isFieldEmpty(fld_area)|| Helper.isFieldEmpty(fld_bed)||
                 cmb_hotelname.getSelectedItem()==null || cmb_season.getSelectedItem()==null || cmb_room_type.getSelectedItem().toString().equals("")||cmb_pansiyon.getSelectedItem()==null||
                 (!radioButton1.isSelected()&& !radioButton2.isSelected()&& !radioButton3.isSelected()&& !radioButton4.isSelected()&& !radioButton5.isSelected()))
                {
                    Helper.showMsg("Alanları doldurun");
                }else{
             String roomType=cmb_room_type.getSelectedItem().toString();
             int stock=Integer.parseInt(fld_stock.getText().toString());
             int season_id=0;
             int adult_price=Integer.parseInt(fld_adult_price.getText().toString());
             int child_price=Integer.parseInt(fld_child_price.getText().toString());
             Item Hoteltype=(Item)cmb_pansiyon.getSelectedItem();
             int hotel_type_id=Hoteltype.getKey();
             Item hotelname=(Item)cmb_hotelname.getSelectedItem();
             int hotel_id=hotelname.getKey();
             for(HotelSeason obj: HotelSeason.getListByHotelID(hotel_id)) //season id yi çekmek için
             {
                 String season=(obj.getSeason_start().toString()+"-"+obj.getSeason_end().toString());
                 if(season.equals(cmb_season.getSelectedItem().toString())){
                     season_id=obj.getId();
                     break;
                 }
                 if(Room.add(roomType,stock,season_id,adult_price,child_price,hotel_type_id,hotel_id)){
                     ArrayList<Room> roomList=new ArrayList<>();
                     Room addedRoom=roomList.get(Room.getList().size()-1);
                     int addedroom_id=addedRoom.getId();
                     String roomProperties="";
                     if(radioButton1.isSelected()){
                         roomProperties+="\n"+radioButton1.getText();
                     }
                     if(radioButton2.isSelected()){
                         roomProperties+="\n"+radioButton2.getText();
                     }
                     if(radioButton3.isSelected()){
                         roomProperties+="\n"+radioButton3.getText();
                     }

                     if(radioButton4.isSelected()){
                         roomProperties+="\n"+radioButton4.getText();
                     }
                     if(radioButton5.isSelected()){
                         roomProperties+="\n"+radioButton5.getText();
                     }
             }
             }
         }

            }
        });
    }
    //Otel isimlerini comobaxa getiren metot.
private void loadhotelNameCombo(){
        cmb_hotelname.removeAllItems();
        cmb_hotelname.addItem(new Item(0,null));
        for(Hotel Obj: Hotel.getList()){
            cmb_hotelname.addItem(new Item(Obj.getId(), Obj.getName()));
        }
}
private void laodhotelTypeCombo(){
        Item hotelItem=(Item) cmb_hotelname.getSelectedItem();
        cmb_pansiyon.removeAllItems();
        cmb_pansiyon.addItem(new Item(0,null));
        for(HotelType obj: HotelType.getListByHotelID(hotelItem.getKey())){
            cmb_pansiyon.addItem(new Item(obj.getId(),obj.getType()));
        }
}
private void laodSeasonCombo(){
    Item hotelItem=(Item) cmb_hotelname.getSelectedItem();
    cmb_season.removeAllItems();
    cmb_season.addItem(new Item(0,null));
    for(HotelSeason obj: HotelSeason.getListByHotelID(hotelItem.getKey())){
        cmb_season.addItem(new Item(obj.getId(),obj.getSeason_start()+"-"+obj.getSeason_end()));
    }
}
}
