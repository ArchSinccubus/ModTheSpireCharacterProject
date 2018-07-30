package valiant.Actions;

import valiant.Powers.WaveringPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
        import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
        import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
        import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
        import com.megacrit.cardcrawl.core.AbstractCreature;
        import com.megacrit.cardcrawl.core.Settings;
        import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.PoisonPower;

public class MultiplyPowerAction extends AbstractGameAction {
    private float startingDuration;
    private int multiplyAmount;
    private String PowerName;

    public MultiplyPowerAction(AbstractCreature target, AbstractCreature source , int MulAmount, String name) {
        this.target = target;
        this.source = source;
        this.multiplyAmount = MulAmount;
        this.PowerName = name;
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.DEBUFF;
        this.attackEffect = AttackEffect.FIRE;
        this.duration = this.startingDuration;
    }

    public void update() {
        if (this.duration == this.startingDuration && this.target != null && this.target.hasPower(PowerName)) {

            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(this.target, this.source, new WaveringPower(this.target, this.target.getPower(PowerName).amount * multiplyAmount , false), this.target.getPower(PowerName).amount * multiplyAmount));
        }

        this.tickDuration();
    }
}
