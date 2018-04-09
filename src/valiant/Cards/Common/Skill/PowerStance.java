package Cards.Common.Skill;
import valiant.MainMod.*;
import valiant.Patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PowerStance extends CustomCard
{
    public static final String ID = "PowerStance";
    public static final String NAME = "Power Stance";
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG_PATH = "Cards/Skills/corona.png";
    private static final int COST = 2;
    private static final int POOL = 1;
    private static final int BLOCK_AMOUNT = 5;
    private static final int UPGRADE_COST = 0;
    private static final CardRarity rarity = CardRarity.COMMON;
    private static final CardTarget target = CardTarget.SELF;

    public static final Logger logger = LogManager.getLogger(Fudgesickle.class.getName());

    public PowerStance() {
        super(ID, CARD_STRINGS.NAME, Fudgesickle.makePath(Fudgesickle.POWER_STANCE), COST, CARD_STRINGS.DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.Holy,
                rarity, target, POOL);
        this.magicNumber = this.baseMagicNumber = BLOCK_AMOUNT;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        int NewBlock = this.baseBlock * AbstractDungeon.player.energy.energy;
        this.block = NewBlock;

        logger.info(NewBlock + " " + p.energy.energy + " " + this.baseBlock + " " + this.block);

        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
    }

    @Override
    public void applyPowers() {
        this.baseBlock = this.baseMagicNumber * this.energyOnUse;
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

    @Override
    public AbstractCard makeCopy() {
        return new PowerStance();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(UPGRADE_COST);
            this.initializeDescription();
        }

    }
}
