package valiant.cards.uncommon.power;

import basemod.helpers.BaseModCardTags;
import valiant.ValiantMod;
import valiant.patches.AbstractCardEnum;
import valiant.powers.MightFormPower;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;

public class MightForm extends CustomCard
{
    public static final String ID = "MightForm";
    public static final String NAME = "Might Form";
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final int COST = 3;
    private static final int POOL = 1;
    private static final CardRarity rarity = CardRarity.UNCOMMON;
    private static final CardTarget target = CardTarget.SELF;
    private static final CardType type = CardType.POWER;
    private static final int BLOCK_AMOUNT = 10;
    private static final int BLOCK_AMOUNT_UPGRADE = 4;


    public MightForm() {
        super(ID, CARD_STRINGS.NAME, ValiantMod.MIGHT_FORM, COST, CARD_STRINGS.DESCRIPTION,
                type, AbstractCardEnum.Holy,
                rarity, target);
        this.tags.add(BaseModCardTags.FORM);
        this.baseMagicNumber = this.magicNumber = BLOCK_AMOUNT;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new MightFormPower(p,1, this.magicNumber), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
    }



    @Override
    public AbstractCard makeCopy() {
        return new MightForm();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(BLOCK_AMOUNT_UPGRADE);
        }

    }
}

