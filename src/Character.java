import java.util.ArrayList;

import Patches.CharacterEnum;
import basemod.BaseMod;
import basemod.ModPanel;

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.utility.ExhaustAllEtherealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

public class Character  extends AbstractPlayer{

    @SpireEnum
    public static AbstractPlayer.PlayerClass Character;

    public static final int ENERGY_PER_TURN = 3; // how much energy you get every turn
    public static final String MY_CHARACTER_SHOULDER_2 = "ASSETS/Cards/Skills/corona_p.png"; // campfire pose
    public static final String MY_CHARACTER_SHOULDER_1 = "ASSETS/Cards/Skills/corona_p.png"; // another campfire pose
    public static final String MY_CHARACTER_CORPSE = "ASSETS/Char/Blahblahhugebitch.png"; // dead corpse
    public static final String MY_CHARACTER_SKELETON_ATLAS = "ASSETS/TestAnim/idle/skeleton.atlas"; // spine animation atlas
    public static final String MY_CHARACTER_SKELETON_JSON = "ASSETS/TestAnim/idle/skeleton.json"; // spine animation json


    public static final int STARTING_HP = 90;
    public static final int MAX_HP = 90;
    public static final int STARTING_GOLD = 99;
    public static final int HAND_SIZE = 5;

    public Character (String name, PlayerClass chosenClass) {
        super(name, chosenClass);

        this.dialogX = (this.drawX + 0.0F * Settings.scale); // set location for text bubbles
        this.dialogY = (this.drawY + 220.0F * Settings.scale); // you can just copy these values

        initializeClass(null, MY_CHARACTER_SHOULDER_2, // required call to load textures and setup energy/loadout
                MY_CHARACTER_SHOULDER_1,
                MY_CHARACTER_CORPSE,
                getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));

        //loadAnimation(MY_CHARACTER_SKELETON_ATLAS, MY_CHARCTER_SKELETON_JSON, 1.0F); // if you're using modified versions of base game animations or made animations in spine make sure to include this bit and the following lines

        AnimationState.TrackEntry e = this.state.setAnimation(0, "animation", true);
        e.setTime(e.getEndTime() * MathUtils.random());
    }

    // ADD CARDS
    public static ArrayList<String> getStartingDeck() { // starting deck 'nuff said
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("Strike_W");
        retVal.add("Strike_W");
        retVal.add("Strike_W");
        retVal.add("Strike_W");
        retVal.add("Strike_W");
        retVal.add("Defend_W");
        retVal.add("Defend_W");
        retVal.add("Defend_W");
        retVal.add("Defend_W");
        retVal.add("Defend_W");

        return retVal;
    }

    // ADD RELICS
    public static ArrayList<String> getStartingRelics() { // starting relics - also simple
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("DivineWrath");
        UnlockTracker.markRelicAsSeen("DivineWrath");

        return retVal;
    }


    public static CharSelectInfo getLoadout() { // the rest of the character loadout so includes your character select screen info plus hp and starting gold
        return new CharSelectInfo("My Character", "A nun sent to destroy the heart of evil in the name of The Lord. Wields both holy power and untold fury.",
                STARTING_HP, MAX_HP, STARTING_GOLD, HAND_SIZE,
                CharacterEnum.Character, getStartingRelics(), getStartingDeck(), false);
    }


}
