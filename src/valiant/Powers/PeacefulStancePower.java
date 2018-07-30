package valiant.Powers;

import valiant.MainMod.Fudgesickle;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class PeacefulStancePower extends AbstractPower {
    public static final String POWER_ID = "PeacefulStance";
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public PeacefulStancePower(AbstractCreature owner,int numBlock, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.updateDescription();
        this.priority = 100;
        this.img = Fudgesickle.getTex("Powers/Charge.png");
    }

//    public float atDamageGive(float damage, DamageInfo.DamageType type) {
//        if (type == DamageInfo.DamageType.NORMAL) {
//            return damage * 0.5F;
//        } else {
//            return damage;
//        }
//    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];

    }

    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
            if (this.owner.isPlayer) {
                return damage * 0.5F;
            }
        return damage;
    }

//    public int onAttacked(DamageInfo info, int damageAmount) {
//            return (int)(damageAmount * 0.75F);
//
//    }

    static {
        DESCRIPTIONS = new String[] {
                "You take 50% less damage."
        };
        NAME = "Peaceful Stance";
    }
}
