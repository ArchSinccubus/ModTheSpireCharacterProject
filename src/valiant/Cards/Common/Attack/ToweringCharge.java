package valiant.Cards.Common.Attack;
import valiant.MainMod.*;
import valiant.Patches.AbstractCardEnum;
import valiant.Powers.WaveringPower;
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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ToweringCharge extends CustomCard
{
    public static final String ID = "ToweringCharge";
    public static final String NAME = "Towering Charge";
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG_PATH = "Cards/Attacks/comet.png";
    private static final int COST = 1;
    private static final int POOL = 1;
    private static final int ATTACK_DMG = 4;
    private static final int ATTACK_DMG_PLUS = 2;
    private static final int WEAK_AMOUNT = 3;
    private static final int WEAK_AMOUNT_PLUS = 2;
    private static final CardRarity rarity = CardRarity.COMMON;
    private static final CardTarget target = CardTarget.ENEMY;
    private static final CardType type = CardType.ATTACK;

    public static final Logger logger = LogManager.getLogger(Fudgesickle.class.getName());
    public ToweringCharge() {
        super(ID, CARD_STRINGS.NAME, Fudgesickle.makePath(Fudgesickle.TOWERING_CHARGE), COST, CARD_STRINGS.DESCRIPTION,
                type, AbstractCardEnum.Holy,
                rarity, target, POOL);
        this.baseDamage = this.damage = ATTACK_DMG;
        this.baseMagicNumber =WEAK_AMOUNT;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.BLUNT_HEAVY));

        logger.info(this.energyOnUse + " TRANSITION ");

        if (this.energyOnUse - 1 >= 2)
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new WaveringPower(m, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));

    }

    @Override
    public AbstractCard makeCopy() {
        return new ToweringCharge();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(ATTACK_DMG_PLUS);
            this.upgradeMagicNumber(WEAK_AMOUNT_PLUS);
        }

    }
}
