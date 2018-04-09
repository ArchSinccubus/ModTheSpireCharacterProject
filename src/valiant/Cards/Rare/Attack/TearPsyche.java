package Cards.Rare.Attack;
import valiant.MainMod.*;
import valiant.Patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;

public class TearPsyche extends CustomCard
{
    public static final String ID = "TearPsyche";
    public static final String NAME = "Tear Psyche";
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG_PATH = "Cards/Attacks/comet.png";
    private static final int COST = 3;
    private static final int POOL = 1;
    private static final CardRarity rarity = CardRarity.RARE;
    private static final CardTarget target = CardTarget.ENEMY;
    private static final CardType type = CardType.ATTACK;
    private static final int DAMAGE = 40;
    private static final int DAMAGE_UPGRADE = 15;
    private static final int PERCENT = 10;
    private static final int PERCENT_UPGRADE = -5;

    public TearPsyche() {
        super(ID, CARD_STRINGS.NAME, Fudgesickle.makePath(Fudgesickle.TEAR_PSYCHE), COST, CARD_STRINGS.DESCRIPTION,
                type, AbstractCardEnum.Holy,
                rarity, target, POOL);
        this.baseDamage = this.damage = DAMAGE;
        this.baseMagicNumber = this.magicNumber = PERCENT;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        float percent = (float)this.magicNumber / 100;

        int HP_loss = (int) (p.maxHealth * percent);

        AbstractDungeon.actionManager.addToBottom(new SFXAction("THUNDERCLAP", 0.05F));

        AbstractDungeon.actionManager.addToBottom(new LoseHPAction(p, p, HP_loss));
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.BLUNT_HEAVY));

}

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        float percent = (float)(this.magicNumber) / 100;
        boolean canUse = super.canUse(p, m);

        if (!canUse) {
            return false;
        }
        canUse = false;
        if (p.currentHealth > percent * p.maxHealth) {
            canUse = true;
            return canUse;
        }
        this.cantUseMessage = "I won't be able to take it...";
        return canUse;
    }

    @Override
    public AbstractCard makeCopy() {
        return new TearPsyche();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(DAMAGE_UPGRADE);
            this.upgradeMagicNumber(PERCENT_UPGRADE);
        }
    }
}