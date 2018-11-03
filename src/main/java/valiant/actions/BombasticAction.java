package valiant.actions;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class BombasticAction extends AbstractGameAction {
    private AbstractPlayer p;
    private ArrayList<AbstractCard> cannotUpgrade = new ArrayList<>();
    private ArrayList<AbstractCard> canUpgrade = new ArrayList<>();

    public static final String TEXT = "Select a card to cost 0.";

    public BombasticAction() {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {

            for (AbstractCard c : this.p.hand.group) {
                if (c.cost <= 1) {
                    this.cannotUpgrade.add(c);
                }
            }

            if (this.cannotUpgrade.size() == this.p.hand.group.size()) {
                this.isDone = true;
                return;
            }

            if (this.p.hand.group.size() - this.cannotUpgrade.size() == 1) {
                for (AbstractCard c : this.p.hand.group) {
                    if (c.cost >= 2) {
                        c.modifyCostForCombat(-c.cost);
                        this.isDone = true;
                        return;
                    }
                }
            }

                this.p.hand.group.removeAll(this.cannotUpgrade);

                if (this.p.hand.group.size() > 1) {
                    AbstractDungeon.handCardSelectScreen.open(TEXT, 1, false, false);
                    tickDuration();
                    return;
                }
                if (this.p.hand.group.size() == 1) {
                    this.p.hand.getTopCard().modifyCostForCombat(this.p.hand.getTopCard().cost);
                    returnCards();
                    this.isDone = true;
                }




        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                if (c.cost >= 2)
                c.modifyCostForCombat(-c.cost);
                this.p.hand.addToTop(c);
            }

            returnCards();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            this.isDone = true;
        }

        tickDuration();
    }

    private void reapplyPowers() {
        // apply powers
        for (AbstractCard c : this.p.hand.group) {
            c.applyPowers();
        }
    }

    public boolean canReduce(AbstractCard c) {
        if (c.type == AbstractCard.CardType.CURSE) {
            return false;
        } else if (c.type == AbstractCard.CardType.STATUS) {
            return false;
        } else {
            return (c.cost > 0);
        }
    }

    private void returnCards() {
        for (AbstractCard c : this.cannotUpgrade) {
            this.p.hand.addToTop(c);
        }

        reapplyPowers();

        this.p.hand.refreshHandLayout();
    }
}

