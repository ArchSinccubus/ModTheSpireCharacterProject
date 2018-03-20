package Cards.Common.Attack;
import Actions.SmiteAction;
import MainMod.*;
import Patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import basemod.abstracts.CustomCard;

public class RecklessSmite extends CustomCard
{
    public static final String ID = "RecklessSmite";
    public static final String NAME = "Reckless Smite";
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG_PATH = "Cards/Attacks/comet.png";
    private static final int COST = 1;
    private static final int POOL = 1;
    private static final CardRarity rarity = CardRarity.COMMON;
    private static final CardTarget target = CardTarget.ENEMY;
    private static final int DAMAGE = 9;

    public RecklessSmite() {
        super(ID, CARD_STRINGS.NAME, Fudgesickle.makePath(IMG_PATH), COST, CARD_STRINGS.DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.Holy,
                rarity, target, POOL);
        this.baseDamage = this.damage = DAMAGE;
        this.baseMagicNumber = this.magicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {

        int finalPower = this.damage;
        if (m.hasPower("Weakened")) {
            if (!this.upgraded)
                finalPower = (int) (this.damage * 0.5f);
        }

        AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.FIRE));
        AbstractDungeon.actionManager.addToBottom(new SmiteAction(m, new DamageInfo(p, finalPower, this.damageTypeForTurn)));

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new VulnerablePower(p, 1, false), 1, true, AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    public AbstractCard makeCopy() {
        return new RecklessSmite();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}
