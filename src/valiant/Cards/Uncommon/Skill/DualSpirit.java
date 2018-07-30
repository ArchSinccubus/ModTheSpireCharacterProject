package valiant.Cards.Uncommon.Skill;

import valiant.Actions.DualSpiritAction;
import valiant.MainMod.Fudgesickle;
import valiant.Patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DualSpirit extends CustomCard {
    public static final String ID = "DualSpirit";
    public static final String NAME = "Dual Spirit";
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG_PATH = "Cards/Skills/corona.png";
    private static final int COST = 1;
    private static final int POOL = 1;
    private static final AbstractCard.CardRarity rarity = AbstractCard.CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget target = CardTarget.SELF;
    private static final AbstractCard.CardType type = AbstractCard.CardType.SKILL;



    public DualSpirit() {
        super(ID, CARD_STRINGS.NAME, Fudgesickle.makePath(Fudgesickle.DUAL_SPIRIT), COST, CARD_STRINGS.DESCRIPTION,
                type, AbstractCardEnum.Holy,
                rarity, target, POOL);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new DualSpiritAction(p, this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new DualSpirit();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}
