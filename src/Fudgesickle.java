import Patches.AbstractCardEnum;
import Patches.CharacterEnum;
import basemod.BaseMod;

import basemod.interfaces.EditCharactersSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpireInitializer
public class Fudgesickle implements EditCharactersSubscriber {

    //ValiantAssets
    private static final String VALIANT_BUTTON = "ASSETS/TestIcon.png";
    private static final String VALIANT_POTRAIT = "ASSETS/Leila Pic.png";

    public static final Logger logger = LogManager.getLogger(Fudgesickle.class.getName());

    public Fudgesickle(){

        BaseMod.subscribeToEditCharacters(this);
    }

    public static void initialize() {
        Fudgesickle mod = new Fudgesickle();
    }

    @Override
    public void receiveEditCharacters() {
        logger.info("begin editting characters");

        logger.info("add " + CharacterEnum.Character.toString());
        BaseMod.addCharacter(Character.class, "The Valiant", "A nun sent to destroy the heart of evil in the name of justice. Wields both holy power and untold fury.",
                AbstractCardEnum.WHITE.toString(), "My Character",
                VALIANT_BUTTON , VALIANT_POTRAIT,
                CharacterEnum.Character.toString());

        logger.info("done editting characters");
    }
}
