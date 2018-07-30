package valiant.Powers;

import java.util.ArrayList;

import valiant.MainMod.Fudgesickle;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class CombatStancePower extends AbstractPower {
    public static final String POWER_ID = "CombatStancePower";
    public static final String NAME = "Combat Stance";
    public static final String[] DESCRIPTIONS = new String[] {
            "When you are attacked this turn apply ",
            " Wavering and ",
            " Weak to the attacker."
    };

    private ArrayList<AbstractCreature> attackers;

    public CombatStancePower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        this.type = AbstractPower.PowerType.BUFF;
        this.isTurnBased = true;
        this.priority = 90;
        this.img = Fudgesickle.getTex("Powers/CombatStance.png");
        this.attackers = new ArrayList<AbstractCreature>();
    }

    @Override
    public void atEndOfRound() {
        this.attackers.clear();
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "CombatStancePower"));
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS
                && info.owner != null && info.owner != this.owner && !this.attackers.contains(info.owner)) {
            this.flash();
            this.attackers.add(info.owner);
            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(info.owner, this.owner,
                    new WeakPower(info.owner, this.amount, true), this.amount, true, AbstractGameAction.AttackEffect.NONE));
            AbstractDungeon.actionManager.addToTop(
                    new ApplyPowerAction(info.owner, this.owner, new WaveringPower(info.owner, this.amount, true), this.amount,
                            true, AbstractGameAction.AttackEffect.NONE));
        }
        return damageAmount;
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] +
                this.amount + DESCRIPTIONS[2];
    }
}