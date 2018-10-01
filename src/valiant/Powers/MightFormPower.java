package valiant.Powers;

import valiant.MainMod.Fudgesickle;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class MightFormPower extends AbstractPower {
    public static final String POWER_ID = "MightForm";
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    public static int NumBlock;

    public MightFormPower(AbstractCreature owner,int numBlock, int amount) {
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
        this.img = Fudgesickle.getTex("Powers/Might form.png");
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0].replace("#b" , "" + this.amount);

    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        if (this.amount == 0) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "MightForm"));
        }

        if (this.amount >= 999) {
            this.amount = 999;
        }

        if (this.amount < 0) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "MightForm"));
        }

    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (!card.purgeOnUse && card.cost >= 3 ) {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(owner, owner, this.amount));
        }

    }



    static {
        DESCRIPTIONS = new String[] {
                "Whenever you play a card that costs 3 or more, gain #b Block."
        };
        NAME = "Might Form";
    }
}
