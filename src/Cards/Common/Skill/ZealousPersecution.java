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
import com.megacrit.cardcrawl.powers.WeakPower;

public class ZealousPersecution extends CustomCard
{
    public static final String ID = "ZealousPersecution";
    public static final String NAME = "Zealous Persecution";
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG_PATH = "Cards/Skills/corona.png";
    private static final int COST = 0;
    private static final int POOL = 1;
    private static final int CARD_DRAW = 2;
    private static final int CARD_DRAW_UPGRADE = 1;
    private static final CardRarity rarity = CardRarity.COMMON;
    private static final CardTarget target = CardTarget.SELF;

    private int costLimit;


    public ZealousPersecution() {
        super(ID, CARD_STRINGS.NAME, Fudgesickle.makePath(IMG_PATH), COST, CARD_STRINGS.DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.Holy,
                rarity, target, POOL);
        this.baseMagicNumber = this.magicNumber = CARD_DRAW;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new LoseHPAction(p, p, 6));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {

    boolean canUse = super.canUse(p, m);


    if (!canUse) {
        return false;
    }
    canUse = false;
    if (hasHighEnoughCost(3)) {
        canUse = true;
        return canUse;
    }
    this.cantUseMessage = "I don't have a card with enough energy cost...";
    return canUse;
}

    public boolean hasHighEnoughCost(int costLimit)
    {
        int SmiteCount = 0;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.cost >= costLimit)
                return true;
            SmiteCount++;
        }
        return false;

    }

    @Override
    public AbstractCard makeCopy() {
        return new ZealousPersecution();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(CARD_DRAW_UPGRADE);
            this.costLimit = 2;
        }

    }
}