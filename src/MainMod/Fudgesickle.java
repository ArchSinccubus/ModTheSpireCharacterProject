package MainMod;

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
import Cards.*;
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

    private static final Color WHITE = CardHelper.getColor(255.0f, 250.0f, 250.0f);
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
    public static final String Strike_W = "ASSETS/Cards/Attacks/comet.png";
    public static final String Defend_W = "ASSETS/Cards/Skills/corona.png";

    //icons
    private static final String VALIANT_BUTTON = "ASSETS/TestIcon.png";
    private static final String VALIANT_POTRAIT = "ASSETS/Leila Pic.png";

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

        logger.info("creating the color " + AbstractCardEnum.WHITE.toString());
        BaseMod.addColor(AbstractCardEnum.WHITE.toString(),
                WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE,
                ATTACK_WHITE, SKILL_WHITE,
                POWER_WHITE, ENERGY_ORB_PURPLE,
                ATTACK_PURPLE_PORTRAIT, SKILL_PURPLE_PORTRAIT,
                POWER_PURPLE_PORTRAIT, ENERGY_ORB_PURPLE_PORTRAIT);
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

        logger.info("done editting cards");
    }

    @Override
    public void receiveEditCharacters() {
        logger.info("begin editting characters");

        logger.info("add " + CharacterEnum.TheValiant.toString());
        BaseMod.addCharacter(Valiant.class, "The Valiant", "A nun sent to destroy the heart of evil in the name of The Lord. Wields both holy power and untold fury.",
                AbstractCardEnum.WHITE.toString(), "My Valiant.Valiant",
                VALIANT_BUTTON , VALIANT_POTRAIT,
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


    public static Texture getRelicTexture() {
        logger.info("getting texture");
        Texture tex = new Texture(makePath(texturePath));
logger.info(tex.getDepth() + " " + tex.getHeight());
        return tex;
    }
}
