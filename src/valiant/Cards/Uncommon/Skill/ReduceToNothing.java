package valiant.Cards.Uncommon.Skill;

import valiant.Actions.MultiplyPowerAction;
import valiant.MainMod.Fudgesickle;
import valiant.Patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ReduceToNothing extends CustomCard
{
    public static final String ID = "ReduceToNothing";
    public static final String NAME = "Reduce To Nothing";
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG_PATH = "Cards/Skills/corona.png";
    private static final int COST = 1;
    private static final int POOL = 1;
    private static final CardRarity rarity = CardRarity.UNCOMMON;
    private static final CardTarget target = CardTarget.ENEMY;
    private static final CardType type = CardType.SKILL;


    public ReduceToNothing() {
        super(ID, CARD_STRINGS.NAME, Fudgesickle.makePath(Fudgesickle.REDUCE_TO_NOTHING), COST, CARD_STRINGS.DESCRIPTION,
                type, AbstractCardEnum.Holy,
                rarity, target, POOL);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        if (!this.upgraded) {
            AbstractDungeon.actionManager.addToBottom(new MultiplyPowerAction(m, p, 1,"Wavering"));
        } else {
            AbstractDungeon.actionManager.addToBottom(new MultiplyPowerAction(m, p, 2,"Wavering"));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new ReduceToNothing();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }

    }
}
