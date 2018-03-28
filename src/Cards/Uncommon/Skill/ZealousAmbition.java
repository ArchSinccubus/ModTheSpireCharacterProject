package Cards.Uncommon.Skill;

import MainMod.Fudgesickle;
import Patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.WeakPower;

import java.util.Iterator;

public class ZealousAmbition extends CustomCard
{
    public static final String ID = "ZealousAmbition";
    public static final String NAME = "Zealous Ambition";
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG_PATH = "Cards/Skills/corona.png";
    private static final int COST = 1;
    private static final int POOL = 1;
    private static final CardRarity rarity = CardRarity.UNCOMMON;
    private static final CardTarget target = CardTarget.SELF;
    private static final CardType type = CardType.SKILL;


    public ZealousAmbition() {
        super(ID, CARD_STRINGS.NAME, Fudgesickle.makePath(Fudgesickle.ZEALOUS_AMBITION), COST, CARD_STRINGS.DESCRIPTION,
                type, AbstractCardEnum.Holy,
                rarity, target, POOL);
        this.exhaust = true;
        this.baseMagicNumber = this.magicNumber = 6;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        int HP = p.currentHealth;
        if (HP >= p.maxHealth / 2)
        {
            AbstractDungeon.actionManager.addToBottom(new LoseHPAction(p, p, 6));
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p , 2));
        }
        else
        {
            AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, this.magicNumber));
            AbstractDungeon.actionManager.addToBottom(new DiscardAction(p , p , 2 , false));
        }

    }

    @Override
    public AbstractCard makeCopy() {
        return new ZealousAmbition();
    }

    @Override
    public void applyPowers()
    {
        super.applyPowers();
        //applyPowersToHeal();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo)
    {
        super.calculateCardDamage(mo);
        Iterator var2 = AbstractDungeon.player.powers.iterator();
        while (var2.hasNext()) {
            AbstractPower p = (AbstractPower) var2.next();
            if (p.name == "Spirit") {
                this.magicNumber = this.baseMagicNumber + p.amount;
                this.isMagicNumberModified = true;
            }
        }

    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
        }

    }
}

