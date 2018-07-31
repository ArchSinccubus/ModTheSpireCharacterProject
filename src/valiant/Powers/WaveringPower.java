package valiant.Powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import valiant.MainMod.Fudgesickle;

public class WaveringPower extends AbstractPower {
    public static final String POWER_ID = "Wavering";
    public static final String NAME = "Wavering";
    private boolean justApplied = false;
    public static final float WAVERING_MOD = 2;
    public static final Logger logger = LogManager.getLogger(Fudgesickle.class.getName());


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
    public float modifyBlock(float blockAmount) {
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
        return blockAmount * 3 / 4;
    }

    @Override
    public void onGainedBlock(float blockAmount){
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
        owner.currentBlock = owner.currentBlock * 3/4;
    }

    @Override
    public void atStartOfTurn() {
        //if (!isPlayer) {
            if (this.justApplied) {
                this.justApplied = false;
            } else {
                if (this.amount == 0) {
                    AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "Wavering"));
                } else {
                    int damage = 5;
                    if (AbstractDungeon.player.hasRelic("Crumpled Paper")) {
                        damage *= 2;
                    }

//                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner, new StrengthPower(owner, -damage), -damage));
//                    if (owner != null && !owner.hasPower("Artifact")) {
//                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner, new GainStrengthPower(owner, damage), damage));
//                    }
                    //AbstractDungeon.actionManager.addToBottom(new DamageAction(this.owner,
                    //        new DamageInfo(AbstractDungeon.player, damage, DamageInfo.DamageType.HP_LOSS),
                    //        AbstractGameAction.AttackEffect.FIRE));
                    //AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "Wavering", 1));
                }

            }
        //}
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        float percent = ((amount * WAVERING_MOD) / (float)100);
        logger.info("Pre paper: " + percent);
        if (type == DamageInfo.DamageType.NORMAL && !this.owner.isPlayer) {
            if (AbstractDungeon.player.hasRelic("Crumpled Paper")) {
                percent *= 2;
                logger.info("Post paper: " + percent);
            }
            return damage * (1 + percent);

        }
        return damage;

    }

    @Override
    public void updateDescription() {
        int damage = amount;
        if (AbstractDungeon.player.hasRelic("Crumpled Paper")) {
            damage *= 2;
        }
        this.description = "This enemy takes " + damage + "% more damage from attacks.";

    }


}
