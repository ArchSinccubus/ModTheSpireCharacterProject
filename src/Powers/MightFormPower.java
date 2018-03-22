package Powers;

import MainMod.Fudgesickle;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
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

public class MightFormPower extends AbstractPower {
    public static final String POWER_ID = "MightForm";
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    public static int NumBlock;

    public MightFormPower(AbstractCreature owner,int numBlock, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.updateDescription();
        this.NumBlock = numBlock;
        this.img = Fudgesickle.getTex("Powers/Charge.png");
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];

    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (!card.purgeOnUse && card.type == CardType.ATTACK) {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(owner, owner, NumBlock));
        }

    }



    static {
        DESCRIPTIONS = new String[] {
                "Whenever you play a card that costs 3 or more, gain " + NumBlock + " Block."
        };
        NAME = "Might Form";
    }
}
