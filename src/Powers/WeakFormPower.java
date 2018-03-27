package Powers;

import Actions.ExhaustTopOfDrawPileAction;
import Actions.PowerThroughAction;
import Actions.SharpenAction;
import MainMod.Fudgesickle;
import basemod.BaseMod;
import basemod.interfaces.PostBattleSubscriber;
import basemod.interfaces.PostDrawSubscriber;
import basemod.interfaces.PostDungeonInitializeSubscriber;
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
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

public class WeakFormPower extends AbstractPower implements PostDrawSubscriber, PostBattleSubscriber,
        PostDungeonInitializeSubscriber {
    public static final String POWER_ID = "WeakForm";
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public WeakFormPower(AbstractCreature owner,int numBlock, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.updateDescription();
        this.img = Fudgesickle.getTex("Powers/Charge.png");
    }

    @Override
    public void onInitialApplication() {
        BaseMod.subscribeToPostDraw(this);
        BaseMod.subscribeToPostBattle(this);
        BaseMod.subscribeToPostDungeonInitialize(this);
    }

    @Override
    public void receivePostBattle(AbstractRoom arg0) {
        BaseMod.unsubscribeFromPostDraw(this);
        BaseMod.unsubscribeFromPostDungeonInitialize(this);
        Thread delayed = new Thread(() -> {
            try {
                Thread.sleep(200);
            } catch (Exception e) {
                System.out.println("could not delay unsubscribe to avoid ConcurrentModificationException");
                e.printStackTrace();
            }
            BaseMod.unsubscribeFromPostBattle(this);
        });
        delayed.start();
    }

    @Override
    public void receivePostDungeonInitialize() {
        BaseMod.unsubscribeFromPostDraw(this);
        BaseMod.unsubscribeFromPostBattle(this);
        Thread delayed = new Thread(() -> {
            try {
                Thread.sleep(200);
            } catch (Exception e) {
                System.out.println("could not delay unsubscribe to avoid ConcurrentModificationException");
                e.printStackTrace();
            }
            BaseMod.unsubscribeFromPostDungeonInitialize(this);
        });
        delayed.start();
    }

    @Override
    public void receivePostDraw(AbstractCard c) {
        if(c.cost >= 3) {
            c.setCostForTurn(c.cost - 1);
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];

    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        return (int)(damageAmount * 1.5F);

    }

    static {
        DESCRIPTIONS = new String[] {
                "Whenever you draw a card that costs 3 or more, reduce its cost by 1 for this turn. You take 25% more damage."
        };
        NAME = "Weak Form";
    }
}