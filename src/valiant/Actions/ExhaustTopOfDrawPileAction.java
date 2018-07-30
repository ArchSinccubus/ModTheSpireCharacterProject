package valiant.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ExhaustTopOfDrawPileAction extends AbstractGameAction {
    private int ExhaustTimes;

    public ExhaustTopOfDrawPileAction(AbstractCreature target, int times) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.WAIT;
        this.source = AbstractDungeon.player;
        this.target = target;
        this.ExhaustTimes = times;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (AbstractDungeon.player.drawPile.size() + AbstractDungeon.player.discardPile.size() == 0) {
                this.isDone = true;
                return;
            }

            if (AbstractDungeon.player.drawPile.isEmpty()) {
                AbstractDungeon.actionManager.addToTop(new ExhaustTopOfDrawPileAction(this.target, this.ExhaustTimes));
                AbstractDungeon.actionManager.addToTop(new EmptyDeckShuffleAction());
                this.isDone = true;
                return;
            }

            for(int i = 0; i < this.ExhaustTimes; ++i) {
                if (!AbstractDungeon.player.drawPile.isEmpty()) {
                    AbstractCard card = AbstractDungeon.player.drawPile.getTopCard();
                    AbstractDungeon.player.drawPile.group.remove(card);
                    AbstractDungeon.getCurrRoom().souls.remove(card);
                    AbstractDungeon.player.limbo.group.add(card);
                    card.current_y = -200.0F * Settings.scale;
                    card.target_x = (float)Settings.WIDTH / 2.0F + (float)(i * 200);
                    card.target_y = (float)Settings.HEIGHT / 2.0F;
                    card.targetAngle = 0.0F;
                    card.lighten(false);
                    card.drawScale = 0.12F;
                    card.targetDrawScale = 0.75F;
                    AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(card, AbstractDungeon.player.limbo));
                }
            }

            this.isDone = true;
        }

    }
}

