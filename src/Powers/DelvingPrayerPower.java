package Powers;

import Actions.ExhaustTopOfDrawPileAction;
import Actions.PowerThroughAction;
import Actions.SharpenAction;
import MainMod.Fudgesickle;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

public class DelvingPrayerPower extends AbstractPower {
    public static final String POWER_ID = "DelvingPrayer";
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    public static boolean Upgraded;
    public static int cardsToDraw;

    public DelvingPrayerPower(AbstractCreature owner,int numBlock, int amount,int cards , boolean upgraded) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        if (this.amount >= 999) {
            this.amount = 999;
        }

        if (this.amount <= -999) {
            this.amount = -999;
        }
        this.updateDescription();
        this.Upgraded = upgraded;
        this.cardsToDraw = cards;
        this.img = Fudgesickle.getTex("Powers/Charge.png");
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        if (this.amount == 0) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "InsightfulPrayer"));
        }

        if (this.amount >= 999) {
            this.amount = 999;
        }

        if (this.amount <= -999) {
            this.amount = -999;
        }

    }

    public void updateDescription() {
        cardsToDraw = amount;
        if (!Upgraded)
            this.description = DESCRIPTIONS[0];
        else
            this.description = DESCRIPTIONS[1];

    }

    public void atStartOfTurn() {
        AbstractDungeon.actionManager.addToTop(new DrawCardAction(this.owner,cardsToDraw));
        AbstractDungeon.actionManager.addToTop(new ExhaustTopOfDrawPileAction(this.owner,cardsToDraw));
    }

    static {
        DESCRIPTIONS = new String[] {
                "At the beginning to each turn, draw a card and Exhaust the top card of your draw pile",
                "At the beginning to each turn, draw " + cardsToDraw + " card and Exhaust the top " + cardsToDraw + " cards of your draw pile."
        };
        NAME = "Delving Prayer";
    }
}
