package Cards.Rare.Skill;
import MainMod.*;
import Patches.AbstractCardEnum;
import basemod.BaseMod;
import basemod.interfaces.PostBattleSubscriber;
import basemod.interfaces.PostDungeonInitializeSubscriber;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.combat.HealVerticalLineEffect;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;

public class Salvation extends CustomCard /*implements PostBattleSubscriber,PostDungeonInitializeSubscriber*/
{
    public static final String ID = "Salvation";
    public static final String NAME = "Salvation";
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG_PATH = "Cards/Skills/corona.png";
    private static final int COST = 3;
    private static final int HP_AMOUNT = 25;
    private static final int UPGRADE_HP_AMOUNT = 10;
    private static final int POOL = 1;
    private static final CardRarity rarity = CardRarity.RARE;
    private static final CardTarget target = CardTarget.SELF;
    private static final CardType type = CardType.SKILL;
    private int percent_gain;

    boolean foundSpirit = false;

    public static final Logger logger = LogManager.getLogger(Fudgesickle.class.getName());

    public Salvation() {
        super(ID, CARD_STRINGS.NAME, Fudgesickle.makePath(Fudgesickle.SALVATION), COST, CARD_STRINGS.DESCRIPTION,
                type, AbstractCardEnum.Holy,
                rarity, target, POOL);
        this.baseBlock = HP_AMOUNT;
        float base = (float)(this.magicNumber) / 100;
        //this.baseMagicNumber = (int)((float)AbstractDungeon.player.maxHealth * percent);
        this.baseMagicNumber = this.magicNumber = this.baseBlock;
        //this.heal=this.baseHeal = HP_AMOUNT;
        this.exhaust= true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        float percent = (float)(this.baseBlock) / 100;
        this.baseMagicNumber = (int)((float)AbstractDungeon.player.maxHealth * percent);
        this.magicNumber = this.baseMagicNumber;
        Iterator var2 = AbstractDungeon.player.powers.iterator();
        while (var2.hasNext()) {
            AbstractPower power = (AbstractPower) var2.next();
            if (power.name == "Spirit") {
                this.magicNumber = this.baseMagicNumber + power.amount;
                //this.isMagicNumberModified = true;
            }
        }
        AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, this.magicNumber));
    }

    @Override
    public void applyPowers()
    {
        //float percent = (float)(percent_gain) / 100;
        //this.baseMagicNumber = (int)((float)AbstractDungeon.player.maxHealth * percent);
        super.applyPowers();
        setDescription(true);
    }

//    @Override
//    public void atTurnStart() {
//        logger.info("THE TURN HAS STARTED");
//        foundSpirit = false;
//        this.applyPowers();
//        super.update();
//    }

//    @Override
//    public void receivePostBattle(AbstractRoom arg0) {
//        BaseMod.unsubscribeFromPostDungeonInitialize(this);
//
//        Thread delayed = new Thread(() -> {
//            try {
//                Thread.sleep(200);
//            } catch (Exception e) {
//                System.out.println("could not delay unsubscribe to avoid ConcurrentModificationException");
//                e.printStackTrace();
//            }
//            BaseMod.unsubscribeFromPostBattle(this);
//        });
//        delayed.start();
//    }
//
//    @Override
//    public void receivePostDungeonInitialize() {
//        BaseMod.unsubscribeFromPostBattle(this);
//        setDescription(false);
//        Thread delayed = new Thread(() -> {
//            try {
//                Thread.sleep(200);
//            } catch (Exception e) {
//                System.out.println("could not delay unsubscribe to avoid ConcurrentModificationException");
//                e.printStackTrace();
//            }
//            BaseMod.unsubscribeFromPostDungeonInitialize(this);
//        });
//        delayed.start();
//    }

    @Override
    public void calculateCardDamage(AbstractMonster mo)
    {
        //super.calculateCardDamage(mo);
        Iterator var2 = AbstractDungeon.player.powers.iterator();
        while (var2.hasNext()) {
            AbstractPower p = (AbstractPower) var2.next();
            if (p.name == "Spirit") {
                logger.info("FOUND SPIRIT. MAGIC NUMBER:" + this.magicNumber + " BASE MAGIC NUMBER: " + baseMagicNumber);
                this.magicNumber = this.baseMagicNumber + p.amount;
                this.isMagicNumberModified = true;
            }
        }

    }

    private void setDescription(boolean addExtended) {
//        float percent = (float)(this.magicNumber) / 100;
//        this.baseMagicNumber = (int)((float)AbstractDungeon.player.maxHealth * percent);
        float percent = (float)(this.baseBlock) / 100;
        this.baseMagicNumber = (int)((float)AbstractDungeon.player.maxHealth * percent);
        this.magicNumber = this.baseMagicNumber;
        this.rawDescription = CARD_STRINGS.DESCRIPTION;
        if (addExtended) {
            this.rawDescription += CARD_STRINGS.EXTENDED_DESCRIPTION[0];
        }

        this.initializeDescription();
    }


    @Override
    public AbstractCard makeCopy() {
        return new Salvation();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(UPGRADE_HP_AMOUNT);
        }
    }
}
