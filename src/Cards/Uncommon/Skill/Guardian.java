package Cards.Uncommon.Skill;
import Actions.GuardianAction;
import MainMod.*;
import Patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class Guardian extends CustomCard
{
    public static final String ID = "Guardian";
    public static final String NAME = "Guardian";
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG_PATH = "Cards/Skills/corona.png";
    private static final int COST = -1;
    private static final int POOL = 1;
    private static final CardRarity rarity = CardRarity.UNCOMMON;
    private static final CardTarget target = CardTarget.SELF;
    private static final CardType type = CardType.SKILL;
    private static final int BLOCK_COUNT = 10;
    private static final int BLOCK_COUNT_UPGRADE = 4;


    public Guardian() {
        super(ID, CARD_STRINGS.NAME, Fudgesickle.makePath(Fudgesickle.GUARDIAN), COST, CARD_STRINGS.DESCRIPTION,
                type, AbstractCardEnum.Holy,
                rarity, target, POOL);
        this.baseMagicNumber = this.magicNumber = BLOCK_COUNT;
        //this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        this.baseBlock = this.baseMagicNumber * EnergyPanel.totalCount;
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));

        if (!this.freeToPlayOnce) {
            p.energy.use(EnergyPanel.totalCount);
        }
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        this.baseBlock = this.baseMagicNumber * EnergyPanel.totalCount;
        this.setDescription(false);
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
        return new Guardian();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(BLOCK_COUNT_UPGRADE);
        }

    }
}
