package valiant.cards.uncommon.attack;

import valiant.ValiantMod;
import valiant.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Tantrum extends CustomCard
{
    public static final String ID = "Tantrum";
    public static final String NAME = "Tantrum";
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 1;
    private static final int POOL = 1;
    private static final CardRarity rarity = CardRarity.UNCOMMON;
    private static final CardTarget target = CardTarget.ALL_ENEMY;
    private static final CardType type = CardType.ATTACK;
    private static final int DAMAGE = 5;
    private static final int DAMAGE_PLUS = 3;

    public static final Logger logger = LogManager.getLogger(ValiantMod.class.getName());


    public Tantrum() {
        super(ID, CARD_STRINGS.NAME, ValiantMod.TANTRUM, COST, CARD_STRINGS.DESCRIPTION,
                type, AbstractCardEnum.Holy,
                rarity, target);
        this.baseMagicNumber = DAMAGE;

        if (AbstractDungeon.player != null) {
            logger.info("DAMAGE THIS TURN = " + AbstractDungeon.player.damagedThisCombat );
            this.baseDamage = AbstractDungeon.player.damagedThisCombat * this.magicNumber;
        }
        this.isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new CleaveEffect(), 0.1F));
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    public void applyPowers() {
        this.baseDamage = AbstractDungeon.player.damagedThisCombat * this.baseMagicNumber;
        super.applyPowers();
        this.setDescription(true);
    }

    private void setDescription(boolean addExtended) {
        this.rawDescription = CARD_STRINGS.DESCRIPTION;
        if (addExtended) {
            this.rawDescription += CARD_STRINGS.EXTENDED_DESCRIPTION[0];
        }
        logger.info("THIS FUCKING SHIT:" + this.exhaustOnUseOnce + " " + this.exhaust);
        if (this.exhaustOnUseOnce && !this.exhaust)
            this.rawDescription += " NL Exhaust.";
        this.initializeDescription();
    }

    @Override
    public void tookDamage() {
        logger.info("DAMAGE THIS TURN = " + AbstractDungeon.player.damagedThisCombat );
        logger.info("Took damage");
        ValiantMod.IsDamaged = true;
    }

    @Override
    public void atTurnStart() {
        logger.info("Turn started");
        AbstractDungeon.player.damagedThisCombat = 0;
        ValiantMod.IsDamaged = false;
    }

    @Override
    public AbstractCard makeCopy() {
        return new Tantrum();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(DAMAGE_PLUS);
        }

    }
}

