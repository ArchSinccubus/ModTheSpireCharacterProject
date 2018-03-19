package Cards;
import MainMod.*;
import Patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import basemod.abstracts.CustomCard;

public class MinorHealing extends CustomCard{
    public static final String ID = "MinorHealing";
    public static final String NAME = "Minor Healing";
    public static final String DESCRIPTION = "Heal !M! HP. Exhaust.";
    public static final String IMG_PATH = "ASSETS/Cards/Skills/corona.png";
    private static final int COST = 1;
    private static final int HP_AMOUNT = 6;
    private static final int UPGRADE_HP_AMOUNT = 2;
    private static final int POOL = 1;

    public MinorHealing() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.WHITE,
                CardRarity.BASIC, CardTarget.SELF, POOL);
        this.heal=this.baseHeal = HP_AMOUNT;
        this.exhaust= true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {

        if (com.megacrit.cardcrawl.core.Settings.isDebug) {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, 50));
        } else {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.heal));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Defend_W();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(UPGRADE_HP_AMOUNT);
        }
    }
}
