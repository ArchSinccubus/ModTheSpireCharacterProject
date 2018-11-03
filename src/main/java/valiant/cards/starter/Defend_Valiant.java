package valiant.cards.starter;
import basemod.helpers.BaseModCardTags;
import valiant.ValiantMod;
import valiant.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;

public class Defend_Valiant extends CustomCard{
    public static final String ID = "Defend_Valiant";
    public static final String NAME = "Defend";
    public static final String DESCRIPTION = "Gain !B! block.";
    private static final int COST = 1;
    private static final int BLOCK_AMOUNT = 5;
    private static final int UPGRADE_BLOCK_DMG = 3;
    private static final CardRarity rarity = CardRarity.BASIC;
    private static final CardTarget target = CardTarget.SELF;

    public Defend_Valiant() {
        super(ID, NAME, ValiantMod.DEFEND_V, COST, DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.Holy,
                rarity, target);
        this.tags.add(BaseModCardTags.BASIC_DEFEND);
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
        return new Defend_Valiant();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(UPGRADE_BLOCK_DMG);
        }
    }
}
