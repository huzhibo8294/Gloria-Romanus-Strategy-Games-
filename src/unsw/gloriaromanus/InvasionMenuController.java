package unsw.gloriaromanus;

import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;

import java.util.List;
import java.util.ArrayList;

import unsw.gloriaromanus.backend.*;

public class InvasionMenuController extends MenuController{
    @FXML
    private TextField invading_province;
    @FXML
    private TextField opponent_province;
    @FXML
    private TextArea output_terminal;
    @FXML
    private ChoiceBox <String> troop_choice;

    @FXML
    private TextField nexthumanprovince;
    @FXML
    private TextField currenthumanprovince;

    @FXML
    private ChoiceBox <String> unit_choice;

    // https://stackoverflow.com/a/30171444
    @FXML
    private URL location; // has to be called location

    public void setInvadingProvince(String p) {
        invading_province.setText(p);
    }

    public void setOpponentProvince(String p) {
        opponent_province.setText(p);
    }

    public void sethumannextProvince(String p) {
        nexthumanprovince.setText(p);
    }

    public void sethumancurrentProvince(String p) {
        currenthumanprovince.setText(p);
    }

    public void appendToTerminal(String message) {
        output_terminal.appendText(message + "\n");
    }

    @FXML
    public void initialize() {
        List<String> new_list = new ArrayList<String>();
        new_list.add("melee cavalry");
        new_list.add("pikemen");
        new_list.add("hoplite");
        new_list.add("javelin skirmisher");
        new_list.add("elephant");
        new_list.add("horse archer");
        new_list.add("druid");
        new_list.add("melee infantry");
        unit_choice.getItems().addAll(new_list);

        //Systemcontrol new_system = new Systemcontrol();
    }

    @FXML
    public void clickedInvadeButton(ActionEvent e) throws IOException {
        getParent().clickedInvadeButton(e);
    }

    
    @FXML
    public void clickedmoveButton(ActionEvent e) throws IOException {
        
        String my_troop = troop_choice.getValue();
        getParent().clickedmoveButton(e, my_troop);
        
    }

    @FXML
    public void clickedrecuitbutton(ActionEvent e) throws IOException {
        //Systemcontrol system = getParent().get_system();
        String human_unit = unit_choice.getValue();
        getParent().recuit_unit(human_unit);;

        /*
        List<String> new_list = new ArrayList<String>();
        String a = "A";
        String b = "B";
        String c = "C";
        new_list.add(a);
        new_list.add(b);
        new_list.add(c);
        troop_choice.getItems().addAll(new_list);
        */
        
    }

    public void unit_add(String province) {
        System.out.println(province);

        List<String> new_list = new ArrayList<String>();
        Systemcontrol system = getParent().get_system();
        for (Province p : system.get_myfaction().getProvinces()) {
            if (p.get_name().equals(province)) {
                for (Unit u : p.get_units()) {
                    System.out.println(u.get_name());
                    new_list.add(u.get_name());
                }
            }
        }
        troop_choice.getItems().addAll(new_list);
        /*
        List<String> new_list = new ArrayList<String>();
        String a = "A";
        String b = "B";
        String c = "C";
        new_list.add(a);
        new_list.add(b);
        new_list.add(c);
        troop_choice.getItems().addAll(new_list);
        */
    }

    public void unit_remove() {
        troop_choice.getItems().clear();    
    }
    
}
