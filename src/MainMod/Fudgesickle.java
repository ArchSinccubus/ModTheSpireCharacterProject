package MainMod;

import Cards.Common.Attack.*;
import Cards.Common.Skill.*;
import Cards.Starter.BlindingLight;
import Cards.Starter.Defend_W;
import Cards.Starter.MinorHealing;
import Cards.Starter.Strike_W;
import Patches.AbstractCardEnum;
import Patches.CharacterEnum;
import basemod.BaseMod;

import basemod.ModPanel;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;

import Relics.*;
import Character.*;

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
        EditStringsSubscriber {

    private static final Color HOLY = CardHelper.getColor(255.0f, 250.0f, 250.0f);
    private static final String ARCHMOD_ASSETS_FOLDER = "ASSETS"; //TODO: Change to your folder if different;

    //MiscStuff
    private static final String Dev = "ArchSinccubus";

    //ValiantAssets backgrounds
    private static final String ATTACK_WHITE = "ASSETS/Cards/Backgrounds/512/WhiteBackgroundAttack.png";
    private static final String SKILL_WHITE = "ASSETS/Cards/Backgrounds/512/WhiteBackgroundSkill.png";
    private static final String POWER_WHITE = "ASSETS/Cards/Backgrounds/512/WhiteBackgroundPower.png";
    private static final String ENERGY_ORB_PURPLE = "ASSETS/Cards/Backgrounds/512/card_hybrid_orb.png";

    private static final String ATTACK_PURPLE_PORTRAIT = "ASSETS/Cards/Backgrounds/1024/bg_attack_hybrid.png";
    private static final String SKILL_PURPLE_PORTRAIT = "ASSETS/Cards/Backgrounds/1024/bg_skill_hybrid.png";
    private static final String POWER_PURPLE_PORTRAIT = "ASSETS/Cards/Backgrounds/1024/bg_power_hybrid.png";
    private static final String ENERGY_ORB_PURPLE_PORTRAIT = "ASSETS/Cards/Backgrounds/1024/card_hybrid_orb.png";

    //card pics
    public static final String Strike_W = "Cards/Attacks/comet.png";
    public static final String Defend_W = "Cards/Skills/corona.png";

    //icons
    private static final String VALIANT_BUTTON = "TestIcon.png";
    private static final String VALIANT_POTRAIT = "LeilaPic.jpg";

    // badge
    public static final String BADGE_IMG = "ASSETS/BaseModBadge.png";

    private static final String texturePath = "Relics/arcanosphere.png";

    public static final Logger logger = LogManager.getLogger(Fudgesickle.class.getName());



    public static final String makePath(String ressource) {
        return ARCHMOD_ASSETS_FOLDER + "/" + ressource;
    }

    public Fudgesickle(){

        logger.info("subscribing to postInitialize event");
        BaseMod.subscribeToPostInitialize(this);

        logger.info("subscribing to editCharacters event");
        BaseMod.subscribeToEditCharacters(this);

        logger.info("subscribing to editRelics event");
        BaseMod.subscribeToEditRelics(this);

        logger.info("subscribing to editCards event");
        BaseMod.subscribeToEditCards(this);

        logger.info("subscribing to editStrings event");
        BaseMod.subscribeToEditStrings(this);

        logger.info("creating the color " + AbstractCardEnum.Holy.toString());
        BaseMod.addColor(AbstractCardEnum.Holy.toString(),
                HOLY, HOLY, HOLY, HOLY, HOLY, HOLY, HOLY,
                ATTACK_WHITE, SKILL_WHITE,
                POWER_WHITE, ENERGY_ORB_PURPLE,
                ATTACK_PURPLE_PORTRAIT, SKILL_PURPLE_PORTRAIT,
                POWER_PURPLE_PORTRAIT, ENERGY_ORB_PURPLE_PORTRAIT);
    }

    public static Texture GetAttack_WTexture()
    {
        return new Texture(makePath(Strike_W));
    }

    public static Texture GetDefend_WTexture()
    {
        return new Texture(makePath(Defend_W));
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
        Texture badgeTexture = new Texture(Gdx.files.internal("ASSETS/BaseModBadge.png"));
        ModPanel settingsPanel = new ModPanel();
        settingsPanel.addLabel("This mod does not have any settings (yet)", 400.0f, 700.0f, (me) -> {});
        BaseMod.registerModBadge(badgeTexture, Dev, Dev, "This is muh shit. F**k ye", settingsPanel);

        Settings.isDailyRun = false;
        Settings.isTrial = false;
        Settings.isDemo = false;
    }

    @Override
    public void receiveEditRelics() {
        logger.info("begin editting relics");

        // Add relics
        RelicLibrary.add(new DivineWrath());

        logger.info("done editting relics");
    }

    @Override
    public void receiveEditCards() {
        logger.info("begin editting cards");

        //logger.info("add cards for " + TheSeekerEnum.THE_SEEKER.toString());

        BaseMod.addCard(new Defend_W());
        BaseMod.addCard(new Strike_W());
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
        BaseMod.addCard(new DivineLashes());
        BaseMod.addCard(new PowerStrike());
        BaseMod.addCard(new GracefulSiphon());
        BaseMod.addCard(new ToweringCharge());

        BaseMod.addCard(new Quickstep());
        BaseMod.addCard(new DivineFavor());
        BaseMod.addCard(new ArcaneHealing());
        BaseMod.addCard(new FlashOfAnger());
        BaseMod.addCard(new PowerStance());
        BaseMod.addCard(new ZealousPersecution());
        BaseMod.addCard(new HolyFury());
        BaseMod.addCard(new ItsSmitingTime());
        BaseMod.addCard(new Quickstep());
        BaseMod.addCard(new SeekAnswers());
        BaseMod.addCard(new TakeAim());
        BaseMod.addCard(new Intimidation());

        logger.info("done editting cards");
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
    public void receiveEditStrings() {
        logger.info("begin editting strings");

        // RelicStrings

        String relicStrings = Gdx.files.internal("Localization/Valiant-RelicStrings.json").readString(
                String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
        // CardStrings
        String cardStrings = Gdx.files.internal("Localization/Valiant-CardStrings.json").readString(
                String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(CardStrings.class, cardStrings);

        logger.info("done editting strings");
    }



}
