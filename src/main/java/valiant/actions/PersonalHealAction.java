package valiant.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.Iterator;

public class PersonalHealAction extends AbstractGameAction {

    AbstractCard source;

    public PersonalHealAction(AbstractCreature target, AbstractCreature source, AbstractCard cardSource, int amount) {
        this.setValues(target, source, amount);
        this.source = cardSource;
        this.actionType = ActionType.HEAL;
    }

    public void update() {
        if (this.duration == 0.5F) {
if (source.type == AbstractCard.CardType.SKILL) {
    Iterator var2 = target.powers.iterator();
    while (var2.hasNext()) {
        AbstractPower p = (AbstractPower) var2.next();
        if (p.name == "Spirit") {
            amount += p.amount;
        }
    }
}
            this.target.heal(this.amount);
        }

        this.tickDuration();
    }
}