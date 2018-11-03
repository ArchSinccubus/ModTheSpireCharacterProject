package valiant;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import valiant.cards.common.attack.*;
import valiant.cards.common.skill.*;
import valiant.cards.rare.attack.*;
import valiant.cards.rare.power.*;
import valiant.cards.rare.skill.*;
import valiant.cards.starter.BlindingLight;
import valiant.cards.starter.Defend_Valiant;
import valiant.cards.starter.MinorHealing;
import valiant.cards.starter.Strike_Valiant;
import valiant.cards.uncommon.attack.*;
import valiant.cards.uncommon.power.*;
import basemod.BaseMod;
import basemod.ModPanel;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import valiant.cards.uncommon.skill.*;
import valiant.characters.Valiant;
import valiant.patches.AbstractCardEnum;
import valiant.patches.CharacterEnum;
import valiant.relics.*;

import java.nio.charset.StandardCharsets;

@SpireInitializer
public class ValiantMod implements PostInitializeSubscriber,
        EditCardsSubscriber, EditRelicsSubscriber, EditCharactersSubscriber,
        EditStringsSubscriber,EditKeywordsSubscriber,SetUnlocksSubscriber {

    private static final Color HOLY = CardHelper.getColor(255.0f, 250.0f, 250.0f);

    //MiscStuff
    private static final String Dev = "ArchSinccubus";

    public static final String RESOURCE_PATH = "valiant/";
    public static final String IMG_PATH = RESOURCE_PATH + "img/";
    public static final String LOCALIZATION_PATH = RESOURCE_PATH + "localization/";
    public static final String CARD_STRINGS = LOCALIZATION_PATH+ "ValiantMod-CardStrings.json";
    public static final String POWER_STRINGS = LOCALIZATION_PATH + "ValiantMod-PowerStrings.json";
    public static final String RELIC_STRINGS = LOCALIZATION_PATH + "ValiantMod-RelicStrings.json";

    //ValiantAssets backgrounds
    private static final String ATTACK_WHITE = IMG_PATH + "512/bg_attack_holy_512.png";
    private static final String SKILL_WHITE = IMG_PATH + "512/bg_skill_holy_512.png";
    private static final String POWER_WHITE = IMG_PATH + "512/bg_power_holy_512.png";
    private static final String ENERGY_ORB_HOLY = IMG_PATH + "512/card_holy_orb.png";

    private static final String ATTACK_WHITE_PORTRAIT = IMG_PATH + "1024/bg_attack_holy_1024.png";
    private static final String SKILL_WHITE_PORTRAIT = IMG_PATH + "1024/bg_skill_holy_1024.png";
    private static final String POWER_WHITE_PORTRAIT = IMG_PATH + "1024/bg_power_holy_1024.png";
    private static final String ENERGY_ORB_HOLY_PORTRAIT = IMG_PATH + "1024/card_holy_orb.png";

    public static final String CARD_IMG_PATH = IMG_PATH + "cards/";
    public static final String PLACE_HOLDER_CARD_PATH = "corona.png";
    public static final String STRIKE_V = CARD_IMG_PATH + "Strike.png";
    public static final String DEFEND_V = CARD_IMG_PATH + "Defend.png";
    public static final String COMBAT_DANCE = CARD_IMG_PATH + "Combat Dance.png";
    public static final String DECIMATE = CARD_IMG_PATH + "Decimate.png";
    public static final String DIVINE_LASHES = "comet.png";
    public static final String EMPOWERING_FORCE = CARD_IMG_PATH + "Empowering Force.png";
    public static final String GRACEFUL_SIPHON = CARD_IMG_PATH + "Debilitation.png";
    public static final String MERCILESS_SMITE = CARD_IMG_PATH + "Merciless Smite.png";
    public static final String PIERCING_STAB = CARD_IMG_PATH + "Piercing Stab.png";
    public static final String POWER_STRIKE = CARD_IMG_PATH + "brainstorm.png";
    public static final String QUICK_SIPHON = CARD_IMG_PATH + "Quick Siphon.png";
    public static final String RECKLESS_SMITE = CARD_IMG_PATH + "Reckless Smite.png";
    public static final String SMITE = CARD_IMG_PATH + "Smite.png";
    public static final String TOWERING_CHARGE = CARD_IMG_PATH + "Towering Charge.png";
    public static final String WIDE_SMITE = CARD_IMG_PATH + "Wide Smite.png";
    public static final String ARCANE_HEALING = CARD_IMG_PATH + "Arcane Healing.png";
    public static final String DIVINE_FAVOR = CARD_IMG_PATH + "Divine Favor.png";
    public static final String FLASH_OF_ANGER = CARD_IMG_PATH + "Flash of Anger.png";
    public static final String HOLY_FURY = CARD_IMG_PATH + "Holy Fury.png";
    public static final String ITS_SMITING_TIME = CARD_IMG_PATH + "Its Smiting Time.png";
    public static final String MIGHT_BRACER = CARD_IMG_PATH + "Might Bracer.png";
    public static final String POWER_STANCE = CARD_IMG_PATH + "power Stance.png";
    public static final String QUICKSTEP = CARD_IMG_PATH + "Quickstep.png";
    public static final String SEEK_ANSWERS = CARD_IMG_PATH + "Seek Answers.png";
    public static final String TAKE_AIM = CARD_IMG_PATH + "Take Aim.png";
    public static final String UNYIELDING_ZEAL = CARD_IMG_PATH + "Unyielding Zeal.png";
    public static final String ZEALOUS_PERSECUTION = CARD_IMG_PATH + "Zealous Persecution.png";
    public static final String ALMIGHTY_SMITE = CARD_IMG_PATH + "Almighty Smite.png";
    public static final String DANGEROUS_SMASH = CARD_IMG_PATH + "Exhausting Smash.png";
    public static final String EXECUTION = CARD_IMG_PATH + "Execution.png";
    public static final String FINAL_GAMBIT = CARD_IMG_PATH + "Final Gambit.png";
    public static final String HOLY_STORM = CARD_IMG_PATH + "DEUS VULT.png";
    public static final String TANTRUM = CARD_IMG_PATH + "Tantrum.png";
    public static final String FULL_AWAKENING = CARD_IMG_PATH + "Full Awakening.png";
    public static final String OVERBURN = CARD_IMG_PATH + "Overburn.png";
    public static final String PEACEFUL_STANCE = CARD_IMG_PATH + "Peaceful Stance.png";
    public static final String WEAK_FORM = CARD_IMG_PATH + "Weak Form.png";
    public static final String AT_ALL_COSTS = CARD_IMG_PATH + "At All Costs.png";
    public static final String BOMBASTIC = CARD_IMG_PATH + "Bombastic.png";
    public static final String DEADLY_PLAY = CARD_IMG_PATH + "Deadly Play.png";
    public static final String SALVATION = CARD_IMG_PATH + "Salvation.png";
    public static final String TEMPO_MASTER = CARD_IMG_PATH + "Tempo Master.png";
    public static final String ABANDON_REASON = CARD_IMG_PATH + "Abandon Reason.png";
    public static final String AURA_DISCHARGE = CARD_IMG_PATH + "Aura Discharge.png";
    public static final String BREAK_THROUGH = CARD_IMG_PATH + "Break Through.png";
    public static final String CHAINER = CARD_IMG_PATH + "Chainer.png";
    public static final String DESPERATION = CARD_IMG_PATH + "Desperate Wailing.png";
    public static final String FURIOUS_SMITE = CARD_IMG_PATH + "Furious Smite.png";
    public static final String RETALIATE = CARD_IMG_PATH + "Retaliate.png";
    public static final String STRAINING_SMITE = CARD_IMG_PATH + "Chained Smite.png";
    public static final String TEAR_PSYCHE = CARD_IMG_PATH + "Tear Psyche.png";
    public static final String TEAR_SOUL = CARD_IMG_PATH + "Tear Soul.png";
    public static final String UPRISING = CARD_IMG_PATH + "Uprising.png";
    public static final String ZEALOUS_SMITE = CARD_IMG_PATH + "Zealous Smite.png";
    public static final String DELVING_PRAYER = CARD_IMG_PATH + "Delving Prayer.png";
    public static final String ENSOUL = CARD_IMG_PATH + "Ensoul.png";
    public static final String INSIGHTFUL_PRAYER = CARD_IMG_PATH + "Insightful Prayer.png";
    public static final String MIGHT_FORM = CARD_IMG_PATH + "Might Form.png";
    public static final String PUSH_ONWARD = CARD_IMG_PATH + "Push Onward.png";
    public static final String SHARPEN = CARD_IMG_PATH + "Sharpen.png";
    public static final String TURN_TABLES = CARD_IMG_PATH + "Turn Tables.png";
    public static final String ZEN = CARD_IMG_PATH + "Zen.png";
    public static final String ARCHANGEL_BOON = CARD_IMG_PATH + "Archangel's Denouement.png";
    public static final String BRACE_THE_PAIN = CARD_IMG_PATH + "Brace the pain.png";
    public static final String CHARGE = CARD_IMG_PATH + "Charge.png";
    public static final String CLEANSE = CARD_IMG_PATH + "Cleanse.png";
    public static final String COMBAT_STANCE = CARD_IMG_PATH + "Combat Dance.png";
    public static final String DUAL_SPIRIT = CARD_IMG_PATH + "Dual Spirit.png";
    public static final String GUARDIAN = CARD_IMG_PATH + "Guardian.png";
    public static final String MADDENING_REVELATION = CARD_IMG_PATH + "Maddening Revelation.png";
    public static final String MEABALIZE = CARD_IMG_PATH + "Meaballize.png";
    public static final String OVERWHELMING_LIGHT = CARD_IMG_PATH + "thought_raze.png";
    public static final String RADIANCE = CARD_IMG_PATH + "Radiance.png";
    public static final String REDUCE_TO_NOTHING = CARD_IMG_PATH + "Reduce to Nothing.png";
    public static final String SOUL_STRAIN = CARD_IMG_PATH + "Soul Strain.png";
    public static final String VINDICATION = CARD_IMG_PATH + "Vindication.png";
    public static final String ZEALOUS_AMBITION = CARD_IMG_PATH + "Zealous Ambition.png";
    public static final String BLINDING_LIGHT = CARD_IMG_PATH + "Blinding Flash.png";
    public static final String MINOR_HEALING = CARD_IMG_PATH + "Minor Healing.png";
    public static final String HOLY_LIGHT = CARD_IMG_PATH + "Holy Light.png";


    //powers
    public static final String POWER_IMG_PATH = IMG_PATH + "powers/";
    public static final String SPIRIT = POWER_IMG_PATH + "Spirit.png";
    public static final String COMBAT_STANCE_POWER =  POWER_IMG_PATH + "CombatStance.png";
    public static final String ZEN_POWER = POWER_IMG_PATH + "zen.png";
    public static final String CHARGE_POWER = POWER_IMG_PATH + "charge.png";
    public static final String ASCENSION_POWER = POWER_IMG_PATH + "FullAwakening.png";
    public static final String DEADLY_PLAY_POWER = POWER_IMG_PATH + "DeadlyPlay.png";
    public static final String DELVING_PRAYER_POWER = POWER_IMG_PATH + "DelvingPrayer.png";
    public static final String INSIGHTFUL_PRAYER_POWER = POWER_IMG_PATH + "InsightfulPrayer.png";
    public static final String POWER_ONWARDS_POWER_PATH = POWER_IMG_PATH + "PushOnward.png";
    public static final String MIGHT_FORM_POWER_PATH = POWER_IMG_PATH + "Might form.png";
    public static final String PEACEFUL_STANCE_POWER = POWER_IMG_PATH + "Peace.png";
    public static final String PUSH_ONWARD_POWER = POWER_IMG_PATH + "PushOnward.png";
    public static final String SHARPEN_POWER = POWER_IMG_PATH + "anvil.png";
    public static final String TEAR_SOUL_POWER = POWER_IMG_PATH + "darkness.png";
    public static final String WEAK_FORM_POWER = POWER_IMG_PATH + "weakForm.png";

    //icons
    private static final String VALIANT_CHAR_SELECT_PATH = IMG_PATH + "charSelect/";
    private static final String VALIANT_BUTTON = VALIANT_CHAR_SELECT_PATH + "Logo1.png";
    private static final String VALIANT_POTRAIT = VALIANT_CHAR_SELECT_PATH + "Valiant_Select_Screen.png";

    // badge
    public static final String BADGE_IMG = IMG_PATH + "BaseModBadge.png";

    public static final String RELIC_IMG_PATH = IMG_PATH + "relics/";
    public static final String texturePath = RELIC_IMG_PATH + "arcanosphere.png";
    public static final String ARCHANGEL_FEATHER = RELIC_IMG_PATH + "ArchangelsFeather.png";
    public static final String BLADE_OF_LEGEND = RELIC_IMG_PATH + "BladeOfLegend.png";
    public static final String CROWN_OF_THORNS = RELIC_IMG_PATH + "CrownOfThorns.png";
    public static final String CRUMPLED_PAPER = RELIC_IMG_PATH + "CrumpledPaper.png";
    public static final String HUGE_EGG = RELIC_IMG_PATH + "HugeEgg.png";
    public static final String ORB_OF_LIGHT = RELIC_IMG_PATH + "OrbOfLight.png";
    public static final String PRAYER_BEADS = RELIC_IMG_PATH + "PrayerBeads.png";
    public static final String WEAKENING_INCENSE = RELIC_IMG_PATH + "WeakeningIncease.png";
    public static final String WINGED_NECKLACE = RELIC_IMG_PATH + "WingedNecklace.png";
    public static final String CROSS_PENDANT = RELIC_IMG_PATH + "CrossPendantPic.png";
    public static final String DIVINE_WRATH = RELIC_IMG_PATH + "Wrath.png";


    public static final Logger logger = LogManager.getLogger(ValiantMod.class.getName());

    public static boolean IsDamaged;

    public ValiantMod(){

        BaseMod.subscribe(this);

        logger.info("creating the color " + AbstractCardEnum.Holy.toString());
        BaseMod.addColor(AbstractCardEnum.Holy,
                HOLY, HOLY, HOLY, HOLY, HOLY, HOLY, HOLY,
                ATTACK_WHITE, SKILL_WHITE,
                POWER_WHITE, ENERGY_ORB_HOLY,
                ATTACK_WHITE_PORTRAIT, SKILL_WHITE_PORTRAIT,
                POWER_WHITE_PORTRAIT, ENERGY_ORB_HOLY_PORTRAIT);

        IsDamaged = false;
    }


    public static Texture GetSpirit_Texture()
    {
        return new Texture(SPIRIT);
    }

    public static void initialize() {
        ValiantMod mod = new ValiantMod();
    }

    @Override
    public void receivePostInitialize() {
        // Mod badge
        Texture badgeTexture = new Texture(Gdx.files.internal(BADGE_IMG));
        ModPanel settingsPanel = new ModPanel();
        //settingsPanel("This mod does not have any settings (yet)", 400.0f, 700.0f, (me) -> {});
        BaseMod.registerModBadge(badgeTexture, "The Valiant", "ArchSinccubus", "A nun sent to destroy the heart of evil in the name of The Lord. Wields both holy power and untold fury.", null);

        Settings.isDailyRun = false;
        Settings.isTrial = false;
        Settings.isDemo = false;
        Settings.isModded = true;
    }

    @Override
    public void receiveEditRelics() {
        logger.info("begin editting relics");

        // Add relics
        RelicLibrary.add(new CrossPendant());
        RelicLibrary.add(new WingedNecklace());
        BaseMod.addRelicToCustomPool(new OrbOfLight(), AbstractCardEnum.Holy);
        //RelicLibrary.add(new OrbOfLight());
        BaseMod.addRelicToCustomPool(new WeakeningIncense(), AbstractCardEnum.Holy);
        //RelicLibrary.add(new WeakeningIncense());
        BaseMod.addRelicToCustomPool(new CrumpledPaper(), AbstractCardEnum.Holy);
        //RelicLibrary.add(new CrumpledPaper());
        RelicLibrary.add(new HugeEgg());
        RelicLibrary.add(new PrayerBeads());
        RelicLibrary.add(new ArchAngelsFeather());
        BaseMod.addRelicToCustomPool(new CrownOfThorns(), AbstractCardEnum.Holy);
        //RelicLibrary.add(new CrownOfThorns());
        BaseMod.addRelicToCustomPool(new BladeOfLegend(), AbstractCardEnum.Holy);
        //RelicLibrary.add(new BladeOfLegend());


        logger.info("done editting relics");
    }

    @Override
    public void receiveEditCards() {
        logger.info("begin editting cards");

        //logger.info("add cards for " + TheSeekerEnum.THE_SEEKER.toString());

        BaseMod.addCard(new Defend_Valiant());
        BaseMod.addCard(new Strike_Valiant());
        BaseMod.addCard(new BlindingLight());
        BaseMod.addCard(new MinorHealing());

        BaseMod.addCard(new PiercingStab());
        BaseMod.addCard(new EmpoweringForce());
        BaseMod.addCard(new QuickSiphon());
        BaseMod.addCard(new Smite());
        BaseMod.addCard(new RecklessSmite());
        BaseMod.addCard(new Decimate());
        BaseMod.addCard(new WideSmite());
        BaseMod.addCard(new MercilessSmite());
        BaseMod.addCard(new CombatDance());
        BaseMod.addCard(new GracefulSiphon());
        BaseMod.addCard(new ToweringCharge());

        BaseMod.addCard(new Quickstep());
        BaseMod.addCard(new DivineFavor());
        BaseMod.addCard(new ArcaneHealing());
        BaseMod.addCard(new FlashOfAnger());
        BaseMod.addCard(new ZealousPersecution());
        BaseMod.addCard(new HolyFury());
        BaseMod.addCard(new ItsSmitingTime());
        BaseMod.addCard(new SeekAnswers());
        BaseMod.addCard(new TakeAim());
        BaseMod.addCard(new UnyieldingZeal());
        BaseMod.addCard(new MightBracer());

        BaseMod.addCard(new ZealousSmite());
        //BaseMod.addCard(new BreakThrough());
        BaseMod.addCard(new HolyLight());
        BaseMod.addCard(new TearPsyche());
        BaseMod.addCard(new TearSoul());
        BaseMod.addCard(new Uprising());
        BaseMod.addCard(new FuriousSmite());
        BaseMod.addCard(new Retaliate());
        BaseMod.addCard(new AuraDischarge());
        BaseMod.addCard(new Chainer());
        BaseMod.addCard(new StrainingSmite());
        BaseMod.addCard(new Desperation());
        BaseMod.addCard(new AbandonReason());

        BaseMod.addCard(new BraceThePain());
        BaseMod.addCard(new Guardian());
        //BaseMod.addCard(new OverwhelmingLight());
        BaseMod.addCard(new Radiance());
        BaseMod.addCard(new Vindication());
        BaseMod.addCard(new ArchangelsBoon());
        BaseMod.addCard(new MaddeningRevelation());
        BaseMod.addCard(new Meaballize());
        BaseMod.addCard(new DualSpirit());
        BaseMod.addCard(new CombatStance());
        BaseMod.addCard(new ReduceToNothing());
        BaseMod.addCard(new Cleanse());
        BaseMod.addCard(new Charge());
        BaseMod.addCard(new ZealousAmbition());
        BaseMod.addCard(new SoulStrain());

        BaseMod.addCard(new Ensoul());
        BaseMod.addCard(new InsightfulPrayer());
        BaseMod.addCard(new MightForm());
        BaseMod.addCard(new PushOnward());
        BaseMod.addCard(new Sharpen());
        BaseMod.addCard(new TurnTables());
        BaseMod.addCard(new Zen());
        BaseMod.addCard(new DelvingPrayer());

        BaseMod.addCard(new AlmightySmite());
        BaseMod.addCard(new DangerousSmash());
        BaseMod.addCard(new Execution());
        BaseMod.addCard(new FinalGambit());
        BaseMod.addCard(new HolyStorm());
        BaseMod.addCard(new Tantrum());

        BaseMod.addCard(new Salvation());
        BaseMod.addCard(new AtAllCosts());
        //BaseMod.addCard(new PowerBloom());
        BaseMod.addCard(new DeadlyPlay());
        BaseMod.addCard(new Bombastic());
        BaseMod.addCard(new TempoMaster());

        BaseMod.addCard(new FullAwakening());
        BaseMod.addCard(new WeakForm());
        BaseMod.addCard(new Overburn());
        BaseMod.addCard(new PeacefulStance());


        logger.info("done editting cards");
    }

    @Override
    public void receiveSetUnlocks() {
//		UnlockTracker.addCard("Charge");
//		UnlockTracker.addCard("Decimate");
//		UnlockTracker.addCard("MercilessSmite");
//		// valiant unlock 1
//		BaseMod.addUnlockBundle(new CustomUnlockBundle(
//				"Charge", "Decimate", "MercilessSmite"
//				), CharacterEnum.TheValiant, 1);
//
//        // valiant unlock 2
//        BaseMod.addUnlockBundle(new CustomUnlockBundle(
//                "Winged Necklace", "Huge Egg", "Crown of Thorns"
//        ), CharacterEnum.TheValiant, 2);
//        UnlockTracker.addRelic("Winged Necklace");
//        UnlockTracker.addRelic("Huge Egg");
//        UnlockTracker.addRelic("Crown of Thorns");
//
//		// valiant unlock 3
//		BaseMod.addUnlockBundle(new CustomUnlockBundle(
//				"TempoMaster", "QuickSiphon", "Zen"
//				), CharacterEnum.TheValiant, 3);
//		UnlockTracker.addCard("TempoMaster");
//		UnlockTracker.addCard("QuickSiphon");
//		UnlockTracker.addCard("Zen");
//
//        // valiant unlock 5
//        BaseMod.addUnlockBundle(new CustomUnlockBundle(
//                "Archangel's Feather", "Blade of Legends", "Prayer Beads"
//        ), CharacterEnum.TheValiant, 4);
//        UnlockTracker.addRelic("Archangel's Feather");
//        UnlockTracker.addRelic("Blade of Legends");
//        UnlockTracker.addRelic("Prayer Beads");
//
//		// valiant unlock 5
//		BaseMod.addUnlockBundle(new CustomUnlockBundle(
//				"FinalGambit", "AtAllCosts", "Uprising"
//				), CharacterEnum.TheValiant, 5);
//		UnlockTracker.addCard("FinalGambit");
//		UnlockTracker.addCard("AtAllCosts");
//		UnlockTracker.addCard("Nexus");
    }

    @Override
    public void receiveEditCharacters() {
        logger.info("begin editting characters");

        logger.info("add " + CharacterEnum.TheValiant.toString());
        BaseMod.addCharacter(new Valiant(CardCrawlGame.playerName), VALIANT_BUTTON,
                VALIANT_POTRAIT, CharacterEnum.TheValiant);

        logger.info("done editting characters");
    }


    @Override
    public void receiveEditKeywords() {
        logger.info("setting up custom keywords");
        BaseMod.addKeyword(new String[] {"spirit", "Spirit"}, "Spirit increases the potency of #yhealing.");
        BaseMod.addKeyword(new String[] {"wavering", "Wavering"}, "At the start of each turn, this monster is dealt #y5 damage.");
        BaseMod.addKeyword(new String[] {"haste", "Haste"}, "For each card you play this turn, deal 10% more damage.");
    }

    @Override
    public void receiveEditStrings() {
        logger.info("begin editting strings");

        // RelicStrings

        String relicStrings = Gdx.files.internal(RELIC_STRINGS).readString(
                String.valueOf(StandardCharsets.UTF_8));

        logger.info("UNO");

        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);

        logger.info("DOS");
        // CardStrings
        String cardStrings = Gdx.files.internal(CARD_STRINGS).readString(
                String.valueOf(StandardCharsets.UTF_8));

        logger.info("TRES");
        BaseMod.loadCustomStrings(CardStrings.class, cardStrings);

        logger.info("QUATRO");

        logger.info("done editting strings");
    }



}
