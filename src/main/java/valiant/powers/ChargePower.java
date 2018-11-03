package valiant.powers;

import com.badlogic.gdx.graphics.Texture;
import valiant.ValiantMod;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import valiant.characters.Valiant;

public class ChargePower extends AbstractPower {
    public static final String POWER_ID = "Charge";
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public ChargePower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.img = new Texture(ValiantMod.CHARGE_POWER);
    }

    public void updateDescription() {
            this.description = DESCRIPTIONS[0];

    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (!card.purgeOnUse && card.type == CardType.ATTACK && this.amount > 0) {
            AbstractDungeon.effectList.add(new SpeechBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, "@IT'S@ @OVER!@", true));
            AbstractMonster m = null;
            if (action.target != null) {
                m = (AbstractMonster)action.target;
            }

            --this.amount;
            if (this.amount == 0) {
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "Charge"));
            }
        }

    }

    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "Charge"));
        }

    }

    public float atDamageGive(float damage, DamageInfo.DamageType type) {
         return type == DamageInfo.DamageType.NORMAL ? damage * 1.5F : damage;
    }

    static {
        DESCRIPTIONS = new String[] {
                "On this turn, your next attack deals 50% more damage."
        };
        NAME = "Charge";
    }
}

