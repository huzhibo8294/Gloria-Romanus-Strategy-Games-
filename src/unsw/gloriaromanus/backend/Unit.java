package unsw.gloriaromanus.backend;

//import java.io.*; 
import java.util.ArrayList;
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
    private int numSoldiers; // the number of troops in this unit (should reduce based on depletion)
    private int range; // range of the unit
    private double armour; // armour defense
    private double morale; // resistance to fleeing
    private int helmet;
    private double speed; // ability to disengage from disadvantageous battle
    private double attack; // can be either missile or melee attack to simplify. Could improve
                   // implementation by differentiating!
    private int defenseSkill; // skill to defend in battle. Does not protect from arrows!
    private int shieldDefense; // a shield
    private int movementpoints;
    private String unit_name;
    private ArrayList<Ability> abilities;
    private Boolean armed;
    private Troop troop;
    private String name;
    private Double defence;
    private Integer shield;
    private boolean training_completed;
    private int turns;
    private String province;
    private int cost;
    private String faction;
    
    // maybe used in each soldier.
    private Double blood_volume;



    public Unit(String type) {
        this.unit_name = type;
        try {
            JSONObject new_unit = new JSONObject(
                    Files.readString(Paths.get("src/unsw/gloriaromanus/Unit_values.json")));
            
            JSONObject chosen_unit = new_unit.getJSONObject(type);
            this.category = chosen_unit.getString("category");
            this.numSoldiers = chosen_unit.getInt("numTroops");
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

    public double get_blood() {
        return this.blood_volume;
    }

    public void set_blood(Double blood) {
        this.blood_volume = blood;
    }

    public double get_defence() {
        return this.defence;
    }

    public void set_defence(double new_defence) {
        this.defence = new_defence;
    }

    public String get_faction() {
        return this.faction;
    }

    public void set_armed(boolean i) {
        this.armed = i;
    }

    public Double get_attack() {
        return this.attack;
    }

    public void set_attack(Double new_attack) {
        this.attack = new_attack;
    }

    public Double get_morale() {
        return this.morale;
    }

    public void set_morale(Double morale) {
        this.morale = morale;
    }

    public ArrayList<Ability> get_abilities() {
        return this.abilities;
    }

    public void set_abilities(ArrayList<Ability> new_abilities) {
        this.abilities = new_abilities;
    }

    public String get_name() {
        return this.name;
    }

    public void complete_training() {
        if (this.turns == 3) {
            this.training_completed = true;
        }
    }

    public void set_helmet(int new_helmet) {
        this.helmet = new_helmet;
    }

    public void set_armour(double new_armour) {
        this.armour = new_armour;
    }

    public void set_speed(double new_speed) {
        this.speed = new_speed;
    }

    public double get_speed() {
        return this.speed;
    }

    public int getNumSoldiers(){
        return this.numSoldiers;
    }

    public void setNumSoldiers(int num){
        this.numSoldiers = num;
    }


    public void set_movementpoints(int num){
        this.movementpoints = num;
    }

    public Whole_army_bonus ability_add(boolean heroic){
        if (this.faction.equals("Gallic") || this.faction.equals("Celtic") || this.faction.equals("Germanic")) {
            Ability ability = new Ability();
            ability.set_name("Berserker rage");
            ability.ability_add(this);
        } else if (this.faction.equals("Roman")) {
            Ability ability = new Ability();
            ability.set_name("Legionary eagle");
            ability.ability_add(this);
        }
        
        Whole_army_bonus bonus = new Whole_army_bonus();
        if (this.name.equals("pikemen") || this.name.equals("hoplite")) {
            Ability ability = new Ability();
            ability.set_name("Phalanx");
            bonus = ability.ability_add(this);
        } else if (this.name.equals("javelin-skirmisher")) {
            Ability ability = new Ability();
            ability.set_name("skirmisher anti-armour");
            bonus = ability.ability_add(this);
        } else if (this.name.equals("melee cavalry")) {
            if (heroic == true) {
                Ability ability = new Ability();
                ability.set_name("Heroic charge");
                ability.ability_add(this);
            }
        } else if (this.name.equals("druid")) {
            Ability ability = new Ability();
            ability.set_name("Druidic fervour");
            bonus = ability.ability_add(this);
        }
        
        return bonus;
        //ArrayList<Ability> abilities = soldier.get_abilities();
        //abilities.add(this);
        //soldier.set_abilities(abilities);
    }
    /*
    public static void main(String[] arg){
        String name = "skirmishers";
        Unit new_unit = new Unit(name);
        assert(new_unit.getNumTroops()==20);
        System.out.println(new_unit.getNumTroops());

        Unit x = new Unit("heavy infantry");
        System.out.println(x.getNumTroops());
        assertEquals(x.getNumTroops(), 5);
    }
    */

    public void decreaseMorale(){
        this.morale -= 1;
    }
}
