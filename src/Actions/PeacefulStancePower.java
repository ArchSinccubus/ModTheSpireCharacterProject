package Actions;

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
    public static int LifeLost;

    public PeacefulStancePower(AbstractCreature owner,int numBlock, int amount , int LifeLost) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.updateDescription();
        this.LifeLost = LifeLost;
        this.priority = 10;
        this.img = Fudgesickle.getTex("Powers/Charge.png");
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL) {
            return damage * 0.5F;
        } else {
            return damage;
        }
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        if (damageAmount > 0 && info.owner == this.owner) {
            return 0;
        }

        return damageAmount;
    }

    static {
        DESCRIPTIONS = new String[] {
                "You can no longer lose life from cards. Deal 50% less damage."
        };
        NAME = "Peaceful Stance";
    }
}
