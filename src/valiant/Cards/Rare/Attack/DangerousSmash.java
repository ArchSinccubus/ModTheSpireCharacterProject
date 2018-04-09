package Cards.Rare.Attack;

import valiant.MainMod.*;
import valiant.Patches.AbstractCardEnum;
import valiant.Powers.TearSoulPower;
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
import com.megacrit.cardcrawl.vfx.combat.DamageHeartEffect;

public class DangerousSmash extends CustomCard
{
    public static final String ID = "DangerousSmash";
    public static final String NAME = "Dangerous Smash";
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG_PATH = "Cards/Attacks/comet.png";
    private static final int COST = 3;
    private static final int POOL = 1;
    private static final CardRarity rarity = CardRarity.RARE;
    private static final CardTarget target = CardTarget.ENEMY;
    private static final CardType type = CardType.ATTACK;
    private static final int DAMAGE_INIT = 35;
    private static final int DAMAGE_UPGRADE = 13;


    public DangerousSmash() {
        super(ID, CARD_STRINGS.NAME, Fudgesickle.makePath(Fudgesickle.DANGEROUS_SMASH), COST, CARD_STRINGS.DESCRIPTION,
                type, AbstractCardEnum.Holy,
                rarity, target, POOL);
        this.baseDamage = this.damage = DAMAGE_INIT;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.actionManager.addToBottom(new WaitAction(0.8F));

        if (m != null) {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new DamageHeartEffect(0,m.hb.cX, m.hb.cY, AbstractGameAction.AttackEffect.SLASH_HEAVY)));
        }

        AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.NONE));

        if(energyOnUse - cost == 0)
        {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new TearSoulPower(1), 1));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new DangerousSmash();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(DAMAGE_UPGRADE);
        }

    }
}
