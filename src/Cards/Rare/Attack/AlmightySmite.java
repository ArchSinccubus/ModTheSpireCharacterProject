package Cards.Rare.Attack;
import Actions.SmiteAction;
import MainMod.*;
import Patches.AbstractCardEnum;
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

public class AlmightySmite extends CustomCard
{
    public static final String ID = "AlmightySmite";
    public static final String NAME = "Almighty Smite";
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG_PATH = "Cards/Attacks/comet.png";
    private static final int COST = 3;
    private static final int POOL = 1;
    private static final CardRarity rarity = CardRarity.RARE;
    private static final CardTarget target = CardTarget.ENEMY;
    private static final int DAMAGE = 24;
    private int extraDamage;

    public AlmightySmite() {
        super(ID, CARD_STRINGS.NAME, Fudgesickle.makePath(IMG_PATH), COST, CARD_STRINGS.DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.Holy,
                rarity, target, POOL);
        this.baseDamage = this.damage = DAMAGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        extraDamage = this.damage / 2;
        if (this.upgraded)
        {
            extraDamage *= 2;
        }
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
        AbstractDungeon.actionManager.addToBottom(new SmiteAction(m, new DamageInfo(p, extraDamage, this.damageTypeForTurn)));


    }

    @Override
    public void applyPowers() {
        extraDamage = this.baseDamage / 2;
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
        this.initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return new AlmightySmite();
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
