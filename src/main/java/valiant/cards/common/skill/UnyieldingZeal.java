package valiant.cards.common.skill;
import valiant.ValiantMod;
import valiant.patches.AbstractCardEnum;
import valiant.powers.WaveringPower;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;

public class UnyieldingZeal extends CustomCard
{
    public static final String ID = "UnyieldingZeal";
    public static final String NAME = "Unyielding Zeal";
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 0;
    private static final int POOL = 1;
    private static final int WEAK_AMOUNT = 6;
    private static final int WEAK_AMOUNT_PLUS = 3;
    private static final CardRarity rarity = CardRarity.COMMON;
    private static final CardTarget target = CardTarget.ENEMY;


    public UnyieldingZeal() {
        super(ID, CARD_STRINGS.NAME, ValiantMod.UNYIELDING_ZEAL, COST, CARD_STRINGS.DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.Holy,
                rarity, target);
        this.baseMagicNumber = this.magicNumber = WEAK_AMOUNT;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new WaveringPower(m, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    public void applyPowers()
    {
        super.applyPowers();
        this.setDescription(false);
    }

    @Override
    public AbstractCard makeCopy() {
        return new UnyieldingZeal();
    }

    private void setDescription(boolean addExtended) {
        this.rawDescription = CARD_STRINGS.DESCRIPTION;
        if (addExtended) {
            this.rawDescription += CARD_STRINGS.EXTENDED_DESCRIPTION[0];
        }
        if (this.exhaustOnUseOnce && !this.exhaust)
            this.rawDescription += " NL Exhaust.";
        this.initializeDescription();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(WEAK_AMOUNT_PLUS);
        }

    }
}