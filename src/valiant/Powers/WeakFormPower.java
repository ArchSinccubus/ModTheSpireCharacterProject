package valiant.Powers;

import valiant.MainMod.Fudgesickle;
import basemod.BaseMod;
import basemod.interfaces.PostBattleSubscriber;
import basemod.interfaces.PostDrawSubscriber;
import basemod.interfaces.PostDungeonInitializeSubscriber;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

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
        BaseMod.subscribe(this);
    }

    @Override
    public void receivePostBattle(AbstractRoom arg0) {
        BaseMod.unsubscribeLater(this);
    }

    @Override
    public void receivePostDungeonInitialize() {
        BaseMod.unsubscribeLater(this);
    }

    @Override
    public void receivePostDraw(AbstractCard c) {
        if(c.cost >= 2) {
            c.setCostForTurn(1);
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];

    }

    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL) {
            if (this.owner.isPlayer) {
                return damage * 1.25F;
            } else {
                return damage;
            }
        }
            return damage;
    }
//
//    public int onAttacked(DamageInfo info, int damageAmount) {
//        return (int)(damageAmount * 1.25F);
//
//    }

    static {
        DESCRIPTIONS = new String[] {
                "Whenever you draw a card that costs 2 or more, reduce its cost to 1 for this turn. You take 25% more damage."
        };
        NAME = "Weak Form";
    }
}