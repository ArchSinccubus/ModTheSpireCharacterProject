package Cards.Rare.Skill;
import MainMod.*;
import Patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.Iterator;

public class Salvation extends CustomCard
{
    public static final String ID = "Salvation";
    public static final String NAME = "Salvation";
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG_PATH = "Cards/Skills/corona.png";
    private static final int COST = 3;
    private static final int HP_AMOUNT = 20;
    private static final int UPGRADE_HP_AMOUNT = 10;
    private static final int POOL = 1;
    private static final CardRarity rarity = CardRarity.RARE;
    private static final CardTarget target = CardTarget.SELF;
    private static final CardType type = CardType.SKILL;

    public Salvation() {
        super(ID, CARD_STRINGS.NAME, Fudgesickle.makePath(Fudgesickle.SALVATION), COST, CARD_STRINGS.DESCRIPTION,
                type, AbstractCardEnum.Holy,
                rarity, target, POOL);
        this.baseMagicNumber = HP_AMOUNT;
        this.magicNumber = this.baseMagicNumber;
        this.heal=this.baseHeal = HP_AMOUNT;
        this.exhaust= true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        float percent = (float)(this.magicNumber) / 100;
        int finalHP = (int)((float)p.maxHealth * percent);

        if (com.megacrit.cardcrawl.core.Settings.isDebug) {
            AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, 50));
        } else {
            AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, finalHP));
        }
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
                setDescription(p, tmp, true);
                foundSpirit = true;
            }
            else
            {
                this.rawDescription = CARD_STRINGS.DESCRIPTION;
                this.initializeDescription();
            }
        }

        this.isMagicNumberModified = foundSpirit;

        if (tmp < 0) {
            tmp = 0;
        }
    }

    private void setDescription(AbstractPower p , int tmp, boolean addExtended) {
        float percent = (float)(this.magicNumber) / 100;
        int finalHP = (int)((float)AbstractDungeon.player.maxHealth * percent);
        this.rawDescription = CARD_STRINGS.DESCRIPTION;
        if (addExtended) {
            this.rawDescription += CARD_STRINGS.EXTENDED_DESCRIPTION[0].replace("!F!" , "" + (finalHP + p.amount));
        }

        this.initializeDescription();
    }

    private void setDescription(int tmp, boolean addExtended) {
        float percent = (float)(this.magicNumber) / 100;
        int finalHP = (int)((float)AbstractDungeon.player.maxHealth * percent);
        this.rawDescription = CARD_STRINGS.DESCRIPTION;
        if (addExtended) {
            this.rawDescription += CARD_STRINGS.EXTENDED_DESCRIPTION[0].replace("!F!" , "" + (finalHP));
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
            this.upgradeMagicNumber(UPGRADE_HP_AMOUNT);
        }
    }
}
