package TourismAgencySystem.Wiew;

import TourismAgencySystem.Helper.Config;
import TourismAgencySystem.Helper.Helper;
import TourismAgencySystem.Model.Admin;
import TourismAgencySystem.Model.Employee;
import TourismAgencySystem.Model.User;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame{
    private JPanel wrapper;
    private JTextField fld_uname;
    private JPasswordField fld_pass;
    private JButton girişYapButton;
    public LoginGUI(){
        add(wrapper);
        setSize(400,400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setLocation(Helper.screenCenter("x",getSize()),Helper.screenCenter("y",getSize()));
        setResizable(false);
        setVisible(true);
        girişYapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (Helper.isFieldEmpty(fld_uname) || Helper.isFieldEmpty(fld_pass)) {
                    Helper.showMsg("fill");
                } else {
                    User user = User.getFetch(fld_uname.getText(), fld_pass.getText());
                    if (user == null) {
                        Helper.showMsg("Kullanıcı Bulunamadı");
                    } else {
                        switch (user.getType()) {
                            case "admin" -> {
                                //rezervasyon listesi ondan sonra oda arama
                                AdminGUI guı=new AdminGUI((Admin)user);
                                dispose();
                            }
                            case "employee" -> {

                                EmployeeGUI employee = new EmployeeGUI( (Employee) user);
                                dispose();
                            }
                        }

                    }
                }
            }

        });

    }

    public static void main(String[] args) {
        TourismAgencySystem.Wiew.LoginGUI loginGUIn=new TourismAgencySystem.Wiew.LoginGUI();
    }
}
