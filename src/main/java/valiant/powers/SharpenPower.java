package valiant.powers;

import com.badlogic.gdx.graphics.Texture;
import valiant.actions.SharpenAction;
import valiant.ValiantMod;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;


public class SharpenPower extends AbstractPower {
    public static final String POWER_ID = "Sharpen";
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    public static int damage;


    public SharpenPower(AbstractCreature owner,int numBlock, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.updateDescription();
        this.damage = numBlock;
        this.img = new Texture(ValiantMod.SHARPEN_POWER);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];

    }

    public void atEndOfTurn(boolean isPlayer) {
        AbstractDungeon.actionManager.addToBottom(new SharpenAction(damage));
    }

    static {
        DESCRIPTIONS = new String[] {
                "At the end of your turn, upgrade 1 attack card in your hand. If you can't, lose 2 HP."
        };
        NAME = "Sharpening!";
    }
}
