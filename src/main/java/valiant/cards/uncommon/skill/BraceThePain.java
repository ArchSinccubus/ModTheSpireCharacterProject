package valiant.cards.uncommon.skill;
import valiant.ValiantMod;
import valiant.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;

public class BraceThePain extends CustomCard
{
    public static final String ID = "BraceThePain";
    public static final String NAME = "BraceThePain";
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 2;
    private static final int POOL = 1;
    private static final CardRarity rarity = CardRarity.UNCOMMON;
    private static final CardTarget target = CardTarget.SELF;
    private static final CardType type = CardType.SKILL;
    private static final int HP_THREASHOLD = 6;
    private static final int HP_THREASHOLD_UPGRADE = 2;
    private static int BASIC_DAMAGE;


    public BraceThePain() {
        super(ID, CARD_STRINGS.NAME, ValiantMod.BRACE_THE_PAIN, COST, CARD_STRINGS.DESCRIPTION,
                type, AbstractCardEnum.Holy,
                rarity, target);
        BASIC_DAMAGE = 2;
        this.baseMagicNumber = this.magicNumber = HP_THREASHOLD;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        int calculate =AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth;
        this.baseBlock = (calculate / 8) * this.baseMagicNumber;
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));

    }

    @Override
    public void applyPowers() {
        int calculate =AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth;
        this.baseBlock = (calculate / 8) * this.baseMagicNumber;
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
        return new BraceThePain();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(HP_THREASHOLD_UPGRADE);
        }

    }
}
