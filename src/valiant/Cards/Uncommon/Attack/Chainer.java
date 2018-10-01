package valiant.Cards.Uncommon.Attack;
import valiant.MainMod.*;
import valiant.Patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;

public class Chainer extends CustomCard
{
    public static final String ID = "Chainer";
    public static final String NAME = "Chainer";
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG_PATH = "Cards/Attacks/comet.png";
    private static final int COST = 1;
    private static final int POOL = 1;
    private static final CardRarity rarity = CardRarity.UNCOMMON;
    private static final CardTarget target = CardTarget.ENEMY;
    private static final CardType type = CardType.ATTACK;
    private static final int HP_THREASHOLD = 2;
    private static final int HP_THREASHOLD_UPGRADE = 1;
    private static int BASIC_DAMAGE;


    public Chainer() {
        super(ID, CARD_STRINGS.NAME, Fudgesickle.makePath(Fudgesickle.CHAINER), COST, CARD_STRINGS.DESCRIPTION,
                type, AbstractCardEnum.Holy,
                rarity, target);
        BASIC_DAMAGE = 2;
        this.baseMagicNumber = this.magicNumber = HP_THREASHOLD;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        this.baseDamage = (AbstractDungeon.player.currentHealth / 10) * this.baseMagicNumber;
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_VERTICAL));

    }

    @Override
    public void applyPowers() {
        this.baseDamage = (AbstractDungeon.player.currentHealth / 10) * this.baseMagicNumber;
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
        return new Chainer();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(HP_THREASHOLD_UPGRADE);
        }

    }
}
