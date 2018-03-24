package Powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import MainMod.Fudgesickle;

public class WaveringPower extends AbstractPower {
    public static final String POWER_ID = "Wavering";
    public static final String NAME = "Wavering";
    private boolean justApplied = false;

    public WaveringPower(AbstractCreature owner, int amount, boolean isSourcePlayer) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.type = AbstractPower.PowerType.BUFF;
        this.isTurnBased = false;
        this.loadRegion("frail");
        if (AbstractDungeon.actionManager.turnHasEnded && isSourcePlayer) {
            this.justApplied = true;
        }

        this.type = PowerType.DEBUFF;
        this.isTurnBased = true;
        this.priority = 99;
    }



    @Override
    public int modifyBlock(int blockAmount) {
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
        return blockAmount * 3 / 4;
    }

    @Override
    public void onGainedBlock(int blockAmount){
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
        owner.currentBlock = owner.currentBlock * 3/4;
    }

    public void atEndOfRound() {
        if (this.justApplied) {
            this.justApplied = false;
        } else {
            if (this.amount == 0) {
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "Wavering"));
            } else {
                AbstractDungeon.actionManager.addToBottom(new DamageAction(this.owner,
                        new DamageInfo(AbstractDungeon.player, 3, DamageInfo.DamageType.HP_LOSS),
                        AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "Wavering", 1));
            }

        }
    }

    @Override
    public void updateDescription() {
        if (this.amount == 1) {
            this.description = "At the start of the next turn, this monster is dealt 3 damage.";
        } else {
            this.description = "At the start of the next " + this.amount + " turns, this monster is dealt 3 damage.";
        }

    }


}
