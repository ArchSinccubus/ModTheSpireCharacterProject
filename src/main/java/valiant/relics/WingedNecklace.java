package valiant.relics;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import valiant.ValiantMod;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WingedNecklace extends CustomRelic {
    public static final String ID = "Winged Necklace";
    public static final RelicStrings STRINGS = CardCrawlGame.languagePack.getRelicStrings(ID);
    public static final String[] DESCRIPTIONS = STRINGS.DESCRIPTIONS;
    private boolean WasUsed;

    public static final Logger logger = LogManager.getLogger(WingedNecklace.class.getName());


    public WingedNecklace() {
        super(ID, new Texture(ValiantMod.WINGED_NECKLACE), RelicTier.COMMON, LandingSound.FLAT);
        logger.info("initialized");
        WasUsed = false;
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
