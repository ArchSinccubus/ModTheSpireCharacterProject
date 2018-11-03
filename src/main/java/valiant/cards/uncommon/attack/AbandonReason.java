package valiant.cards.uncommon.attack;
import valiant.ValiantMod;
import valiant.patches.AbstractCardEnum;
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

public class AbandonReason extends CustomCard
{
    public static final String ID = "AbandonReason";
    public static final String NAME = "Abandon Reason";
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 5;
    private static final int POOL = 1;
    private static final CardRarity rarity = CardRarity.UNCOMMON;
    private static final CardTarget target = CardTarget.ENEMY;
    private static final CardType type = CardType.ATTACK;
    private static final int DAMAGE = 19;
    private static final int DAMAGE_PLUS = 6;


    public AbandonReason() {
        super(ID, CARD_STRINGS.NAME, ValiantMod.ABANDON_REASON, COST, CARD_STRINGS.DESCRIPTION,
                type, AbstractCardEnum.Holy,
                rarity, target);
        this.baseDamage = DAMAGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }

    /*
    @Override
    public void triggerWhenDrawn() {
        super.triggerWhenDrawn();
        this.setCostForTurn(this.cost - (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth) / 10);
    }
*/

    @Override
    public void applyPowers() {
        this.setCostForTurn(this.cost - (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth) / 10);
        super.applyPowers();
    }

    @Override
    public AbstractCard makeCopy() {
        return new AbandonReason();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            if (this.cost < 5) {
                this.upgradeBaseCost(this.cost - 1);
                if (this.cost < 0) {
                    this.cost = 0;
                }
            } else {
                this.upgradeBaseCost(4);
            }
            this.upgradeDamage(DAMAGE_PLUS);
        }

    }
}
