package Cards.Common.Attack;
import Actions.EmpoweringForceAction;
import MainMod.*;
import Patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class EmpoweringForce extends CustomCard{
    public static final String ID = "EmpoweringForce";
    public static final String NAME = "Empowering Force";
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG_PATH = "Cards/Attacks/comet.png";
    private static final int COST = 1;
    private static final int ATTACK_DMG = 6;
    private static final int UPGRADE_PLUS_DMG = 3;
    private static final int UPGRADE_NUM = 1;
    private static final int UPGRADE_PLUS_NUM = 2;
    private static final int POOL = 1;

    public EmpoweringForce() {
        super(ID, CARD_STRINGS.NAME, Fudgesickle.makePath(IMG_PATH), COST, CARD_STRINGS.DESCRIPTION, AbstractCard.CardType.ATTACK,
                AbstractCardEnum.Holy, AbstractCard.CardRarity.BASIC,
                AbstractCard.CardTarget.ENEMY, POOL);

        this.baseDamage = this.damage = ATTACK_DMG;
        this.baseMagicNumber = this.magicNumber = UPGRADE_NUM;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.BLUNT_HEAVY));

        AbstractDungeon.actionManager.addToBottom(new EmpoweringForceAction(this.upgraded));
    }

    private void UpgradeAction(boolean updraded)
    {

    }

    @Override
    public AbstractCard makeCopy() {
        return new EmpoweringForce();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
            this.upgradeMagicNumber(UPGRADE_PLUS_NUM);
        }
    }
}
