package Cards.Uncommon.Skill;
import valiant.MainMod.*;
import valiant.Patches.AbstractCardEnum;
import valiant.Powers.WaveringPower;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;

public class ArchangelsBoon extends CustomCard
{
    public static final String ID = "ArchangelsBoon";
    public static final String NAME = "Archangel's Boon";
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG_PATH = "Cards/Skills/corona.png";
    private static final int COST = 2;
    private static final int POOL = 1;
    private static final CardRarity rarity = CardRarity.UNCOMMON;
    private static final CardTarget target = CardTarget.ENEMY;
    private static final CardType type = CardType.SKILL;
    private static final int FRAIL_POWER = 4;
    private static final int FRAIL_POWER_UPGRADE = 2;


    public ArchangelsBoon() {
        super(ID, CARD_STRINGS.NAME, Fudgesickle.makePath(Fudgesickle.ARCHANGEL_BOON), COST, CARD_STRINGS.DESCRIPTION,
                type, AbstractCardEnum.Holy,
                rarity, target, POOL);
        this.magicNumber = this.baseMagicNumber = FRAIL_POWER;
        this.baseBlock = this.block = FRAIL_POWER*2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new WaveringPower(m, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));

        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));

    }

    @Override
    public AbstractCard makeCopy() {
        return new ArchangelsBoon();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(FRAIL_POWER_UPGRADE);
            this.upgradeBlock(FRAIL_POWER_UPGRADE*2);
        }

    }
}
