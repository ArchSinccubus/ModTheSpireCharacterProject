package Actions;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class TakeAimAction extends AbstractGameAction {
    private AbstractPlayer p;
    private ArrayList<AbstractCard> cannotReduce = new ArrayList<>();
    private ArrayList<AbstractCard> canUpgrade = new ArrayList<>();
    private int CostReduceBy;

    public static final String TEXT = "Select a card to cheapen.";

    public TakeAimAction(AbstractCreature target, AbstractCreature source, int amount) {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.p = AbstractDungeon.player;
        this.CostReduceBy = amount;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {

            for (AbstractCard c : this.p.hand.group) {
                if (!canReduce(c)) {
                    this.cannotReduce.add(c);
                }
            }

            if (this.cannotReduce.size() == this.p.hand.group.size()) {
                this.isDone = true;
                return;
            }

            if (this.p.hand.group.size() - this.cannotReduce.size() == 1) {
                for (AbstractCard c : this.p.hand.group) {
                    if (canReduce(c)) {
                        c.modifyCostForTurn(-1);
                        this.isDone = true;
                        return;
                    }
                }
            }

                this.p.hand.group.removeAll(this.cannotReduce);

                if (this.p.hand.group.size() > 1) {

                    AbstractDungeon.handCardSelectScreen.open(TEXT, 1, false, true);
                    tickDuration();

                    return;
                }
                else if (this.p.hand.group.size() == 1) {
                    this.p.hand.getTopCard().modifyCostForTurn(-1);
                    returnCards();
                    this.isDone = true;
                }
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                c.modifyCostForTurn(-1);
                this.p.hand.addToTop(c);
            }

            returnCards();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            this.isDone = true;
        }

        tickDuration();
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

    private void reapplyPowers() {
        // apply powers
        for (AbstractCard c : this.p.hand.group) {
            c.applyPowers();
        }
    }

    private void returnCards() {
        for (AbstractCard c : this.cannotReduce) {
            this.p.hand.addToTop(c);
        }

        reapplyPowers();

        this.p.hand.refreshHandLayout();
    }
}