package Powers;

import Actions.ExhaustTopOfDrawPileAction;
import Actions.PowerThroughAction;
import Actions.SharpenAction;
import MainMod.Fudgesickle;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
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
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

public class WeakFormPower extends AbstractPower {
    public static final String POWER_ID = "DelvingPrayer";
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    public static boolean Upgraded;
    public static int cardsToDraw;

    public WeakFormPower(AbstractCreature owner,int numBlock, int amount,int cards , boolean upgraded) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.updateDescription();
        this.Upgraded = upgraded;
        this.cardsToDraw = cards;
        this.img = Fudgesickle.getTex("Powers/Charge.png");
    }

    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (card.cost >= 3)
        {
            AbstractDungeon.actionManager.addToTop(new GainEnergyAction(1));
        }
    }


    static {
        DESCRIPTIONS = new String[] {
                "Whenever you cast a card that costs 3 or more, gain [R]."
        };
        NAME = "Weak Form";
    }
}