package Cards.Rare.Power;

import MainMod.*;
import Patches.AbstractCardEnum;
import Powers.WeakFormPower;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class WeakForm extends CustomCard
{
    public static final String ID = "WeakForm";
    public static final String NAME = "Weak Form";
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG_PATH = "Cards/Skills/corona.png";
    private static final int COST = 2;
    private static final int POOL = 1;
    private static final CardRarity rarity = CardRarity.RARE;
    private static final CardTarget target = CardTarget.SELF;
    private static final CardType type = CardType.POWER;
    private static final int STRENGTH_LOSS = 4;
    private static final int STRENGTH_LOSS_PLUS = -1;


    public WeakForm() {
        super(ID, CARD_STRINGS.NAME, Fudgesickle.makePath(Fudgesickle.WEAK_FORM), COST, CARD_STRINGS.DESCRIPTION,
                type, AbstractCardEnum.Holy,
                rarity, target, POOL);
        this.baseMagicNumber = this.magicNumber = STRENGTH_LOSS;
        this.isInnate = false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        //AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, -this.magicNumber), -this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new WeakFormPower(p, 1, 1), 1, true, AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    public AbstractCard makeCopy() {
        return new WeakForm();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(STRENGTH_LOSS_PLUS);
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.isInnate = true;
            this.initializeDescription();

        }

    }
}


