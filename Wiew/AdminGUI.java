package TourismAgencySystem.Wiew;


import TourismAgencySystem.Helper.Config;
import TourismAgencySystem.Helper.Helper;
import TourismAgencySystem.Model.Admin;
import TourismAgencySystem.Model.User;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminGUI extends JFrame {
    private JPanel wrapper;
    private JTabbedPane tab_operator;
    private JPanel pnl_user_list;
    private JScrollPane scrl_userlist;
    private JPanel pnl_userform;
    private JTextField fld_uname;
    private JTextField fld_search_name;
    private JTextField fld_search_username;
    private JComboBox cmb_search_type;
    private JButton btn_search;
    private JTable tbl_userlist;
    private JLabel lbl_username;
    private JTextField fld_name;
    private JLabel lbl_password;
    private JTextField fld_password;
    private JLabel lbl_type;
    private JComboBox cmb_type;
    private JButton btn_adduser;
    private JTextField fld_user_ıd;
    private JButton btn_user_delete;
    private Admin operator;
    private DefaultTableModel mdl_user_list;
    Object []row_user_list;
    Object[] col_user_list=new Object[5];

    public AdminGUI(Admin operator){
        this.operator=operator;
        add(wrapper);
        setSize(1200,400);
        setLocation(Helper.screenCenter("x",getSize()),Helper.screenCenter("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        Object[] col_user_list= {"ID","Ad Soyad","Kullanıcı Adı","Şifre","Yetki Tipi"};
        mdl_user_list=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column==0)
                    return false;

                return super.isCellEditable(row, column);
            }
        };
        mdl_user_list.setColumnIdentifiers(col_user_list);
        row_user_list=new Object[col_user_list.length];
        for(User user: User.getList()){
            Object [] row= new Object[col_user_list.length];
            row[0]=user.getId();
            row[1]=user.getName();
            row[2]=user.getUname();
            row[3]=user.getPass();
            row[4]=user.getType();
            mdl_user_list.addRow(row);
        }
        tbl_userlist.setModel(mdl_user_list);
        tbl_userlist.getTableHeader().setReorderingAllowed(false);
        tbl_userlist.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                try {
                    String selected_id = tbl_userlist.getValueAt(tbl_userlist.getSelectedRow(),0).toString();
                    fld_user_ıd.setText(selected_id);
                }catch (Exception exception) {

                }
            }
        });

        btn_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name=fld_search_name.getText();
                String username=fld_search_username.getText();
                String type=cmb_search_type.getSelectedItem().toString();
                ArrayList<User> searchingUser= User.searchUserList(User.search(name,username,type));
                loadUserModel(searchingUser);

            }
        });
        btn_adduser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name=fld_name.getText();
                String uname= fld_uname.getText();
                String password=fld_password.getText();
                String type= cmb_type.getSelectedItem().toString();
                if(fld_uname.getText().length()==0 || fld_name.getText().length()==0 ||fld_password.getText().length()==0){
                    JOptionPane.showMessageDialog(null,"Bu Kullanıcı Daha Önceden Eklenmiş!!","Hata",JOptionPane.INFORMATION_MESSAGE);
                    loadUserModel();


                }else {
                    User.add(name, uname, password, type);
                    loadUserModel();


                    JOptionPane.showMessageDialog(null, "Kullanıcı Başarıyla Eklendi", "Hata", JOptionPane.INFORMATION_MESSAGE);
                }
                }
        });

        btn_user_delete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(fld_user_ıd.getText().length()==0){
                    JOptionPane.showMessageDialog(null,"Tüm Alanları Doldurun","Hata",JOptionPane.INFORMATION_MESSAGE);
                }else{
                    int user_id=Integer.parseInt(fld_user_ıd.getText());
                    if(User.delete(user_id)) {
                        JOptionPane.showMessageDialog(null, "Silme Başarılı", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
                        loadUserModel();

                        fld_user_ıd.setText(null);
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Kullanıcı Silinemedi","Hata",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
    }
    public void loadUserModel(ArrayList<User> userList){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_userlist.getModel();
        clearModel.setRowCount(0);
        for (User user : userList) {
            Object[] row = new Object[col_user_list.length];
            row[0] = user.getId();
            row[1] = user.getName();
            row[2] = user.getUname();
            row[3] = user.getPass();
            row[4] = user.getType();
            mdl_user_list.addRow(row);
        }
    }
    public void loadUserModel(){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_userlist.getModel();
        clearModel.setRowCount(0);
        for (User user : User.getList()) {
            Object[] row = new Object[col_user_list.length];
            row[0] = user.getId();
            row[1] = user.getName();
            row[2] = user.getUname();
            row[3] = user.getPass();
            row[4] = user.getType();
            mdl_user_list.addRow(row);
        }
    }
}