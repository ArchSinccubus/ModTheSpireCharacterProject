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

public class DelvingPrayer extends CustomCard
{
    public static final String ID = "DelvingPrayer";
    public static final String NAME = "Delving Prayer";
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG_PATH = "Cards/Powers/enigma.png";
    private static final int COST = 2;
    private static final int POOL = 1;
    private static final CardRarity rarity = CardRarity.UNCOMMON;
    private static final CardTarget target = CardTarget.SELF;
    private static final CardType type = CardType.POWER;
    private static final int CARS_TO_DRAW = 2;
    private static final int CARS_TO_DRAW_UPGRADE = 1;


    public DelvingPrayer() {
        super(ID, CARD_STRINGS.NAME, Fudgesickle.makePath(Fudgesickle.DELVING_PRAYER), COST, CARD_STRINGS.DESCRIPTION,
                type, AbstractCardEnum.Holy,
                rarity, target, POOL);
        this.baseMagicNumber = this.magicNumber = CARS_TO_DRAW;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DelvingPrayerPower(p, this.magicNumber, this.magicNumber), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
    }



    @Override
    public AbstractCard makeCopy() {
        return new DelvingPrayer();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
            this.upgradeMagicNumber(CARS_TO_DRAW_UPGRADE);
        }

    }
}

