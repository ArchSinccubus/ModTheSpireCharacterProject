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

import com.megacrit.cardcrawl.vfx.SpeechBubble;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Retaliate extends CustomCard
{
    public static final String ID = "Retaliate";
    public static final String NAME = "Retaliate";
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 0;
    private static final int POOL = 1;
    private static final CardRarity rarity = CardRarity.UNCOMMON;
    private static final CardTarget target = CardTarget.ENEMY;
    private static final CardType type = CardType.ATTACK;
    private static final int DAMAGE = 12;
    private static final int DAMAGE_PLUS = 4;
    private static boolean LostLife = false;

    public static final Logger logger = LogManager.getLogger(ValiantMod.class.getName());


    public Retaliate() {
        super(ID, CARD_STRINGS.NAME, ValiantMod.RETALIATE, COST, CARD_STRINGS.DESCRIPTION,
                type, AbstractCardEnum.Holy,
                rarity, target);
        logger.info("Initialized");
        this.baseDamage = DAMAGE;
        if (AbstractDungeon.player != null)
        LostLife = ValiantMod.IsDamaged = (AbstractDungeon.player.damagedThisCombat > 0);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.effectList.add(new SpeechBubble(p.dialogX, p.dialogY, 3.0F, "TAKE THIS!!!", true));

        AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

    @Override
    public void tookDamage() {
        logger.info("Took damage");
        LostLife = true;
        ValiantMod.IsDamaged = true;
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        } else {
                if (!LostLife) {
                    canUse = false;
                    this.cantUseMessage = "I didn't lose life this turn...";
                    return canUse;
                }
            this.cantUseMessage = "TAKE THIS!";
            return canUse;
        }
    }

    /*
    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        LostLife = false;
    }

    @Override
    public void triggerOnEndOfPlayerTurn() {
        super.triggerOnEndOfPlayerTurn();
        LostLife = false;
    }
    */

    @Override
    public void atTurnStart() {
        logger.info("Turn started");
        AbstractDungeon.player.damagedThisCombat = 0;
        LostLife = false;
        ValiantMod.IsDamaged = false;
    }

    @Override
    public AbstractCard makeCopy() {

        return new Retaliate();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(DAMAGE_PLUS);
        }

    }
}
