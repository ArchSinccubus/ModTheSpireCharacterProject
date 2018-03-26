package Powers;

import Cards.Rare.Attack.FinalGambit;
import Cards.Rare.Skill.DeadlyPlay;
import MainMod.Fudgesickle;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeadlyPlayPower extends AbstractPower {
    public static final String POWER_ID = "DeadlyPlay";
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    public static final Logger logger = LogManager.getLogger(Fudgesickle.class.getName());


    public DeadlyPlayPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "DeadlyPlay";
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.img = Fudgesickle.getTex("Powers/Charge.png");
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        }

    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (!card.purgeOnUse && CanBeCopied(card) && this.amount > 0) {
            this.flash();
            AbstractMonster m = null;
            if (action.target != null) {
                m = (AbstractMonster)action.target;
            }

            AbstractCard tmp = card.makeStatEquivalentCopy();
            if (tmp.name == new FinalGambit().name)
            {tmp.damage = card.damage;}
            AbstractDungeon.player.limbo.addToBottom(tmp);
            tmp.current_x = card.current_x;
            tmp.current_y = card.current_y;
            tmp.target_x = (float)Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
            tmp.target_y = (float)Settings.HEIGHT / 2.0F;
            tmp.freeToPlayOnce = true;
            if (m != null) {
                tmp.calculateCardDamage(m);
            }

            tmp.purgeOnUse = true;
            AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(tmp, m, card.energyOnUse));
            --this.amount;
            if (this.amount == 0) {
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "DeadlyPlay"));
            }
        }

    }

    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "DeadlyPlay"));
        }

    }

    public boolean CanBeCopied (AbstractCard c)
    {
        logger.info(c.name + " "+ c.type + " " + c.rawDescription);
        if (c.name == new DeadlyPlay().name)
            return false;
     if ((c.name == new FinalGambit().name) || (
        (c.type != CardType.POWER && c.type != CardType.STATUS && c.type != CardType.CURSE) &&
        (c.rawDescription.contains("Lose") || c.rawDescription.contains("lose")) &&
        (c.rawDescription.contains("HP"))))
        {
            return true;
        }
        return false;
    }

    static {
        DESCRIPTIONS = new String[] {
                "On this turn, play the next card that makes you lose HP twice.",
                "On this turn, play your next ",
                " cards that make you lose HP twice."
        };
        NAME = "Deadly Play";
    }
}
