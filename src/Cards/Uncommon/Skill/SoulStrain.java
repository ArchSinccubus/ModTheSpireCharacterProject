package Cards.Uncommon.Skill;
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
import com.megacrit.cardcrawl.powers.WeakPower;

public class SoulStrain extends CustomCard
{
    public static final String ID = "SoulStrain";
    public static final String NAME = "SoulStrain";
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG_PATH = "Cards/Skills/corona.png";
    private static final int COST = 1;
    private static final int POOL = 1;
    private static final CardRarity rarity = CardRarity.UNCOMMON;
    private static final CardTarget target = CardTarget.SELF;
    private static final CardType type = CardType.SKILL;
    private static final int ENERGY_GAIN = 3;
    private static final int ENERGY_PLUS = 1;


    public SoulStrain() {
        super(ID, CARD_STRINGS.NAME, Fudgesickle.makePath(Fudgesickle.SOUL_STRAIN), COST, CARD_STRINGS.DESCRIPTION,
                type, AbstractCardEnum.Holy,
                rarity, target, POOL);
        this.baseMagicNumber = this.magicNumber = ENERGY_GAIN;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new LoseHPAction(p, p, 4));
    }



    @Override
    public AbstractCard makeCopy() {
        return new SoulStrain();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
            this.upgradeMagicNumber(ENERGY_PLUS);
        }

    }
}

