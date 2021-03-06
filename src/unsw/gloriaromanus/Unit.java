package unsw.gloriaromanus;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.*;

/**
 * Represents a basic unit of soldiers
 * 
 * incomplete - should have heavy infantry, skirmishers, spearmen, lancers,
 * heavy cavalry, elephants, chariots, archers, slingers, horse-archers,
 * onagers, ballista, etc... higher classes include ranged infantry, cavalry,
 * infantry, artillery
 * 
 * current version represents a heavy infantry unit (almost no range, decent
 * armour and morale)
 */
public class Unit {
    private String category;
    private int numTroops; // the number of troops in this unit (should reduce based on depletion)
    private int range; // range of the unit
    private double armour; // armour defense
    private int morale; // resistance to fleeing
    private int helmet;
    private double speed; // ability to disengage from disadvantageous battle
    private int attack; // can be either missile or melee attack to simplify. Could improve
                   // implementation by differentiating!
    private int defenseSkill; // skill to defend in battle. Does not protect from arrows!
    private int shieldDefense; // a shield
    private int movementpoints;
    private String unit_name;
//

    public Unit(String type) {
        
        try {
            JSONObject new_unit = new JSONObject(
                    Files.readString(Paths.get("values/Unit_values.json")));
            
            JSONObject chosen_unit = new_unit.getJSONObject(type);
            //this.category = chosen_unit.getString("category");
            this.numTroops = chosen_unit.getInt("numTroops");
            this.armour = 1;
            //this.armour = chosen_unit.getInt("armour");
            this.attack = chosen_unit.getInt("attack");
            this.defenseSkill = chosen_unit.getInt("defenseSkill");
            this.morale = chosen_unit.getInt("morale");
            this.movementpoints = chosen_unit.getInt("movementpoints");
            this.range = chosen_unit.getInt("range");
            this.shieldDefense = chosen_unit.getInt("shieldDefense");
            this.helmet = 0;

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    public void set_helmet(int new_helmet) {
        this.helmet = new_helmet;
    }

    public void set_armour(double new_armour) {
        this.armour = new_armour;
    }

    public void set_speed(int new_speed) {
        this.speed = new_speed;
    }

    public double get_speed() {
        return this.speed;
    }

    public int getNumTroops(){
        return this.numTroops;
    }


    public void set_movementpoints(int num){
        this.movementpoints = num;
    }

    public String get_type(){
        return this.unit_name;
    }

    public static void main(String[] arg){
        String name = "skirmishers";
        Unit new_unit = new Unit(name);
        assert(new_unit.getNumTroops()==20);
        System.out.println(new_unit.getNumTroops());

        Unit x = new Unit("heavy infantry");
        System.out.println(x.getNumTroops());
        assertEquals(x.getNumTroops(), 50);
    }
}
