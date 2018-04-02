package Powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class AscensionPower extends AbstractPower {
    public static final String POWER_ID = "Demon Form";
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public AscensionPower(AbstractCreature owner, int strengthAmount) {
        this.name = NAME;
        this.ID = "Ascension";
        this.owner = owner;
        this.amount = strengthAmount;
        this.updateDescription();
        this.img = ImageMaster.loadImage("images/powers/32/drawCardRed.png");
    }

    public void updateDescription() {
        this.description = "At the beginning of each turn, gain " + this.amount + " Strength, Dexterity, and Spirit.";
    }

    public void atStartOfTurnPostDraw() {
        this.flash();
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, this.amount), this.amount));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new DexterityPower(this.owner, this.amount), this.amount));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new SpiritPower(this.owner, this.amount), this.amount));
    }

    static {
        NAME = "Ascending";
        DESCRIPTIONS = new String[] { "At the beginning of each turn, gain 1 Strength, Dexterity and Spirit."};
    }
}
