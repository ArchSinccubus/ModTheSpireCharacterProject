package Powers;

import Actions.PowerThroughAction;
import Actions.SharpenAction;
import MainMod.Fudgesickle;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

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

    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL) {
            return damage * 0.5F;
        } else {
            return damage;
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];

    }

    public int onAttacked(DamageInfo info, int damageAmount) {
            return (int)(damageAmount * 0.25F);

    }

    static {
        DESCRIPTIONS = new String[] {
                "You take 25% damage and give 50% less damage."
        };
        NAME = "Peaceful Stance";
    }
}
