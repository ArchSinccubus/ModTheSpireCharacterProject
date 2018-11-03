package valiant.relics;

import valiant.ValiantMod;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CrumpledPaper extends CustomRelic {
    public static final String ID = "Crumpled Paper";
    public static final RelicStrings STRINGS = CardCrawlGame.languagePack.getRelicStrings(ID);
    public static final String[] DESCRIPTIONS = STRINGS.DESCRIPTIONS;
    private static final int StrengthAmount = 1;

    public static final Logger logger = LogManager.getLogger(CrumpledPaper.class.getName());


    public CrumpledPaper() {
        super(ID, new Texture(ValiantMod.CRUMPLED_PAPER), RelicTier.UNCOMMON, LandingSound.FLAT);
        logger.info("initialized");
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new CrumpledPaper();
    }

}
