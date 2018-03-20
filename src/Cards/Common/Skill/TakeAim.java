package Cards.Common.Skill;
import Actions.TakeAimAction;
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

public class TakeAim extends CustomCard
{
    public static final String ID = "TakeAim";
    public static final String NAME = "Take Aim";
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG_PATH = "Cards/Skills/corona.png";
    private static final int COST = 1;
    private static final int POOL = 1;
    private static final int COST_REDUCTION = 1;
    private static final CardRarity rarity = CardRarity.COMMON;
    private static final CardTarget target = CardTarget.SELF;


    public TakeAim() {
        super(ID, CARD_STRINGS.NAME, Fudgesickle.makePath(IMG_PATH), COST, CARD_STRINGS.DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.Holy,
                rarity, target, POOL);
        this.baseMagicNumber = this.magicNumber = COST_REDUCTION;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new TakeAimAction(p, p, -this.magicNumber));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {

        boolean canUse = super.canUse(p, m);


        if (!canUse) {
            return false;
        }
        canUse = false;
        if (HasReducableCost()) {
            canUse = true;
            return canUse;
        }
        this.cantUseMessage = "I don't have a card that can be aimed...";
        return canUse;
    }

    public boolean HasReducableCost()
    {
        int SmiteCount = 0;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.cost > 0 && c != this)
                return true;
            SmiteCount++;
        }
        return false;

    }
    @Override
    public AbstractCard makeCopy() {
        return new TakeAim();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
        }
    }
}