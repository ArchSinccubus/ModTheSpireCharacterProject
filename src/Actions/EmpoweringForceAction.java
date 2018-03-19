package Actions;


import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class EmpoweringForceAction extends AbstractGameAction {
    private AbstractPlayer p;
    private ArrayList<AbstractCard> cannotUpgrade = new ArrayList<>();
    private ArrayList<AbstractCard> canUpgrade = new ArrayList<>();
    private boolean upgraded = false;

    public static final String TEXT = "Select a card to upgrade.";
    public static final String TEXTUpgrade = "Select cards to upgrade.";

    public EmpoweringForceAction(boolean upgraded) {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;
        this.upgraded = upgraded;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {

            for (AbstractCard c : this.p.hand.group) {
                if (!c.canUpgrade()) {
                    this.cannotUpgrade.add(c);
                }
            }

            if (this.cannotUpgrade.size() == this.p.hand.group.size()) {
                this.isDone = true;
                return;
            }

            if (this.p.hand.group.size() - this.cannotUpgrade.size() == 1) {
                for (AbstractCard c : this.p.hand.group) {
                    if (c.canUpgrade()) {
                        c.upgrade();
                        this.isDone = true;
                        return;
                    }
                }
            }

            if (!this.upgraded) {
                this.p.hand.group.removeAll(this.cannotUpgrade);

                if (this.p.hand.group.size() > 1) {
                    AbstractDungeon.handCardSelectScreen.open(TEXT, 1, false, true, false, true);
                    tickDuration();
                    return;
                }
                if (this.p.hand.group.size() == 1) {
                    this.p.hand.getTopCard().upgrade();
                    returnCards();
                    this.isDone = true;
                }
            }
            else
            {
                this.p.hand.group.removeAll(this.cannotUpgrade);

                if (this.p.hand.group.size() > 1) {
                    AbstractDungeon.handCardSelectScreen.open(TEXTUpgrade, 3, false, true, false, true);
                    tickDuration();
                    return;
                }
                if (this.p.hand.group.size() == 1) {
                    this.p.hand.getTopCard().upgrade();
                    returnCards();
                    this.isDone = true;
                }
            }




        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                c.upgrade();
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

    private void returnCards() {
        for (AbstractCard c : this.cannotUpgrade) {
            this.p.hand.addToTop(c);
        }

        reapplyPowers();

        this.p.hand.refreshHandLayout();
    }
}
