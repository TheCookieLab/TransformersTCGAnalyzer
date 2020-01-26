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
    public final BattleCardType battleCardType;

    public BattleCard() {
        this(null, new ArrayList<>());
    }

    public BattleCard(String name, List<Pip> pips) {
        this(name, null, pips);
    }

    public BattleCard(String name, BattleCardType battleCardType, List<Pip> pips) {
        this.name = name;
        this.pips = pips;
        this.battleCardType = battleCardType;
    }

    public BattleCard(String name, BattleCardType battleCardType, Pip... pips) {
        this(name, battleCardType, Arrays.asList(pips));
    }

    public BattleCard(BattleCardType battleCardType) {
        this(null, battleCardType, Arrays.asList());
    }

    public BattleCard(String name, Pip... pips) {
        this(name, null, Arrays.asList(pips));
    }

    public BattleCard(Pip... pips) {
        this(null, null, Arrays.asList(pips));
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
    public static BattleCard BATTLEFIELD_INCURSION = new BattleCard("BattleField Incursion", BattleCardType.ACTION, Pip.GREEN);
    public static BattleCard CONFIDENCE = new BattleCard("Confidence", BattleCardType.ACTION, Pip.ORANGE);
    public static BattleCard CALCULATED_STRIKE = new BattleCard("Calculated Strike", BattleCardType.ACTION, Pip.BLACK);
    public static BattleCard CONSTRUCTICON_ENIGMA = new BattleCard("Constructicon Enigma", BattleCardType.ACTION, Pip.GREEN);
    public static BattleCard DESIGNATED_TARGET = new BattleCard("Designated Target", BattleCardType.ACTION, Pip.BLACK, Pip.BLACK);
    public static BattleCard DISARM = new BattleCard("Disarm", BattleCardType.ACTION, Pip.BLUE);
    public static BattleCard ESCAPE_ROUTE = new BattleCard("Escape Route", BattleCardType.ACTION, Pip.GREEN, Pip.WHITE);
    public static BattleCard EQUIPMENT_ENTHUSIAST = new BattleCard("Equipment Enthusiast", BattleCardType.ACTION, Pip.WHITE);
    public static BattleCard FIGHT_FOR_POSITION = new BattleCard("Fight For Position", BattleCardType.ACTION, Pip.ORANGE, Pip.BLACK);
    public static BattleCard FUEL_CACHE = new BattleCard("Fuel Cache", BattleCardType.ACTION, Pip.ORANGE, Pip.BLUE, Pip.WHITE);
    public static BattleCard HEAT_OF_BATTLE = new BattleCard("Heat of Battle", BattleCardType.ACTION, Pip.WHITE);
    public static BattleCard HIDDEN_FORTIFICATION = new BattleCard("Hidden Fortification", BattleCardType.SECRET_ACTION, Pip.BLUE);
    public static BattleCard HIDING_SPOT = new BattleCard("Hiding Spot", BattleCardType.SECRET_ACTION, Pip.BLACK);
    public static BattleCard INCOMING_TRANSMISSION = new BattleCard("Incoming Transmission", BattleCardType.ACTION, Pip.ORANGE);
    public static BattleCard INSPIRING_LEADERSHIP = new BattleCard("Inspiring Leadership", BattleCardType.ACTION, Pip.BLUE);
    public static BattleCard I_STILL_FUNCTION = new BattleCard("I Still Function!", BattleCardType.ACTION);

    public static BattleCard LEAP_INTO_BATTLE = new BattleCard("Leap Into Battle", BattleCardType.ACTION, Pip.BLUE);
    public static BattleCard MARKSMANSHIP = new BattleCard("Marksmanship", BattleCardType.ACTION, Pip.BLUE);
    public static BattleCard MASTER_PLAN = new BattleCard("Master Plan", BattleCardType.ACTION, Pip.BLUE, Pip.GREEN);
    public static BattleCard ONE_SHALL_STAND_ONE_SHALL_FALL = new BattleCard("One Shall Stand, One Shall Fall", BattleCardType.ACTION);
    public static BattleCard OPPORTUNE_OFFENSIVE = new BattleCard("Opportune Offensive", BattleCardType.ACTION, Pip.BLACK, Pip.BLUE);
    public static BattleCard OVERWHELMING_ADVANTAGE = new BattleCard("Overwhelming Advantage", BattleCardType.ACTION, Pip.ORANGE, Pip.GREEN);
    public static BattleCard PEACE_THROUGH_TYRANNY = new BattleCard("Peace Through Tyranny", BattleCardType.ACTION, Pip.ORANGE, Pip.ORANGE);
    public static BattleCard PRESS_THE_ADVANTAGE = new BattleCard("Press The Advantage", BattleCardType.ACTION, Pip.ORANGE, Pip.GREEN);
    public static BattleCard RAMMING_SPEED = new BattleCard("Ramming Speed", BattleCardType.ACTION, Pip.ORANGE);
    public static BattleCard RECKLESS_CHARGE = new BattleCard("Reckless Charge", BattleCardType.ACTION, Pip.ORANGE);

    public static BattleCard RELENTLESS_INVASION = new BattleCard("Relentless Invasion", BattleCardType.ACTION, Pip.WHITE, Pip.ORANGE, Pip.BLUE);
    public static BattleCard REPROCESS = new BattleCard("Reprocess", BattleCardType.ACTION, Pip.GREEN);
    public static BattleCard ROCK_TOSS = new BattleCard("Rock Toss", BattleCardType.ACTION, Pip.ORANGE, Pip.BLACK);
    public static BattleCard ROLL_OUT = new BattleCard("Roll Out!", BattleCardType.ACTION, Pip.ORANGE, Pip.BLUE);

    public static BattleCard SABOTAGED_ARMAMENTS = new BattleCard("Sabotaged Armaments", BattleCardType.SECRET_ACTION, Pip.BLUE);
    public static BattleCard SECRET_DEALINGS = new BattleCard("Secret Dealings", BattleCardType.ACTION, Pip.GREEN, Pip.WHITE);
    public static BattleCard SECURITY_CHECKPOINT = new BattleCard("Security Checkpoint", BattleCardType.ACTION, Pip.BLUE, Pip.BLUE);
    public static BattleCard SMELT = new BattleCard("Smelt", BattleCardType.ACTION, Pip.BLUE, Pip.GREEN);
    public static BattleCard START_YOUR_ENGINES = new BattleCard("Start Your Engines", BattleCardType.ACTION, Pip.BLUE);
    public static BattleCard STEADY_SHOT = new BattleCard("Steady Shot", BattleCardType.ACTION, Pip.BLACK, Pip.BLUE);
    public static BattleCard SPECIAL_DELIVERY = new BattleCard("Special Delivery", BattleCardType.ACTION, Pip.BLACK);
    public static BattleCard TECH_RESEARCH = new BattleCard("Tech Research", BattleCardType.ACTION, Pip.GREEN);
    public static BattleCard THE_BIGGER_THEY_ARE = new BattleCard("The Bigger They Are", BattleCardType.ACTION, Pip.BLUE);
    public static BattleCard VAPORIZE = new BattleCard("Vaporize", BattleCardType.ACTION, Pip.BLUE);
    public static BattleCard WORK_OVERTIME = new BattleCard("Work Overtime", BattleCardType.ACTION, Pip.WHITE);


    public static BattleCard WEDGE_FORMATION = new BattleCard("Wedge Formation", BattleCardType.ACTION, Pip.ORANGE, Pip.BLACK, Pip.GREEN);

    public static BattleCard QUARTERMASTER = new BattleCard("Quartermaster", BattleCardType.ACTION, Pip.WHITE);

    // Upgrade - Weapons
    public static BattleCard ARMED_HOVERCRAFT = new BattleCard("Armed Hovercraft", BattleCardType.UPGRADE_WEAPON, Pip.BLUE);
    public static BattleCard BACKUP_BEAM = new BattleCard("Backup Beam", BattleCardType.UPGRADE_WEAPON, Pip.BLUE, Pip.GREEN);
    public static BattleCard COMBAT_DAGGER = new BattleCard("Combat Dagger", BattleCardType.UPGRADE_WEAPON, Pip.ORANGE, Pip.BLACK);
    public static BattleCard CROWBAR = new BattleCard("Crowbar", BattleCardType.UPGRADE_WEAPON, Pip.BLACK, Pip.BLACK);

    public static BattleCard ENERGON_AXE = new BattleCard("Energon Axe", BattleCardType.UPGRADE_WEAPON, Pip.BLUE);
    public static BattleCard ENERGON_SLINGSHOT = new BattleCard("Energon Slingshot", BattleCardType.UPGRADE_WEAPON, Pip.BLUE);
    public static BattleCard ERRATIC_LIGHTNING = new BattleCard("Erratic Lightning", BattleCardType.UPGRADE_WEAPON, Pip.ORANGE);
    public static BattleCard GRENADE_LAUNCHER = new BattleCard("Grenade Launcher", BattleCardType.UPGRADE_WEAPON, Pip.ORANGE);
    public static BattleCard HANDHELD_BLASTER = new BattleCard("Handheld Blaster", BattleCardType.UPGRADE_WEAPON, Pip.BLUE, Pip.BLUE);
    public static BattleCard HV_ELECTRON_BREACHER = new BattleCard("HV Electron Breacher", BattleCardType.UPGRADE_WEAPON, Pip.BLACK);
    public static BattleCard ION_BLASTER_OF_OPTIMUS_PRIME = new BattleCard("Ion Blaster of Optimus Prime", BattleCardType.UPGRADE_WEAPON, Pip.BLUE);
    public static BattleCard LASER_CUTLASS = new BattleCard("Laser Cutlass", BattleCardType.UPGRADE_WEAPON, Pip.BLUE);
    public static BattleCard NOBLES_BLASTER = new BattleCard("Noble's Blaster", BattleCardType.UPGRADE_WEAPON, Pip.BLUE, Pip.GREEN);
    public static BattleCard PIERCING_BLASTER = new BattleCard("Piercing Blaster", BattleCardType.UPGRADE_WEAPON, Pip.WHITE);
    public static BattleCard RR_DISRUPTOR_BLADE = new BattleCard("RR Disruptor Blade", BattleCardType.UPGRADE_WEAPON, Pip.BLACK);
    public static BattleCard SCOUNDRELS_BLASTER = new BattleCard("Scoundrel's Blaster", BattleCardType.UPGRADE_WEAPON, Pip.BLUE, Pip.GREEN);
    public static BattleCard SMOKETHROWER = new BattleCard("Smokethrower", BattleCardType.UPGRADE_WEAPON, Pip.BLACK);
    public static BattleCard STURDY_JAVELIN = new BattleCard("Sturdy Javelin", BattleCardType.UPGRADE_WEAPON, Pip.WHITE);

    // Upgrade - Armor
    public static BattleCard BASHING_SHIELD = new BattleCard("Bashing Shield", BattleCardType.UPGRADE_ARMOR, Pip.ORANGE, Pip.GREEN);
    public static BattleCard COVERT_ARMOR = new BattleCard("Covert Armor", BattleCardType.UPGRADE_ARMOR, Pip.BLUE, Pip.GREEN);
    public static BattleCard FORCE_FIELD = new BattleCard("Force Field", BattleCardType.UPGRADE_ARMOR, Pip.WHITE);

    public static BattleCard HAZARDOUS_SHIELD = new BattleCard("Hazardous Shield", BattleCardType.UPGRADE_ARMOR, Pip.BLACK);

    public static BattleCard IMPROVISED_SHIELD = new BattleCard("Improvised Shield", BattleCardType.UPGRADE_ARMOR, Pip.ORANGE, Pip.ORANGE);
    public static BattleCard INCREASED_DURABILITY = new BattleCard("Increased Durability", BattleCardType.UPGRADE_ARMOR, Pip.BLACK);

    public static BattleCard REFLEX_CIRCUITS = new BattleCard("Reflex Circuits", BattleCardType.UPGRADE_ARMOR, Pip.BLUE, Pip.GREEN);
    public static BattleCard REINFORCED_PLATING = new BattleCard("Reinforced Plating", BattleCardType.UPGRADE_ARMOR, Pip.BLUE);
    public static BattleCard SMOKE_CLOAK = new BattleCard("Smoke Cloak", BattleCardType.UPGRADE_ARMOR, Pip.BLACK, Pip.BLUE);
    public static BattleCard SPARRING_GEAR = new BattleCard("Sparring Gear", BattleCardType.UPGRADE_ARMOR, Pip.ORANGE, Pip.GREEN);
    public static BattleCard STURDY_ARMOR = new BattleCard("Sturdy Armor", BattleCardType.UPGRADE_ARMOR, Pip.BLUE, Pip.GREEN);
    public static BattleCard SUPERIOR_PLATING = new BattleCard("Superior Plating", BattleCardType.UPGRADE_ARMOR, Pip.BLUE);

    // Upgrade - Utilities
    public static BattleCard AERIAL_RECON = new BattleCard("Aerial Recon", BattleCardType.UPGRADE_UTILITY, Pip.ORANGE);
    public static BattleCard BRAVERY = new BattleCard("Bravery", BattleCardType.UPGRADE_UTILITY, Pip.BLUE);

    public static BattleCard COMPOSITE_ARMOR = new BattleCard("Composite Armor", BattleCardType.UPGRADE_UTILITY, Pip.BLACK, Pip.ORANGE);
    public static BattleCard DATA_BANK = new BattleCard("Data Bank", BattleCardType.UPGRADE_UTILITY, Pip.WHITE);
    public static BattleCard ENERGY_PACK = new BattleCard("Energy Pack", BattleCardType.UPGRADE_UTILITY, Pip.ORANGE);
    public static BattleCard ERRATIC_ENERGY_GRENADE = new BattleCard("Erratic Energy Grenade", BattleCardType.UPGRADE_UTILITY, Pip.BLACK);
    public static BattleCard FIELD_COMMUNICATOR = new BattleCard("Field Communicator", BattleCardType.UPGRADE_UTILITY, Pip.WHITE);
    public static BattleCard IMMERSED_IN_SHADOW = new BattleCard("Immersed In Shadow", BattleCardType.UPGRADE_UTILITY, Pip.ORANGE, Pip.BLACK);

    public static BattleCard MATRIX_OF_LEADERSHIP = new BattleCard("Matrix of Leadership", BattleCardType.UPGRADE_UTILITY, Pip.ORANGE, Pip.BLUE);
    public static BattleCard MINOR_MEDIC_KIT = new BattleCard("Minor Medic Kit", BattleCardType.UPGRADE_UTILITY, Pip.BLACK, Pip.BLACK);
    public static BattleCard PERSONAL_TARGETING_DRONE = new BattleCard("Personal Targeting Drone", BattleCardType.UPGRADE_UTILITY, Pip.WHITE, Pip.GREEN);
    public static BattleCard POCKET_PROCESSOR = new BattleCard("Pocket Processor", BattleCardType.UPGRADE_UTILITY, Pip.GREEN);
    public static BattleCard SPARE_PARTS = new BattleCard("Spare Parts", BattleCardType.UPGRADE_UTILITY, Pip.WHITE, Pip.GREEN);
    public static BattleCard SUPERIOR_JETPACK = new BattleCard("Superior Jetpack", BattleCardType.UPGRADE_UTILITY, Pip.WHITE);
    public static BattleCard TURBO_BOOSTERS = new BattleCard("Turbo Boosters", BattleCardType.UPGRADE_UTILITY, Pip.ORANGE);

}
