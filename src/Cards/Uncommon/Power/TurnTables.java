package Cards.Uncommon.Power;

import MainMod.*;
import Patches.AbstractCardEnum;
import Powers.MightFormPower;
import Powers.SharpenPower;
import Powers.TurnTablesPower;
import Powers.ZenPower;
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

public class TurnTables extends CustomCard
{
    public static final String ID = "TurnTables";
    public static final String NAME = "Turn Tables";
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG_PATH = "Cards/Powers/enigma.png";
    private static final int COST = 2;
    private static final int POOL = 1;
    private static final CardRarity rarity = CardRarity.UNCOMMON;
    private static final CardTarget target = CardTarget.SELF;
    private static final CardType type = CardType.POWER;


    public TurnTables() {
        super(ID, CARD_STRINGS.NAME, Fudgesickle.makePath(IMG_PATH), COST, CARD_STRINGS.DESCRIPTION,
                type, AbstractCardEnum.Holy,
                rarity, target, POOL);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new TurnTablesPower(p), 1, true, AbstractGameAction.AttackEffect.NONE));
    }



    @Override
    public AbstractCard makeCopy() {
        return new TurnTables();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1);
        }

    }
}

