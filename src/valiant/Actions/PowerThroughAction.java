package valiant.Actions;

import java.util.ArrayList;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class PowerThroughAction extends AbstractGameAction {
    private AbstractPlayer p;
    private ArrayList<AbstractCard> cannotUpgrade = new ArrayList<>();
    private ArrayList<AbstractCard> canUpgrade = new ArrayList<>();
    private boolean upgraded = false;

    public boolean exhausted;

    public static final String TEXT = "Select a card to Exhaust.";

    public PowerThroughAction(boolean upgraded) {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;
        this.upgraded = upgraded;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {

            AbstractDungeon.actionManager.addToBottom(new ExhaustAction(p, p, 1, false));
            AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, 3));
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

