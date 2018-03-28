package Relics;

import Actions.AddCardToHandAtion;
import Actions.CrossPendantAction;
import Cards.Rare.Attack.FinalGambit;
import Cards.Rare.Skill.DeadlyPlay;
import MainMod.*;
import Powers.SpiritPower;
import Powers.WaveringPower;
import basemod.abstracts.CustomCard;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.unique.CodexAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
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

public class CrownOfThorns extends CustomRelic {
    public static final String ID = "Crown Of Thorns";
    public static final RelicStrings STRINGS = CardCrawlGame.languagePack.getRelicStrings(ID);
    public static final String[] DESCRIPTIONS = STRINGS.DESCRIPTIONS;
    private int AMT;

    private static final String texturePath = "Relics/CrossPendantPic.png";

    public static final Logger logger = LogManager.getLogger(DivineWrath.class.getName());

    public CrownOfThorns() {
        super(ID, Fudgesickle.getTex(Fudgesickle.CROWN_OF_THORNS), RelicTier.RARE, LandingSound.FLAT);
        logger.info("initialized");
        AMT = 0;
    }

    public void setCounter(int c) {
        this.counter = c;
        if (this.AMT == 0) {
            this.description = this.DESCRIPTIONS[0] + 1 + this.DESCRIPTIONS[1] + this.DESCRIPTIONS[2];
        } else {
            this.description = this.DESCRIPTIONS[0] + 1 + this.DESCRIPTIONS[1] + this.DESCRIPTIONS[3].replace("#g" , "" + AMT) + this.DESCRIPTIONS[4];
        }

        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.initializeTips();
    }

    public void onMasterDeckChange() {
        this.counter = 0;
        Iterator var1 = AbstractDungeon.player.masterDeck.group.iterator();
        AMT = 0;
        while(var1.hasNext()) {
            AbstractCard c = (AbstractCard)var1.next();
            if (IsLifeLossCard(c)) {
                ++this.AMT;
            }
        }
        logger.info("AMT: " + AMT);
        this.counter = AMT / 3;
        logger.info("COUNTER: " + this.counter);

        if (this.AMT == 0) {
            this.description = this.DESCRIPTIONS[0] + 1 + this.DESCRIPTIONS[1] + this.DESCRIPTIONS[2];
        } else {
            this.description = this.DESCRIPTIONS[0] + 1 + this.DESCRIPTIONS[1] + this.DESCRIPTIONS[3].replace("#g" , "" + AMT) + this.DESCRIPTIONS[4];
        }

        logger.info(this.DESCRIPTIONS[0]);
        logger.info(this.DESCRIPTIONS[1]);
        logger.info(this.DESCRIPTIONS[2]);
        logger.info(this.DESCRIPTIONS[3]);
        logger.info(this.DESCRIPTIONS[4]);

        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.initializeTips();
    }

    public void onEquip() {
        this.counter = 0;
        Iterator var1 = AbstractDungeon.player.masterDeck.group.iterator();
        AMT = 0;
        while(var1.hasNext()) {
            AbstractCard c = (AbstractCard)var1.next();
            if (IsLifeLossCard(c)) {
                ++this.AMT;
            }
        }
        this.counter = AMT / 3;

        if (this.counter == 0) {
            this.description = this.DESCRIPTIONS[0] + 1 + this.DESCRIPTIONS[1] + this.DESCRIPTIONS[2];
        } else {
            this.description = this.DESCRIPTIONS[0] + 1 + this.DESCRIPTIONS[1] + this.DESCRIPTIONS[3].replace("#g" , "" + AMT) + this.DESCRIPTIONS[4];
        }

        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.initializeTips();
    }

    public void atBattleStart() {
        if (this.counter > 0) {
            this.flash();
            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, this.counter), this.counter));
            AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        }

    }

    public boolean IsLifeLossCard (AbstractCard c)
    {
        logger.info(c.name + " "+ c.type + " " + c.rawDescription);
        if (c.name == new DeadlyPlay().name)
            return false;
        if ((c.name == new FinalGambit().name) || (
                (c.type != AbstractCard.CardType.POWER && c.type != AbstractCard.CardType.STATUS && c.type != AbstractCard.CardType.CURSE) &&
                        (c.rawDescription.contains("Lose") || c.rawDescription.contains("lose")) &&
                        (c.rawDescription.contains("HP"))))
        {
            return true;
        }
        return false;
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

    }

    public void atTurnStart() {

    }


    @Override
    public AbstractRelic makeCopy() {
        return new CrownOfThorns();
    }

}
