package Powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;

public class HastePower extends AbstractPower {
    public static final String POWER_ID = "Haste";
    private static PowerStrings powerStrings;
    public String NAME;
    public String[] DESCRIPTIONS;

    public HastePower(AbstractCreature owner, int amount) {
        this.name = "Haste";
        this.ID = "Haste";
        DESCRIPTIONS = new String[] {
                "For each card you play this turn, deal 20% more damage.",
                " (You will deal #b" + (20 * amount) + "% more damage"
        };
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.loadRegion("time");
    }

    public void atEndOfRound() {
        this.amount = 0;
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
        if (this.amount != 0) {
            this.description = this.description + DESCRIPTIONS[1];
        }

    }

    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new HastePower(this.owner, 1), 1));
    }

    public float atDamageGive(float damage, DamageType type) {
        return type == DamageType.NORMAL ? damage * (1.0F + (float)this.amount * 0.2F) : damage;
    }

}
