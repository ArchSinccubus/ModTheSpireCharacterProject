package Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;

public class CrossPendantAction extends AbstractGameAction {
    public static int numPlaced;
    private boolean retrieveCard = false;

    public CrossPendantAction() {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            AbstractDungeon.cardRewardScreen.codexOpen();
            this.tickDuration();
        } else {
            if (!this.retrieveCard) {
                if (AbstractDungeon.cardRewardScreen.codexCard != null) {
                    AbstractCard codexCard = AbstractDungeon.cardRewardScreen.codexCard.makeStatEquivalentCopy();
                    AbstractCard c = codexCard.makeStatEquivalentCopy();
                    c.current_x = -1000.0F * Settings.scale;
                    if (c.type != AbstractCard.CardType.POWER) {
                        if (!c.exhaust) {
                            c.exhaustOnUseOnce = true;
                            c.rawDescription = codexCard.rawDescription + " Exhaust.";
                        }
                    }
                    c.initializeDescription();
                    //codexCard.isEthereal = true;
                    AbstractDungeon.player.hand.addToTop(c);
                    AbstractDungeon.cardRewardScreen.codexCard = null;
                }

                this.retrieveCard = true;
            }

            this.tickDuration();
        }
    }
}
