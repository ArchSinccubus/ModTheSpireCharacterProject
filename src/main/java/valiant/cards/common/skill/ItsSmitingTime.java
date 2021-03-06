package valiant.cards.common.skill;

import valiant.ValiantMod;
import valiant.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;

public class ItsSmitingTime extends CustomCard
{
    public static final String ID = "ItsSmitingTime";
    public static final String NAME = "It's Smitin' Time!";
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 2;
    private static final int POOL = 1;
    private static final int BLOCK_AMOUNT = 6;
    private static final int BLOCK_AMOUNT_UPGRADE = 2;
    private static final CardRarity rarity = CardRarity.COMMON;
    private static final CardTarget target = CardTarget.SELF;


    public ItsSmitingTime() {
        super(ID, CARD_STRINGS.NAME, ValiantMod.ITS_SMITING_TIME, COST, CARD_STRINGS.DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.Holy,
                rarity, target);
        this.baseMagicNumber = this.magicNumber = BLOCK_AMOUNT;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new LightningEffect(p.drawX, p.drawY), 0.05F));
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
    }

    @Override
    public void applyPowers() {
        this.baseBlock = countSmiteInHand() * this.magicNumber;
        super.applyPowers();
        this.setDescription(true);
    }

    private void setDescription(boolean addExtended) {
        this.rawDescription = CARD_STRINGS.DESCRIPTION;
        if (addExtended) {
            this.rawDescription += CARD_STRINGS.EXTENDED_DESCRIPTION[0];
        }
        if (this.exhaustOnUseOnce && !this.exhaust)
            this.rawDescription += " NL Exhaust.";
        this.initializeDescription();
    }

    public static int countSmiteInHand() {
        int SmiteCount = 0;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (!c.name.contains("Smit"))
                continue;
            SmiteCount++;
        }
        return SmiteCount;
    }

    @Override
    public AbstractCard makeCopy() {
        return new ItsSmitingTime();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(BLOCK_AMOUNT_UPGRADE);
        }

    }
}