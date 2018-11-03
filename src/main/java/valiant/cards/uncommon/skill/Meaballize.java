package valiant.cards.uncommon.skill;

import valiant.ValiantMod;
import valiant.patches.AbstractCardEnum;
import valiant.powers.TearSoulPower;
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

public class Meaballize extends CustomCard
{
    public static final String ID = "Meaballize";
    public static final String NAME = "Meaballize";
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 0;
    private static final int HP_AMOUNT = 10;
    private static final int UPGRADE_HP_AMOUNT = 4;
    private static final int POOL = 1;

    public Meaballize() {
        super(ID, CARD_STRINGS.NAME, ValiantMod.MEABALIZE, COST, CARD_STRINGS.DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.Holy,
                CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = HP_AMOUNT;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(AbstractCard.CardTags.HEALING);
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

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new TearSoulPower(1), 1));
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
                //this.isMagicNumberModified = true;
            }
        }
        //applyPowersToHeal();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo)
    {
        super.calculateCardDamage(mo);

    }

    @Override
    public AbstractCard makeCopy() {
        return new Meaballize();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_HP_AMOUNT);
        }
    }
}
