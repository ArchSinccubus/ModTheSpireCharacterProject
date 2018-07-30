package valiant.Cards.Common.Attack;
import valiant.MainMod.*;
import valiant.Patches.AbstractCardEnum;
import valiant.Powers.WaveringPower;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

public class GracefulSiphon extends CustomCard
{
    public static final String ID = "GracefulSiphon";
    public static final String NAME = "Graceful Siphon";
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG_PATH = "Cards/Attacks/comet.png";
    private static final int COST = 2;
    private static final int POOL = 1;
    private static final int ATTACK_DMG = 6;
    private static final int ATTACK_DMG_PLUS = 2;
    private static final int HEAL_AMOUNT = 2;
    private static final int HEAL_AMOUNT_PLUS = 2;
    private static final CardRarity rarity = CardRarity.COMMON;
    private static final CardTarget target = CardTarget.ALL_ENEMY;
    private static final CardType type = CardType.ATTACK;


    public GracefulSiphon() {
        super(ID, CARD_STRINGS.NAME, Fudgesickle.makePath(Fudgesickle.GRACEFUL_SIPHON), COST, CARD_STRINGS.DESCRIPTION,
                type, AbstractCardEnum.Holy,
                rarity, target, POOL);
        this.baseDamage = this.damage = ATTACK_DMG;
        this.baseMagicNumber = HEAL_AMOUNT;
        this.magicNumber = this.baseMagicNumber;
        this.isMultiDamage = true;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new CleaveEffect(), 0.1F));
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HEAVY));

        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new WaveringPower(mo, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        }
    }

    @Override
    public void applyPowers()
    {
        super.applyPowers();
    }

    @Override
    public AbstractCard makeCopy() {
        return new GracefulSiphon();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(ATTACK_DMG_PLUS);
            this.upgradeMagicNumber(HEAL_AMOUNT_PLUS);
        }

    }
}
