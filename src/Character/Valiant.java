package Character;

import java.util.ArrayList;
import java.util.Iterator;

import Patches.CharacterEnum;
import basemod.BaseMod;
import basemod.ModPanel;

import Relics.*;

import MainMod.Fudgesickle;
import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.utility.ExhaustAllEtherealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.BirdFacedUrn;
import com.megacrit.cardcrawl.relics.CursedKey;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Valiant extends CustomPlayer{

    public static final int ENERGY_PER_TURN = 3; // how much energy you get every turn
    public static final String MY_CHARACTER_SHOULDER_2 = "resources/TestAnim/shoulder2.png"; // campfire pose
    public static final String MY_CHARACTER_SHOULDER_1 = "resources/TestAnim/shoulder.png"; // another campfire pose
    public static final String MY_CHARACTER_CORPSE = "resources/TestAnim/corpse.png"; // dead corpse
    public static final String MY_CHARACTER_SKELETON_ATLAS = "resources/TestAnim/idle/skeleton.atlas"; // spine animation atlas
    public static final String MY_CHARACTER_SKELETON_JSON = "resources/TestAnim/idle/skeleton.json"; // spine animation json

    public static final Logger logger = LogManager.getLogger(Valiant.class.getName());

    public static final String[] orbTextures = {
            "resources/orb/layer1.png",
            "resources/orb/layer2.png",
            "resources/orb/layer3.png",
            "resources/orb/layer4.png",
            "resources/orb/layer5.png",
            "resources/orb/layer6.png",
            "resources/orb/layer1d.png",
            "resources/orb/layer2d.png",
            "resources/orb/layer3d.png",
            "resources/orb/layer4d.png",
            "resources/orb/layer5d.png",
    };


    public static final int STARTING_HP = 90;
    public static final int MAX_HP = 90;
    public static final int STARTING_GOLD = 99;
    public static final int HAND_SIZE = 5;

    public Valiant(String name, PlayerClass chosenClass) {
        super(name, chosenClass,orbTextures ,"resources/orb/vfx.png" ,null, MY_CHARACTER_SKELETON_JSON );

        this.dialogX = (this.drawX + 0.0F * Settings.scale); // set location for text bubbles
        this.dialogY = (this.drawY + 220.0F * Settings.scale); // you can just copy these values

        initializeClass(null, MY_CHARACTER_SHOULDER_2, // required call to load textures and setup energy/loadout
                MY_CHARACTER_SHOULDER_1,
                MY_CHARACTER_CORPSE,
                getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));

        loadAnimation(MY_CHARACTER_SKELETON_ATLAS, MY_CHARACTER_SKELETON_JSON, 1.0F); // if you're using modified versions of base game animations or made animations in spine make sure to include this bit and the following lines

        AnimationState.TrackEntry e = this.state.setAnimation(0, "animation", true);
        e.setTime(e.getEndTime() * MathUtils.random());
    }

    // ADD CARDS
    public static ArrayList<String> getStartingDeck() { // starting deck 'nuff said
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("AttackDemo");
        retVal.add("AttackDemo");
        retVal.add("AttackDemo");
        retVal.add("AttackDemo");
        retVal.add("AttackDemo");
        retVal.add("SkillDemo");
        retVal.add("SkillDemo");
        retVal.add("SkillDemo");
        retVal.add("SkillDemo");
        retVal.add("BlindingLight");
        retVal.add("MinorHealing");
        return retVal;
    }

    @Override
    public void addPower(AbstractPower powerToApply) {
        logger.info(powerToApply.ID + " LOOK AT THIS SHIT SON");
        if (hasRelic("Winged Necklace") && powerToApply.ID == "Frail")
            getRelic("Winged Necklace").flash();
        else
            super.addPower(powerToApply);

    }

    // ADD RELICS
    public static ArrayList<String> getStartingRelics() { // starting relics - also simple
        ArrayList<String> retVal = new ArrayList<>();

        retVal.add(CrossPendant.ID);

        UnlockTracker.markRelicAsSeen("DivineWrath");


        return retVal;
    }

    @Override
    public void applyStartOfTurnPostDrawPowers()
    {
        super.applyStartOfTurnPostDrawPowers();
        this.damagedThisCombat = 0;
    }

    public static CharSelectInfo getLoadout() { // the rest of the character loadout so includes your character select screen info plus hp and starting gold
        return new CharSelectInfo("Valiant", "A zealous nun wielding both holy power and untold fury.",
                STARTING_HP, MAX_HP, STARTING_GOLD, HAND_SIZE,
                CharacterEnum.TheValiant, getStartingRelics(), getStartingDeck(), false);
    }


}
