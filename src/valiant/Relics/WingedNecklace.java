package valiant.Relics;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import valiant.MainMod.*;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WingedNecklace extends CustomRelic {
    public static final String ID = "Winged Necklace";
    public static final RelicStrings STRINGS = CardCrawlGame.languagePack.getRelicStrings(ID);
    public static final String[] DESCRIPTIONS = STRINGS.DESCRIPTIONS;
    private boolean WasUsed;

    private static final String texturePath = "Relics/CrossPendantPic.png";

    public static final Logger logger = LogManager.getLogger(DivineWrath.class.getName());


    public WingedNecklace() {
        super(ID, Fudgesickle.getTex(Fudgesickle.WINGED_NECKLACE), RelicTier.COMMON, LandingSound.FLAT);
        logger.info("initialized");
        WasUsed = false;
    }

    private static Texture getRelicTexture() {
        logger.info("getting texture");
        Texture tex = new Texture(texturePath);
        logger.info("got texture");
        return tex;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public int onPlayerGainBlock(int blockAmount) {
        if (!WasUsed)
        {
            WasUsed = true;
            pulse = false;
            this.flash();
            return blockAmount + 2;
        }
        return blockAmount;
    }

    @Override
    public void onPlayerEndTurn() {
        this.pulse = false;
    }

    @Override
    public void atTurnStart() {
        WasUsed = false;
        this.pulse = true;
        this.beginPulse();
    }

    public void onVictory() {
        this.pulse = false;
        WasUsed = false;
    }

    @Override
    public AbstractRelic makeCopy() {
        return new WingedNecklace();
    }

}
