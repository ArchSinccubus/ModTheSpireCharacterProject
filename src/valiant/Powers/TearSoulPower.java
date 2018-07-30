
package valiant.Powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import valiant.MainMod.Fudgesickle;

public class TearSoulPower extends AbstractPower {
    public static final String POWER_ID = "Darkness";

    public TearSoulPower(int energyAmt) {
        this.name = "Torn Soul";
        this.ID = "TornSoul";
        this.owner = AbstractDungeon.player;
        this.amount = energyAmt;
        this.updateDescription();
        this.img = Fudgesickle.getTex("Powers/darkness.png");
        this.type = PowerType.DEBUFF;
        this.isTurnBased = true;
    }

    //public void updateDescription() {
    //   this.description = DESCRIPTIONS[0].replace("" + 1 , "" + amount);
    //}

    public void onEnergyRecharge() {
        AbstractDungeon.actionManager.addToBottom(new LoseEnergyAction(amount));
    }

    public void atStartOfTurnPostDraw() {
        this.flash();
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, "Darkness"));
    }

    @Override
    public void updateDescription() {
        if (this.amount > 0) {
            this.description = "Next turn, start with " + this.amount + " Less #yEnergy";
            this.type = PowerType.BUFF;
        }

    }

    //static {
    //    powerStrings = CardCrawlGame.languagePack.getPowerStrings("Darkness");
    //    NAME = powerStrings.NAME;
    //    DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    //}
}
