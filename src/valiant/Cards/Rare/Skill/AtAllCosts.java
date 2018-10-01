package valiant.Cards.Rare.Skill;

import valiant.Actions.AddCardToHandAtion;
import valiant.MainMod.*;
import valiant.Patches.AbstractCardEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.random.Random;

import java.util.ArrayList;
import java.util.Iterator;

public class AtAllCosts extends CustomCard
{
    public static final String ID = "AtAllCosts";
    public static final String NAME = "At All Costs";
    public static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG_PATH = "Cards_other/Skills/corona.png";
    private static final int COST = 0;
    private static final int POOL = 1;
    private static final CardRarity rarity = CardRarity.RARE;
    private static final CardTarget target = CardTarget.SELF;
    private static final CardType type = CardType.SKILL;


    public AtAllCosts() {
        super(ID, CARD_STRINGS.NAME, Fudgesickle.makePath(Fudgesickle.AT_ALL_COSTS), COST, CARD_STRINGS.DESCRIPTION,
                type, AbstractCardEnum.Holy,
                rarity, target);
        //this.isInnate = false;
        this.exhaust = true;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {

        AbstractDungeon.actionManager.addToBottom(new AddCardToHandAtion(1 , upgraded));
        //AbstractCard c = returnTrulyRandomCurse(CardType.CURSE, AbstractDungeon.cardRandomRng).makeCopy();
        //AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, true));
        //AbstractDungeon.effectsQueue.add(/*EL:126*/new FastCardObtainEffect(c, c.current_x, c.current_y));;

    }

    public static AbstractCard returnTrulyRandomCurse(CardType type, Random rng) {
        ArrayList<AbstractCard> list = new ArrayList();
        Iterator var3 = AbstractDungeon.srcCurseCardPool.group.iterator();

        AbstractCard c;
        while(var3.hasNext()) {
            c = (AbstractCard)var3.next();
            if (c.type == type) {
                list.add(c);
            }
        }

        return (AbstractCard)list.get(rng.random(list.size() - 1));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        } else {
            boolean hasCard = false;

            int toatl = p.drawPile.size();
            if (upgraded)
            {
                toatl += p.discardPile.size();
                toatl += p.exhaustPile.size();
            }

            if (toatl > 0)
            {
                hasCard = true;
            }

            if (!hasCard) {
                this.cantUseMessage = (!this.upgraded) ? "I don't have any cards in my draw pile!..." : "I don't have any cards in any pile!...";
                canUse = false;
            }

            return canUse;
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new AtAllCosts();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
            //this.isInnate = true;
        }

    }
}
