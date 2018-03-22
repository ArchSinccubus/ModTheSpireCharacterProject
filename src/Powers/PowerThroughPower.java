package Powers;

import Actions.PowerThroughAction;
import Actions.SharpenAction;
import MainMod.Fudgesickle;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
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

public class PowerThroughPower extends AbstractPower {
    public static final String POWER_ID = "PowerThrough";
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    public static boolean Upgraded;

    public PowerThroughPower(AbstractCreature owner,int numBlock, int amount , boolean upgraded) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.updateDescription();
        this.Upgraded = upgraded;
        this.img = Fudgesickle.getTex("Powers/Charge.png");
    }

    public void updateDescription() {
        if (!this.Upgraded)
        this.description = DESCRIPTIONS[0];
        else
            this.description = DESCRIPTIONS[1];

    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        if (damageAmount > 0 && info.owner == this.owner) {
            this.flash();
            AbstractDungeon.actionManager.addToTop(new PowerThroughAction(Upgraded));
        }

        return damageAmount;
    }

    static {
        DESCRIPTIONS = new String[] {
                "Whenever you lose life from a card, Exhaust a random card from your hand and gain 2 HP.",
                "Whenever you lose life from a card, choose and Exhaust a card from your hand and gain 2 HP."
        };
        NAME = "Power Through";
    }
}

