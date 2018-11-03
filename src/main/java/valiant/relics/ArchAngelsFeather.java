package valiant.relics;

import valiant.ValiantMod;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArchAngelsFeather extends CustomRelic {
    public static final String ID = "Archangels Feather";
    public static final RelicStrings STRINGS = CardCrawlGame.languagePack.getRelicStrings(ID);
    public static final String[] DESCRIPTIONS = STRINGS.DESCRIPTIONS;
    private boolean StartOfCombat;

    public static final Logger logger = LogManager.getLogger(ArchAngelsFeather.class.getName());


    public ArchAngelsFeather() {
        super(ID, new Texture(ValiantMod.ARCHANGEL_FEATHER), RelicTier.RARE, LandingSound.MAGICAL);
        logger.info("initialized");
        StartOfCombat = false;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (StartOfCombat)
        {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new HealAction(AbstractDungeon.player,AbstractDungeon.player, 2));
        }
    }

    @Override
    public void onPlayerEndTurn() {
        this.pulse = false;
        StartOfCombat = false;
    }

    public void atPreBattle() {
        StartOfCombat = true;
        this.pulse = true;
        this.beginPulse();
    }

    public void onVictory() {
        this.pulse = false;
        StartOfCombat = false;
    }

    @Override
    public AbstractRelic makeCopy() {
        return new ArchAngelsFeather();
    }

}

