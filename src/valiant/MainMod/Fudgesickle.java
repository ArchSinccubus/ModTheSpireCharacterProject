package valiant.MainMod;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import valiant.Cards.Common.Attack.*;
import valiant.Cards.Common.Skill.*;
import valiant.Cards.Rare.Attack.*;
import valiant.Cards.Rare.Power.*;
import valiant.Cards.Rare.Skill.*;
import valiant.Cards.Starter.BlindingLight;
import valiant.Cards.Starter.Defend_Valiant;
import valiant.Cards.Starter.MinorHealing;
import valiant.Cards.Starter.Strike_Valiant;
import valiant.Cards.Uncommon.Attack.*;
import valiant.Cards.Uncommon.Power.*;
import basemod.BaseMod;
import basemod.ModPanel;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import basemod.animations.SpriterAnimation;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import valiant.Cards.Uncommon.Skill.*;
import valiant.Character.Valiant;
import valiant.Patches.AbstractCardEnum;
import valiant.Patches.CharacterEnum;
import valiant.Relics.*;

import java.nio.charset.StandardCharsets;

@SpireInitializer
public class Fudgesickle implements PostInitializeSubscriber,
        EditCardsSubscriber, EditRelicsSubscriber, EditCharactersSubscriber,
        EditStringsSubscriber,EditKeywordsSubscriber,SetUnlocksSubscriber {

    private static final Color HOLY = CardHelper.getColor(255.0f, 250.0f, 250.0f);
    private static final String ARCHMOD_ASSETS_FOLDER = "resources"; //TODO: Change to your folder if different;

    //MiscStuff
    private static final String Dev = "ArchSinccubus";

    //ValiantAssets backgrounds
    private static final String ATTACK_WHITE = "resources/Cards_other/Backgrounds/512/bg_attack_holy_512.png";
    private static final String SKILL_WHITE = "resources/Cards_other/Backgrounds/512/bg_skill_holy_512.png";
    private static final String POWER_WHITE = "resources/Cards_other/Backgrounds/512/bg_power_holy_512.png";
    private static final String ENERGY_ORB_HOLY = "resources/Cards_other/Backgrounds/512/card_holy_orb.png";

    private static final String ATTACK_WHITE_PORTRAIT = "resources/Cards_other/Backgrounds/1024/bg_attack_holy_1024.png";
    private static final String SKILL_WHITE_PORTRAIT = "resources/Cards_other/Backgrounds/1024/bg_skill_holy_1024.png";
    private static final String POWER_WHITE_PORTRAIT = "resources/Cards_other/Backgrounds/1024/bg_power_holy_1024.png";
    private static final String ENERGY_ORB_HOLY_PORTRAIT = "resources/Cards_other/Backgrounds/1024/card_holy_orb.png";

    public static final String STRIKE_V = "CardsFinal/Strike.png";
    public static final String DEFEND_V = "CardsFinal/Defend.png";
    public static final String COMBAT_DANCE = "CardsFinal/Combat Dance.png";
    public static final String DECIMATE = "CardsFinal/Decimate.png";
    public static final String EMPOWERING_FORCE = "CardsFinal/Empowering Force.png";
    public static final String GRACEFUL_SIPHON = "CardsFinal/Debilitation.png";
    public static final String MERCILESS_SMITE = "CardsFinal/Merciless Smite.png";
    public static final String PIERCING_STAB = "CardsFinal/Piercing Stab.png";
    public static final String POWER_STRIKE = "CardsFinal/brainstorm.png";
    public static final String QUICK_SIPHON = "CardsFinal/Quick Siphon.png";
    public static final String RECKLESS_SMITE = "CardsFinal/Reckless Smite.png";
    public static final String SMITE = "CardsFinal/Smite.png";
    public static final String TOWERING_CHARGE = "CardsFinal/Towering Charge.png";
    public static final String WIDE_SMITE = "CardsFinal/Wide Smite.png";
    public static final String ARCANE_HEALING = "CardsFinal/Arcane Healing.png";
    public static final String DIVINE_FAVOR = "CardsFinal/Divine Favor.png";
    public static final String FLASH_OF_ANGER = "CardsFinal/Flash of Anger.png";
    public static final String HOLY_FURY = "CardsFinal/Holy Fury.png";
    public static final String ITS_SMITING_TIME = "CardsFinal/Its Smiting Time.png";
    public static final String MIGHT_BRACER = "CardsFinal/Might Bracer.png";
    public static final String POWER_STANCE = "CardsFinal/Power Stance.png";
    public static final String QUICKSTEP = "CardsFinal/Quickstep.png";
    public static final String SEEK_ANSWERS = "CardsFinal/Seek Answers.png";
    public static final String TAKE_AIM = "CardsFinal/Take Aim.png";
    public static final String UNYIELDING_ZEAL = "CardsFinal/Unyielding Zeal.png";
    public static final String ZEALOUS_PERSECUTION = "CardsFinal/Zealous Persecution.png";
    public static final String ALMIGHTY_SMITE = "CardsFinal/Almighty Smite.png";
    public static final String DANGEROUS_SMASH = "CardsFinal/Exhausting Smash.png";
    public static final String EXECUTION = "CardsFinal/Execution.png";
    public static final String FINAL_GAMBIT = "CardsFinal/Final Gambit.png";
    public static final String HOLY_STORM = "CardsFinal/DEUS VULT.png";
    public static final String TANTRUM = "CardsFinal/Tantrum.png";
    public static final String FULL_AWAKENING = "CardsFinal/Full Awakening.png";
    public static final String OVERBURN = "CardsFinal/Overburn.png";
    public static final String PEACEFUL_STANCE = "CardsFinal/Peaceful Stance.png";
    public static final String WEAK_FORM = "CardsFinal/Weak Form.png";
    public static final String AT_ALL_COSTS = "CardsFinal/At All Costs.png";
    public static final String BOMBASTIC = "CardsFinal/Bombastic.png";
    public static final String DEADLY_PLAY = "CardsFinal/Deadly Play.png";
    public static final String SALVATION = "CardsFinal/Salvation.png";
    public static final String TEMPO_MASTER = "CardsFinal/Tempo Master.png";
    public static final String ABANDON_REASON = "CardsFinal/Abandon Reason.png";
    public static final String AURA_DISCHARGE = "CardsFinal/Aura Discharge.png";
    public static final String BREAK_THROUGH = "CardsFinal/Break Through.png";
    public static final String CHAINER = "CardsFinal/Chainer.png";
    public static final String DESPERATION = "CardsFinal/Desperate Wailing.png";
    public static final String FURIOUS_SMITE = "CardsFinal/Furious Smite.png";
    public static final String RETALIATE = "CardsFinal/Retaliate.png";
    public static final String STRAINING_SMITE = "CardsFinal/Chained Smite.png";
    public static final String TEAR_PSYCHE = "CardsFinal/Tear Psyche.png";
    public static final String TEAR_SOUL = "CardsFinal/Tear Soul.png";
    public static final String UPRISING = "CardsFinal/Uprising.png";
    public static final String ZEALOUS_SMITE = "CardsFinal/Zealous Smite.png";
    public static final String DELVING_PRAYER = "CardsFinal/Delving Prayer.png";
    public static final String ENSOUL = "CardsFinal/Ensoul.png";
    public static final String INSIGHTFUL_PRAYER = "CardsFinal/Insightful Prayer.png";
    public static final String MIGHT_FORM = "CardsFinal/Might Form.png";
    public static final String PUSH_ONWARD = "CardsFinal/Push Onward.png";
    public static final String SHARPEN = "CardsFinal/Sharpen.png";
    public static final String TURN_TABLES = "CardsFinal/Turn Tables.png";
    public static final String ZEN = "CardsFinal/Zen.png";
    public static final String ARCHANGEL_BOON = "CardsFinal/Archangel's Denouement.png";
    public static final String BRACE_THE_PAIN = "CardsFinal/Brace the pain.png";
    public static final String CHARGE = "CardsFinal/Charge.png";
    public static final String CLEANSE = "CardsFinal/Cleanse.png";
    public static final String COMBAT_STANCE = "CardsFinal/Combat Dance.png";
    public static final String DUAL_SPIRIT = "CardsFinal/Dual Spirit.png";
    public static final String GUARDIAN = "CardsFinal/Guardian.png";
    public static final String MADDENING_REVELATION = "CardsFinal/Maddening Revelation.png";
    public static final String MEABALIZE = "CardsFinal/Meaballize.png";
    public static final String OVERWHELMING_LIGHT = "CardsFinal/thought_raze.png";
    public static final String RADIANCE = "CardsFinal/Radiance.png";
    public static final String REDUCE_TO_NOTHING = "CardsFinal/Reduce to Nothing.png";
    public static final String SOUL_STRAIN = "CardsFinal/Soul Strain.png";
    public static final String VINDICATION = "CardsFinal/Vindication.png";
    public static final String ZEALOUS_AMBITION = "CardsFinal/Zealous Ambition.png";
    public static final String BLINDING_LIGHT = "CardsFinal/Blinding Flash.png";
    public static final String MINOR_HEALING = "CardsFinal/Minor Healing.png";
    public static final String HOLY_LIGHT = "CardsFinal/Holy Light.png";


    //powers
    public static final String SpiritPic = "Powers/Spirit.png";
    public static final String CombatStancePic = "Powers/CombatStance.png";

    //icons
    private static final String VALIANT_BUTTON = "Logo1.png";
    private static final String VALIANT_POTRAIT = "Valiant_Select_Screen.png";

    // badge
    public static final String BADGE_IMG = "resources/BaseModBadge.png";

    public static final String texturePath = "valiant/Relics/arcanosphere.png";
    public static final String ARCHANGEL_FEATHER = "Relics/ArchangelsFeather.png";
    public static final String BLADE_OF_LEGEND = "Relics/BladeOfLegend.png";
    public static final String CROWN_OF_THORNS = "Relics/CrownOfThorns.png";
    public static final String CRUMPLED_PAPER = "Relics/CrumpledPaper.png";
    public static final String HUGE_EGG = "Relics/HugeEgg.png";
    public static final String ORB_OF_LIGHT = "Relics/OrbOfLight.png";
    public static final String PRAYER_BEADS = "Relics/PrayerBeads.png";
    public static final String WEAKENING_INCENSE = "Relics/WeakeningIncease.png";
    public static final String WINGED_NECKLACE = "Relics/WingedNecklace.png";
    public static final String CROSS_PENDANT = "Relics/CrossPendantPic.png";


    public static final Logger logger = LogManager.getLogger(Fudgesickle.class.getName());

    public static boolean IsDamaged;


    public static final String makePath(String ressource) {
        return ARCHMOD_ASSETS_FOLDER + "/" + ressource;
    }

    public Fudgesickle(){

        BaseMod.subscribe(this);

//        logger.info("subscribing to postInitialize event");
//        BaseMod.subscribeToPostInitialize(this);
//
//        logger.info("subscribing to editCharacters event");
//        BaseMod.subscribeToEditCharacters(this);
//
//        logger.info("subscribing to editRelics event");
//        BaseMod.subscribeToEditRelics(this);
//
//        logger.info("subscribing to editCards event");
//        BaseMod.subscribeToEditCards(this);
//
//        /* Disable this during playtesting for being counterproductive */
//         //logger.info("subscribing to setUnlocks event");
//         //BaseMod.subscribeToSetUnlocks(this);
//
//        logger.info("subscribing to editStrings event");
//        BaseMod.subscribeToEditStrings(this);
//
//        logger.info("subscribing to editKeywords event");
//        BaseMod.subscribeToEditKeywords(this);

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
        return new Texture(makePath(SpiritPic));
    }

    public static Texture getTex(String path)
    {
        return new Texture(makePath(path));
    }

    public static Texture getRelicTexture() {
        logger.info("getting texture");
        Texture tex = new Texture(makePath(texturePath));
        logger.info(tex.getDepth() + " " + tex.getHeight());
        return tex;
    }

    public static void initialize() {
        Fudgesickle mod = new Fudgesickle();
    }

    @Override
    public void receivePostInitialize() {
        // Mod badge
        Texture badgeTexture = new Texture(Gdx.files.internal("resources/BaseModBadge.png"));
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
        BaseMod.addCharacter(new Valiant(CardCrawlGame.playerName), makePath(VALIANT_BUTTON),
                makePath(VALIANT_POTRAIT), CharacterEnum.TheValiant);

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

        String relicStrings = Gdx.files.internal("Localization/Valiant-RelicStrings.json").readString(
                String.valueOf(StandardCharsets.UTF_8));

        logger.info("UNO");

        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);

        logger.info("DOS");
        // CardStrings
        String cardStrings = Gdx.files.internal("Localization/Valiant-CardStrings.json").readString(
                String.valueOf(StandardCharsets.UTF_8));

        logger.info("TRES");
        BaseMod.loadCustomStrings(CardStrings.class, cardStrings);

        logger.info("QUATRO");

        logger.info("done editting strings");
    }



}
