package Cards.Uncommon.Skill;
import MainMod.*;
import Patches.AbstractCardEnum;
import Powers.ChargePower;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Charge extends CustomCard
{
    public static final String ID = "Charge";
    public static final String NAME = "Charge";
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG_PATH = "Cards/Skills/corona.png";
    private static final int COST = 0;
    private static final int POOL = 1;
    private static final CardRarity rarity = CardRarity.UNCOMMON;
    private static final CardTarget target = CardTarget.SELF;
    private static final CardType type = CardType.SKILL;
    private static final int HP_LOSS = 4;
    private static final int HP_LOSS_UPGRADE = -2;

    public static final Logger logger = LogManager.getLogger(Fudgesickle.class.getName());

    public Charge() {
        super(ID, CARD_STRINGS.NAME, Fudgesickle.makePath(Fudgesickle.CHARGE), COST, CARD_STRINGS.DESCRIPTION,
                type, AbstractCardEnum.Holy,
                rarity, target, POOL);
        this.baseMagicNumber = this.magicNumber = HP_LOSS;
        //this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        AbstractDungeon.effectList.add(new SpeechBubble(p.dialogX, p.dialogY, 3.0F, "Hmm...", true));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ChargePower(p, 1), 1, true, AbstractGameAction.AttackEffect.NONE));
        AbstractDungeon.actionManager.addToBottom(new LoseHPAction(p, p, this.baseMagicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Charge();
    }

    @Override
    public void applyPowers()
    {
        super.applyPowers();
        this.setDescription(false);
    }

    private void setDescription(boolean addExtended) {
        this.rawDescription = CARD_STRINGS.DESCRIPTION;
        if (addExtended) {
            this.rawDescription += CARD_STRINGS.EXTENDED_DESCRIPTION[0];
        }
        if (this.exhaustOnUseOnce && !this.exhaust)
            this.rawDescription += " NL Exhaust.";
        this.initializeDescription();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            logger.info("THIS FUCKING SHIT:" + this.exhaustOnUseOnce + " " + this.exhaust);
            //this.exhaust = false;
            this.upgradeMagicNumber(HP_LOSS_UPGRADE);
            //this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
            //this.upgradeBaseCost(0);
        }

    }
}
