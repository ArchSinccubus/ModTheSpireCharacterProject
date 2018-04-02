package Powers;

import Actions.ExhaustTopOfDrawPileAction;
import Actions.PowerThroughAction;
import Actions.SharpenAction;
import Actions.SmiteAction;
import Cards.Rare.Attack.FinalGambit;
import Cards.Rare.Skill.DeadlyPlay;
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
import com.megacrit.cardcrawl.cards.curses.Decay;
import com.megacrit.cardcrawl.cards.curses.Pain;
import com.megacrit.cardcrawl.cards.curses.Regret;
import com.megacrit.cardcrawl.cards.status.Burn;
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
import com.megacrit.cardcrawl.vfx.combat.ExplosionSmallEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OverburnPower extends AbstractPower implements PostDrawSubscriber, PostBattleSubscriber,
        PostDungeonInitializeSubscriber {
    public static final String POWER_ID = "OverburnPower";
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    //public static final Logger logger = LogManager.getLogger(Character.Valiant.class.getName());

    public OverburnPower(AbstractCreature owner,int numBlock, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        if (this.amount >= 999) {
            this.amount = 999;
        }

        if (this.amount <= -999) {
            this.amount = -999;
        }
        this.updateDescription();
        this.loadRegion("explosive");
    }

    @Override
    public void onInitialApplication() {
        BaseMod.subscribe(this);
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        if (this.amount == 0) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "OverburnPower"));
        }

        if (this.amount >= 999) {
            this.amount = 999;
        }

        if (this.amount <= -999) {
            this.amount = -999;
        }

    }

//    public int onAttacked(DamageInfo info, int damageAmount) {
//        if (damageAmount > 0 && info.owner == this.owner) {
//            AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
//            AbstractDungeon.actionManager.addToBottom(new VFXAction(this.owner, new CleaveEffect(), 0.25F));
//            AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(this.owner, DamageInfo.createDamageMatrix(this.amount, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE));
//        }
//
//        return damageAmount;
//    }

    @Override
    public void onRemove() {
        BaseMod.subscribe(this);
    }

    @Override
    public void receivePostDraw(AbstractCard c)
    {
        if (IsLifeLossCard(c)) {
            this.flash();
            for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                AbstractDungeon.actionManager.addToBottom(new SFXAction("EXPLOSION", 0.05F));
                AbstractDungeon.actionManager.addToTop(new VFXAction(new ExplosionSmallEffect(mo.hb.cX, mo.hb.cY), 0.1F));
            }
            AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(this.owner, DamageInfo.createDamageMatrix(this.amount, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE));
        }
    }

    public void updateDescription() {
            this.description = DESCRIPTIONS[0].replace("#b" , "" + this.amount);

    }

    @Override
    public void receivePostBattle(AbstractRoom arg0) {
        BaseMod.unsubscribeLater(this);
    }

    @Override
    public void receivePostDungeonInitialize() {
        BaseMod.unsubscribeLater(this);
    }

    public boolean IsLifeLossCard (AbstractCard c)
    {
        //logger.info(c.name + " "+ c.type + " " + c.rawDescription);
        if (c.name == new DeadlyPlay().name)
            return false;
        if ((c.name == new FinalGambit().name) || (c instanceof Burn || c instanceof Decay || c instanceof Pain || c instanceof Regret) || (
                (c.type != AbstractCard.CardType.POWER && c.type != AbstractCard.CardType.STATUS && c.type != AbstractCard.CardType.CURSE) &&
                        (c.rawDescription.contains("Lose") || c.rawDescription.contains("lose")) &&
                        (c.rawDescription.contains("HP"))))
        {
            return true;
        }
        return false;
    }

    static {
        DESCRIPTIONS = new String[] {
                "Whenever you draw a card that damages you, deal #b damage to ALL enemies."
        };
        NAME = "Overburn";
    }
}
