package Cards.Common.Attack;
import valiant.MainMod.*;
import valiant.Patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Decimate extends CustomCard
{
    public static final String ID = "Decimate";
    public static final String NAME = "Decimate";
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG_PATH = "Cards/Attacks/comet.png";
    private static final int COST = 2;
    private static final int POOL = 1;
    private static final int ATTACK_DMG = 20;
    private static final int UPGRADE_PLUS_DMG = 2;
    private float percent;
    private static final int MAGIC_PERCENT = 75;
    private static final int UPGRADE_MAGIC_PERCENT = -15;
    private static final CardRarity rarity = CardRarity.COMMON;
    private static final CardTarget target = CardTarget.ENEMY;
    private static final CardType type = CardType.ATTACK;

    public static final Logger logger = LogManager.getLogger(Fudgesickle.class.getName());


    public Decimate() {
        super(ID, CARD_STRINGS.NAME, Fudgesickle.makePath(Fudgesickle.DECIMATE), COST, CARD_STRINGS.DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.Holy,
                rarity, target, POOL);
        this.baseDamage = this.damage = ATTACK_DMG;
        this.baseMagicNumber = this.magicNumber = MAGIC_PERCENT;
        percent = MAGIC_PERCENT / 100;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {

        percent = (float)(this.magicNumber) / 100;
        boolean canUse = super.canUse(p, m);
        logger.info(this.magicNumber + " " + percent + " " + p.maxHealth + " " + p.maxHealth * percent + " " + p.currentHealth + " " + canUse);

        if (!canUse) {
            return false;
        }
        canUse = false;
            if (!(p.currentHealth < (p.maxHealth * percent))) {
                canUse = true;
                return canUse;
            }
        this.cantUseMessage = "I'm too weak...";
        return canUse;
    }

    @Override
    public AbstractCard makeCopy() {
        return new Decimate();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
            this.upgradeMagicNumber(UPGRADE_MAGIC_PERCENT);
            this.initializeDescription();
        }

    }
}
