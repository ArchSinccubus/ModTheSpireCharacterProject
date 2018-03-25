package Cards.Uncommon.Attack;

import MainMod.Fudgesickle;
import Patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

import java.util.Iterator;

public class AuraDischarge extends AbstractCard {
    public static final String ID = "AuraDischarge";
    public static final String NAME = "Aura Discharge";
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG_PATH = "Cards/Attacks/comet.png";
    private static final int COST = 1;
    private static final int POOL = 1;
    private static final CardRarity rarity = CardRarity.UNCOMMON;
    private static final CardTarget target = CardTarget.ALL_ENEMY;
    private static final CardType type = CardType.ATTACK;
    private static final int DAMAGE = 6;
    private static final int DAMAGE_UPGRADE = 2;
    private static final int HP_HEAL = 6;
    private static final int HP_HEAL_UPGRADE = 2;

    public AuraDischarge() {
        super(ID, CARD_STRINGS.NAME, Fudgesickle.makePath(Fudgesickle.AURA_DISCHARGE), COST, CARD_STRINGS.DESCRIPTION,
                type, AbstractCardEnum.Holy,
                rarity, target, POOL);
        this.baseDamage = DAMAGE;
        this.isMultiDamage = true;
        this.baseMagicNumber = this.magicNumber = HP_HEAL;

        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new CleaveEffect(), 0.1F));
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AttackEffect.NONE));
        AbstractDungeon.actionManager.addToBottom(new HealAction(p , p, this.magicNumber));
    }

    @Override
    public void applyPowers()
    {
        super.applyPowers();
        applyPowersToHeal();
    }

    private void applyPowersToHeal() {
        int tmp = this.baseMagicNumber;
        Iterator var2 = AbstractDungeon.player.powers.iterator();
        boolean foundSpirit = false;
        while(var2.hasNext()) {
            AbstractPower p = (AbstractPower)var2.next();
            if (p.name == "Spirit") {
                setDescription(p, tmp);
                foundSpirit = true;
            }
            else
            {
                this.rawDescription = CARD_STRINGS.DESCRIPTION;
                this.initializeDescription();
            }
        }

        this.isMagicNumberModified = foundSpirit;

        if (tmp < 0) {
            tmp = 0;
        }
    }

    private void setDescription(AbstractPower p , int tmp) {
        this.rawDescription = CARD_STRINGS.DESCRIPTION.replace("!M!" , "" + (tmp + p.amount));
        if (this.exhaustOnUseOnce && !this.exhaust)
            this.rawDescription += " NL Exhaust.";
        this.initializeDescription();
    }

    public AbstractCard makeCopy() {
        return new AuraDischarge();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(DAMAGE_UPGRADE);
            this.upgradeMagicNumber(HP_HEAL_UPGRADE);
        }

    }
}
