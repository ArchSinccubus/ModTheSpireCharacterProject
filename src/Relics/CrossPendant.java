package Relics;

import Actions.CrossPendantAction;
import MainMod.*;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.unique.CodexAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CrossPendant extends CustomRelic {
    public  static final String ID = "Cross Pendant";
    public static final RelicStrings STRINGS = CardCrawlGame.languagePack.getRelicStrings(ID);
    public static final String[] DESCRIPTIONS = STRINGS.DESCRIPTIONS;
    private static final int StrengthAmount = 1;
    public AbstractCard chosenCard;

    private static final String texturePath = "Relics/CrossPendantPic.png";

    public static final Logger logger = LogManager.getLogger(DivineWrath.class.getName());


    public CrossPendant() {
        super(ID, Fudgesickle.getTex(Fudgesickle.CROSS_PENDANT), RelicTier.STARTER, LandingSound.MAGICAL);
        logger.info("initialized");
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
    public AbstractRelic makeCopy() {
        return new CrossPendant();
    }

    @Override
    public void atBattleStart() {
        this.flash();
        AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        AbstractDungeon.actionManager.addToBottom(new CrossPendantAction());
    }
}
