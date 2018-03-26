package Cards.Common.Skill;
import MainMod.*;
import Patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.WeakPower;

import java.util.Iterator;

public class DivineFavor extends CustomCard
{
    public static final String ID = "DivineFavor";
    public static final String NAME = "Divine Favor";
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG_PATH = "Cards/Skills/corona.png";
    private static final int COST = 1;
    private static final int POOL = 1;
    private static final int BLOCK_AMOUNT = 6;
    private static final int UPGRADE_BLOCK_DMG = 3;
    private static final int HP_AMOUNT = 2;
    private static final int UPGRADE_HP_AMOUNT = 1;
    private static final CardRarity rarity = CardRarity.COMMON;
    private static final CardTarget target = CardTarget.SELF;


    public DivineFavor() {
        super(ID, CARD_STRINGS.NAME, Fudgesickle.makePath(Fudgesickle.DIVINE_FAVOR), COST, CARD_STRINGS.DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.Holy,
                rarity, target, POOL);
        this.baseBlock = this.block = BLOCK_AMOUNT;
        this.baseMagicNumber = this.magicNumber = HP_AMOUNT;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, this.magicNumber));
    }
    @Override
    public void applyPowers()
    {
        super.applyPowers();
        applyPowersToHeal();
    }

    private void applyPowersToHeal() {
        int tmp = this.baseMagicNumber;
        Iterator var2 = AbstractDungeon.player.powers.iterator();
        boolean foundSpirit = false;
        while(var2.hasNext()) {
            AbstractPower p = (AbstractPower)var2.next();
            if (p.name == "Spirit") {
                setDescription(p, tmp);
                foundSpirit = true;
            }
            else if (!foundSpirit)
            {
                setDescription(tmp);
            }
        }

        if (foundSpirit)
        {

        }
        //this.isMagicNumberModified = foundSpirit;

        if (tmp < 0) {
            tmp = 0;
        }
    }

    private void setDescription(AbstractPower p , int tmp) {
        this.rawDescription = CARD_STRINGS.DESCRIPTION.replace("!M!" , "" + (tmp + p.amount));
        if (this.exhaustOnUseOnce && !this.exhaust)
            this.rawDescription += " NL Exhaust.";
        this.initializeDescription();
    }

    private void setDescription(int tmp) {
        this.rawDescription = CARD_STRINGS.DESCRIPTION.replace("!M!" , "" + (tmp));
        if (this.exhaustOnUseOnce && !this.exhaust)
            this.rawDescription += " NL Exhaust.";
        this.initializeDescription();
    }


    @Override
    public AbstractCard makeCopy() {
        return new DivineFavor();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock((UPGRADE_BLOCK_DMG));
            //this.upgradeMagicNumber(UPGRADE_HP_AMOUNT);
        }

    }
}