package valiant.cards.uncommon.power;

import valiant.ValiantMod;
import valiant.patches.AbstractCardEnum;
import valiant.powers.SharpenPower;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;

public class Sharpen extends CustomCard
{
    public static final String ID = "Sharpen";
    public static final String NAME = "Sharpen";
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final int COST = 2;
    private static final int POOL = 1;
    private static final CardRarity rarity = CardRarity.UNCOMMON;
    private static final CardTarget target = CardTarget.SELF;
    private static final CardType type = CardType.POWER;


    public Sharpen() {
        super(ID, CARD_STRINGS.NAME, ValiantMod.SHARPEN, COST, CARD_STRINGS.DESCRIPTION,
                type, AbstractCardEnum.Holy,
                rarity, target);
        this.baseMagicNumber = this.magicNumber = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        //AbstractDungeon.actionManager.addToBottom(new SFXAction("UPGRADE", 0.05F));

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new SharpenPower(p,this.magicNumber, 1), 1, true, AbstractGameAction.AttackEffect.NONE));
    }



    @Override
    public AbstractCard makeCopy() {
        return new Sharpen();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
            this.isInnate = true;
            this.upgradeBaseCost(1);
        }

    }
}

