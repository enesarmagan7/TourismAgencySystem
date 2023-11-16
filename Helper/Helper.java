package TourismAgencySystem.Helper;

import javax.swing.*;
import java.awt.*;

public class Helper {

    public static int screenCenter(String point, Dimension size) {
        int position;
        switch (point) {
            case "x":
                position = (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
                break;
            case "y":
                position = (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
                break;
            default:
                position = 0;
        }

        return position;
    }
    public static void setLayout() {
        UIManager.LookAndFeelInfo[] var0 = UIManager.getInstalledLookAndFeels();
        int var1 = var0.length;

        for (int var2 = 0; var2 < var1; ++var2) {
            UIManager.LookAndFeelInfo info = var0[var2];
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (InstantiationException | UnsupportedLookAndFeelException | IllegalAccessException |
                         ClassNotFoundException var5) {
                    throw new RuntimeException(var5);
                }
            }
        }
    }
    public static void showMsg(String str){
        optionPaneTR();
        String msg;
        String title;
        switch (str){
            case "fill":
                msg = "Lütfen tüm alanları doldurunuz.";
                title = "Hata!";
                break;
            case "done":
                msg = "İşlem Başarılı";
                title = "Sonuç";
                break;
            case "error":
                msg = "Bir hata oluştu.";
                title = "Hata!";
            default:
                msg = str;
                title = "Mesaj";

        }
        JOptionPane.showMessageDialog(null,msg,title,JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean confirm(String str){
        optionPaneTR();
        String msg;
        switch (str){
            case "sure":
                msg = "Bu işlemi gerçekleştirmek istediğinize emin misiniz?";
                break;
            default:
                msg=str;
        }
        return JOptionPane.showConfirmDialog(null,msg,"Son Kararın mı?", JOptionPane.YES_NO_OPTION) == 0;
    }
    public static void optionPaneTR(){
        UIManager.put("OptionPane.okButtonText","Tamam");
        UIManager.put("OptionPane.yesButtonText","Evet");
        UIManager.put("OptionPane.noButtonText","Hayır");

    }
    public static boolean isFieldEmpty(JTextField field){
        return field.getText().trim().isEmpty();
    }
    public static boolean isAreaEmpty(JTextArea area){
        return area.getText().trim().isEmpty();
    }
    public static String Hoteltype(String number){
        String type="";
        switch(number){
            case "1":
                type="Ultra Herşey Dahil";
                break;
            case"2":
                type="Herşey Dahil";
                break;
            case "3":
                type="Oda Kahvaltı";
                break;
            case"4":
                type="Tam Pansiyon";
                break;
            case"5":
                type="Yarım Pansiyon";
                break;
            case"6": type="Sadece Yatak";
            break;
            case"7": type="Alkol Hariç Full Credit";
            break;

        }
      return type;
    }
    public static String roomProeprty(String number){
        String property ="";
        switch("number"){
            case "1":property="televizyon";
            break;
            case "2":property="Minibar";
            break;
            case "3":property="Oyun Konsolu";
            break;
            case "4": property="Kasa";
            break;
            case"5": property="Projeksiyon";
            break;
        }
        return property;

    }
}
