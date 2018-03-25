package Cards.Rare.Attack;
import MainMod.*;
import Patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;

public class FinalGambit extends CustomCard
{
    public static final String ID = "FinalGambit";
    public static final String NAME = "Final Gambit";
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG_PATH = "Cards/Attacks/comet.png";
    private static final int COST = 3;
    private static final int POOL = 1;
    private static final CardRarity rarity = CardRarity.RARE;
    private static final CardTarget target = CardTarget.ENEMY;
    private static final CardType type = CardType.ATTACK;



    public FinalGambit() {
        super(ID, CARD_STRINGS.NAME, Fudgesickle.makePath(Fudgesickle.FINAL_GAMBIT), COST, CARD_STRINGS.DESCRIPTION,
                type, AbstractCardEnum.Holy,
                rarity, target, POOL);
        this.baseDamage = 0;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        int LifeLoss;
        if (!upgraded)
            LifeLoss = p.currentHealth - 1;
        else
            LifeLoss = (int)(p.currentHealth * 0.8F);

        AbstractDungeon.actionManager.addToBottom(new LoseHPAction(p, p,LifeLoss));

        if (m != null) {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY)));
        }

        AbstractDungeon.actionManager.addToBottom(new WaitAction(0.8F));
        this.baseDamage = AbstractDungeon.player.currentHealth;
        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.NONE));


    }

    @Override
    public void applyPowers() {
        this.baseDamage = AbstractDungeon.player.currentHealth;
        super.applyPowers();
        this.setDescription(true);
    }

    private void setDescription(boolean addExtended) {
        this.rawDescription = CARD_STRINGS.DESCRIPTION;
        if (addExtended) {
            this.rawDescription += CARD_STRINGS.EXTENDED_DESCRIPTION[0];
        }
        if(exhaustOnUseOnce && !exhaust)
        {
            this.rawDescription = this.rawDescription + "Exhaust. NL";
        }
        this.initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return new FinalGambit();
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
