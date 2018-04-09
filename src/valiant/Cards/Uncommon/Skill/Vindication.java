package Cards.Uncommon.Skill;
import valiant.MainMod.*;
import valiant.Patches.AbstractCardEnum;
import valiant.Powers.WaveringPower;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.powers.*;

public class Vindication extends CustomCard
{
    public static final String ID = "Vindication";
    public static final String NAME = "Vindication";
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG_PATH = "Cards/Skills/corona.png";
    private static final int COST = 2;
    private static final int SPIRIT_AMOUNT = 2;
    private static final int SPIRIT_UPGRADE = 1;
    private static final int POOL = 1;
    private static final CardRarity rarity = CardRarity.UNCOMMON;
    private static final CardTarget target = CardTarget.ENEMY;
    private static final CardType type = CardType.SKILL;

    public Vindication() {
        super(ID, CARD_STRINGS.NAME, Fudgesickle.makePath(Fudgesickle.VINDICATION), COST, CARD_STRINGS.DESCRIPTION,
                type, AbstractCardEnum.Holy,
                rarity, target, POOL);
        this.baseMagicNumber =SPIRIT_AMOUNT;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new WaveringPower(m, 10, false), 10, true, AbstractGameAction.AttackEffect.NONE));

        if ((m.maxHealth / 2) >= m.currentHealth)
        {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p , p , new StrengthPower(p , this.magicNumber), this.magicNumber));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Vindication();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded)
        {
            this.upgradeName();
            this.upgradeMagicNumber(SPIRIT_UPGRADE);
            //this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            //this.initializeDescription();
        }

    }
}

