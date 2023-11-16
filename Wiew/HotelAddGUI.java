package TourismAgencySystem.Wiew;

import TourismAgencySystem.Helper.Config;
import TourismAgencySystem.Helper.Helper;
import TourismAgencySystem.Model.Employee;
import TourismAgencySystem.Model.Hotel;
import TourismAgencySystem.Model.HotelSeason;
import TourismAgencySystem.Model.HotelType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HotelAddGUI extends JFrame{

    private JPanel wrapper;
    private JTextField fld_hotelName;
    private JComboBox comboBox1;
    private JTextArea fld_property;
    private JTextArea fld_adres;
    private JTextField fld_phone;
    private JTextField fld_mail;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JRadioButton radioButton4;
    private JRadioButton radioButton5;
    private JRadioButton radioButton6;
    private JRadioButton radioButton7;
    private JTextField fld_start_1;
    private JTextField fld_end_1;
    private JTextField textField6;
    private JTextField textField7;
    private JButton sistemeKaydetButton;
    private final Employee employee;
    private int added_hotel_id;
private String select_star;
    public HotelAddGUI(Employee employee) {
        this.employee = employee;
        add(wrapper);
        setSize(1200,400);
        setLocation(Helper.screenCenter("x",getSize()),Helper.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        radioButton1.setText(Helper.Hoteltype("1"));
        radioButton2.setText(Helper.Hoteltype("2"));
        radioButton3.setText(Helper.Hoteltype("3"));
        radioButton4.setText(Helper.Hoteltype("4"));
        radioButton5.setText(Helper.Hoteltype("5"));
        radioButton6.setText(Helper.Hoteltype("6"));
        radioButton7.setText(Helper.Hoteltype("7"));
        select_star=comboBox1.getSelectedItem().toString();
        sistemeKaydetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Helper.isFieldEmpty(fld_hotelName)|| Helper.isFieldEmpty(fld_mail)|| Helper.isFieldEmpty(fld_phone)|| Helper.isAreaEmpty(fld_adres) || Helper.isAreaEmpty(fld_property) ||( !radioButton1.isSelected()
                && !radioButton2.isSelected()&& !radioButton3.isSelected()&& !radioButton4.isSelected()&& !radioButton5.isSelected()&& !radioButton6.isSelected()&&!radioButton7.isSelected())||
                Helper.isFieldEmpty(fld_start_1)|| Helper.isFieldEmpty(fld_end_1)){
                    Helper.showMsg("Fill");
                }else{
                    String name=fld_hotelName.getText();
                    String star=(String)comboBox1.getSelectedItem();
                    String adres=fld_adres.getText();
                    String phone=fld_phone.getText();
                    String email=fld_mail.getText();
                    String property=fld_property.getText();
                    String season_start_1=fld_start_1.getText();
                    String season_end_1=fld_end_1.getText();
                    String season_start_2=textField6.getText();
                    String season_end_2=textField7.getText();
                    if(Hotel.add(name,star,property,adres,phone,email)){
                        Hotel addedHotel=Hotel.getFetchByEmail(email);
                        added_hotel_id=addedHotel.getId();


                                    if(radioButton1.isSelected()){
                                        HotelType.add(radioButton1.getText(),added_hotel_id);
                                    }

                                    if(radioButton2.isSelected()){
                                        HotelType.add(radioButton2.getText(),added_hotel_id);
                                    }
                                      if(radioButton3.isSelected()){
                                    HotelType.add(radioButton3.getText(),added_hotel_id);
                                }
                                    if(radioButton4.isSelected()){
                                    HotelType.add(radioButton4.getText(),added_hotel_id);
                                }
                                     if(radioButton5.isSelected()){
                                    HotelType.add(radioButton5.getText(),added_hotel_id);
                                }


                               if(radioButton6.isSelected()){
                                    HotelType.add(radioButton6.getText(),added_hotel_id);
                                }

                                 if(radioButton7.isSelected()){
                                        HotelType.add(radioButton7.getText(),added_hotel_id);
                                    }


                            HotelSeason.add(season_start_1,season_end_1,added_hotel_id);
                            if(Helper.isFieldEmpty(textField6) && Helper.isFieldEmpty(textField7)){
                                HotelSeason.add(season_start_2,season_end_2,added_hotel_id);
                            }
                        }
                      Helper.showMsg("Otel sisteme eklendi.");
                        fld_hotelName.setText(null);
                        fld_property.setText(null);
                        fld_adres.setText(null);
                        fld_mail.setText(null);
                        fld_phone.setText(null);
                        fld_start_1.setText(null);
                        fld_end_1.setText(null);
                        textField6.setText(null);
                        textField7.setText(null);
                        comboBox1.setSelectedItem(0);
                        radioButton1.setSelected(false);
                        radioButton2.setSelected(false);
                        radioButton3.setSelected(false);
                        radioButton4.setSelected(false);
                        radioButton5.setSelected(false);
                        radioButton6.setSelected(false);
                        radioButton7.setSelected(false);


                    }
                }

        });
    }
}