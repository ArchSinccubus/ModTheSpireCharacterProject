import Patches.AbstractCardEnum;
import Patches.CharacterEnum;
import basemod.BaseMod;

import basemod.ModPanel;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;

import Relics.*;
import Cards.*;

import com.megacrit.cardcrawl.helpers.RelicLibrary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;

@SpireInitializer
public class Fudgesickle implements PostInitializeSubscriber,
        EditCardsSubscriber, EditRelicsSubscriber, EditCharactersSubscriber,
        EditStringsSubscriber {

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

    public static final Logger logger = LogManager.getLogger(Fudgesickle.class.getName());

    public Fudgesickle(){

        BaseMod.subscribeToEditCharacters(this);

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
    }

    public static void initialize() {
        Fudgesickle mod = new Fudgesickle();
    }

    @Override
    public void receivePostInitialize() {
        // Mod badge
        Texture badgeTexture = new Texture(Gdx.files.internal("ASSETS/TestIcon.png"));
        ModPanel settingsPanel = new ModPanel();
        settingsPanel.addLabel("This mod does not have any settings (yet)", 400.0f, 700.0f, (me) -> {});
        BaseMod.registerModBadge(badgeTexture, Dev, Dev, "This is muh shit. F**k ye", settingsPanel);
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

        logger.info("add " + CharacterEnum.Character.toString());
        BaseMod.addCharacter(Character.class, "The Valiant", "A nun sent to destroy the heart of evil in the name of The Lord. Wields both holy power and untold fury.",
                AbstractCardEnum.WHITE.toString(), "My Character",
                VALIANT_BUTTON , VALIANT_POTRAIT,
                CharacterEnum.Character.toString());

        logger.info("done editting characters");
    }

    @Override
    public void receiveEditStrings() {
        logger.info("begin editting strings");

        // RelicStrings
        String relicStrings = Gdx.files.internal("localization/FruityMod-RelicStrings.json").readString(
                String.valueOf(StandardCharsets.UTF_8));
        //BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
        // CardStrings
        String cardStrings = Gdx.files.internal("localization/FruityMod-CardStrings.json").readString(
                String.valueOf(StandardCharsets.UTF_8));
        //BaseMod.loadCustomStrings(CardStrings.class, cardStrings);

        logger.info("done editting strings");
    }
}
