package valiant.powers;

import com.badlogic.gdx.graphics.Texture;
import valiant.ValiantMod;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class InsightfulPrayerPower extends AbstractPower {
    public static final String POWER_ID = "InsightfulPrayer";
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    public static boolean Upgraded;
    public int cardsToDraw;

    public InsightfulPrayerPower(AbstractCreature owner, int amount,int cards) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.description = DESCRIPTIONS[0];
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
        this.img = new Texture(ValiantMod.INSIGHTFUL_PRAYER_POWER);
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.cardsToDraw += stackAmount;
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
        if (amount == 1)
            this.description = DESCRIPTIONS[0] + DESCRIPTIONS[1];
        else
            this.description = DESCRIPTIONS[0] + DESCRIPTIONS[2].replace("#b" , ""+ cardsToDraw);

    }

    public int onHeal(int healAmount) {
        float percent = 0.60f;
        if (owner.currentHealth > (owner.maxHealth * percent))
        {
            AbstractDungeon.actionManager.addToTop(new DrawCardAction(this.owner,cardsToDraw));
        }
        return healAmount;
    }

    static {
        DESCRIPTIONS = new String[] {
                "Whenever you heal, if you have more than 60% HP, draw ",
                "a card.",
                "#b cards."
        };
        NAME = "Insightful Prayer";
    }
}
