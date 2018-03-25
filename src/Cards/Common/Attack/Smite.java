package Cards.Common.Attack;
import Actions.SmiteAction;
import MainMod.*;
import Patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
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
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;

public class Smite extends CustomCard
{
    public static final String ID = "Smite";
    public static final String NAME = "Smite";
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG_PATH = "Cards/Attacks/comet.png";
    private static final int COST = 1;
    private static final int POOL = 1;
    private static final CardRarity rarity = CardRarity.COMMON;
    private static final CardTarget target = CardTarget.ENEMY;
    private static final CardType type = CardType.ATTACK;
    private static final int DAMAGE = 8;
    private int extraDamage;

    public Smite() {
        super(ID, CARD_STRINGS.NAME, Fudgesickle.makePath(Fudgesickle.SMITE), COST, CARD_STRINGS.DESCRIPTION,
                type, AbstractCardEnum.Holy,
                rarity, target, POOL);
        this.baseDamage = this.damage = DAMAGE;
        extraDamage = this.damage / 2;
    }

    @Override
    public void applyPowers() {
        extraDamage = this.baseDamage / 2;
        if (isDamageModified)
            extraDamage = this.damage / 2;
        if (this.upgraded)
        {
            extraDamage *= 2;
        }
        super.applyPowers();
        this.setDescription(true);
    }

    private void setDescription(boolean addExtended) {
        //this.rawDescription = CARD_STRINGS.DESCRIPTION;
        if (addExtended) {
            this.rawDescription = CARD_STRINGS.EXTENDED_DESCRIPTION[0].replace("!F!" , "" + extraDamage);
        }
        if (this.exhaustOnUseOnce && !this.exhaust)
            this.rawDescription += " NL Exhaust.";
        this.initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        extraDamage = this.damage / 2;
        if (this.upgraded)
        {
            extraDamage *= 2;
        }
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new LightningEffect(m.drawX, m.drawY), 0.05F));
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
        AbstractDungeon.actionManager.addToBottom(new SmiteAction(m, new DamageInfo(p, extraDamage, this.damageTypeForTurn)));


    }

    @Override
    public AbstractCard makeCopy() {
        return new Smite();
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
