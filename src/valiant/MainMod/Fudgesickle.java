package valiant.MainMod;

import Cards.Common.Attack.*;
import Cards.Common.Skill.*;
import Cards.Rare.Attack.*;
import Cards.Rare.Power.*;
import Cards.Rare.Skill.*;
import Cards.Starter.BlindingLight;
import Cards.Starter.Defend_Valiant;
import Cards.Starter.MinorHealing;
import Cards.Starter.Strike_Valiant;
import Cards.Uncommon.Attack.*;
import Cards.Uncommon.Power.*;
import Cards.Uncommon.Skill.*;
import Cards.Uncommon.Skill.Cleanse;
import valiant.Patches.AbstractCardEnum;
import valiant.Patches.CharacterEnum;
import basemod.BaseMod;

import basemod.ModPanel;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;

import valiant.Relics.*;
import valiant.Character.*;

import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.RelicLibrary;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;

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

    //card pics
    public static final String AttackDemo = "cards/STRIKE.png";
    public static final String SkillDemo = "Cards_other/Skills/corona.png";
    public static final String PowerDemo = "Cards_other/valiant.Powers/enigma.png";


    public static final String COMBAT_DANCE = "cards/anomaly.png";
    public static final String DECIMATE = "cards/starburst.png";
    public static final String DIVINE_LASHES = "cards/arcane_tempest.png";
    public static final String EMPOWERING_FORCE = "cards/irradiate.png";
    public static final String GRACEFUL_SIPHON = "cards/archives.png";
    public static final String MERCILESS_SMITE = "cards/astral_haze.png";
    public static final String PIERCING_STAB = "cards/dimension_door.png";
    public static final String POWER_STRIKE = "cards/brainstorm.png";
    public static final String QUICK_SIPHON = "cards/brilliance.png";
    public static final String RECKLESS_SMITE = "cards/channel.png";
    public static final String SMITE = "cards/chaos_form.png";
    public static final String TOWERING_CHARGE = "cards/coalescence.png";
    public static final String WIDE_SMITE = "cards/comet.png";
    public static final String ARCANE_HEALING = "cards/convergence.png";
    public static final String DIVINE_FAVOR = "cards/corona.png";
    public static final String FLASH_OF_ANGER = "cards/creativity.png";
    public static final String HOLY_FURY = "cards/dark_matter.png";
    public static final String ITS_SMITING_TIME = "cards/defend_purple.png";
    public static final String MIGHT_BRACER = "cards/arcane_armor.png";
    public static final String POWER_STANCE = "cards/echo.png";
    public static final String QUICKSTEP = "cards/eclipse.png";
    public static final String SEEK_ANSWERS = "cards/enigma.png";
    public static final String TAKE_AIM = "cards/entropy.png";
    public static final String UNYIELDING_ZEAL = "cards/equinox.png";
    public static final String ZEALOUS_PERSECUTION = "cards/phase_coil.png";
    public static final String ALMIGHTY_SMITE = "cards/flicker.png";
    public static final String DANGEROUS_SMASH = "cards/plasma_wave.png";
    public static final String EXECUTION = "cards/pulse_barrier.png";
    public static final String FINAL_GAMBIT = "cards/nebula.png";
    public static final String HOLY_STORM = "cards/ether_blast.png";
    public static final String TANTRUM = "cards/essence_dart.png";
    public static final String FULL_AWAKENING = "cards/eureka.png";
    public static final String OVERBURN = "cards/event_horizon.png";
    public static final String PEACEFUL_STANCE = "cards/conversion.png";
    public static final String WEAK_FORM = "cards/feedback.png";
    public static final String AT_ALL_COSTS = "cards/flare.png";
    public static final String BOMBASTIC = "cards/flow.png";
    public static final String DEADLY_PLAY = "cards/null_storm.png";
    public static final String POWER_BLOOM = "cards/void_ray.png";
    public static final String SALVATION = "cards/flux_shield.png";
    public static final String TEMPO_MASTER = "cards/force_ripple.png";
    public static final String ABANDON_REASON = "cards/unstable_orb.png";
    public static final String AURA_DISCHARGE = "cards/gravity_well.png";
    public static final String BREAK_THROUGH = "cards/hypothesis.png";
    public static final String CHAINER = "cards/illuminate.png";
    public static final String DESPERATION = "cards/implosion.png";
    public static final String FURIOUS_SMITE = "cards/magic_missile.png";
    public static final String RETALIATE = "cards/magnetize.png";
    public static final String STRAINING_SMITE = "cards/meteor_shower.png";
    public static final String TEAR_PSYCHE = "cards/mind_over_matter.png";
    public static final String TEAR_SOUL = "cards/nexus.png";
    public static final String UPRISING = "cards/nova.png";
    public static final String ZEALOUS_SMITE = "cards/periapt_of_celerity.png";
    public static final String DELVING_PRAYER = "cards/periapt_of_potency.png";
    public static final String ENSOUL = "cards/zenith.png";
    public static final String INSIGHTFUL_PRAYER = "cards/vortex.png";
    public static final String MIGHT_FORM = "cards/power_overwhelming.png";
    public static final String PUSH_ONWARD = "cards/overload.png";
    public static final String SHARPEN = "cards/disperse.png";
    public static final String TURN_TABLES = "cards/reflection_ward.png";
    public static final String ZEN = "cards/retrograde.png";
    public static final String ARCHANGEL_BOON = "cards/runic_binding.png";
    public static final String BRACE_THE_PAIN = "cards/shimmer.png";
    public static final String CHARGE = "cards/convergence.png";
    public static final String CLEANSE = "cards/siphon_power.png";
    public static final String COMBAT_STANCE = "cards/siphon_speed.png";
    public static final String DUAL_SPIRIT = "cards/strike_purple.png";
    public static final String GUARDIAN = "cards/stroke_of_genius.png";
    public static final String MADDENING_REVELATION = "cards/surge.png";
    public static final String MEABALIZE = "cards/syzygy.png";
    public static final String OVERWHELMING_LIGHT = "cards/thought_raze.png";
    public static final String RADIANCE = "cards/transference.png";
    public static final String REDUCE_TO_NOTHING = "cards/umbral_bolt.png";
    public static final String SOUL_STRAIN = "cards/singularity.png";
    public static final String VINDICATION = "cards/vacuum.png";
    public static final String ZEALOUS_AMBITION = "cards/genesis.png";
    public static final String BLINDING_LIGHT = "cards/prismatic_sphere.png";
    public static final String MINOR_HEALING = "cards/flux.png";
    public static final String HOLY_LIGHT = "cards/vortex.png";


    public static final String VORTEX = "cards/vortex.png";
    public static final String ZENITH = "cards/zenith.png";
    public static final String PHASE_COIL = "cards/phase_coil.png";

    //powers
    public static final String SpiritPic = "valiant/Powers/Spirit.png";
    public static final String CombatStancePic = "valiant/Powers/CombatStance.png";

    //icons
    private static final String VALIANT_BUTTON = "Logo1.png";
    private static final String VALIANT_POTRAIT = "LeilaPic.jpg";

    // badge
    public static final String BADGE_IMG = "resources/BaseModBadge.png";

    public static final String texturePath = "valiant/Relics/arcanosphere.png";
    public static final String ARCHANGEL_FEATHER = "valiant/Relics/ArchangelsFeather.png";
    public static final String BLADE_OF_LEGEND = "valiant/Relics/BladeOfLegend.png";
    public static final String CROWN_OF_THORNS = "valiant/Relics/CrownOfThorns.png";
    public static final String CRUMPLED_PAPER = "valiant/Relics/CrumpledPaper.png";
    public static final String HUGE_EGG = "valiant/Relics/HugeEgg.png";
    public static final String ORB_OF_LIGHT = "valiant/Relics/OrbOfLight.png";
    public static final String PRAYER_BEADS = "valiant/Relics/PrayerBeads.png";
    public static final String WEAKENING_INCENSE = "valiant/Relics/WeakeningIncease.png";
    public static final String WINGED_NECKLACE = "valiant/Relics/WingedNecklace.png";
    public static final String CROSS_PENDANT = "valiant/Relics/CrossPendantPic.png";


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
        BaseMod.addColor(AbstractCardEnum.Holy.toString(),
                HOLY, HOLY, HOLY, HOLY, HOLY, HOLY, HOLY,
                ATTACK_WHITE, SKILL_WHITE,
                POWER_WHITE, ENERGY_ORB_HOLY,
                ATTACK_WHITE_PORTRAIT, SKILL_WHITE_PORTRAIT,
                POWER_WHITE_PORTRAIT, ENERGY_ORB_HOLY_PORTRAIT);

        IsDamaged = false;
    }

    public static Texture GetAttack_WTexture()
    {
        return new Texture(makePath(AttackDemo));
    }

    public static Texture GetDefend_WTexture()
    {
        return new Texture(makePath(SkillDemo));
    }

    public static Texture GetPower_WTexture()
    {
        return new Texture(makePath(PowerDemo));
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
        BaseMod.registerModBadge(badgeTexture, Dev, Dev, "The Valiant, wielder of rage and purity, is now en route to the spire!", settingsPanel);

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
        BaseMod.addRelicToCustomPool(new OrbOfLight(), AbstractCardEnum.Holy.name());
        //RelicLibrary.add(new OrbOfLight());
        BaseMod.addRelicToCustomPool(new WeakeningIncense(), AbstractCardEnum.Holy.name());
        //RelicLibrary.add(new WeakeningIncense());
        BaseMod.addRelicToCustomPool(new CrumpledPaper(), AbstractCardEnum.Holy.name());
        //RelicLibrary.add(new CrumpledPaper());
        RelicLibrary.add(new HugeEgg());
        RelicLibrary.add(new PrayerBeads());
        RelicLibrary.add(new ArchAngelsFeather());
        BaseMod.addRelicToCustomPool(new CrownOfThorns(), AbstractCardEnum.Holy.name());
        //RelicLibrary.add(new CrownOfThorns());
        BaseMod.addRelicToCustomPool(new BladeOfLegend(), AbstractCardEnum.Holy.name());
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
        //BaseMod.addCard(new DivineLashes());
        //BaseMod.addCard(new PowerStrike());
        BaseMod.addCard(new GracefulSiphon());
        BaseMod.addCard(new ToweringCharge());

        BaseMod.addCard(new Quickstep());
        BaseMod.addCard(new DivineFavor());
        BaseMod.addCard(new ArcaneHealing());
        BaseMod.addCard(new FlashOfAnger());
        //BaseMod.addCard(new PowerStance());
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
        BaseMod.addCharacter(Valiant.class, "The Valiant", "A nun sent to destroy the heart of evil in the name of The Lord. Wields both holy power and untold fury.",
                AbstractCardEnum.Holy.toString(), "The Valiant",
                makePath(VALIANT_BUTTON) , makePath(VALIANT_POTRAIT),
                CharacterEnum.TheValiant.toString());

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