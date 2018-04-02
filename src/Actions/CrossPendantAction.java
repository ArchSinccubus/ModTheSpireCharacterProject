package Actions;

import MainMod.Fudgesickle;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CrossPendantAction extends AbstractGameAction {
    public static int numPlaced;
    private boolean retrieveCard = false;
    public static final Logger logger = LogManager.getLogger(Fudgesickle.class.getName());

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
                    //codexCard = new Charge();
                    AbstractCard c = codexCard.makeStatEquivalentCopy();
                    c.current_x = -1000.0F * Settings.scale;
                    if (c.type != AbstractCard.CardType.POWER) {
                        //if (!c.exhaust) {
                            c.exhaustOnUseOnce = true;
                            c.rawDescription += " NL Exhaust.";
                            c.applyPowers();
                            c.initializeDescription();
                        //}
                    }
                    //codexCard.isEthereal = true;
                    AbstractDungeon.player.hand.addToTop(c);
                    AbstractDungeon.cardRewardScreen.codexCard = null;
                    for (AbstractCard cToUpdate : AbstractDungeon.player.hand.group) {
                        cToUpdate.applyPowers();
                    }
                }

                this.retrieveCard = true;
            }

            this.tickDuration();
        }
    }
}
