package Actions;

import java.util.ArrayList;

import MainMod.Fudgesickle;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class SharpenAction extends AbstractGameAction {
    private AbstractPlayer p;
    private ArrayList<AbstractCard> cannotUpgrade = new ArrayList<>();
    private ArrayList<AbstractCard> canUpgrade = new ArrayList<>();
    private boolean upgraded = false;
    public static final Logger logger = LogManager.getLogger(Fudgesickle.class.getName());

    public static final String TEXT = "Select a card to upgrade.";

    public SharpenAction() {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
//!c.canUpgrade() && c.type != AbstractCard.CardType.ATTACK
            for (AbstractCard c : this.p.hand.group) {
                if (!c.canUpgrade() || c.type != AbstractCard.CardType.ATTACK) {
                    this.cannotUpgrade.add(c);
                }
            }

            if (this.cannotUpgrade.size() == this.p.hand.group.size()) {
                AbstractDungeon.actionManager.addToBottom(new LoseHPAction(p,p, 2));
                this.isDone = true;
                return;
            }

            if (this.p.hand.group.size() - this.cannotUpgrade.size() == 1) {
                for (AbstractCard c : this.p.hand.group) {
                    if (c.canUpgrade() || c.type != AbstractCard.CardType.ATTACK) {
                        c.upgrade();
                        this.isDone = true;
                        return;
                    }
                    else
                    {
                        AbstractDungeon.actionManager.addToBottom(new LoseHPAction(p,p, 2));
                    }
                }
            }

                this.p.hand.group.removeAll(this.cannotUpgrade);

                if (this.p.hand.group.size() > 1) {
                    AbstractDungeon.handCardSelectScreen.open(TEXT, 1, false, false, false, true);
                    tickDuration();
                    return;
                }
                if (this.p.hand.group.size() == 1) {
                    this.p.hand.group.removeAll(this.cannotUpgrade);
                    if ( this.p.hand.getTopCard().type == AbstractCard.CardType.ATTACK)
                    this.p.hand.getTopCard().upgrade();
                    tickDuration();
                    returnCards();
                    this.isDone = true;
                }




        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                if (c.type == AbstractCard.CardType.ATTACK)
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

