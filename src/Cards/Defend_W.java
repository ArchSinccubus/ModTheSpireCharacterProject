package Cards;
import Patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import basemod.abstracts.CustomCard;

public class Defend_W extends CustomCard{
    public static final String ID = "Defend_W";
    public static final String NAME = "Defend_W";
    public static final String DESCRIPTION = "Gain !B! block.";
    public static final String IMG_PATH = "ASSETS/Cards/Skills/corona.png";
    private static final int COST = 1;
    private static final int BLOCK_AMOUNT = 5;
    private static final int UPGRADE_BLOCK_DMG = 3;
    private static final int POOL = 1;

    public Defend_W() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.WHITE,
                CardRarity.BASIC, CardTarget.SELF, POOL);
        this.block=this.baseBlock = BLOCK_AMOUNT;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        if (com.megacrit.cardcrawl.core.Settings.isDebug) {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, 50));
        } else {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Strike_W();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(UPGRADE_BLOCK_DMG);
        }
    }
}
