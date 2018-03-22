package Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class SmiteAction extends AbstractGameAction {
    private DamageInfo info;
    private AbstractMonster m;

    public SmiteAction(AbstractMonster target, DamageInfo info) {
        this.info = info;
        this.setValues(target, info);
        this.m = target;
        this.actionType = ActionType.DAMAGE;
        this.attackEffect = AttackEffect.FIRE;
        this.duration = 0.01F;
    }

    public void update() {
        if (this.target == null) {
            this.isDone = true;
        } else {
            if (this.m.hasPower("Frail")) {
                if (this.duration == 0 && this.target != null && this.target.currentHealth > 0) {
                    if (this.info.type != DamageType.THORNS && this.info.owner.isDying) {
                        this.isDone = true;
                        return;
                    }

                    AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect));
                }

                this.tickDuration();
                if (this.isDone && this.target != null && this.target.currentHealth > 0) {
                    this.target.damage(this.info);
                    if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                        AbstractDungeon.actionManager.clearPostCombatActions();
                    }

                    AbstractDungeon.actionManager.addToTop(new WaitAction(0.01F));
                }
            } else {
                this.isDone = true;
            }

        }
    }
}
