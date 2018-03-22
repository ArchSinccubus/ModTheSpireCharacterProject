package Powers;

import MainMod.Fudgesickle;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

public class SpiritPower extends AbstractPower {
    public static final String POWER_ID = "Spirit";
    public static final String NAME;
    public boolean MagicNumberModified;

    static {
        NAME = "Spirit";
    }

    public SpiritPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "Spirit";
        this.owner = owner;
        this.amount = amount;
        if (this.amount >= 999) {
            this.amount = 999;
        }

        if (this.amount <= -999) {
            this.amount = -999;
        }
        this.img = Fudgesickle.GetSpirit_Texture();
        this.updateDescription();
    }

    @Override
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        if (this.amount == 0) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "Spirit"));
        }

        if (this.amount >= 999) {
            this.amount = 999;
        }

        if (this.amount <= -999) {
            this.amount = -999;
        }

    }

    @Override
    public void reducePower(int reduceAmount) {
        this.fontScale = 8.0F;
        this.amount -= reduceAmount;
        if (this.amount == 0) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, NAME));
        }

        if (this.amount >= 999) {
            this.amount = 999;
        }

        if (this.amount <= -999) {
            this.amount = -999;
        }

    }

    @Override
    public void updateDescription() {
        if (this.amount > 0) {
            this.description = "Increases healing by " + this.amount + ".";
            this.type = PowerType.BUFF;
        } else {
            int tmp = -this.amount;
            this.description = "Decreases healing by " + this.amount + ".";
            this.type = PowerType.DEBUFF;
        }

    }


    @Override
    public int onHeal(int healAmount) {
        return healAmount + amount;
    }




   /*
    public float atDamageGive(float damage, DamageType type) {
        return type == DamageType.NORMAL ? damage + (float)this.amount : damage;
    }
    */


}
