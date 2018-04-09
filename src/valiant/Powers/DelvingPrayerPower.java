package valiant.Powers;

import Actions.ExhaustTopOfDrawPileAction;
import valiant.MainMod.Fudgesickle;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class DelvingPrayerPower extends AbstractPower {
    public static final String POWER_ID = "DelvingPrayer";
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    public static boolean Upgraded;
    public static int cardsToDraw;

    public DelvingPrayerPower(AbstractCreature owner, int amount,int cards) {
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
        this.cardsToDraw = cards;
        this.img = Fudgesickle.getTex("valiant/Powers/Charge.png");
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        if (this.amount == 0) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "DelvingPrayer"));
        }

        if (this.amount >= 999) {
            this.amount = 999;
        }

        if (this.amount <= 0) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "DelvingPrayer"));
        }

    }

    public void updateDescription() {
        cardsToDraw = amount;
        if (amount == 1)
            this.description = DESCRIPTIONS[0];
        else
            this.description = DESCRIPTIONS[1].replace("#b" , "" + amount);

    }

    public void atStartOfTurn() {
        AbstractDungeon.actionManager.addToTop(new DrawCardAction(this.owner,cardsToDraw));
        AbstractDungeon.actionManager.addToBottom(new ExhaustTopOfDrawPileAction(this.owner,1));
    }

    static {
        DESCRIPTIONS = new String[] {
                "At the beginning to each turn, draw a card and #yExhaust the top card of your draw pile",
                "At the beginning to each turn, draw #b cards and #yExhaust the top card of your draw pile."
        };
        NAME = "Delving Prayer";
    }
}
