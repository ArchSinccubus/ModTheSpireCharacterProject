package valiant.powers;

import com.badlogic.gdx.graphics.Texture;
import valiant.actions.PowerThroughAction;
import valiant.ValiantMod;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class PowerThroughPower extends AbstractPower {
    public static final String POWER_ID = "PushOnward";
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public PowerThroughPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.updateDescription();
        this.img = new Texture(ValiantMod.POWER_ONWARDS_POWER_PATH);
    }


    public void updateDescription() {

    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        if (damageAmount > 0 && info.owner == this.owner) {
            this.flash();
            AbstractDungeon.actionManager.addToTop(new PowerThroughAction(false));
        }

        return damageAmount;
    }

    static {
        DESCRIPTIONS = new String[] {
                "Whenever you lose life from a card, Exhaust a random card from your hand and gain 2 HP."
        };
        NAME = "power Through";
    }
}

