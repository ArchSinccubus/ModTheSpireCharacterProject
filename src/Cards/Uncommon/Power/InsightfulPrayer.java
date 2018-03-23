package Cards.Uncommon.Power;

import MainMod.*;
import Patches.AbstractCardEnum;
import Powers.*;
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

public class InsightfulPrayer extends CustomCard
{
    public static final String ID = "PowerThrough";
    public static final String NAME = "Power Through";
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG_PATH = "Cards/Powers/enigma.png";
    private static final int COST = 3;
    private static final int POOL = 1;
    private static final CardRarity rarity = CardRarity.UNCOMMON;
    private static final CardTarget target = CardTarget.SELF;
    private static final CardType type = CardType.POWER;
    private static final int CARS_TO_DRAW = 2;
    private static final int CARS_TO_DRAW_UPGRADE = 1;


    public InsightfulPrayer() {
        super(ID, CARD_STRINGS.NAME, Fudgesickle.makePath(IMG_PATH), COST, CARD_STRINGS.DESCRIPTION,
                type, AbstractCardEnum.Holy,
                rarity, target, POOL);
        this.baseMagicNumber = this.magicNumber = CARS_TO_DRAW;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new InsightfulPrayerPower(p, 1, this.magicNumber), 1, true, AbstractGameAction.AttackEffect.NONE));
    }



    @Override
    public AbstractCard makeCopy() {
        return new InsightfulPrayer();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(CARS_TO_DRAW_UPGRADE);
        }

    }
}

