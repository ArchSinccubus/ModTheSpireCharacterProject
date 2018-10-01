package valiant.Powers;

import com.megacrit.cardcrawl.monsters.exordium.GremlinNob;
import com.megacrit.cardcrawl.powers.RagePower;
import valiant.MainMod.Fudgesickle;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class TurnTablesPower extends AbstractPower {
    public static final String POWER_ID = "TurnTables";
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    public static int LifeLost;

    public TurnTablesPower(AbstractCreature owner, int life) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.description = DESCRIPTIONS[0];
        this.LifeLost = life;
        this.owner = owner;
        this.amount = -1;
        this.updateDescription();
        this.loadRegion("anger");
    }

    @Override
    public void updateDescription() {


    }

    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power.ID == "Wavering")
        {
            AbstractDungeon.actionManager.addToTop(new GainEnergyAction(1));
            AbstractDungeon.actionManager.addToTop(new LoseHPAction(this.owner, this.owner,LifeLost));

        }
    }

    static {
        DESCRIPTIONS = new String[] {
                "Whenever you apply Frail to an Enemy, Lose 3 HP and gain 1 Energy."
        };
        NAME = "Turn Tables";
    }
}