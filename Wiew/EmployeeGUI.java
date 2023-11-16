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

public class EmployeeGUI extends JFrame{
    private Employee employee;
    private JPanel wrapper;
    private JTabbedPane tabbedPane1;
    private JTable tbl_hotel_list;
    private JTable tbl_hotel_type;
    private JTable tbl_hotel_season;
    private JButton btn_hotel_add;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton odaAraButton;
    private JButton rezervasyonYapButton;
    private JTable tbl_room_list;
    private JTable table2;
    private JButton odaEkleButton;
    private int selected_hotel_id;
    private DefaultTableModel mdl_hotel_list=new DefaultTableModel();
    DefaultTableModel mdl_hotel_type;
    private Object[] row_hotel_type;
    private Object[] row_room_list;
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
        tbl_room_list.getTableHeader().setReorderingAllowed(false);

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
    }

    private void loadRoomListModel() {
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
            mdl_hotel_list.addRow(row_room_list);


        }
    }

    private void loadHotelSeasonModel(int selectedHotelId) {
        DefaultTableModel clearModel=(DefaultTableModel) tbl_hotel_type.getModel();
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
}
