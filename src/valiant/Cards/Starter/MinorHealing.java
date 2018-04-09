package Cards.Starter;
import valiant.MainMod.*;
import valiant.Patches.AbstractCardEnum;
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

public class MinorHealing extends CustomCard
{
    public static final String ID = "MinorHealing";
    public static final String NAME = "Minor Healing";
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG_PATH = "Cards/Skills/corona.png";
    private static final int COST = 1;
    private static final int HP_AMOUNT = 6;
    private static final int UPGRADE_HP_AMOUNT = 2;
    private static final int POOL = 1;

    public MinorHealing() {
        super(ID, CARD_STRINGS.NAME, Fudgesickle.makePath(Fudgesickle.MINOR_HEALING), COST, CARD_STRINGS.DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.Holy,
                CardRarity.BASIC, CardTarget.SELF, POOL);
        this.baseMagicNumber = HP_AMOUNT;
        this.magicNumber = this.baseMagicNumber;
        this.heal=this.baseHeal = HP_AMOUNT;
        this.exhaust= true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {

        if (com.megacrit.cardcrawl.core.Settings.isDebug) {
            AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, 50));
        } else {
            AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, this.magicNumber));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new MinorHealing();
    }

    @Override
    public void applyPowers()
    {
        super.applyPowers();
        Iterator var2 = AbstractDungeon.player.powers.iterator();
        while (var2.hasNext()) {
            AbstractPower p = (AbstractPower) var2.next();
            if (p.name == "Spirit") {
                this.magicNumber = this.baseMagicNumber + p.amount;
                this.isMagicNumberModified = true;
            }
        }
        setDescription();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo)
    {
        super.calculateCardDamage(mo);

    }

    private void setDescription(AbstractPower p , int tmp) {
        this.rawDescription = CARD_STRINGS.DESCRIPTION.replace("!M!" , "" + (tmp + p.amount));
        if (this.exhaustOnUseOnce && !this.exhaust)
            this.rawDescription += " NL Exhaust.";
        this.initializeDescription();
    }

    private void setDescription() {
        this.rawDescription = CARD_STRINGS.DESCRIPTION;
        if (this.exhaustOnUseOnce && !this.exhaust)
            this.rawDescription += " NL Exhaust.";
        this.initializeDescription();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_HP_AMOUNT);
        }
    }
}
