package Relics;

import Actions.AddCardToHandAtion;
import Actions.CrossPendantAction;
import MainMod.*;
import Powers.SpiritPower;
import Powers.WaveringPower;
import basemod.abstracts.CustomCard;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.unique.CodexAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;

public class ArchAngelsFeather extends CustomRelic {
    public static final String ID = "Archangels Feather";
    public static final RelicStrings STRINGS = CardCrawlGame.languagePack.getRelicStrings(ID);
    public static final String[] DESCRIPTIONS = STRINGS.DESCRIPTIONS;
    private boolean StartOfCombat;

    private static final String texturePath = "Relics/CrossPendantPic.png";

    public static final Logger logger = LogManager.getLogger(DivineWrath.class.getName());


    public ArchAngelsFeather() {
        super(ID, Fudgesickle.getTex(Fudgesickle.ARCHANGEL_FEATHER), RelicTier.RARE, LandingSound.MAGICAL);
        logger.info("initialized");
        StartOfCombat = false;
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

