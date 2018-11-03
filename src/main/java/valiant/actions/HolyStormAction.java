package valiant.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class HolyStormAction extends AbstractGameAction {
    private boolean freeToPlayOnce = false;
    private int damage;
    private AbstractPlayer p;
    private AbstractMonster m;
    private DamageType damageTypeForTurn;
    private boolean upgraded;
    private int energyOnUse = -1;

    public HolyStormAction(AbstractPlayer p, AbstractMonster m,boolean upgraded, int damage, DamageType damageTypeForTurn, boolean freeToPlayOnce, int energyOnUse) {
        this.p = p;
        this.m = m;
        this.damage = damage;
        this.freeToPlayOnce = freeToPlayOnce;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.SPECIAL;
        this.damageTypeForTurn = damageTypeForTurn;
        this.energyOnUse = energyOnUse;
        this.upgraded = upgraded;
    }

    public void update() {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1) {
            effect = this.energyOnUse;
        }

        //this.damage *= 3;

        if (upgraded)
        {
            effect++;

        }
        else
        {
            //this.damage = effect;
        }

        if (this.p.hasRelic("Chemical X")) {
            effect += 2;
            this.p.getRelic("Chemical X").flash();
        }
        Random rand = new Random();
        if (effect > 0) {
            for(int i = 0; i < effect; ++i) {
                //if (this.m != null) {
                    //AbstractDungeon.actionManager.addToBottom(new VFXAction(new W(0,m.hb.cX, m.hb.cY, AbstractGameAction.AttackEffect.FIRE)));
                //}

                int xRand = ThreadLocalRandom.current().nextInt(-20, 21);
                int yRand = ThreadLocalRandom.current().nextInt(-20, 21);
                AbstractDungeon.actionManager.addToBottom(new SFXAction("THUNDERCLAP", 0.005F));
                AbstractDungeon.actionManager.addToBottom(new VFXAction(new LightningEffect(m.drawX + xRand, m.drawY + yRand), 0.05F));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(this.m, new DamageInfo(this.p, damage, this.damageTypeForTurn), AttackEffect.FIRE));
            }

            if (!this.freeToPlayOnce) {
                this.p.energy.use(EnergyPanel.totalCount);
            }
        }

        this.isDone = true;
    }
}

