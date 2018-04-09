package Cards.Rare.Power;

import valiant.MainMod.*;
import valiant.Patches.AbstractCardEnum;
import valiant.Powers.HastePower;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;

public class TempoMaster extends CustomCard
{
    public static final String ID = "TempoMaster";
    public static final String NAME = "Tempo Master";
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG_PATH = "Cards/Skills/corona.png";
    private static final int COST = 1;
    private static final int POOL = 1;
    private static final CardRarity rarity = CardRarity.RARE;
    private static final CardType type = CardType.POWER;
    private static final int SLOW_AMOUNT = 1;


    public TempoMaster() {
        super(ID, CARD_STRINGS.NAME, Fudgesickle.makePath(Fudgesickle.TEMPO_MASTER), COST, CARD_STRINGS.DESCRIPTION,
                type, AbstractCardEnum.Holy,
                rarity, CardTarget.SELF, POOL);
        this.magicNumber = this.baseMagicNumber = SLOW_AMOUNT;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new HastePower(p, this.magicNumber), 1, true, AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    public AbstractCard makeCopy() {
        return new TempoMaster();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.isInnate = true;
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();

        }

    }
}

