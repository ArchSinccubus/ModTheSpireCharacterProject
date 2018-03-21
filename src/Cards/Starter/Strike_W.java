package Cards.Starter;
import MainMod.Fudgesickle;
import Patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Strike_W extends CustomCard{
    public static final String ID = "AttackDemo";
    public static final String NAME = "Strike";
    public static final String DESCRIPTION = "Deal !D! damage.";
    public static final String IMG_PATH = "Cards/Attacks/comet.png";
    private static final int COST = 1;
    private static final int ATTACK_DMG = 6;
    private static final int UPGRADE_PLUS_DMG = 3;
    private static final int POOL = 1;
    private static final CardRarity rarity = CardRarity.BASIC;
    private static final CardTarget target = CardTarget.ENEMY;


    public Strike_W() {
        super(ID, NAME, Fudgesickle.makePath(IMG_PATH), COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
                AbstractCardEnum.Holy, rarity,
                target, POOL);

        this.baseDamage = ATTACK_DMG;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Strike_W();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
        }
    }
}
