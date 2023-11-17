package TourismAgencySystem.Wiew;

import TourismAgencySystem.Helper.Config;
import TourismAgencySystem.Helper.Helper;
import TourismAgencySystem.Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EmployeeGUI extends JFrame{
    private Employee employee;
    private JPanel wrapper;
    private JTabbedPane tabbedPane1;
    private JTable tbl_hotel_list;
    private JTable tbl_hotel_type;
    private JTable tbl_hotel_season;
    private JButton btn_hotel_add;
    private JTextField fld_region;
    private JTextField fld_check_in;
    private JTextField fld_check_out;
    private JTextField fld_older_guest_number;
    private JTextField fld_child_guest_number;
    private JButton odaAraButton;
    private JButton rezervasyonYapButton;
    private JTable tbl_room_list;
    private JTable tbl_room_properties;
    private JButton odaEkleButton;
    private JTextField fld_room_id;
    private JButton çıkışYapButton;
    private JButton rezervasyonSilButton;
    private JTable tbl_reservation;
    private int selected_hotel_id;
    private int selectedreservation;
    private  String checkin;
    private String checkout;
    private String season_start;
    private String season_end;

    private int selected_room_id;
    private DefaultTableModel mdl_hotel_list;
   private  DefaultTableModel mdl_hotel_type;
    private DefaultTableModel mdl_room_properties;
    private DefaultTableModel mdl_reservation;
    private Object[]row_room_properties;
    private Object[] row_hotel_type;
    private Object[] row_room_list;
    private Object[] row_reservation;
    DefaultTableModel mdl_hotel_season;
    DefaultTableModel mdl_room_list;

    private  Object[] row_hotel_season;
    Object[]row_hotel_list;
    public EmployeeGUI (Employee employee){
        this.employee = employee;
        add(wrapper);
        setSize(1200,400);
        setLocation(Helper.screenCenter("x",getSize()),Helper.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        //hotel tablosu kodları başlangıcı
        mdl_hotel_list=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column==0)
                    return false;

                return super.isCellEditable(row, column);
            }
        };
        Object[] col_hotel_list={"id","Hotel Adı","Yıldız","Tesis Özellikleri","Adres","Telefon","E-mail"};
        mdl_hotel_list.setColumnIdentifiers(col_hotel_list);
        row_hotel_list=new  Object[col_hotel_list.length];
       loadHotelModel();
       tbl_hotel_list.setModel(mdl_hotel_list);

       // otel konaklama tablosu
        mdl_hotel_type=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column==0)
                    return false;

                return super.isCellEditable(row, column);
            }
        };
        Object[] col_hotel_type={"Pansiyon Tipleri"};
        mdl_hotel_type.setColumnIdentifiers(col_hotel_type);
        row_hotel_type=new Object[col_hotel_type.length];
        tbl_hotel_type.setModel(mdl_hotel_type);
        tbl_hotel_type.getTableHeader().setReorderingAllowed(false);

        mdl_hotel_season=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column==0)
                    return false;

                return super.isCellEditable(row, column);
            }
        };
        Object[] col_hotel_season={"Dönem Başlangıcı","Dönem Bitişi"};
        mdl_hotel_season.setColumnIdentifiers(col_hotel_season);
        row_hotel_season=new Object[col_hotel_season.length];
        tbl_hotel_season.setModel(mdl_hotel_season);
        tbl_hotel_season.getTableHeader().setReorderingAllowed(false);

       //pansiyon tipleri ve sezonları listelemek için otel id sini alma
        tbl_hotel_list.getSelectionModel().addListSelectionListener(e -> {
            try{
                selected_hotel_id=Integer.parseInt(tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(),0).toString());
            }catch(Exception ex){

            }
            loadHotelTypeModel(selected_hotel_id);
            loadHotelSeasonModel(selected_hotel_id);

        });

        mdl_room_list=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column==0)
                    return false;

                return super.isCellEditable(row, column);
            }
        };
        //Oda listesinin tabloya yansıtılması
        Object[] col_room_list={"id","Hotel Adı","Oda Tipi","Stok","Sezon Tarihleri","Yetişkin Fiyatı","Çocuk Fiyatı","Pansiyon Tipi"};
        mdl_room_list.setColumnIdentifiers(col_room_list);
        row_room_list=new Object[col_room_list.length];



        loadRoomListModel();
        tbl_room_list.setModel(mdl_room_list);
        tbl_room_list.getColumnModel().getColumn(4).setPreferredWidth(200);
        tbl_room_list.getTableHeader().setReorderingAllowed(false);

        //Rezervasyon tablosu

        mdl_reservation=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column==0)
                    return false;

                return super.isCellEditable(row, column);
            }
        };
        Object[] col_reservation_list={"id","Ad Soyad","Telefon","E-mail","Not","Hotel Adı","Oda Tipi","Giriş Tarihi","Çıkış Tarihi","Yetişkin Sayısı","Çocuk Sayısı","Toplam Ücret"};
        mdl_reservation.setColumnIdentifiers(col_reservation_list);
        row_reservation=new Object[col_reservation_list.length];

         loadReservationList();
        tbl_reservation.setModel(mdl_reservation);
        tbl_reservation.getColumnModel().getColumn(3).setPreferredWidth(200);
        tbl_reservation.getColumnModel().getColumn(4).setPreferredWidth(200);
        tbl_reservation.getTableHeader().setReorderingAllowed(false);
        btn_hotel_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HotelAddGUI add=new HotelAddGUI(employee);


                add.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadHotelModel();
                        tbl_hotel_list.getSelectionModel().clearSelection();
                    }
                });



            }
        });

        odaEkleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RoomAddGUI roomAddGUI=new RoomAddGUI(employee);
                roomAddGUI.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadRoomListModel();
                        tbl_room_list.getSelectionModel().clearSelection();
                        super.windowClosed(e);
                    }
                });

            }
        });
        //oda özellikleri tablosu
        mdl_room_properties=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column==0)
                    return false;

                return super.isCellEditable(row, column);
            }
        };

         Object[] colProoerties={"Oda Özellikleri","Yatak Bilgisi","Alan(m2)"};
         mdl_room_properties.setColumnIdentifiers(colProoerties);
         row_room_properties=new Object[3];

         tbl_room_properties.setModel(mdl_room_properties);
         tbl_room_properties.getTableHeader().setReorderingAllowed(false);


        //oda özelliklerini göstermek için tıkayınca oda idsini alma
        tbl_room_list.getSelectionModel().addListSelectionListener(e->{
            try{
                selected_room_id=Integer.parseInt(tbl_room_list.getValueAt(tbl_room_list.getSelectedRow(),0).toString());
            }catch (Exception ex){
            ex.getMessage();
            }
            fld_room_id.setText(Integer.toString(selected_room_id));
            loadRoomProperties(selected_room_id);
        });
        odaAraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String regionhotelName=fld_region.getText();
                checkin=(String)fld_check_in.getText();
                checkout=(String)fld_check_out.getText();


String sql="SELECT r.* FROM Room r JOIN hotel o ON r.hotel_id = o.id JOIN season s ON r.season_id = s.id WHERE (o.name LIKE '%{{name}}%' OR o.address LIKE '%{{address}}%');";

                sql = sql.replace("{{name}}", regionhotelName);
                sql = sql.replace("{{address}}", regionhotelName);

                ArrayList<Room> searchingHotel = Room.getListByquery(sql);
                loadsearchRoomListModel(searchingHotel);






            }
        });
        rezervasyonYapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int child_numb=0;
                int adult_numb=0;
                tbl_room_list.getSelectionModel().addListSelectionListener(a->{
                    try{
                        selected_room_id=Integer.parseInt(tbl_room_list.getValueAt(tbl_room_list.getSelectedRow(),0).toString());
                    }catch (Exception ex){
                        ex.getMessage();
                    }

                });
                checkin=(String)fld_check_in.getText();
                checkout=(String)fld_check_out.getText();

                if(fld_check_in.getText().trim().isEmpty() && fld_check_out.getText().trim().isEmpty() || fld_older_guest_number.getText().trim().isEmpty() ||fld_child_guest_number.getText().trim().isEmpty()){
                    Helper.showMsg("Lütfen boş alanları doldurun.");
                }else {
                    adult_numb = Integer.parseInt((String) fld_older_guest_number.getText());
                    child_numb = Integer.parseInt((String) fld_child_guest_number.getText());
                }
                    if(Room.getRoomByID(selected_room_id).getStock()==0){
                        Helper.showMsg("Oda Stoğu kalmadı Lütfen başka bir oda seçin.");
                    } else if( checkSeason(checkin,checkout)){
                        Helper.showMsg("Lütfen sezon tarihleri içinde girin");
                    }
                        else{
                    ReservationGUI reservationGUI = new ReservationGUI(selected_room_id, adult_numb, child_numb, checkin, checkout);

                    reservationGUI.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {

                            tbl_reservation.getSelectionModel().clearSelection();


                            loadRoomListModel();
                            loadReservationList();
                        }
                    });
                }
            }
        });
        rezervasyonSilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                tbl_reservation.getSelectionModel().addListSelectionListener(a->{
                    try{
                        selectedreservation=Integer.parseInt(tbl_reservation.getValueAt(tbl_reservation.getSelectedRow(),0).toString());
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }

                });
               if( Reservation.delete(selectedreservation)) {
                   Helper.showMsg("Rezervasyon Silindi");
               }else{
                   Helper.showMsg("Silinecek Rezervasyonu seçin.");
               }
                loadReservationList();
            }
        });
    }
    private void loadReservationList(){
         DefaultTableModel clearModel=(DefaultTableModel) tbl_reservation.getModel();
         clearModel.setRowCount(0);
          for(Reservation obj: Reservation.getList()){
              row_reservation[0]= obj.getId();
              row_reservation[1]=obj.getName();
              row_reservation[2]=obj.getPhone();
              row_reservation[3]=obj.getEmail();
              row_reservation[4]=obj.getNote();
              row_reservation[5]=Hotel.getHotelByRoomID(obj.getRoom_id()).getName();
              row_reservation[6]=Room.getRoomByID(obj.getRoom_id()).getRoom_type();
              row_reservation[7]=obj.getCheckin_date();
              row_reservation[8]=obj.getCheckout_date();
              row_reservation[9]=obj.getAdult_count();
              row_reservation[10]=obj.getChild_count();
              row_reservation[11]=obj.getTotal_price();
              mdl_reservation.addRow(row_reservation);

}

}
    private void loadRoomProperties(int id) {
        DefaultTableModel clearModel=(DefaultTableModel) tbl_room_properties.getModel();
        clearModel.setRowCount(0);
        int i;
        for(RoomProperties obj: RoomProperties.getListByRoomID(id)){
            i=0;
            row_room_properties[i++]=obj.getProperty();
            row_room_properties[i++]=obj.getBed();
            row_room_properties[i++]=obj.getArea();
            mdl_room_properties.addRow(row_room_properties);




        }
    }

    public void loadRoomListModel() {
        DefaultTableModel clearModel=(DefaultTableModel) tbl_room_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for(Room obj: Room.getList()){
            i=0;
            row_room_list[i++]=obj.getId();
            row_room_list[i++]=Hotel.getFetch(obj.getHotel_id()).getName();
            row_room_list[i++]=obj.getRoom_type();
            row_room_list[i++]=obj.getStock();
            row_room_list[i++]=HotelSeason.getFetch(obj.getSeason_id()).getSeason_start() + " - "+HotelSeason.getFetch(obj.getSeason_id()).getSeason_end();
            row_room_list[i++]=obj.getAdult_price();
            row_room_list[i++]=obj.getChild_price();
            row_room_list[i++]=HotelType.getFetch(obj.getHotel_type_id()).getType();
            mdl_room_list.addRow(row_room_list);



        }
    }
    public void loadsearchRoomListModel(ArrayList<Room> hotelList) {
        Date start = null;
        Date end = null;
        Date seasonStart = null;
        Date seasonEnd = null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        if (!fld_check_in.getText().isEmpty() && !fld_check_out.getText().isEmpty()) {
            checkin = fld_check_in.getText().trim();
            checkout = fld_check_out.getText().trim();
            try {
                start = formatter.parse(checkin);
                end = formatter.parse(checkout);

            } catch (ParseException e) {
                System.out.println("ParseException: " + e.getMessage());
            }
        }

        DefaultTableModel clearModel = (DefaultTableModel) tbl_room_list.getModel();
        clearModel.setRowCount(0);

        int i=0;


        for (Room obj : hotelList) {


            try {
                seasonStart = formatter.parse(HotelSeason.getFetch(obj.getSeason_id()).getSeason_start());
                seasonEnd = formatter.parse(HotelSeason.getFetch(obj.getSeason_id()).getSeason_end());

            } catch (ParseException e) {
                System.out.println("ParseException: " + e.getMessage());
            }


                  if(start!=null && end!=null) {
                      if (start.before(seasonStart) || end.after(seasonEnd)) {

                          continue;
                      }
                  }

            row_room_list[0] = obj.getId();
            row_room_list[1] = Hotel.getFetch(obj.getHotel_id()).getName();
            row_room_list[2] = obj.getRoom_type();
            row_room_list[3] = obj.getStock();
            row_room_list[4] = HotelSeason.getFetch(obj.getSeason_id()).getSeason_start() + " - " + HotelSeason.getFetch(obj.getSeason_id()).getSeason_end();
            row_room_list[5] = obj.getAdult_price();
            row_room_list[6] = obj.getChild_price();
            row_room_list[7] = HotelType.getFetch(obj.getHotel_type_id()).getType();
            mdl_room_list.addRow(row_room_list);

        }

    }

    private void loadHotelSeasonModel(int selectedHotelId) {
        DefaultTableModel clearModel=(DefaultTableModel) tbl_hotel_season.getModel();
        clearModel.setRowCount(0);
        int i;
        for(HotelSeason obj: HotelSeason.getListByHotelID(selectedHotelId)){
            i=0;
            row_hotel_season[i++]=obj.getSeason_start();
            row_hotel_season[i++]=obj.getSeason_end();


            mdl_hotel_season.addRow(row_hotel_season);



        }
    }

    //Otel tiplerinin tabloya yazdırılması
    private void loadHotelTypeModel(int id){
        DefaultTableModel clearModel=(DefaultTableModel) tbl_hotel_type.getModel();
        clearModel.setRowCount(0);
        int i;
        for(HotelType obj: HotelType.getListByHotelID(id)){
            i=0;
            row_hotel_type[i++]=obj.getType();

            mdl_hotel_type.addRow(row_hotel_type);



        }
    }
    private void loadHotelModel(){
        int i;
        DefaultTableModel clearModel=(DefaultTableModel) tbl_hotel_list.getModel();
        clearModel.setRowCount(0);
        for(Hotel obj: Hotel.getList()){
            i=0;
            row_hotel_list[i++]=obj.getId();
            row_hotel_list[i++]=obj.getName();

            row_hotel_list[i++]=obj.getStar();
            row_hotel_list[i++]=obj.getProperty();
            row_hotel_list[i++]=obj.getAddress();
            row_hotel_list[i++]=obj.getPhone();
            row_hotel_list[i++]=obj.getEmail();
            mdl_hotel_list.addRow(row_hotel_list);



        }

    }
    private boolean checkSeason(String check_in,String check_out ){
        Date start = null;
        Date end = null;
        Date seasonStart = null;
        Date seasonEnd = null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        if (!fld_check_in.getText().isEmpty() && !fld_check_out.getText().isEmpty()) {
            check_in = fld_check_in.getText().trim();
            check_out = fld_check_out.getText().trim();
            try {
                start = formatter.parse(checkin);
                end = formatter.parse(checkout);

            } catch (ParseException k) {
                System.out.println("ParseException: " + k.getMessage());
            }
        }


        try {
            seasonStart = formatter.parse(HotelSeason.getFetch(Room.getRoomByID(selected_room_id).getSeason_id()).getSeason_start());
            seasonEnd = formatter.parse(HotelSeason.getFetch(Room.getRoomByID(selected_room_id).getSeason_id()).getSeason_end());

        } catch (ParseException f) {
            System.out.println("ParseException: " + f.getMessage());
        }


        if(start!=null && end!=null) {
            if (start.before(seasonStart) || end.after(seasonEnd)||end.before(seasonStart)|| start.after(seasonEnd)) {


                return true;
            }
        }
        return false;
    }

}
