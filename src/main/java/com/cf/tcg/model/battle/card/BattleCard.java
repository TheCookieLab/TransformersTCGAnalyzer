package com.cf.tcg.model.battle.card;

import com.cf.tcg.model.Pip;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

/**
 * @author David
 */
public class BattleCard {

    public final String name;
    public final List<Pip> pips;

    public BattleCard() {
        this(null, new ArrayList<>());
    }

    public BattleCard(String name, List<Pip> pips) {
        this.name = name;
        this.pips = pips;
    }

    public BattleCard(String name, Pip... pips) {
        this(name, Arrays.asList(pips));
    }

    public BattleCard(Pip... pips) {
        this(null, Arrays.asList(pips));
    }

    public Boolean containsAllPips(Pip... requiredPips) {
        if (requiredPips == null || this.pips.isEmpty()) {
            return true;
        }

        Map<Pip, Integer> requiredPipCount = new HashMap<>();
        for (Pip requiredPip : requiredPips) {
            Integer count = requiredPipCount.getOrDefault(requiredPip, 0);
            requiredPipCount.put(requiredPip, ++count);
        }

        for (Entry<Pip, Integer> entry : requiredPipCount.entrySet()) {
            if (this.getPipCount(entry.getKey()).equals(entry.getValue()) == false) {
                return false;
            }
        }

        return true;
    }

    public Boolean containsAnyOfPips(Pip... anyOfPips) {
        if (anyOfPips == null || anyOfPips.length == 0) {
            return true;
        }

        for (Pip pip : anyOfPips) {
            if (this.pips.contains(pip)) {
                return true;
            }
        }

        return false;
    }

    public Integer getPipCount(Pip pip) {
        int count = 0;
        for (Pip p : this.pips) {
            if (p == pip) {
                count++;
            }
        }

        return count;
    }

    @Override
    public int hashCode() {
        int hash = 7;

        hash = 89 * hash + Objects.hashCode(this.pips);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BattleCard other = (BattleCard) obj;

        if (!Objects.equals(this.pips, other.pips)) {
            return false;
        }

        return Objects.equals(this.name, other.name);

    }

    public boolean equalsIgnoreName(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BattleCard other = (BattleCard) obj;

        if (!Objects.equals(this.pips, other.pips)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    // Generic Pip Battle Cards
    public static BattleCard SINGLE_ORANGE = new BattleCard(Pip.ORANGE);
    public static BattleCard DOUBLE_ORANGE = new BattleCard(Pip.ORANGE, Pip.ORANGE);

    public static BattleCard SINGLE_BLUE = new BattleCard(Pip.BLUE);
    public static BattleCard DOUBLE_BLUE = new BattleCard(Pip.BLUE, Pip.BLUE);

    public static BattleCard BLUE_ORANGE = new BattleCard(Pip.BLUE, Pip.ORANGE);
    public static BattleCard BLUE_ORANGE_WHITE = new BattleCard(Pip.BLUE, Pip.ORANGE, Pip.WHITE);

    public static BattleCard SINGLE_WHITE = new BattleCard(Pip.WHITE);

    public static BattleCard SINGLE_BLACK = new BattleCard(Pip.BLACK);
    public static BattleCard DOUBLE_BLACK = new BattleCard(Pip.BLACK, Pip.BLACK);
    public static BattleCard TRIPLE_BLACK = new BattleCard(Pip.BLACK, Pip.BLACK, Pip.BLACK);

    public static BattleCard BLACK_ORANGE = new BattleCard(Pip.BLACK, Pip.ORANGE);
    public static BattleCard BLACK_BLUE = new BattleCard(Pip.BLACK, Pip.BLUE);

    public static BattleCard SINGLE_GREEN = new BattleCard(Pip.GREEN);
    public static BattleCard WHITE_GREEN = new BattleCard(Pip.WHITE, Pip.GREEN);
    public static BattleCard BLUE_GREEN = new BattleCard(Pip.BLUE, Pip.GREEN);
    public static BattleCard ORANGE_GREEN = new BattleCard(Pip.ORANGE, Pip.GREEN);
    public static BattleCard BLACK_GREEN = new BattleCard(Pip.BLACK, Pip.GREEN);

    public static BattleCard BLANK = new BattleCard();

    // Named Battle Cards
    // Actions
    public static BattleCard BATTLEFIELD_INCURSION = new BattleCard("BattleField Incursion", Pip.GREEN);
    public static BattleCard CONFIDENCE = new BattleCard("Confidence", Pip.ORANGE);
    public static BattleCard CALCULATED_STRIKE = new BattleCard("Calculated Strike", Pip.BLACK);
    public static BattleCard CONSTRUCTICON_ENIGMA = new BattleCard("Constructicon Enigma", Pip.GREEN);
    public static BattleCard DESIGNATED_TARGET = new BattleCard("Designated Target", Pip.BLACK, Pip.BLACK);
    public static BattleCard ESCAPE_ROUTE = new BattleCard("Escape Route", Pip.GREEN, Pip.WHITE);
    public static BattleCard FIGHT_FOR_POSITION = new BattleCard("Fight For Position", Pip.ORANGE, Pip.BLACK);
    public static BattleCard FUEL_CACHE = new BattleCard("Fuel Cache", Pip.ORANGE, Pip.BLUE, Pip.WHITE);
    public static BattleCard HEAT_OF_BATTLE = new BattleCard("Heat of Battle", Pip.WHITE);
    public static BattleCard HIDDEN_FORTIFICATION = new BattleCard("Hidden Fortification", Pip.BLUE);
    public static BattleCard HIDING_SPOT = new BattleCard("Hiding Spot", Pip.BLACK);

    public static BattleCard INSPIRING_LEADERSHIP = new BattleCard("Inspiring Leadership", Pip.BLUE);
    public static BattleCard I_STILL_FUNCTION = new BattleCard("I Still Function!");

    public static BattleCard LEAP_INTO_BATTLE = new BattleCard("Leap Into Battle", Pip.BLUE);
    public static BattleCard MARKSMANSHIP = new BattleCard("Marksmanship", Pip.BLUE);
    public static BattleCard MASTER_PLAN = new BattleCard("Master Plan", Pip.BLUE, Pip.GREEN);
    public static BattleCard ONE_SHALL_STAND_ONE_SHALL_FALL = new BattleCard("One Shall Stand, One Shall Fall");
    public static BattleCard OPPORTUNE_OFFENSIVE = new BattleCard("Opportune Offensive", Pip.BLACK, Pip.BLUE);
    public static BattleCard OVERWHELMING_ADVANTAGE = new BattleCard("Overwhelming Advantage", Pip.ORANGE, Pip.GREEN);
    public static BattleCard PEACE_THROUGH_TYRANNY = new BattleCard("Peace Through Tyranny", Pip.ORANGE, Pip.ORANGE);
    public static BattleCard PRESS_THE_ADVANTAGE = new BattleCard("Press The Advantage", Pip.ORANGE, Pip.GREEN);
    public static BattleCard RAMMING_SPEED = new BattleCard("Ramming Speed", Pip.ORANGE);
    public static BattleCard RECKLESS_CHARGE = new BattleCard("Reckless Charge", Pip.ORANGE);

    public static BattleCard RELENTLESS_INVASION = new BattleCard("Relentless Invasion", Pip.WHITE, Pip.ORANGE, Pip.BLUE);
    public static BattleCard REPROCESS = new BattleCard("Reprocess", Pip.GREEN);
    public static BattleCard ROCK_TOSS = new BattleCard("Rock Toss", Pip.ORANGE, Pip.BLACK);
    public static BattleCard ROLL_OUT = new BattleCard("Roll Out!", Pip.ORANGE, Pip.BLUE);

    public static BattleCard SABOTAGED_ARMAMENTS = new BattleCard("Sabotaged Armaments", Pip.BLUE);
    public static BattleCard SECRET_DEALINGS = new BattleCard("Secret Dealings", Pip.GREEN, Pip.WHITE);
    public static BattleCard SECURITY_CHECKPOINT = new BattleCard("Security Checkpoint", Pip.BLUE, Pip.BLUE);
    public static BattleCard SMELT = new BattleCard("Smelt", Pip.BLUE, Pip.GREEN);
    public static BattleCard START_YOUR_ENGINES = new BattleCard("Start Your Engines", Pip.BLUE);
    public static BattleCard STEADY_SHOT = new BattleCard("Steady Shot", Pip.BLACK, Pip.BLUE);
    public static BattleCard SPECIAL_DELIVERY = new BattleCard("Special Delivery", Pip.BLACK);
    public static BattleCard TECH_RESEARCH = new BattleCard("Tech Research", Pip.GREEN);
    public static BattleCard THE_BIGGER_THEY_ARE = new BattleCard("The Bigger They Are", Pip.BLUE);
    public static BattleCard VAPORIZE = new BattleCard("Vaporize", Pip.BLUE);
    public static BattleCard WORK_OVERTIME = new BattleCard("Work Overtime", Pip.WHITE);


    public static BattleCard WEDGE_FORMATION = new BattleCard("Wedge Formation", Pip.ORANGE, Pip.BLACK, Pip.GREEN);

    public static BattleCard QUARTERMASTER = new BattleCard("Quartermaster", Pip.WHITE);

    // Upgrade - Weapons
    public static BattleCard ARMED_HOVERCRAFT = new BattleCard("Armed Hovercraft", Pip.BLUE);
    public static BattleCard BACKUP_BEAM = new BattleCard("Backup Beam", Pip.BLUE, Pip.GREEN);
    public static BattleCard COMBAT_DAGGER = new BattleCard("Combat Dagger", Pip.ORANGE, Pip.BLACK);
    public static BattleCard CROWBAR = new BattleCard("Crowbar", Pip.BLACK, Pip.BLACK);

    public static BattleCard ENERGON_AXE = new BattleCard("Energon Axe", Pip.BLUE);
    public static BattleCard ENERGON_SLINGSHOT = new BattleCard("Energon Slingshot", Pip.BLUE);
    public static BattleCard ERRATIC_LIGHTNING = new BattleCard("Erratic Lightning", Pip.ORANGE);
    public static BattleCard GRENADE_LAUNCHER = new BattleCard("Grenade Launcher", Pip.ORANGE);
    public static BattleCard HANDHELD_BLASTER = new BattleCard("Handheld Blaster", Pip.BLUE, Pip.BLUE);
    public static BattleCard HV_ELECTRON_BREACHER = new BattleCard("HV Electron Breacher", Pip.BLACK);
    public static BattleCard ION_BLASTER_OF_OPTIMUS_PRIME = new BattleCard("Ion Blaster of Optimus Prime", Pip.BLUE);
    public static BattleCard LASER_CUTLASS = new BattleCard("Laser Cutlass", Pip.BLUE);
    public static BattleCard NOBLES_BLASTER = new BattleCard("Noble's Blaster", Pip.BLUE, Pip.GREEN);
    public static BattleCard PIERCING_BLASTER = new BattleCard("Piercing Blaster", Pip.WHITE);
    public static BattleCard RR_DISRUPTOR_BLADE = new BattleCard("RR Disruptor Blade", Pip.BLACK);
    public static BattleCard SCOUNDRELS_BLASTER = new BattleCard("Scoundrel's Blaster", Pip.BLUE, Pip.GREEN);
    public static BattleCard SMOKETHROWER = new BattleCard("Smokethrower", Pip.BLACK);
    public static BattleCard STURDY_JAVELIN = new BattleCard("Sturdy Javelin", Pip.WHITE);

    // Upgrade - Armor
    public static BattleCard BASHING_SHIELD = new BattleCard("Bashing Shield", Pip.ORANGE, Pip.GREEN);
    public static BattleCard COVERT_ARMOR = new BattleCard("Covert Armor", Pip.BLUE, Pip.GREEN);
    public static BattleCard FORCE_FIELD = new BattleCard("Force Field", Pip.WHITE);

    public static BattleCard HAZARDOUS_SHIELD = new BattleCard("Hazardous Shield", Pip.BLACK);

    public static BattleCard IMPROVISED_SHIELD = new BattleCard("Improvised Shield", Pip.ORANGE, Pip.ORANGE);
    public static BattleCard INCREASED_DURABILITY = new BattleCard("Increased Durability", Pip.BLACK);

    public static BattleCard REFLEX_CIRCUITS = new BattleCard("Reflex Circuits", Pip.BLUE, Pip.GREEN);
    public static BattleCard REINFORCED_PLATING = new BattleCard("Reinforced Plating", Pip.BLUE);
    public static BattleCard SMOKE_CLOAK = new BattleCard("Smoke Cloak", Pip.BLACK, Pip.BLUE);
    public static BattleCard SPARRING_GEAR = new BattleCard("Sparring Gear", Pip.ORANGE, Pip.GREEN);
    public static BattleCard STURDY_ARMOR = new BattleCard("Sturdy Armor", Pip.BLUE, Pip.GREEN);
    public static BattleCard SUPERIOR_PLATING = new BattleCard("Superior Plating", Pip.BLUE);

    // Upgrade - Utilities
    public static BattleCard AERIAL_RECON = new BattleCard("Aerial Recon", Pip.ORANGE);
    public static BattleCard BRAVERY = new BattleCard("Bravery", Pip.BLUE);

    public static BattleCard COMPOSITE_ARMOR = new BattleCard("Composite Armor", Pip.BLACK, Pip.ORANGE);
    public static BattleCard DATA_BANK = new BattleCard("Data Bank", Pip.WHITE);
    public static BattleCard ERRATIC_ENERGY_GRENADE = new BattleCard("Erratic Energy Grenade", Pip.BLACK);
    public static BattleCard FIELD_COMMUNICATOR = new BattleCard("Field Communicator", Pip.WHITE);
    public static BattleCard IMMERSED_IN_SHADOW = new BattleCard("Immersed In Shadow", Pip.ORANGE, Pip.BLACK);

    public static BattleCard MATRIX_OF_LEADERSHIP = new BattleCard("Matrix of Leadership", Pip.ORANGE, Pip.BLUE);
    public static BattleCard MINOR_MEDIC_KIT = new BattleCard("Minor Medic Kit", Pip.BLACK, Pip.BLACK);
    public static BattleCard PERSONAL_TARGETING_DRONE = new BattleCard("Personal Targeting Drone", Pip.WHITE, Pip.GREEN);
    public static BattleCard POCKET_PROCESSOR = new BattleCard("Pocket Processor", Pip.GREEN);
    public static BattleCard SPARE_PARTS = new BattleCard("Spare Parts", Pip.WHITE, Pip.GREEN);
    public static BattleCard SUPERIOR_JETPACK = new BattleCard("Superior Jetpack", Pip.WHITE);
    public static BattleCard TURBO_BOOSTERS = new BattleCard("Turbo Boosters", Pip.ORANGE);

}
