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
    private  Employee employee;
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
        radioButton1.setText(Helper.roomProperty("1"));
        radioButton2.setText(Helper.roomProperty("2"));
        radioButton3.setText(Helper.roomProperty("3"));
        radioButton4.setText(Helper.roomProperty("4"));
        radioButton5.setText(Helper.roomProperty("5"));
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
                 String season = (obj.getSeason_start().toString() + "-" + obj.getSeason_end().toString());
                 if (season.equals(cmb_season.getSelectedItem().toString())) {
                     season_id = obj.getId();
                     break;
                 }
             }
                 if(Room.add(roomType,stock,season_id,adult_price,child_price,hotel_type_id,hotel_id)){
                     ArrayList<Room> roomList=Room.getList();
                     Room addedRoom=roomList.get(Room.getList().size()-1);
                     int addedroom_id=addedRoom.getId();
                     System.out.println(addedroom_id);
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
                     RoomProperties.add(roomProperties,addedroom_id,fld_bed.getText(),Integer.parseInt(fld_area.getText().toString()));
                     Helper.showMsg("Oda başarılı bir şekilde eklendi");
                     cmb_hotelname.setSelectedIndex(0);
                     cmb_season.setSelectedIndex(0);
                     cmb_pansiyon.setSelectedIndex(0);
                     cmb_room_type.setSelectedIndex(0);
                     fld_stock.setText(null);
                     fld_bed.setText(null);
                     fld_area.setText(null);
                     fld_adult_price.setText(null);
                     fld_child_price.setText(null);
                     radioButton1.setSelected(false);
                     radioButton2.setSelected(false);
                     radioButton3.setSelected(false);
                     radioButton4.setSelected(false);
                     radioButton5.setSelected(false);
             }
             }
         }


        });
    }
    //Otel isimlerini comobaxa getiren metot.
public void loadhotelNameCombo(){
    Item hotelItem=(Item) cmb_hotelname.getSelectedItem();
        cmb_hotelname.removeAllItems();
        cmb_hotelname.addItem(new Item(0,null));

        for(Hotel Obj:Hotel.getList()){
            cmb_hotelname.addItem(new Item(Obj.getId(), Obj.getName()));
        }
}
private void laodhotelTypeCombo(){
        Item hotelItem=(Item) cmb_hotelname.getSelectedItem();
        cmb_pansiyon.removeAllItems();
        cmb_pansiyon.addItem(new Item(0,null));
        for(HotelType obj: HotelType.getListByHotelID(hotelItem.getKey())){
            cmb_pansiyon.addItem(new Item(obj.getId(),obj.getType()));
            System.out.println(obj.getId());
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
