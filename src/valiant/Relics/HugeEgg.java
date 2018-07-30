package valiant.Relics;

import valiant.MainMod.*;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HugeEgg extends CustomRelic {
    public static final String ID = "Huge Egg";
    public static final RelicStrings STRINGS = CardCrawlGame.languagePack.getRelicStrings(ID);
    public static final String[] DESCRIPTIONS = STRINGS.DESCRIPTIONS;
    private static final int StrengthAmount = 1;

    private static final String texturePath = "Relics/CrossPendantPic.png";

    public static final Logger logger = LogManager.getLogger(DivineWrath.class.getName());


    public HugeEgg() {
        super(ID, Fudgesickle.getTex(Fudgesickle.HUGE_EGG), RelicTier.UNCOMMON, LandingSound.HEAVY);
        logger.info("initialized");
    }

    private static Texture getRelicTexture() {
        logger.info("getting texture");
        Texture tex = new Texture(texturePath);
        logger.info("got texture");
        return tex;
    }

    public void onObtainCard(AbstractCard c) {
        if (c.cost >= 3 && c.canUpgrade()) {
            c.upgrade();
        }

    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new HugeEgg();
    }

}

